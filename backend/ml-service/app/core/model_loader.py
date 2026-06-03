import joblib
import json
from pathlib import Path

model = None
scaler = None
feature_columns = None

# Get the absolute path to the backend/ml-service directory
# __file__ is model_loader.py -> parent is core -> parent is app -> parent is ml-service
BASE_DIR = Path(__file__).resolve().parent.parent.parent

def model_loader():
    global model, scaler, feature_columns
    
    # Safely join the absolute base path with the models folder
    model_path = BASE_DIR / "models" / "logistic_regression_model.joblib"
    scaler_path = BASE_DIR / "models" / "preprocessing_pipeline.joblib"
    json_path = BASE_DIR / "models" / "feature_columns.json"

    model = joblib.load(model_path)
    scaler = joblib.load(scaler_path)
    
    with open(json_path, "r") as f:
        feature_columns = json.load(f)