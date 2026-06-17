package com.justifai.coreservice.service.impl;

import java.net.http.HttpClient;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.justifai.coreservice.dto.PredictionRequest;
import com.justifai.coreservice.dto.PredictionResponse;
import com.justifai.coreservice.service.MLServiceClient;

@Service
public class MLServiceClientImpl implements MLServiceClient {

    private final RestClient restClient;

    public MLServiceClientImpl(
            @Value("${justifai.ml-service.url}") String mlServiceBaseUrl) {

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        this.restClient = RestClient.builder()
                .requestFactory(new JdkClientHttpRequestFactory(Objects.requireNonNull(httpClient)))
                .baseUrl(Objects.requireNonNull(
                        mlServiceBaseUrl,
                        "mlServiceBaseUrl must not be null"))
                .build();
    }

    @Override
    public PredictionResponse getRiskPrediction(
            @NonNull PredictionRequest request) {

        try {

            ObjectMapper mapper = new ObjectMapper();

            String json = mapper.writeValueAsString(request);

            System.out.println(
                    "DEBUG STATEMENT:- JSON BEING SENT:- " + json);

            return this.restClient.post()
                    .uri("/predict")
                    .contentType(Objects.requireNonNull(MediaType.APPLICATION_JSON))
                    .body(Objects.requireNonNull(json))
                    .retrieve()
                    .body(PredictionResponse.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
