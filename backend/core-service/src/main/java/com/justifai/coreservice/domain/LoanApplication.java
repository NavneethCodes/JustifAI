package com.justifai.coreservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("loan_applications")
public class LoanApplication {

    @Id
    private Long id;

    @Column("revolving_utilization_of_unsecured_lines")
    private Double revolvingUtilizationOfUnsecuredLines;

    @Column("age")
    private Integer age;

    @Column("number_of_time_30_to_59_days_past_due_not_worse")
    private Integer numberOfTime30To59DaysPastDueNotWorse;

    @Column("debt_ratio")
    private Double debtRatio;

    @Column("number_of_open_credit_lines_and_loans")
    private Integer numberOfOpenCreditLinesAndLoans;

    @Column("number_of_times_90_days_late")
    private Integer numberOfTimes90DaysLate;

    @Column("number_real_estate_loans_or_lines")
    private Integer numberRealEstateLoansOrLines;

    @Column("number_of_time_60_to_89_days_past_due_not_worse")
    private Integer numberOfTime60To89DaysPastDueNotWorse;

    @Column("monthly_income")
    private Double monthlyIncome;

    @Column("number_of_dependents")
    private Double numberOfDependents;

    @Column("risk_score")
    private Double riskScore;

    @Column("prediction")
    private Integer prediction;

    @Column("decision_label")
    private String decisionLabel;

    @Column("model_version")
    private String modelVersion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRevolvingUtilizationOfUnsecuredLines() {
        return revolvingUtilizationOfUnsecuredLines;
    }

    public void setRevolvingUtilizationOfUnsecuredLines(Double revolvingUtilizationOfUnsecuredLines) {
        this.revolvingUtilizationOfUnsecuredLines = revolvingUtilizationOfUnsecuredLines;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getNumberOfTime30To59DaysPastDueNotWorse() {
        return numberOfTime30To59DaysPastDueNotWorse;
    }

    public void setNumberOfTime30To59DaysPastDueNotWorse(Integer numberOfTime30To59DaysPastDueNotWorse) {
        this.numberOfTime30To59DaysPastDueNotWorse = numberOfTime30To59DaysPastDueNotWorse;
    }

    public Double getDebtRatio() {
        return debtRatio;
    }

    public void setDebtRatio(Double debtRatio) {
        this.debtRatio = debtRatio;
    }

    public Integer getNumberOfOpenCreditLinesAndLoans() {
        return numberOfOpenCreditLinesAndLoans;
    }

    public void setNumberOfOpenCreditLinesAndLoans(Integer numberOfOpenCreditLinesAndLoans) {
        this.numberOfOpenCreditLinesAndLoans = numberOfOpenCreditLinesAndLoans;
    }

    public Integer getNumberOfTimes90DaysLate() {
        return numberOfTimes90DaysLate;
    }

    public void setNumberOfTimes90DaysLate(Integer numberOfTimes90DaysLate) {
        this.numberOfTimes90DaysLate = numberOfTimes90DaysLate;
    }

    public Integer getNumberRealEstateLoansOrLines() {
        return numberRealEstateLoansOrLines;
    }

    public void setNumberRealEstateLoansOrLines(Integer numberRealEstateLoansOrLines) {
        this.numberRealEstateLoansOrLines = numberRealEstateLoansOrLines;
    }

    public Integer getNumberOfTime60To89DaysPastDueNotWorse() {
        return numberOfTime60To89DaysPastDueNotWorse;
    }

    public void setNumberOfTime60To89DaysPastDueNotWorse(Integer numberOfTime60To89DaysPastDueNotWorse) {
        this.numberOfTime60To89DaysPastDueNotWorse = numberOfTime60To89DaysPastDueNotWorse;
    }

    public Double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Double getNumberOfDependents() {
        return numberOfDependents;
    }

    public void setNumberOfDependents(Double numberOfDependents) {
        this.numberOfDependents = numberOfDependents;
    }

    public Double getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(Double riskScore) {
        this.riskScore = riskScore;
    }

    public Integer getPrediction() {
        return prediction;
    }

    public void setPrediction(Integer prediction) {
        this.prediction = prediction;
    }

    public String getDecisionLabel() {
        return decisionLabel;
    }

    public void setDecisionLabel(String decisionLabel) {
        this.decisionLabel = decisionLabel;
    }

    public String getModelVersion() {
        return modelVersion;
    }

    public void setModelVersion(String modelVersion) {
        this.modelVersion = modelVersion;
    }

}
