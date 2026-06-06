package com.justifai.coreservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PredictionResponse(
        @JsonProperty("risk_score") Double riskScore,
        @JsonProperty("prediction") Integer prediction,
        @JsonProperty("decision_label") String decisionLabel,
        @JsonProperty("model_version") String modelVersion) {

}
