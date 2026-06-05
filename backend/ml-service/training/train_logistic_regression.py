import pandas as pd
import joblib
import json
from sklearn.linear_model import LogisticRegression
from sklearn.preprocessing import StandardScaler
from sklearn.metrics import classification_report, roc_auc_score
from pathlib import Path


ML_SERVICE_DIR = Path(__file__).resolve().parents[1]
PROJECT_ROOT = ML_SERVICE_DIR.parents[1]

DATASET_CANDIDATES = [
    ML_SERVICE_DIR / "datasets" / "processed" / "training_model_ready.csv",
    PROJECT_ROOT / "datasets" / "processed" / "training_model_ready.csv",
    Path("datasets") / "processed" / "training_model_ready.csv",
]

TRAINING_DATA_PATH = next((path for path in DATASET_CANDIDATES if path.exists()), None)

if TRAINING_DATA_PATH is None:
    searched_paths = "\n".join(str(path) for path in DATASET_CANDIDATES)
    raise FileNotFoundError(
        "Could not find training_model_ready.csv. Searched:\n"
        f"{searched_paths}"
    )

MODELS_DIR = ML_SERVICE_DIR / "models"
REPORTS_DIR = ML_SERVICE_DIR / "reports"

MODELS_DIR.mkdir(parents=True, exist_ok=True)
REPORTS_DIR.mkdir(parents=True, exist_ok=True)

# Read the csv file for training
df = pd.read_csv(TRAINING_DATA_PATH)

# Split the data into X and Y for ease in training
target = 'SeriousDlqin2yrs'
X = df.drop(columns=[target])
Y = df[target]

# Save the features as json for api validation. 
# This is crucial so your prediction_request.py is accurate
feature_cols = list(X.columns)
with open(MODELS_DIR / "feature_columns.json", "w", encoding="utf-8") as f:
    json.dump(feature_cols, f)

# Scaleing the data X
scaler = StandardScaler()
X_scaled = scaler.fit_transform(X)

model = LogisticRegression(class_weight='balanced', max_iter=8000)
model.fit(X_scaled, Y)

Y_pred = model.predict(X_scaled)
Y_prob = model.predict_proba(X_scaled)[:, 1]

report = classification_report(Y, Y_pred, output_dict=True)
# print(report)

auc = roc_auc_score(Y, Y_prob)
# print(f"AUC-ROC Score: {auc:.4f}")
joblib.dump(model, MODELS_DIR / "logistic_regression_model.joblib")
joblib.dump(scaler, MODELS_DIR / "preprocessing_pipeline.joblib")

with open(REPORTS_DIR / "model_metrics.json", "w", encoding="utf-8") as f:
    json.dump(report, f, indent=4)

with open(REPORTS_DIR / "auc_score.txt", "w", encoding="utf-8") as f:
    f.write(f"AUC-ROC Score: {auc:.4f}")

print("Model and Metrics saved to  models/ and reports/ folder respectively.")
