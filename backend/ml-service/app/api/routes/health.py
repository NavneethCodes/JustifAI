from fastapi import APIRouter

router = APIRouter()

@router.get("/health")
async def health_check():
    # heartbeat endpoint for monitoring
    return {"status": "healthy", "service": "JustifAI-ML_Core"}
