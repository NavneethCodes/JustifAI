from pydantic import BaseModel, Field
class AgentRequest(BaseModel):
    query : str = Field(
        ...,
        description="The macroeconomic evaluation prompt to feed to the Gemini agent."
    )