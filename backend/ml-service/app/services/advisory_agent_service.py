import os
import requests
from typing import TypedDict, List, Optional
from pydantic import BaseModel, Field
from langchain_google_genai import ChatGoogleGenerativeAI
from langgraph.graph import StateGraph, START, END
from dotenv import load_dotenv

# Load environment variables
load_dotenv()

# ==========================================
# 1. DATA CONTRACTS (Pydantic)
# ==========================================
class RuleProposal(BaseModel):
    """Schema for a single rule proposal."""
    rule_name: str = Field(
        description="The exact system name of the rule, e.g., 'MAX_DEBT_RATIO', 'MAX_UTILIZATION', 'MIN_AGE'"
    )
    suggested_value: float = Field(
        description="The numerical threshold proposed based on current economic conditions."
    )
    agent_reasoning: str = Field(
        description="A 2-3 sentence legal and economic justification for why this value is proposed."
    )

class RuleProposalList(BaseModel):
    """Wrapper schema to force Gemini to output an array of multiple proposals at once."""
    proposals: List[RuleProposal] = Field(
        description="A list of rule proposals evaluating all requested credit features."
    )

# ==========================================
# 2. LANGGRAPH STATE
# ==========================================
class AgentState(TypedDict):
    """The shared memory passed between nodes in the graph."""
    query: str
    result: RuleProposalList

# ==========================================
# 3. GRAPH NODES
# ==========================================
def evaluate_regulations(state: AgentState):
    """Node: Acts as a compliance researcher to evaluate multiple rules."""
    
    # Initialize the free Gemini 1.5 Flash model
    llm = ChatGoogleGenerativeAI(
        model="gemini-2.5-flash", 
        temperature=0.2 
    )
    
    # Bind the wrapper schema to force an array of JSON outputs
    structured_llm = llm.with_structured_output(RuleProposalList)
    
    print("Agent is researching regulatory conditions for all credit features...", flush=True)
    output = structured_llm.invoke(state["query"])
    
    # Update the state with the generated list of proposals
    return {"result": output}

# ==========================================
# 4. GRAPH COMPILATION
# ==========================================
workflow = StateGraph(AgentState)
workflow.add_node("researcher", evaluate_regulations)
workflow.add_edge(START, "researcher")
workflow.add_edge("researcher", END)

advisory_app = workflow.compile()

# ==========================================
# 5. EXECUTION & WEBHOOK TO JAVA
# ==========================================
def run_and_submit_proposals(query_text: str):
    print("\n--- [AGENT START] Triggering LangGraph Agent ---", flush=True)
    
    # CRITICAL FIX: host.docker.internal allows the Docker container to reach your host IDE
    java_webhook_url = "http://host.docker.internal:8080/api/v1/admin/rules/propose"
    
    try:
        # Run the LangGraph application
        initial_state = {"query": query_text}
        final_state = advisory_app.invoke(initial_state)
        proposals_list = final_state["result"].proposals
        
        print(f"\nSuccessfully generated {len(proposals_list)} rule proposals. Sending to Java backend...\n", flush=True)
        
        # Loop through each generated rule and send it to the Java Webhook
        for proposal in proposals_list:
            payload = proposal.model_dump()
            print(f"Submitting: {payload['rule_name']}...", flush=True)
            requests.post(java_webhook_url, json=payload)
            
        print("\n--- [AGENT COMPLETE] All proposals submitted successfully ---\n", flush=True)
            
    except Exception as e:
        print(f"\n[CRITICAL ERROR] Agent crashed! Notifying Java Core Service... Error: {e}\n", flush=True)
        
        error_payload = {
            "rule_name": "SYSTEM_ERROR_AGENT_FAILED",
            "suggested_value": 0.0,
            "agent_reasoning": f"The AI agent encountered a critical error and failed to generate market rules. Details: {str(e)}"
        }
        
        try:
            requests.post(java_webhook_url, json=error_payload)
            print("Successfully sent error payload to Java.", flush=True)
        except requests.exceptions.ConnectionError:
            print("[FATAL] Could not even reach Java to report the error. Is Spring Boot running?", flush=True)