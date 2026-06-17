CREATE TABLE IF NOT EXISTS loan_applications (
    id BIGSERIAL PRIMARY KEY,
    revolving_utilization_of_unsecured_lines DOUBLE PRECISION NOT NULL,
    age INTEGER NOT NULL,
    number_of_time_30_to_59_days_past_due_not_worse INTEGER NOT NULL,
    number_of_time_60_to_89_days_past_due_not_worse INTEGER NOT NULL,
    debt_ratio DOUBLE PRECISION NOT NULL,
    number_of_open_credit_lines_and_loans INTEGER NOT NULL,
    number_of_times_90_days_late INTEGER NOT NULL,
    number_real_estate_loans_or_lines INTEGER NOT NULL,
    monthly_income DOUBLE PRECISION,
    number_of_dependents DOUBLE PRECISION,
    risk_score DOUBLE PRECISION,
    prediction INTEGER,
    decision_label VARCHAR(50),
    model_version VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT prediction_value CHECK (prediction IN (0, 1))
);