package com.justifai.coreservice.service.impl;

import org.springframework.stereotype.Service;

import com.justifai.coreservice.dto.RuleProposalRequest;
import com.justifai.coreservice.repository.RuleProposalRepository;
import com.justifai.coreservice.service.RuleProposalService;

@Service
public class RuleProposalServiceImpl implements RuleProposalService {

    private final RuleProposalRepository repository;

    public RuleProposalServiceImpl(RuleProposalRepository repository) {
        this.repository = repository;
    }

    @Override
    public void processAndSaveProposal(RuleProposalRequest request) {
        repository.insertProposal(request.ruleName(), request.suggestedValue(), request.agentReasoning());
    }
}
