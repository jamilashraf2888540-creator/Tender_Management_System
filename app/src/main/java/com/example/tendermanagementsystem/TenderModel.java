package com.example.tendermanagementsystem;

public class TenderModel {

    private int id;
    private String title;
    private String description;
    private String budget;
    private String openingDate;
    private String closingDate;
    private String requirements;
    private String department;

    // المُنشئ (Constructor) الفارغ
    public TenderModel() {
    }

    // المُنشئ الكامل مع الـ ID
    public TenderModel(int id, String title, String description, String budget, String openingDate, String closingDate, String requirements, String department) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.budget = budget;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.requirements = requirements;
        this.department = department;
    }

    // المُنشئ بدون الـ ID (عند الإضافة الجديدة قبل توليد الـ ID تلقائياً)
    public TenderModel(String title, String description, String budget, String openingDate, String closingDate, String requirements, String department) {
        this.title = title;
        this.description = description;
        this.budget = budget;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.requirements = requirements;
        this.department = department;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getBudget() { return budget; }
    public void setBudget(String budget) { this.budget = budget; }

    public String getOpeningDate() { return openingDate; }
    public void setOpeningDate(String openingDate) { this.openingDate = openingDate; }

    public String getClosingDate() { return closingDate; }
    public void setClosingDate(String closingDate) { this.closingDate = closingDate; }

    public String getRequirements() { return requirements; }
    public void setRequirements(String requirements) { this.requirements = requirements; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    }

