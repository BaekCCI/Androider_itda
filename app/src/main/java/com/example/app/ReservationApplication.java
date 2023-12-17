package com.example.app;

import android.app.Application;

import com.example.androidproject.logIn;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReservationApplication extends Application {
    private Reservation currentReservation;
    private DatabaseReference databaseReference1;
    private DatabaseReference databaseReference2;

    @Override
    public void onCreate() {
        super.onCreate();

        // Firebase Database 초기화
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        databaseReference1 = FirebaseDatabase.getInstance().getReference("2021145818/Schedule_Management");
        databaseReference2 = FirebaseDatabase.getInstance().getReference();


        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //databaseReference = database.getReference("2021145818/Timetable/fixation");
    }

    public Reservation getCurrentReservation() {
        if (currentReservation == null) {
            currentReservation = new Reservation();
        }
        return currentReservation;
    }

    public void setCurrentReservation(Reservation currentReservation, String key) {
        this.currentReservation = currentReservation;

        // 파이어베이스에 데이터 업로드
        if (currentReservation != null) {
            databaseReference1.child(key).child("content").child("Date_year").setValue(currentReservation.getDate_year());
            databaseReference1.child(key).child("content").child("Date_month").setValue(currentReservation.getDate_month());
            databaseReference1.child(key).child("content").child("Date_day").setValue(currentReservation.getDate_day());
            databaseReference1.child(key).child("content").child("Date_hour").setValue(currentReservation.getTime());
            databaseReference1.child(key).child("content").child("Date_week").setValue(currentReservation.getDate_week());
            databaseReference1.child(key).child("content").child("counseling_form").setValue(currentReservation.getType());
            databaseReference1.child(key).child("content").child("question").setValue(currentReservation.getQuestion());
            databaseReference1.child(key).child("content").child("state").setValue(currentReservation.getState());

            databaseReference2.child(key).child("Schedule_Management").child("2021145818").child("content").child("Date_year").setValue(currentReservation.getDate_year());
            databaseReference2.child(key).child("Schedule_Management").child("2021145818").child("content").child("Date_month").setValue(currentReservation.getDate_month());
            databaseReference2.child(key).child("Schedule_Management").child("2021145818").child("content").child("Date_day").setValue(currentReservation.getDate_day());
            databaseReference2.child(key).child("Schedule_Management").child("2021145818").child("content").child("Date_hour").setValue(currentReservation.getTime());
            databaseReference2.child(key).child("Schedule_Management").child("2021145818").child("content").child("Date_week").setValue(currentReservation.getDate_week());
            databaseReference2.child(key).child("Schedule_Management").child("2021145818").child("content").child("counseling_form").setValue(currentReservation.getType());
            databaseReference2.child(key).child("Schedule_Management").child("2021145818").child("content").child("question").setValue(currentReservation.getQuestion());
            databaseReference2.child(key).child("Schedule_Management").child("2021145818").child("content").child("state").setValue(currentReservation.getState());


        }
    }
}