package com.justifai.coreservice.service;

import org.jspecify.annotations.NonNull;

import com.justifai.coreservice.dto.RuleProposalRequest;

public interface RuleProposalService {
    void processAndSaveProposal(@NonNull RuleProposalRequest request);
}
