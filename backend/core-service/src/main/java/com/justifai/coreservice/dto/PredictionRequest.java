package com.justifai.coreservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PredictionRequest(
                @JsonProperty("RevolvingUtilizationOfUnsecuredLines") Double RevolvingUtilizationOfUnsecuredLines,
                @JsonProperty("age") Integer age,
                @JsonProperty("NumberOfTime30-59DaysPastDueNotWorse") Integer NumberOfTime30_59DaysPastDueNotWorse,
                @JsonProperty("NumberOfTime60-89DaysPastDueNotWorse") Integer NumberOfTime60_89DaysPastDueNotWorse,
                @JsonProperty("DebtRatio") Double DebtRatio,
                @JsonProperty("NumberOfOpenCreditLinesAndLoans") Integer NumberOfOpenCreditLinesAndLoans,
                @JsonProperty("NumberOfTimes90DaysLate") Integer NumberOfTimes90DaysLate,
                @JsonProperty("NumberRealEstateLoansOrLines") Integer NumberRealEstateLoansOrLines,
                @JsonProperty("MonthlyIncome") Double MonthlyIncome,
                @JsonProperty("NumberOfDependents") Double NumberOfDependents) {
}
