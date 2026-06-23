package com.justifai.coreservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.justifai.coreservice.dto.RuleProposalRequest;
import com.justifai.coreservice.service.RuleProposalService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("${justifai.api.base-path}/rule")
public class RuleProposalController {
    private final RuleProposalService service;

    public RuleProposalController(RuleProposalService service) {
        this.service = service;
    }

    @PostMapping("/propose")
    public ResponseEntity<String> receiveProposal(@RequestBody RuleProposalRequest request) {
        System.out.println("Received request proposal for:- " + request.ruleName());
        service.processAndSaveProposal(request);
        return ResponseEntity.ok("Proposal processed and successfully saved to database.");
    }

}
