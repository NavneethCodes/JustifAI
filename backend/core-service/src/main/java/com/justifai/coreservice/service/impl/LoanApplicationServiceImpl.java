package com.justifai.coreservice.service.impl;

import java.util.Objects;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.justifai.coreservice.domain.LoanApplication;
import com.justifai.coreservice.dto.PredictionRequest;
import com.justifai.coreservice.dto.PredictionResponse;
import com.justifai.coreservice.repository.LoanApplicationRepository;
import com.justifai.coreservice.service.LoanApplicationService;
import com.justifai.coreservice.service.MLServiceClient;

@Service
public class LoanApplicationServiceImpl implements LoanApplicationService {

    private final LoanApplicationRepository loanApplicationRepository;
    private final MLServiceClient mlServiceClient;

    public LoanApplicationServiceImpl(
            LoanApplicationRepository loanApplicationRepository,
            MLServiceClient mlServiceClient) {
        this.loanApplicationRepository = loanApplicationRepository;
        this.mlServiceClient = mlServiceClient;
    }

    @Override
    public LoanApplication receiveRequestAndConvert(PredictionRequest request) {
        LoanApplication application = new LoanApplication();

        application.setRevolvingUtilizationOfUnsecuredLines(request.RevolvingUtilizationOfUnsecuredLines());

        application.setAge(request.age());

        application.setNumberOfTime30To59DaysPastDueNotWorse(request.NumberOfTime30_59DaysPastDueNotWorse());

        application.setDebtRatio(request.DebtRatio());

        application.setNumberOfOpenCreditLinesAndLoans(request.NumberOfOpenCreditLinesAndLoans());

        application.setNumberOfTimes90DaysLate(request.NumberOfTimes90DaysLate());

        application.setNumberRealEstateLoansOrLines(request.NumberRealEstateLoansOrLines());

        application.setNumberOfTime60To89DaysPastDueNotWorse(request.NumberOfTime60_89DaysPastDueNotWorse());

        application.setMonthlyIncome(request.MonthlyIncome());

        application.setNumberOfDependents(request.NumberOfDependents());

        return application;
    }

    @Override
    @NonNull
    public LoanApplication saveLoanApplication(@NonNull LoanApplication application) {
        return Objects.requireNonNull(loanApplicationRepository.save(application),
                "Database failed to return the saved LoanApplication entity");
    }

    @Override
    public PredictionResponse callMLService(PredictionRequest request) {
        return mlServiceClient.getRiskPrediction(Objects.requireNonNull(request));
    }

    @Override
    public LoanApplication attachPredictionResult(LoanApplication application, PredictionResponse response) {

        application.setRiskScore(response.riskScore());

        application.setPrediction(response.prediction());

        application.setDecisionLabel(response.decisionLabel());

        application.setModelVersion(response.modelVersion());

        return application;
    }

    @Override
    public PredictionResponse processLoanApplication(PredictionRequest request) {

        LoanApplication rawApplication = receiveRequestAndConvert(request);

        LoanApplication saveApplication = saveLoanApplication(
                Objects.requireNonNull(rawApplication, "Cannot proceed if the application produces Non Null"));

        PredictionResponse predictionResponse = callMLService(request);

        LoanApplication applicationWithPrediction = attachPredictionResult(saveApplication, predictionResponse);

        saveLoanApplication(Objects.requireNonNull(applicationWithPrediction,
                "Cannot proceed if the application produces Non Null"));

        return predictionResponse;
    }

}
