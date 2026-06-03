import pandas as pd
import numpy as np

from app.core import model_loader
from app.schemas.prediction_request import PredictionRequest

def build_features(request: PredictionRequest) -> pd.DataFrame:
    raw_dict = request.model_dump(by_alias=True)
    df = pd.DataFrame([raw_dict])

    df["MonthlyIncome_NA"] = df["MonthlyIncome"].isna().astype(int)
    df["NumberOfDependents_NA"] = df["NumberOfDependents"].isna().astype(int)

    df["MonthlyIncome"] = df["MonthlyIncome"].fillna(0)
    df["NumberOfDependents"] = df["NumberOfDependents"].fillna(0)

    df["TotalPastDueEvents"] = (
        df["NumberOfTime30-59DaysPastDueNotWorse"]
        + df["NumberOfTime60-89DaysPastDueNotWorse"]
        + df["NumberOfTimes90DaysLate"]
    )

    df["HasDependents"] = (df["NumberOfDependents"] > 0).astype(int)
    df["HasRealEstateLoan"] = (df["NumberRealEstateLoansOrLines"] > 0).astype(int)
    df["HighRevolvingUtilization"] = (
        df["RevolvingUtilizationOfUnsecuredLines"] > 1.0
    ).astype(int)
    df["DebtRatioAboveOne"] = (df["DebtRatio"] > 1.0).astype(int)

    df["IncomePerDependent"] = df["MonthlyIncome"] / (
        df["NumberOfDependents"] + 1
    )
    df["ApproxMonthlyDebt"] = df["MonthlyIncome"] * df["DebtRatio"]
    df["LogMonthlyIncome"] = np.log1p(df["MonthlyIncome"])

    return df

def predict_risk(request: PredictionRequest) -> float:
    full_df = build_features(request)

    full_df = full_df[model_loader.feature_columns]

    scaled_data = model_loader.scaler.transform(full_df)

    probability = model_loader.model.predict_proba(scaled_data)[0][1]

    return float(probability)