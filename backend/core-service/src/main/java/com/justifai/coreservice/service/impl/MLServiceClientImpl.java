package com.justifai.coreservice.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.justifai.coreservice.dto.PredictionRequest;
import com.justifai.coreservice.dto.PredictionResponse;
import com.justifai.coreservice.service.MLServiceClient;

import io.micrometer.common.lang.NonNull;

@Service
public class MLServiceClientImpl implements MLServiceClient {

    private final RestClient restClient;

    public MLServiceClientImpl(@Value("${justifai.ml-service.url}") String mlServiceBaseUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(Objects.requireNonNull(mlServiceBaseUrl, "mlServiceBaseUrl must not be null"))
                .build();
    }

    @Override
    public PredictionResponse getRiskPrediction(@NonNull PredictionRequest request) {
        return this.restClient.post()
                .uri("/predict")
                .contentType(Objects.requireNonNull(MediaType.APPLICATION_JSON, "media type cannot be null"))
                .body(Objects.requireNonNull(request, "request must not be null"))
                .retrieve()
                .body(PredictionResponse.class);
    }
}
