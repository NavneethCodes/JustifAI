from pydantic import BaseModel, Field, ConfigDict
from typing import Optional

class PredictionRequest(BaseModel):
    # Enable alias support for your hyphenated column names
    model_config = ConfigDict(populate_by_name=True)

    # Required fields (the '...' remains implicitly required for these)
    RevolvingUtilizationOfUnsecuredLines: float
    age: int
    NumberOfTime30_59DaysPastDueNotWorse: int = Field(alias="NumberOfTime30-59DaysPastDueNotWorse")
    NumberOfTime60_89DaysPastDueNotWorse: int = Field(alias="NumberOfTime60-89DaysPastDueNotWorse")
    DebtRatio: float
    NumberOfOpenCreditLinesAndLoans: int
    NumberOfTimes90DaysLate: int
    NumberRealEstateLoansOrLines: int
    
    # Optional fields (Now flexible!)
    MonthlyIncome: Optional[float] = None
    NumberOfDependents: Optional[float] = None