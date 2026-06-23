package com.justifai.coreservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("rule_proposals")
public class RuleProposal {

    @Id
    private Long id;

    @Column("rule_name")
    private String ruleName;

    @Column("suggested_value")
    private Double suggestedValue;

    @Column("agent_reasoning")
    private String agentReasoning;

    @Column("rule_status")
    private String ruleStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Double getSuggestedValue() {
        return suggestedValue;
    }

    public void setSuggestedValue(Double suggestedValue) {
        this.suggestedValue = suggestedValue;
    }

    public String getAgentReasoning() {
        return agentReasoning;
    }

    public void setAgentReasoning(String agentReasoning) {
        this.agentReasoning = agentReasoning;
    }

    public String getRuleStatus() {
        return ruleStatus;
    }

    public void setRuleStatus(String ruleStatus) {
        this.ruleStatus = ruleStatus;
    }

}
