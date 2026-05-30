# Missing Value Analysis

## Files Reviewed

- Training rows: 150,000
- Testing rows: 101,503

## Key Missing Columns

| Column | Training Missing | Training % | Testing Missing | Testing % |
|---|---:|---:|---:|---:|
| MonthlyIncome | 29,731 | 19.82% | 20,103 | 19.81% |
| NumberOfDependents | 3,924 | 2.62% | 2,626 | 2.59% |

## Decision

Use model-based imputation with missing indicators. Do not use global mean filling as the final imputation strategy.
