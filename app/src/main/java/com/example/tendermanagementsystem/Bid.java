package com.example.tendermanagementsystem;

// نموذج بيانات العرض (بدون واجهة)
public class Bid {

    private String companyName;
    private double amount;          // قيمة العرض
    private int durationDays;       // مدة التنفيذ بالأيام
    private int qualityScore;       // الجودة من 0 إلى 100
    private int experienceScore;    // الخبرة من 0 إلى 100
    private String notes;
    private double finalScore;      // بيحسبها BidEvaluator

    public Bid(String companyName, double amount, int durationDays,
               int qualityScore, int experienceScore, String notes) {
        this.companyName = companyName;
        this.amount = amount;
        this.durationDays = durationDays;
        this.qualityScore = qualityScore;
        this.experienceScore = experienceScore;
        this.notes = notes;
    }

    public String getCompanyName() { return companyName; }
    public double getAmount() { return amount; }
    public int getDurationDays() { return durationDays; }
    public int getQualityScore() { return qualityScore; }
    public int getExperienceScore() { return experienceScore; }
    public String getNotes() { return notes; }
    public double getFinalScore() { return finalScore; }

    public void setFinalScore(double finalScore) { this.finalScore = finalScore; }
}
