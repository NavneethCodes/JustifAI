import pandas as pd
import joblib
import json
from sklearn.linear_model import LogisticRegression
from sklearn.preprocessing import StandardScaler
from sklearn.metrics import classification_report, roc_auc_score
import os
# Ensure that the directory is home of JustifAI
print("DEBUG PRINT ONLY CWD:-", os.getcwd())

# Read the csv file for training
df = pd.read_csv("datasets/processed/training_model_ready.csv")

# Split the data into X and Y for ease in training
target = 'SeriousDlqin2yrs'
X = df.drop(columns=[target])
Y = df[target]

# Save the features as json for api validation. 
# This is crucial so your prediction_request.py is accurate
feature_cols = list(X.columns)
with open('backend/ml-service/models/feature_columns.json', 'w') as f:
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
joblib.dump(model, 'backend/ml-service/models/logistic_regression_model.joblib')
joblib.dump(scaler, 'backend/ml-service/models/preprocessing_pipeline.joblib')

with open('backend/ml-service/reports/model_metrics.json', 'w') as f:
    json.dump(report, f, indent=4)

with open('backend/ml-service/reports/auc_score.txt', 'w') as f:
    f.write(f"AUC-ROC Score: {auc:.4f}")

print("Model and Metrics saved to  models/ and reports/ folder respectively.")
