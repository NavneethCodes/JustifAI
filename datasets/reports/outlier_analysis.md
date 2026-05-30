# Outlier Analysis

## Review Flags

| Check | Count |
|---|---:|
| age <= 0 | 1 |
| Revolving utilization > 1 | 3,321 |
| Debt ratio > 1 | 35,137 |
| Monthly income = 0 | 1,634 |
| Monthly income missing | 29,731 |

## Decision

Keep the first pipeline conservative. Do not delete or cap aggressively yet. Preserve suspicious values and create derived flags during feature engineering.
