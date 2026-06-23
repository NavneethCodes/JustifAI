package com.justifai.coreservice.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.justifai.coreservice.domain.RuleProposal;

public interface RuleProposalRepository extends CrudRepository<RuleProposal, Long> {

    @Modifying
    @Query("""
              INSERT INTO rule_proposals (rule_name, suggested_value, agent_reasoning)
              VALUES (:ruleName, :suggestedValue, :agentReasoning)
            """)
    void insertProposal(
            @Param("ruleName") String ruleName,
            @Param("suggestedValue") Double suggestedValue,
            @Param("agentReasoning") String agentReasoning);
}