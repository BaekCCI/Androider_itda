package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ScrollView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class DaySchedule extends AppCompatActivity {
    CalendarView calendarView;
    Button btn0900, btn0930, btn1000, btn1030, btn1100, btn1130, btn1200, btn1230, btn1300, btn1330, btn1400, btn1430, btn1500, btn1530, btn1600, btn1630, btn1700, btn1730;
    Button allSelect, completeBtn;
    ImageButton backBtn;
    ScrollView scrollView;
    int Month, Date;
    int btn0900Clicked = 0, btn0930Clicked = 0, btn1000Clicked = 0, btn1030Clicked = 0, btn1100Clicked = 0, btn1130Clicked = 0, btn1200Clicked = 0, btn1230Clicked = 0, btn1300Clicked = 0, btn1330Clicked = 0, btn1400Clicked = 0, btn1430Clicked = 0, btn1500Clicked = 0, btn1530Clicked = 0, btn1600Clicked = 0, btn1630Clicked = 0, btn1700Clicked = 0, btn1730Clicked = 0;

    Dialog schedule_dialog;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String code;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dayschedule);

        firebaseDatabase = FirebaseDatabase.getInstance();
        logIn loginPageInstance = new logIn();
        code = loginPageInstance.getPrivate_key();
        databaseReference = firebaseDatabase.getReference(code);
        databaseReference = databaseReference.child("Timetable/fluctuations");


        calendarView=findViewById(R.id.calendarView);
        btn0900 = findViewById(R.id.btn0900);
        btn0930 = findViewById(R.id.btn0930);
        btn1000 = findViewById(R.id.btn1000);
        btn1030 = findViewById(R.id.btn1030);
        btn1100 = findViewById(R.id.btn1100);
        btn1130 = findViewById(R.id.btn1130);
        btn1200 = findViewById(R.id.btn1200);
        btn1230 = findViewById(R.id.btn1230);
        btn1300 = findViewById(R.id.btn1300);
        btn1330 = findViewById(R.id.btn1330);
        btn1400 = findViewById(R.id.btn1400);
        btn1430 = findViewById(R.id.btn1430);
        btn1500 = findViewById(R.id.btn1500);
        btn1530 = findViewById(R.id.btn1530);
        btn1600 = findViewById(R.id.btn1600);
        btn1630 = findViewById(R.id.btn1630);
        btn1700 = findViewById(R.id.btn1700);
        btn1730 = findViewById(R.id.btn1730);
        allSelect=findViewById(R.id.allSelect);
        completeBtn =findViewById(R.id.completeBtn);
        completeBtn.setEnabled(false);

        backBtn=findViewById(R.id.backBtn);
        schedule_dialog=new Dialog(DaySchedule.this);
        schedule_dialog.setContentView(R.layout.schedule_dialog);
        scrollView=findViewById(R.id.scrollView);

        // 과거의 날짜 모두 비활성화
        Calendar currentCalendar = Calendar.getInstance(); // 현재 날짜 가져오는 메서드
        calendarView.setMinDate(currentCalendar.getTimeInMillis()); // 현재 날짜 이전 날짜 비활성화

        //현재로부터 3달 뒤까지만 접근 가능
        Calendar maxDate = (Calendar) currentCalendar.clone();
        maxDate.add(Calendar.MONTH, 3);
        // CalendarView에 최대 날짜를 설정합니다.
        calendarView.setMaxDate(maxDate.getTimeInMillis());

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Month=month;
                Date=dayOfMonth;
                scrollView.setVisibility(View.VISIBLE);
                completeBtn.setBackgroundResource(R.drawable.availablenextbutton);
                completeBtn.setEnabled(true);

                ResetBtn();
                updateButtonBackground(btn0900, btn0900Clicked);
                updateButtonBackground(btn0930, btn0930Clicked);
                updateButtonBackground(btn1000, btn1000Clicked);
                updateButtonBackground(btn1030, btn1030Clicked);
                updateButtonBackground(btn1100, btn1000Clicked);
                updateButtonBackground(btn1130, btn1030Clicked);
                updateButtonBackground(btn1200, btn1000Clicked);
                updateButtonBackground(btn1230, btn1030Clicked);
                updateButtonBackground(btn1300, btn1300Clicked);
                updateButtonBackground(btn1330, btn1330Clicked);
                updateButtonBackground(btn1400, btn1400Clicked);
                updateButtonBackground(btn1430, btn1430Clicked);
                updateButtonBackground(btn1500, btn1500Clicked);
                updateButtonBackground(btn1530, btn1530Clicked);
                updateButtonBackground(btn1600, btn1600Clicked);
                updateButtonBackground(btn1630, btn1630Clicked);
                updateButtonBackground(btn1700, btn1700Clicked);
                updateButtonBackground(btn1730, btn1730Clicked);
                GetSchedule();


            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), FixedSchedule_P.class);
                startActivity(intent);
            }
        });

        completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UpdateSchedule();
            }
        });
        allSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btn0900Clicked == 1 && btn0930Clicked==1 && btn1000Clicked==1 && btn1030Clicked==1 && btn1100Clicked==1 && btn1130Clicked==1 && btn1200Clicked==1 && btn1230Clicked==1 && btn1300Clicked==1 && btn1330Clicked==1 && btn1400Clicked==1 && btn1430Clicked==1 && btn1500Clicked ==1 && btn1530Clicked ==1 && btn1600Clicked==1 && btn1630Clicked==1 && btn1700Clicked ==1 && btn1730Clicked==1) {

                    btn0900Clicked = 0;
                    btn0930Clicked = 0;
                    btn1000Clicked = 0;
                    btn1030Clicked = 0;
                    btn1100Clicked = 0;
                    btn1130Clicked = 0;
                    btn1200Clicked = 0;
                    btn1230Clicked = 0;
                    btn1300Clicked = 0;
                    btn1330Clicked = 0;
                    btn1400Clicked = 0;
                    btn1430Clicked = 0;
                    btn1500Clicked = 0;
                    btn1530Clicked = 0;
                    btn1600Clicked = 0;
                    btn1630Clicked = 0;
                    btn1700Clicked = 0;
                    btn1730Clicked = 0;
                }
                else {
                    btn0900Clicked = 1;
                    btn0930Clicked = 1;
                    btn1000Clicked = 1;
                    btn1030Clicked = 1;
                    btn1100Clicked = 1;
                    btn1130Clicked = 1;
                    btn1200Clicked = 1;
                    btn1230Clicked = 1;
                    btn1300Clicked = 1;
                    btn1330Clicked = 1;
                    btn1400Clicked = 1;
                    btn1430Clicked = 1;
                    btn1500Clicked = 1;
                    btn1530Clicked = 1;
                    btn1600Clicked = 1;
                    btn1630Clicked = 1;
                    btn1700Clicked = 1;
                    btn1730Clicked = 1;
                }
                updateButtonBackground(btn0900, btn0900Clicked);
                updateButtonBackground(btn0930, btn0930Clicked);
                updateButtonBackground(btn1000, btn1000Clicked);
                updateButtonBackground(btn1030, btn1030Clicked);
                updateButtonBackground(btn1100, btn1000Clicked);
                updateButtonBackground(btn1130, btn1030Clicked);
                updateButtonBackground(btn1200, btn1000Clicked);
                updateButtonBackground(btn1230, btn1030Clicked);
                updateButtonBackground(btn1300, btn1300Clicked);
                updateButtonBackground(btn1330, btn1330Clicked);
                updateButtonBackground(btn1400, btn1400Clicked);
                updateButtonBackground(btn1430, btn1430Clicked);
                updateButtonBackground(btn1500, btn1500Clicked);
                updateButtonBackground(btn1530, btn1530Clicked);
                updateButtonBackground(btn1600, btn1600Clicked);
                updateButtonBackground(btn1630, btn1630Clicked);
                updateButtonBackground(btn1700, btn1700Clicked);
                updateButtonBackground(btn1730, btn1730Clicked);
            }
        });


        btn0900.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn0900Clicked = (btn0900Clicked==1)? 0:1;
                updateButtonBackground(btn0900, btn0900Clicked);
            }
        });

        btn0930.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn0930Clicked = (btn0930Clicked==1)? 0:1;
                updateButtonBackground(btn0930, btn0930Clicked);
            }
        });

        btn1000.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                btn1000Clicked = (btn1000Clicked==1)? 0:1;
                updateButtonBackground(btn1000, btn1000Clicked);
            }
        });
        btn1030.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1030Clicked = (btn1030Clicked==1)? 0:1;
                updateButtonBackground(btn1030, btn1030Clicked);
            }
        });
        btn1100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1100Clicked = (btn1100Clicked==1)? 0:1;
                updateButtonBackground(btn1100, btn1100Clicked);
            }
        });
        btn1130.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1130Clicked = (btn1130Clicked==1)? 0:1;
                updateButtonBackground(btn1130, btn1130Clicked);
            }
        });
        btn1200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1200Clicked = (btn1200Clicked==1)? 0:1;
                updateButtonBackground(btn1200, btn1200Clicked);
            }
        });
        btn1230.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1230Clicked = (btn1230Clicked==1)? 0:1;
                updateButtonBackground(btn1230, btn1230Clicked);
            }
        });
        btn1300.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1300Clicked = (btn1300Clicked==1)? 0:1;
                updateButtonBackground(btn1300, btn1300Clicked);
            }
        });
        btn1330.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1330Clicked = (btn1330Clicked==1)? 0:1;
                updateButtonBackground(btn1330, btn1330Clicked);
            }
        });
        btn1400.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                btn1400Clicked = (btn1400Clicked==1)? 0:1;
                updateButtonBackground(btn1400, btn1400Clicked);
            }
        });
        btn1430.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                btn1430Clicked = (btn1430Clicked==1)? 0:1;
                updateButtonBackground(btn1430, btn1430Clicked);
            }
        });
        btn1500.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                btn1500Clicked = (btn1500Clicked==1)? 0:1;
                updateButtonBackground(btn1500, btn1500Clicked);
            }
        });
        btn1530.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                btn1530Clicked = (btn1530Clicked==1)? 0:1;
                updateButtonBackground(btn1530, btn1530Clicked);
            }
        });
        btn1600.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                btn1600Clicked = (btn1600Clicked==1)? 0:1;
                updateButtonBackground(btn1600, btn1600Clicked);
            }
        });
        btn1630.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                btn1630Clicked = (btn1630Clicked==1)? 0:1;
                updateButtonBackground(btn1630, btn1630Clicked);
            }
        });
        btn1700.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                btn1700Clicked = (btn1700Clicked==1)? 0:1;
                updateButtonBackground(btn1700, btn1700Clicked);
            }
        });
        btn1730.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                btn1730Clicked = (btn1730Clicked==1)? 0:1;
                updateButtonBackground(btn1730, btn1730Clicked);

            }
        });
        completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateSchedule();
                schedule_dialog.show();
                Button okBtn = schedule_dialog.findViewById(R.id.okBtn);
                okBtn.setOnClickListener(new View.OnClickListener(){

                    public void onClick(View v){
                        schedule_dialog.dismiss();
                    }
                });

            }
        });


    }
    private void ResetBtn(){
        btn0900Clicked = 0;
        btn0930Clicked = 0;
        btn1000Clicked = 0;
        btn1030Clicked = 0;
        btn1100Clicked = 0;
        btn1130Clicked = 0;
        btn1200Clicked = 0;
        btn1230Clicked = 0;
        btn1300Clicked = 0;
        btn1330Clicked = 0;
        btn1400Clicked = 0;
        btn1430Clicked = 0;
        btn1500Clicked = 0;
        btn1530Clicked = 0;
        btn1600Clicked = 0;
        btn1630Clicked = 0;
        btn1700Clicked = 0;
        btn1730Clicked = 0;
        updateButtonBackground(btn0900, 0);
        updateButtonBackground(btn0930, 0);
        updateButtonBackground(btn1000, 0);
        updateButtonBackground(btn1030, 0);
        updateButtonBackground(btn1100, 0);
        updateButtonBackground(btn1130, 0);
        updateButtonBackground(btn1200, 0);
        updateButtonBackground(btn1230, 0);
        updateButtonBackground(btn1300, 0);
        updateButtonBackground(btn1330, 0);
        updateButtonBackground(btn1400, 0);
        updateButtonBackground(btn1430, 0);
        updateButtonBackground(btn1500, 0);
        updateButtonBackground(btn1530, 0);
        updateButtonBackground(btn1600, 0);
        updateButtonBackground(btn1630, 0);
        updateButtonBackground(btn1700, 0);
        updateButtonBackground(btn1730, 0);
    }
    private void GetSchedule(){

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String btnState = snapshot.child(getMonthKey(Month)).child(getDayKey(Date)).getValue(String.class);
                    if (btnState != null && btnState.length() == 18) {
                        int[] btnClickedArray = new int[18];
                        for (int i = 0; i < btnClickedArray.length; i++) {
                            btnClickedArray[i] = Character.getNumericValue(btnState.charAt(i));
                        }

                        // 각 버튼에 대해 배경 업데이트
                        btn0900Clicked = btnClickedArray[0];
                        btn0930Clicked = btnClickedArray[1];
                        btn1000Clicked = btnClickedArray[2];
                        btn1030Clicked = btnClickedArray[3];
                        btn1100Clicked = btnClickedArray[4];
                        btn1130Clicked = btnClickedArray[5];
                        btn1200Clicked = btnClickedArray[6];
                        btn1230Clicked = btnClickedArray[7];
                        btn1300Clicked = btnClickedArray[8];
                        btn1330Clicked = btnClickedArray[9];
                        btn1400Clicked = btnClickedArray[10];
                        btn1430Clicked = btnClickedArray[11];
                        btn1500Clicked = btnClickedArray[12];
                        btn1530Clicked = btnClickedArray[13];
                        btn1600Clicked = btnClickedArray[14];
                        btn1630Clicked = btnClickedArray[15];
                        btn1700Clicked = btnClickedArray[16];
                        btn1730Clicked = btnClickedArray[17];
                        updateButtonBackground(btn0900, btnClickedArray[0]);
                        updateButtonBackground(btn0930, btnClickedArray[1]);
                        updateButtonBackground(btn1000, btnClickedArray[2]);
                        updateButtonBackground(btn1030, btnClickedArray[3]);
                        updateButtonBackground(btn1100, btnClickedArray[4]);
                        updateButtonBackground(btn1130, btnClickedArray[5]);
                        updateButtonBackground(btn1200, btnClickedArray[6]);
                        updateButtonBackground(btn1230, btnClickedArray[7]);
                        updateButtonBackground(btn1300, btnClickedArray[8]);
                        updateButtonBackground(btn1330, btnClickedArray[9]);
                        updateButtonBackground(btn1400, btnClickedArray[10]);
                        updateButtonBackground(btn1430, btnClickedArray[11]);
                        updateButtonBackground(btn1500, btnClickedArray[12]);
                        updateButtonBackground(btn1530, btnClickedArray[13]);
                        updateButtonBackground(btn1600, btnClickedArray[14]);
                        updateButtonBackground(btn1630, btnClickedArray[15]);
                        updateButtonBackground(btn1700, btnClickedArray[16]);
                        updateButtonBackground(btn1730, btnClickedArray[17]);

                    }
                    else{
                        ResetBtn();
                    }
                }
            }
            @Override
            public void onCancelled (@NonNull DatabaseError error){


            }

        });
    }
    private void UpdateSchedule(){
        String btnState = String.valueOf(btn0900Clicked)+String.valueOf(btn0930Clicked)+String.valueOf(btn1000Clicked)+String.valueOf(btn1030Clicked)+String.valueOf(btn1100Clicked)+String.valueOf(btn1130Clicked)+String.valueOf(btn1200Clicked)+String.valueOf(btn1230Clicked)+String.valueOf(btn1300Clicked)+String.valueOf(btn1330Clicked)+String.valueOf(btn1400Clicked)+String.valueOf(btn1430Clicked)+String.valueOf(btn1500Clicked)+String.valueOf(btn1530Clicked)+String.valueOf(btn1600Clicked)+String.valueOf(btn1630Clicked)+String.valueOf(btn1700Clicked)+String.valueOf(btn1730Clicked);
        DatabaseReference dateReference = databaseReference.child(getMonthKey(Month)).child(getDayKey(Date));
        dateReference.setValue(btnState);
    }
    private String getMonthKey(int month){
        return String.valueOf(month + 1);
    }
    private String getDayKey(int day){
        String DateKey;
        if(day<10){
            DateKey = "d0" + day;
        }
        else{
            DateKey = "d" + day;
        }
        return DateKey;
    }

    private void updateButtonBackground(Button button, int isClicked) {
        Drawable newBackground;
        if (isClicked==1) {
            newBackground = getResources().getDrawable(R.drawable.unavailabletimebutton);
        } else {
            newBackground = getResources().getDrawable(R.drawable.timebutton);
        }

        button.setBackground(newBackground);
    }

}