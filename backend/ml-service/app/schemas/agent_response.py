from pydantic import BaseModel, Field

class AgentResponse(BaseModel):
    status : str = Field(
        ...,
        description="The status of the backfround task",
        examples=["ACCEPTED"]
    )
    message: str = Field(
        ...,
        description="Details about the execution",
        example="Advisory Agent has been triggered in the background. It will POST proposals to the Core Service upon completion."
    )