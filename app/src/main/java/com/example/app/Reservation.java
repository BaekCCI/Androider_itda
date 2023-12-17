package com.example.app;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Reservation implements Parcelable {
    private int Date_year;
    private int Date_day;
    private int Date_month;
    private float Date_hour;
    private String Date_week;
    private String StringMonth;
    private String StringDay;
    private String StringTime;
    private String counseling_form;
    private String question;
    private String state;

    private static final long serialVersionUID = 1L;

    public Reservation() {
        // 기본 생성자는 Firebase에서 데이터를 읽을 때 필요합니다.
    }


    protected Reservation(Parcel in) {
        Date_year = in.readInt();
        Date_month = in.readInt();
        Date_day = in.readInt();
        Date_hour = in.readFloat();
        Date_week = in.readString();
        counseling_form = in.readString();
        question = in.readString();
        StringMonth = in.readString();
        StringDay = in.readString();
        StringTime = in.readString();
        state = in.readString();

    }

    public static final Creator<Reservation> CREATOR = new Creator<Reservation>() {
        @Override
        public Reservation createFromParcel(Parcel in) {
            return new Reservation(in);
        }

        @Override
        public Reservation[] newArray(int size) {
            return new Reservation[size];
        }
    };

    public int getDate_year () {
        return Date_year;
    }

    public void setDate_year (int Date_year){
        this.Date_year = Date_year;
    }

    public int getDate_month () {
        return Date_month;
    }

    public void setDate_month (int Date_month){
        this.Date_month = Date_month;
    }

    public int getDate_day () {
        return Date_day;
    }

    public void setDate_day (int Date_day){
        this.Date_day = Date_day;
    }

    public float getTime () {
        return Date_hour;
    }

    public void setTime (float Date_hour){
        this.Date_hour = Date_hour;
    }

    public String getType () {
        return counseling_form;
    }

    public void setType (String conseling_form) {
        this.counseling_form = conseling_form;
    }

    public String getQuestion () {return question;}
    public void setQuestion (String question) {this.question = question;}

    public String getDate_week() { return Date_week;}
    public void setDate_week(String Date_week){this.Date_week = Date_week;}

    public String getStringMonth() {return StringMonth;}
    public void setStringMonth(String StringMonth){this.StringMonth = StringMonth;}

    public String getStringDay() {return StringDay;}
    public void setStringDay(String StringDay){this.StringDay = StringDay;}

    public String getStringTime() {return StringTime;}
    public void setStringTime(String StringTime){this.StringTime = StringTime;}

    public String getState() {return state;}
    public void setState(String state){this.state=state;}




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(Date_year);
        parcel.writeInt(Date_month);
        parcel.writeInt(Date_day);
        parcel.writeFloat(Date_hour);
        parcel.writeString(Date_week);
        parcel.writeString(counseling_form);
        parcel.writeString(question);
        parcel.writeString(StringMonth);
        parcel.writeString(StringDay);
        parcel.writeString(StringTime);
        parcel.writeString(state);
    }
}