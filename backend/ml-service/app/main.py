from fastapi import FastAPI
from app.api.routes import health

app_version = "v1"

app = FastAPI(title = "JustifAI ML Service")

app.include_router(health.router, prefix=f"/api/{app_version}")

@app.get("/")
def read_root():
    return {"message": "JustifAI ML Service is Online."}