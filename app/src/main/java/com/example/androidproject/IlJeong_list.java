package com.example.androidproject;


public class IlJeong_list {
    private int Date_day;
    private float Date_hour;
    private int Date_month;
    private String Date_week;
    private int Date_year;
    private String Professor_name;
    private String Professor_number;
    private String classification;
    private String counseling_content;
    private String counseling_form;
    private String counseling_group;
    private String state;

    private String question;

    public IlJeong_list() {
    }

    public int getDate_day() {
        return Date_day;
    }

    public void setDate_day(int Date_day) {
        this.Date_day = Date_day;
    }

    public float getDate_hour() {
        return Date_hour;
    }

    public void setDate_hour(float Date_hour) {
        this.Date_hour = Date_hour;
    }

    public int getDate_month() {
        return Date_month;
    }

    public void setDate_month(int Date_month) {
        this.Date_month = Date_month;
    }

    public int getDate_year() {
        return Date_year;
    }

    public void setDate_week(String Date_week) {
        this.Date_week = Date_week;
    }

    public String getDate_week() {
        return Date_week;
    }

    public void setDate_year(int Date_year) {
        this.Date_year = Date_year;
    }

    public String getProfessor_name() {
        return Professor_name;
    }

    public void setProfessor_name(String Professor_name) {
        this.Professor_name = Professor_name;
    }

    public String getProfessor_number() {
        return Professor_number;
    }

    public void setProfessor_number(String Professor_number) {
        this.Professor_number = Professor_number;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getCounseling_content() {
        return counseling_content;
    }

    public void setCounseling_content(String counseling_content) {
        this.counseling_content = counseling_content;
    }

    public String getCounseling_form() {
        return counseling_form;
    }

    public void setConseling_form(String counseling_form) {
        this.counseling_form = counseling_form;
    }

    public String getCounseling_group() {
        return counseling_group;
    }

    public void setCounseling_group(String counseling_group) {
        this.counseling_group = counseling_group;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getQuestion() { return question;}
    public void setQuestion(String question){ this.question = question;}

    public IlJeong_list(int Date_day, float Date_hour, int Date_month, String Date_week, int Date_year, String Professor_name, String Professor_number, String classification, String counseling_content, String counseling_form, String counseling_group, String state, String question) {
        this.Date_day = Date_day;
        this.Date_hour = Date_hour;
        this.Date_month = Date_month;
        this.Date_week = Date_week;
        this.Date_year = Date_year;
        this.Professor_name = Professor_name;
        this.Professor_number = Professor_number;
        this.classification = classification;
        this.counseling_content = counseling_content;
        this.counseling_form = counseling_form;
        this.counseling_group = counseling_group;
        this.state = state;
        this.question = question;
    }
}


