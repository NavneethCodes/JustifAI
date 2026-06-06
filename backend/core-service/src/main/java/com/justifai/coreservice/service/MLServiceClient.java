package com.justifai.coreservice.service;

import com.justifai.coreservice.dto.PredictionRequest;
import com.justifai.coreservice.dto.PredictionResponse;

import io.micrometer.common.lang.NonNull;

public interface MLServiceClient {
    PredictionResponse getRiskPrediction(@NonNull PredictionRequest request);
}
