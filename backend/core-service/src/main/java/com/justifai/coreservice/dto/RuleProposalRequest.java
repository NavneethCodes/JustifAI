package com.justifai.coreservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RuleProposalRequest(
                @JsonProperty("rule_name") String ruleName,
                @JsonProperty("suggested_value") Double suggestedValue,
                @JsonProperty("agent_reasoning") String agentReasoning,
                @JsonProperty("rule_status") String ruleStatus) {
}
