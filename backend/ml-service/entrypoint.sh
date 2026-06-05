#!/bin/sh
set -e

MODEL_FILE="/app/models/logistic_regression_model.joblib"
SCALER_FILE="/app/models/preprocessing_pipeline.joblib"
FEATURE_COLUMNS_FILE="/app/models/feature_columns.json"

if [ ! -f "$MODEL_FILE" ] || [ ! -f "$SCALER_FILE" ] || [ ! -f "$FEATURE_COLUMNS_FILE" ]; then
  echo "ML artifacts missing. Training Logistic Regression model once..."
  python training/train_logistic_regression.py
else
  echo "ML artifacts found. Skipping training."
fi

exec uvicorn app.main:app --host 0.0.0.0 --port 8000
