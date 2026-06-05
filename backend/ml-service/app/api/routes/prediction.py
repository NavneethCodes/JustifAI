from fastapi import APIRouter

from app.schemas.prediction_response import PredictionResponse
from app.schemas.prediction_request import PredictionRequest
from app.services.prediction_service import predict_risk

router = APIRouter()

@router.post("/predict", response_model=PredictionResponse)
async def predict(request: PredictionRequest):
    risk_score = predict_risk(request)
    threshold = 0.5 # Hard coded for now
    prediction = int(risk_score >= threshold)
    decision_label = "HIGH_RISK" if prediction == 1 else "LOW_RISK"

    return PredictionResponse(
        risk_score=risk_score,
        prediction=prediction,
        decision_label=decision_label,
        model_version="logistic_regression_v1",
    )