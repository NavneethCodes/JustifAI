import joblib
import json

# Global variables to hold the loaded objects in memory
model = None
scaler = None
feature_columns = None

def model_loader():
    global model, scalar, feature_columns

    # Load the machine learning artifacts
    model = joblib.load("backend/ml-service/models/logistic_regression_model.joblib")
    scaler = joblib.load("backend/ml-service/models/preprocessing_pipeline.joblib")

    with open("backend/ml-service/models/feature_columns.json", "r") as f:
        feature_columns = json.load(f)
