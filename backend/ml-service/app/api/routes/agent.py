from fastapi import APIRouter, BackgroundTasks, HTTPException
from app.schemas.agent_request import AgentRequest
from app.schemas.agent_response import AgentResponse
from app.services.advisory_agent_service import run_and_submit_proposals

router = APIRouter()

@router.post("/trigger", status_code=202, response_model=AgentResponse)
async def trigger_advisory_agent(
    request: AgentRequest, 
    background_tasks: BackgroundTasks
):
    try:
        background_tasks.add_task(run_and_submit_proposals, request.query)

        return AgentResponse(
            status="ACCEPTED",
            message="Advisory Agent has been triggered in the background. It will POST proposals to the Core Service upon completion."
        )
    except Exception as e:
        print(f"Error triggering agent: {e}")
        raise HTTPException(
            status_code=500,
            detail="FAILED: The server encountered an error and could not start the background agent."
        )