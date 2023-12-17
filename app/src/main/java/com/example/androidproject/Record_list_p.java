package com.example.androidproject;

import com.google.firebase.database.PropertyName;

public class Record_list_p {
    private int Date_year;
    private int Date_month;
    private int Date_day;
    private float Date_hour;
    private String Date_week;
    private String Student_name;
    private String counseling_form;


    private Record_list_p() {}


//    ----------------------------getter------------------------------

    public int getDate_year() {
        return Date_year;
    }

    public void setDate_year(int date_year) {
        Date_year = date_year;
    }

    public int getDate_month() {
        return Date_month;
    }

    public void setDate_month(int date_month) {
        Date_month = date_month;
    }

    public int getDate_day() {
        return Date_day;
    }

    public void setDate_day(int date_day) {
        Date_day = date_day;
    }

    public float getDate_hour() {
        return Date_hour;
    }

    public void setDate_hour(float date_hour) {
        Date_hour = date_hour;
    }

    public String getDate_week() {
        return Date_week;
    }

    public void setDate_week(String date_week) {
        Date_week = date_week;
    }

    public String getStudent_name() {
        return Student_name;
    }

    public void setStudent_name(String student_name) {
        Student_name = student_name;
    }


    public String getCounseling_form() {
        return counseling_form;
    }

    public void setCounseling_form(String counseling_form) {
        this.counseling_form = counseling_form;
    }



//    생성자---------------------------------------------------------------

    public Record_list_p(int Date_year, int Date_month, int Date_day, float Date_hour, String Date_week, String Student_name, String counseling_form) {
        this.Date_year = Date_year;
        this.Date_month = Date_month;
        this.Date_day = Date_day;
        this.Date_hour = Date_hour;
        this.Date_week=Date_week;
        this.Student_name = Student_name;
        this.counseling_form = counseling_form;
    }
}