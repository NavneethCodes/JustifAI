from fastapi import FastAPI
from contextlib import asynccontextmanager
from app.api.routes import health, prediction, agent
from app.core.model_loader import model_loader
from app.core.scheduler import start_scheduler

app_version = "v1"

@asynccontextmanager
async def lifespan(app: FastAPI):
    print("Loading machine learning artifacts into memory...")
    model_loader()
    print("Artifacts loaded successfully!")
    scheduler = start_scheduler()
    yield
    print("Shutting down... cleaning up memory and stopping scheduler.")
    scheduler.shutdown()

app = FastAPI(title = "JustifAI ML Service", lifespan=lifespan)

app.include_router(health.router, prefix=f"/api/{app_version}", tags=["Health"])
app.include_router(prediction.router, prefix=f"/api/{app_version}", tags=["Prediction"])
app.include_router(agent.router, prefix=f"/api/{app_version}", tags=["Advisory Agent"])

@app.get("/")
def read_root():
    return {"message": "JustifAI ML Service is Online."}