package com.justifai.coreservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.justifai.coreservice.dto.PredictionRequest;
import com.justifai.coreservice.dto.PredictionResponse;
import com.justifai.coreservice.service.LoanApplicationService;

@RestController
@RequestMapping("${justifai.api.base-path}/loan-application")
@CrossOrigin("${justifai.cors.allowed-origins}")
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;

    public LoanApplicationController(LoanApplicationService loanApplicationService) {
        this.loanApplicationService = loanApplicationService;
    }

    @PostMapping
    public ResponseEntity<PredictionResponse> SumbitApplication(@RequestBody PredictionRequest request) {
        PredictionResponse response = loanApplicationService.processLoanApplication(request);
        return ResponseEntity.ok(response);
    }
}
