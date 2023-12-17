package com.example.androidproject;


import java.util.List;

public class MonthlyData2 {
    private String month2;
    private List<String> dayData2;

    public MonthlyData2() {
        // Default constructor required for Firebase
    }

    public MonthlyData2(String month2, List<String> dayData2) {
        this.month2 = month2;
        this.dayData2 = dayData2;
    }

    public String getMonth2() {
        return month2;
    }

    public List<String> getDayData2() {
        return dayData2;
    }
}