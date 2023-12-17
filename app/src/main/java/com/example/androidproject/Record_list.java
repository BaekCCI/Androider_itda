package com.example.androidproject;

public class Record_list {
    private int Date_year;
    private int Date_month;
    private int Date_day;
    private float Date_hour;
    private String Date_week;
    private String Professor_name;
    private String counseling_form;
    private String review;

    private Record_list() {}


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

    public String getProfessor_name() {
        return Professor_name;
    }

    public void setProfessor_name(String professor_name) {
        Professor_name = professor_name;
    }


    public String getCounseling_form() {
        return counseling_form;
    }

    public void setCounseling_form(String counseling_form) {
        this.counseling_form = counseling_form;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

//    생성자---------------------------------------------------------------

    public Record_list(int Date_year, int Date_month, int Date_day, float Date_hour, String Date_week, String Professor_name, String counseling_form, String review) {
        this.Date_year = Date_year;
        this.Date_month = Date_month;
        this.Date_day = Date_day;
        this.Date_hour = Date_hour;
        this.Date_week=Date_week;
        this.Professor_name = Professor_name;
        this.counseling_form = counseling_form;
        this.review=review;
    }
}