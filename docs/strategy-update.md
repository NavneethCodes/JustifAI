# JustifAI Strategy Update

This note captures the current project direction after revising the original JustifAI concept.

## 1. Dataset Strategy Changed Completely

### Original Idea

Synthetic dataset only.

The original document assumed real financial datasets would be unavailable, so the system would rely primarily on generated data.

### Current Decision

Public dataset plus limited synthetic augmentation.

Final dataset:

- Give Me Some Credit Dataset

Reason:

- Around 150k rows
- Real-world financial behavior
- Suitable for explainable AI
- Suitable for bias detection
- Suitable for feature importance analysis

Synthetic data is now supplementary rather than the primary data source.

## 2. ML Model Strategy Simplified

### Original Thinking

Multiple possible models were considered:

- Random Forest
- XGBoost
- Neural Networks
- Ensemble Models

### Current Decision

Phase 1 uses Logistic Regression.

Reason:

- Interpretable
- Fast
- Auditable
- Regulator-friendly
- Easy feature importance extraction

This aligns better with explainability than pure predictive accuracy.

## 3. Regulatory Engine Expanded

### Original Idea

Static rule validation.

### Current Design

Hybrid decision system:

```text
ML Prediction
       +
Rule Engine
       +
Explanation Engine
```

Example:

```text
ML says APPROVE
Rule says debt ratio exceeds threshold
Final decision: REJECT
```

This became one of the strongest features of JustifAI.

## 4. Legal Justification Engine Became a Core Module

Originally it was closer to a reporting component.

Now:

```text
Feature Importance
        +
Rule Trigger
        +
Policy Reference
        ↓
Human-readable explanation
```

Example rejection explanation:

- Debt ratio exceeded policy threshold
- Multiple recent delinquencies
- Risk score exceeded acceptable range

The explanation layer is now a first-class feature.

## 5. RAG Added to the Architecture

The original document did not include a strong RAG component.

Current direction:

```text
Policy Documents
Compliance Documents
Internal Rules
Regulatory References
        ↓
RAG Layer
        ↓
Explanation Engine
```

Purpose:

```text
Decision
   ↓
Supporting policy evidence
```

## 6. Regulatory Update Mechanism Added

The original system assumed static regulations.

Current idea:

```text
Agent monitors regulations
        ↓
Fetches updates
        ↓
Human review
        ↓
Approval
        ↓
Production rule update
```

Key safeguard: AI never directly changes laws. Human approval remains mandatory.

## 7. Multi-Jurisdiction Support Introduced

Original scope: single regulatory environment.

Current scope:

- India
- United States
- European Union

Rules become country-specific rather than global.

## 8. Bias Detection Expanded

Original idea: basic fairness metrics.

Current direction:

- Bias monitoring
- Disparate impact analysis
- Decision distribution analysis
- Fairness reporting

## 9. Database Design Changed

The original document schema was conceptual.

Current design uses stronger separation of concerns:

- applications
- predictions
- explanations
- audit_logs
- rules
- regulatory_documents

## 10. Tech Stack Finalized

Frontend:

- Angular
- SCSS

Backend:

- Spring Boot

Persistence:

- PostgreSQL

ML:

- Python
- Scikit-learn

Containerization:

- Docker

Database access:

- JDBC

Notably, no JPA because JDBC is the preferred approach.

## 11. Firebase Position Changed

Original possibility: Firebase as primary database.

Current decision:

- PostgreSQL is primary
- Firebase is optional future addition

## 12. Architecture Moved Toward Microservices

Original concept: single application.

Current architecture:

```text
Frontend
    ↓
Spring Core Service
    ↓
Python ML Service
    ↓
PostgreSQL
```

Possible structure:

```text
backend/
├── core-service
└── ml-service
```

## 13. Explainability Became More Important Than Accuracy

This is the biggest philosophical change.

Original focus:

- Prediction

Current focus:

- Prediction
- Explanation
- Regulatory compliance
- Auditability
- Governance

JustifAI is now a credit-risk AI that can explain, justify, audit, and defend its decisions.

## 14. Development Strategy Changed

Original plan: build everything at once.

Current roadmap:

### Phase 1

- Dataset
- Logistic Regression
- Basic API

### Phase 2

- Feature Importance
- Explanation Engine

### Phase 3

- Rule Engine

### Phase 4

- Bias Detection

### Phase 5

- RAG Justification Layer

### Phase 6

- Regulatory Update Workflow

## Final Summary

The original JustifAI was essentially an explainable credit scoring system.

The current JustifAI has evolved into an AI governance platform for credit decisions, combining:

- Credit risk prediction
- Explainable AI
- Regulatory compliance
- Rule-based overrides
- Bias monitoring
- Audit trails
- RAG-based justification
- Human-governed regulatory updates

That evolution makes the project substantially stronger and more distinctive than a standard credit-scoring application.
