package com.justifai.coreservice.service;

import org.springframework.lang.NonNull;

import com.justifai.coreservice.domain.LoanApplication;
import com.justifai.coreservice.dto.PredictionRequest;
import com.justifai.coreservice.dto.PredictionResponse;

public interface LoanApplicationService {

    LoanApplication receiveRequestAndConvert(PredictionRequest request);

    @NonNull
    LoanApplication saveLoanApplication(@NonNull LoanApplication application);

    PredictionResponse callMLService(PredictionRequest request);

    LoanApplication attachPredictionResult(LoanApplication application, PredictionResponse response);

    PredictionResponse processLoanApplication(PredictionRequest request);
}
