from pydantic import BaseModel

class PredictionResponse(BaseModel):
    risk_score: float
    prediction: int
    decision_label: str
    model_version: str