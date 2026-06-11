package com.justifai.coreservice.service;

import org.springframework.lang.NonNull;

import com.justifai.coreservice.dto.PredictionRequest;
import com.justifai.coreservice.dto.PredictionResponse;

public interface MLServiceClient {

    PredictionResponse getRiskPrediction(@NonNull PredictionRequest request);

}
