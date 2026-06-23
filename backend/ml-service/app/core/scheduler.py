from apscheduler.schedulers.background import BackgroundScheduler
from apscheduler.triggers.cron import CronTrigger
from app.services.advisory_agent_service import run_and_submit_proposals

def run_daily_advisory_check():
    """
    The automated task executed every night at midnight.
    It passes the master evaluation query to the LangGraph agent.
    """
    print("\n[SCHEDULER] Initiating automated midnight regulatory evaluation...")
    
    master_query = """
    Evaluate the current macroeconomic climate regarding inflation and housing prices. 
    Suggest safe, updated regulatory thresholds for the following credit risk factors:
    
    - RevolvingUtilizationOfUnsecuredLines (Suggest a maximum safe limit)
    - age (Suggest a minimum required age)
    - NumberOfTime30_59DaysPastDueNotWorse (Suggest a maximum tolerance limit)
    - NumberOfTime60_89DaysPastDueNotWorse (Suggest a maximum tolerance limit)
    - DebtRatio (Suggest a maximum safe limit)
    - NumberOfOpenCreditLinesAndLoans (Suggest a maximum limit)
    - NumberOfTimes90DaysLate (Suggest a strict maximum tolerance limit)
    - NumberRealEstateLoansOrLines (Suggest a maximum limit)
    - MonthlyIncome (Suggest a minimum baseline required)
    - NumberOfDependents (Suggest a threshold where risk increases)
    """
    
    try:
        run_and_submit_proposals(master_query)
    except Exception as e:
        print(f"[SCHEDULER] Error during automated execution: {e}")

def start_scheduler():
    """
    Initializes and starts the background thread scheduler.
    """
    scheduler = BackgroundScheduler()
    
    # Schedule the job to run daily at 00:00 (Midnight)
    scheduler.add_job(
        run_daily_advisory_check,
        trigger=CronTrigger(hour=0, minute=0),
        id="daily_advisory_agent_job",
        name="Nightly Credit Risk Regulatory Check",
        replace_existing=True
    )
    
    scheduler.start()
    print("[SCHEDULER] Background scheduler started. Next run scheduled for 12:00 AM.")
    return scheduler