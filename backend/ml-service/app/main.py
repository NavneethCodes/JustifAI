from fastapi import FastAPI
from contextlib import asynccontextmanager
from app.api.routes import health, prediction
from app.core.model_loader import model_loader

app_version = "v1"

@asynccontextmanager
async def lifespan(app: FastAPI):
    print("Loading machine learning artifacts into memory...")
    model_loader()
    print("Artifacts loaded successfully!")
    yield
    print("Shutting down... cleaning up memory.")

app = FastAPI(title = "JustifAI ML Service", lifespan=lifespan)

app.include_router(health.router, prefix=f"/api/{app_version}")
app.include_router(prediction.router, prefix=f"/api/{app_version}")

@app.get("/")
def read_root():
    return {"message": "JustifAI ML Service is Online."}