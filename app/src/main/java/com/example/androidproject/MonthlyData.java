package com.example.androidproject;

import java.util.List;

public class MonthlyData {
    private String month;
    private List<String> dayData;

    public MonthlyData() {
        // Default constructor required for Firebase
    }

    public MonthlyData(String month, List<String> dayData) {
        this.month = month;
        this.dayData = dayData;
    }

    public String getMonth() {
        return month;
    }

    public List<String> getDayData() {
        return dayData;
    }
}