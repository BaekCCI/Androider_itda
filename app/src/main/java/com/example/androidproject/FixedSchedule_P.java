package com.example.androidproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FixedSchedule_P extends AppCompatActivity {
    private DatabaseReference databaseReference;

    private Button nextButton;
    private Button monButton;
    private Button tueButton;
    private Button wedButton;
    private Button thuButton;
    private Button friButton;
    ImageButton backBtn;



    private String monBitString;
    private String tueBitString;
    private String wedBitString;
    private String thuBitString;
    private String friBitString;






    private Button monButton0900;
    private Button monButton0930;
    private Button monButton1000;
    private Button monButton1030;
    private Button monButton1100;
    private Button monButton1130;
    private Button monButton1200;
    private Button monButton1230;
    private Button monButton1300;
    private Button monButton1330;
    private Button monButton1400;
    private Button monButton1430;
    private Button monButton1500;
    private Button monButton1530;
    private Button monButton1600;
    private Button monButton1630;
    private Button monButton1700;
    private Button monButton1730;
    private Button tueButton0900;
    private Button tueButton0930;
    private Button tueButton1000;
    private Button tueButton1030;
    private Button tueButton1100;
    private Button tueButton1130;
    private Button tueButton1200;
    private Button tueButton1230;
    private Button tueButton1300;
    private Button tueButton1330;
    private Button tueButton1400;
    private Button tueButton1430;
    private Button tueButton1500;
    private Button tueButton1530;
    private Button tueButton1600;
    private Button tueButton1630;
    private Button tueButton1700;
    private Button tueButton1730;
    private Button wedButton0900;
    private Button wedButton0930;
    private Button wedButton1000;
    private Button wedButton1030;
    private Button wedButton1100;
    private Button wedButton1130;
    private Button wedButton1200;
    private Button wedButton1230;
    private Button wedButton1300;
    private Button wedButton1330;
    private Button wedButton1400;
    private Button wedButton1430;
    private Button wedButton1500;
    private Button wedButton1530;
    private Button wedButton1600;
    private Button wedButton1630;
    private Button wedButton1700;
    private Button wedButton1730;
    private Button thuButton0900;
    private Button thuButton0930;
    private Button thuButton1000;
    private Button thuButton1030;
    private Button thuButton1100;
    private Button thuButton1130;
    private Button thuButton1200;
    private Button thuButton1230;
    private Button thuButton1300;
    private Button thuButton1330;
    private Button thuButton1400;
    private Button thuButton1430;
    private Button thuButton1500;
    private Button thuButton1530;
    private Button thuButton1600;
    private Button thuButton1630;
    private Button thuButton1700;
    private Button thuButton1730;
    private Button friButton0900;
    private Button friButton0930;
    private Button friButton1000;
    private Button friButton1030;
    private Button friButton1100;
    private Button friButton1130;
    private Button friButton1200;
    private Button friButton1230;
    private Button friButton1300;
    private Button friButton1330;
    private Button friButton1400;
    private Button friButton1430;
    private Button friButton1500;
    private Button friButton1530;
    private Button friButton1600;
    private Button friButton1630;
    private Button friButton1700;
    private Button friButton1730;

    private Button dayScheduleButton;

    private boolean monButton0900Clicked = false;
    private boolean monButton0930Clicked = false;
    private boolean monButton1000Clicked = false;
    private boolean monButton1030Clicked = false;
    private boolean monButton1100Clicked = false;
    private boolean monButton1130Clicked = false;
    private boolean monButton1200Clicked = false;
    private boolean monButton1230Clicked = false;
    private boolean monButton1300Clicked = false;
    private boolean monButton1330Clicked = false;
    private boolean monButton1400Clicked = false;
    private boolean monButton1430Clicked = false;
    private boolean monButton1500Clicked = false;
    private boolean monButton1530Clicked = false;
    private boolean monButton1600Clicked = false;
    private boolean monButton1630Clicked = false;
    private boolean monButton1700Clicked = false;
    private boolean monButton1730Clicked = false;
    private boolean tueButton0900Clicked = false;
    private boolean tueButton0930Clicked = false;
    private boolean tueButton1000Clicked = false;
    private boolean tueButton1030Clicked = false;
    private boolean tueButton1100Clicked = false;
    private boolean tueButton1130Clicked = false;
    private boolean tueButton1200Clicked = false;
    private boolean tueButton1230Clicked = false;
    private boolean tueButton1300Clicked = false;
    private boolean tueButton1330Clicked = false;
    private boolean tueButton1400Clicked = false;
    private boolean tueButton1430Clicked = false;
    private boolean tueButton1500Clicked = false;
    private boolean tueButton1530Clicked = false;
    private boolean tueButton1600Clicked = false;
    private boolean tueButton1630Clicked = false;
    private boolean tueButton1700Clicked = false;
    private boolean tueButton1730Clicked = false;
    // For Wednesday
    private boolean wedButton0900Clicked = false;
    private boolean wedButton0930Clicked = false;
    private boolean wedButton1000Clicked = false;
    private boolean wedButton1030Clicked = false;
    private boolean wedButton1100Clicked = false;
    private boolean wedButton1130Clicked = false;
    private boolean wedButton1200Clicked = false;
    private boolean wedButton1230Clicked = false;
    private boolean wedButton1300Clicked = false;
    private boolean wedButton1330Clicked = false;
    private boolean wedButton1400Clicked = false;
    private boolean wedButton1430Clicked = false;
    private boolean wedButton1500Clicked = false;
    private boolean wedButton1530Clicked = false;
    private boolean wedButton1600Clicked = false;
    private boolean wedButton1630Clicked = false;
    private boolean wedButton1700Clicked = false;
    private boolean wedButton1730Clicked = false;

    // For Thursday
    private boolean thuButton0900Clicked = false;
    private boolean thuButton0930Clicked = false;
    private boolean thuButton1000Clicked = false;
    private boolean thuButton1030Clicked = false;
    private boolean thuButton1100Clicked = false;
    private boolean thuButton1130Clicked = false;
    private boolean thuButton1200Clicked = false;
    private boolean thuButton1230Clicked = false;
    private boolean thuButton1300Clicked = false;
    private boolean thuButton1330Clicked = false;
    private boolean thuButton1400Clicked = false;
    private boolean thuButton1430Clicked = false;
    private boolean thuButton1500Clicked = false;
    private boolean thuButton1530Clicked = false;
    private boolean thuButton1600Clicked = false;
    private boolean thuButton1630Clicked = false;
    private boolean thuButton1700Clicked = false;
    private boolean thuButton1730Clicked = false;

    // For Friday
    private boolean friButton0900Clicked = false;
    private boolean friButton0930Clicked = false;
    private boolean friButton1000Clicked = false;
    private boolean friButton1030Clicked = false;
    private boolean friButton1100Clicked = false;
    private boolean friButton1130Clicked = false;
    private boolean friButton1200Clicked = false;
    private boolean friButton1230Clicked = false;
    private boolean friButton1300Clicked = false;
    private boolean friButton1330Clicked = false;
    private boolean friButton1400Clicked = false;
    private boolean friButton1430Clicked = false;
    private boolean friButton1500Clicked = false;
    private boolean friButton1530Clicked = false;
    private boolean friButton1600Clicked = false;
    private boolean friButton1630Clicked = false;
    private boolean friButton1700Clicked = false;
    private boolean friButton1730Clicked = false;
    private Button lastClickedDayButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fixed_schedule_p);

        //Toolbar record_toolbar=findViewById(R.id.toolbar);
        //record_toolbar.setTitleTextColor(Color.WHITE);
        //setSupportActionBar(record_toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("스케줄 관리");

        dayScheduleButton = findViewById(R.id.dayScheduleButton);
        backBtn = findViewById(R.id.backBtn);

        nextButton = findViewById(R.id.nextButton);

        monButton = findViewById(R.id.monButton);
        tueButton = findViewById(R.id.tueButton);
        wedButton = findViewById(R.id.wedButton);
        thuButton = findViewById(R.id.thuButton);
        friButton = findViewById(R.id.friButton);

        monButton0900 = findViewById(R.id.monButton0900);
        monButton0930 = findViewById(R.id.monButton0930);
        monButton1000 = findViewById(R.id.monButton1000);
        monButton1030 = findViewById(R.id.monButton1030);
        monButton1100 = findViewById(R.id.monButton1100);
        monButton1130 = findViewById(R.id.monButton1130);
        monButton1200 = findViewById(R.id.monButton1200);
        monButton1230 = findViewById(R.id.monButton1230);
        monButton1300 = findViewById(R.id.monButton1300);
        monButton1330 = findViewById(R.id.monButton1330);
        monButton1400 = findViewById(R.id.monButton1400);
        monButton1430 = findViewById(R.id.monButton1430);
        monButton1500 = findViewById(R.id.monButton1500);
        monButton1530 = findViewById(R.id.monButton1530);
        monButton1600 = findViewById(R.id.monButton1600);
        monButton1630 = findViewById(R.id.monButton1630);
        monButton1700 = findViewById(R.id.monButton1700);
        monButton1730 = findViewById(R.id.monButton1730);

        tueButton0900 = findViewById(R.id.tueButton0900);
        tueButton0930 = findViewById(R.id.tueButton0930);
        tueButton1000 = findViewById(R.id.tueButton1000);
        tueButton1030 = findViewById(R.id.tueButton1030);
        tueButton1100 = findViewById(R.id.tueButton1100);
        tueButton1130 = findViewById(R.id.tueButton1130);
        tueButton1200 = findViewById(R.id.tueButton1200);
        tueButton1230 = findViewById(R.id.tueButton1230);
        tueButton1300 = findViewById(R.id.tueButton1300);
        tueButton1330 = findViewById(R.id.tueButton1330);
        tueButton1400 = findViewById(R.id.tueButton1400);
        tueButton1430 = findViewById(R.id.tueButton1430);
        tueButton1500 = findViewById(R.id.tueButton1500);
        tueButton1530 = findViewById(R.id.tueButton1530);
        tueButton1600 = findViewById(R.id.tueButton1600);
        tueButton1630 = findViewById(R.id.tueButton1630);
        tueButton1700 = findViewById(R.id.tueButton1700);
        tueButton1730 = findViewById(R.id.tueButton1730);

        wedButton0900 = findViewById(R.id.wedButton0900);
        wedButton0930 = findViewById(R.id.wedButton0930);
        wedButton1000 = findViewById(R.id.wedButton1000);
        wedButton1030 = findViewById(R.id.wedButton1030);
        wedButton1100 = findViewById(R.id.wedButton1100);
        wedButton1130 = findViewById(R.id.wedButton1130);
        wedButton1200 = findViewById(R.id.wedButton1200);
        wedButton1230 = findViewById(R.id.wedButton1230);
        wedButton1300 = findViewById(R.id.wedButton1300);
        wedButton1330 = findViewById(R.id.wedButton1330);
        wedButton1400 = findViewById(R.id.wedButton1400);
        wedButton1430 = findViewById(R.id.wedButton1430);
        wedButton1500 = findViewById(R.id.wedButton1500);
        wedButton1530 = findViewById(R.id.wedButton1530);
        wedButton1600 = findViewById(R.id.wedButton1600);
        wedButton1630 = findViewById(R.id.wedButton1630);
        wedButton1700 = findViewById(R.id.wedButton1700);
        wedButton1730 = findViewById(R.id.wedButton1730);

        thuButton0900 = findViewById(R.id.thuButton0900);
        thuButton0930 = findViewById(R.id.thuButton0930);
        thuButton1000 = findViewById(R.id.thuButton1000);
        thuButton1030 = findViewById(R.id.thuButton1030);
        thuButton1100 = findViewById(R.id.thuButton1100);
        thuButton1130 = findViewById(R.id.thuButton1130);
        thuButton1200 = findViewById(R.id.thuButton1200);
        thuButton1230 = findViewById(R.id.thuButton1230);
        thuButton1300 = findViewById(R.id.thuButton1300);
        thuButton1330 = findViewById(R.id.thuButton1330);
        thuButton1400 = findViewById(R.id.thuButton1400);
        thuButton1430 = findViewById(R.id.thuButton1430);
        thuButton1500 = findViewById(R.id.thuButton1500);
        thuButton1530 = findViewById(R.id.thuButton1530);
        thuButton1600 = findViewById(R.id.thuButton1600);
        thuButton1630 = findViewById(R.id.thuButton1630);
        thuButton1700 = findViewById(R.id.thuButton1700);
        thuButton1730 = findViewById(R.id.thuButton1730);

        friButton0900 = findViewById(R.id.friButton0900);
        friButton0930 = findViewById(R.id.friButton0930);
        friButton1000 = findViewById(R.id.friButton1000);
        friButton1030 = findViewById(R.id.friButton1030);
        friButton1100 = findViewById(R.id.friButton1100);
        friButton1130 = findViewById(R.id.friButton1130);
        friButton1200 = findViewById(R.id.friButton1200);
        friButton1230 = findViewById(R.id.friButton1230);
        friButton1300 = findViewById(R.id.friButton1300);
        friButton1330 = findViewById(R.id.friButton1330);
        friButton1400 = findViewById(R.id.friButton1400);
        friButton1430 = findViewById(R.id.friButton1430);
        friButton1500 = findViewById(R.id.friButton1500);
        friButton1530 = findViewById(R.id.friButton1530);
        friButton1600 = findViewById(R.id.friButton1600);
        friButton1630 = findViewById(R.id.friButton1630);
        friButton1700 = findViewById(R.id.friButton1700);
        friButton1730 = findViewById(R.id.friButton1730);

        monButton0900.setVisibility(View.INVISIBLE);
        monButton0930.setVisibility(View.INVISIBLE);
        monButton1000.setVisibility(View.INVISIBLE);
        monButton1030.setVisibility(View.INVISIBLE);
        monButton1100.setVisibility(View.INVISIBLE);
        monButton1130.setVisibility(View.INVISIBLE);
        monButton1200.setVisibility(View.INVISIBLE);
        monButton1230.setVisibility(View.INVISIBLE);
        monButton1300.setVisibility(View.INVISIBLE);
        monButton1330.setVisibility(View.INVISIBLE);
        monButton1400.setVisibility(View.INVISIBLE);
        monButton1430.setVisibility(View.INVISIBLE);
        monButton1500.setVisibility(View.INVISIBLE);
        monButton1530.setVisibility(View.INVISIBLE);
        monButton1600.setVisibility(View.INVISIBLE);
        monButton1630.setVisibility(View.INVISIBLE);
        monButton1700.setVisibility(View.INVISIBLE);
        monButton1730.setVisibility(View.INVISIBLE);

        tueButton0900.setVisibility(View.INVISIBLE);
        tueButton0930.setVisibility(View.INVISIBLE);
        tueButton1000.setVisibility(View.INVISIBLE);
        tueButton1030.setVisibility(View.INVISIBLE);
        tueButton1100.setVisibility(View.INVISIBLE);
        tueButton1130.setVisibility(View.INVISIBLE);
        tueButton1200.setVisibility(View.INVISIBLE);
        tueButton1230.setVisibility(View.INVISIBLE);
        tueButton1300.setVisibility(View.INVISIBLE);
        tueButton1330.setVisibility(View.INVISIBLE);
        tueButton1400.setVisibility(View.INVISIBLE);
        tueButton1430.setVisibility(View.INVISIBLE);
        tueButton1500.setVisibility(View.INVISIBLE);
        tueButton1530.setVisibility(View.INVISIBLE);
        tueButton1600.setVisibility(View.INVISIBLE);
        tueButton1630.setVisibility(View.INVISIBLE);
        tueButton1700.setVisibility(View.INVISIBLE);
        tueButton1730.setVisibility(View.INVISIBLE);

        wedButton0900.setVisibility(View.INVISIBLE);
        wedButton0930.setVisibility(View.INVISIBLE);
        wedButton1000.setVisibility(View.INVISIBLE);
        wedButton1030.setVisibility(View.INVISIBLE);
        wedButton1100.setVisibility(View.INVISIBLE);
        wedButton1130.setVisibility(View.INVISIBLE);
        wedButton1200.setVisibility(View.INVISIBLE);
        wedButton1230.setVisibility(View.INVISIBLE);
        wedButton1300.setVisibility(View.INVISIBLE);
        wedButton1330.setVisibility(View.INVISIBLE);
        wedButton1400.setVisibility(View.INVISIBLE);
        wedButton1430.setVisibility(View.INVISIBLE);
        wedButton1500.setVisibility(View.INVISIBLE);
        wedButton1530.setVisibility(View.INVISIBLE);
        wedButton1600.setVisibility(View.INVISIBLE);
        wedButton1630.setVisibility(View.INVISIBLE);
        wedButton1700.setVisibility(View.INVISIBLE);
        wedButton1730.setVisibility(View.INVISIBLE);

        thuButton0900.setVisibility(View.INVISIBLE);
        thuButton0930.setVisibility(View.INVISIBLE);
        thuButton1000.setVisibility(View.INVISIBLE);
        thuButton1030.setVisibility(View.INVISIBLE);
        thuButton1100.setVisibility(View.INVISIBLE);
        thuButton1130.setVisibility(View.INVISIBLE);
        thuButton1200.setVisibility(View.INVISIBLE);
        thuButton1230.setVisibility(View.INVISIBLE);
        thuButton1300.setVisibility(View.INVISIBLE);
        thuButton1330.setVisibility(View.INVISIBLE);
        thuButton1400.setVisibility(View.INVISIBLE);
        thuButton1430.setVisibility(View.INVISIBLE);
        thuButton1500.setVisibility(View.INVISIBLE);
        thuButton1530.setVisibility(View.INVISIBLE);
        thuButton1600.setVisibility(View.INVISIBLE);
        thuButton1630.setVisibility(View.INVISIBLE);
        thuButton1700.setVisibility(View.INVISIBLE);
        thuButton1730.setVisibility(View.INVISIBLE);

        friButton0900.setVisibility(View.INVISIBLE);
        friButton0930.setVisibility(View.INVISIBLE);
        friButton1000.setVisibility(View.INVISIBLE);
        friButton1030.setVisibility(View.INVISIBLE);
        friButton1100.setVisibility(View.INVISIBLE);
        friButton1130.setVisibility(View.INVISIBLE);
        friButton1200.setVisibility(View.INVISIBLE);
        friButton1230.setVisibility(View.INVISIBLE);
        friButton1300.setVisibility(View.INVISIBLE);
        friButton1330.setVisibility(View.INVISIBLE);
        friButton1400.setVisibility(View.INVISIBLE);
        friButton1430.setVisibility(View.INVISIBLE);
        friButton1500.setVisibility(View.INVISIBLE);
        friButton1530.setVisibility(View.INVISIBLE);
        friButton1600.setVisibility(View.INVISIBLE);
        friButton1630.setVisibility(View.INVISIBLE);
        friButton1700.setVisibility(View.INVISIBLE);
        friButton1730.setVisibility(View.INVISIBLE);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("2021145818/Timetable/fixation");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // 데이터 변경이 감지되었을 때 호출됩니다.
                // dataSnapshot을 통해 데이터를 가져올 수 있습니다.
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // 반복문을 사용하여 데이터를 가져오는 예시
                    // snapshot에서 필요한 데이터를 가져와서 처리할 수 있습니다.
                    String key = snapshot.getKey();
                    String value = snapshot.getValue(String.class);

                    if("monday".equals(key)){
                        monBitString = value;
                    }
                    else if("tuesday".equals(key)){
                        tueBitString = value;
                    }
                    else if("wednesday".equals(key)){
                        wedBitString = value;
                    }
                    else if("thursday".equals(key)){
                        thuBitString = value;
                    }
                    else if("friday".equals(key)){
                        friBitString = value;
                    }


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // 데이터 읽기가 취소되었을 때 호출됩니다.
                Log.e("MainActivity", "Error: " + databaseError.getMessage());
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainScreen_P.class);
                startActivity(intent);
            }
        });

        dayScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FixedSchedule_P.this, DaySchedule.class);
                startActivity(intent);
            }
        });



        nextButton.setEnabled(false);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("checkBitString", "monBitString : " + monBitString);
                Log.d("checkBitString", "tueBitString : " + tueBitString);
                Log.d("checkBitString", "wedBitString : " + wedBitString);
                Log.d("checkBitString", "thuBitString : " + thuBitString);
                Log.d("checkBitString", "friBitString : " + friBitString);


                databaseReference.child("monday").setValue(monBitString);
                databaseReference.child("tuesday").setValue(tueBitString);
                databaseReference.child("wednesday").setValue(wedBitString);
                databaseReference.child("thursday").setValue(thuBitString);
                databaseReference.child("friday").setValue(friBitString);

                showCompleteDialog();
            }
        });



        monButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Value", "monValue : " + monBitString + ", " + "tueValue : " + tueBitString + ", " + "wedValue : "
                        + wedBitString + ", " + "thuValue : " + thuBitString + ", " + "friValue : " + friBitString );
                updateHideState();
                nextButton.setEnabled(true);
                nextButton.setBackgroundResource(R.drawable.availablenextbutton);
                updateShowState(monButton);
                if(monBitString.charAt(0)=='1'){
                    monButton0900Clicked = true;
                    monButton0900.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(monBitString.charAt(1)=='1'){
                    monButton0930Clicked = true;
                    monButton0930.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(monBitString.charAt(2)=='1'){
                    monButton1000Clicked = true;
                    monButton1000.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(monBitString.charAt(3)=='1'){
                    monButton1030Clicked = true;
                    monButton1030.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(monBitString.charAt(4)=='1'){
                    monButton1100Clicked = true;
                    monButton1100.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(monBitString.charAt(5)=='1'){
                    monButton1130Clicked = true;
                    monButton1130.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(monBitString.charAt(6)=='1'){
                    monButton1200Clicked = true;
                    monButton1200.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(monBitString.charAt(7)=='1'){
                    monButton1230Clicked = true;
                    monButton1230.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(monBitString.charAt(8)=='1'){
                    monButton1300Clicked = true;
                    monButton1300.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(monBitString.charAt(9)=='1'){
                    monButton1330Clicked = true;
                    monButton1330.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(monBitString.charAt(10)=='1'){
                    monButton1400Clicked = true;
                    monButton1400.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(monBitString.charAt(11)=='1'){
                    monButton1430Clicked = true;
                    monButton1430.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(monBitString.charAt(12)=='1'){
                    monButton1500Clicked = true;
                    monButton1500.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(monBitString.charAt(13)=='1'){
                    monButton1530Clicked = true;
                    monButton1530.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(monBitString.charAt(14)=='1'){
                    monButton1600Clicked = true;
                    monButton1600.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(monBitString.charAt(15)=='1'){
                    monButton1630Clicked = true;
                    monButton1630.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(monBitString.charAt(16)=='1'){
                    monButton1700Clicked = true;
                    monButton1700.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(monBitString.charAt(17)=='1'){
                    monButton1730Clicked = true;
                    monButton1730.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                lastClickedDayButton = monButton;
            }
        });

        tueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateHideState();
                nextButton.setEnabled(true);
                nextButton.setBackgroundResource(R.drawable.availablenextbutton);
                updateShowState(tueButton);
                lastClickedDayButton = tueButton;

                if(tueBitString.charAt(0)=='1'){
                    tueButton0900Clicked = true;
                    tueButton0900.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(tueBitString.charAt(1)=='1'){
                    tueButton0930Clicked = true;
                    tueButton0930.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(tueBitString.charAt(2)=='1'){
                    tueButton1000Clicked = true;
                    tueButton1000.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(tueBitString.charAt(3)=='1'){
                    tueButton1030Clicked = true;
                    tueButton1030.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(tueBitString.charAt(4)=='1'){
                    tueButton1100Clicked = true;
                    tueButton1100.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(tueBitString.charAt(5)=='1'){
                    tueButton1130Clicked = true;
                    tueButton1130.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(tueBitString.charAt(6)=='1'){
                    tueButton1200Clicked = true;
                    tueButton1200.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(tueBitString.charAt(7)=='1'){
                    tueButton1230Clicked = true;
                    tueButton1230.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(tueBitString.charAt(8)=='1'){
                    tueButton1300Clicked = true;
                    tueButton1300.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(tueBitString.charAt(9)=='1'){
                    tueButton1330Clicked = true;
                    tueButton1330.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(tueBitString.charAt(10)=='1'){
                    tueButton1400Clicked = true;
                    tueButton1400.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(tueBitString.charAt(11)=='1'){
                    tueButton1430Clicked = true;
                    tueButton1430.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(tueBitString.charAt(12)=='1'){
                    tueButton1500Clicked = true;
                    tueButton1500.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(tueBitString.charAt(13)=='1'){
                    tueButton1530Clicked = true;
                    tueButton1530.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(tueBitString.charAt(14)=='1'){
                    tueButton1600Clicked = true;
                    tueButton1600.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(tueBitString.charAt(15)=='1'){
                    tueButton1630Clicked = true;
                    tueButton1630.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(tueBitString.charAt(16)=='1'){
                    tueButton1700Clicked = true;
                    tueButton1700.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(tueBitString.charAt(17)=='1'){
                    tueButton1730Clicked = true;
                    tueButton1730.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
            }
        });

        wedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateHideState();
                nextButton.setEnabled(true);
                nextButton.setBackgroundResource(R.drawable.availablenextbutton);
                updateShowState(wedButton);
                lastClickedDayButton = wedButton;

                if(wedBitString.charAt(0)=='1'){
                    wedButton0900Clicked = true;
                    wedButton0900.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(wedBitString.charAt(1)=='1'){
                    wedButton0930Clicked = true;
                    wedButton0930.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(wedBitString.charAt(2)=='1'){
                    wedButton1000Clicked = true;
                    wedButton1000.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(wedBitString.charAt(3)=='1'){
                    wedButton1030Clicked = true;
                    wedButton1030.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(wedBitString.charAt(4)=='1'){
                    wedButton1100Clicked = true;
                    wedButton1100.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(wedBitString.charAt(5)=='1'){
                    wedButton1130Clicked = true;
                    wedButton1130.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(wedBitString.charAt(6)=='1'){
                    wedButton1200Clicked = true;
                    wedButton1200.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(wedBitString.charAt(7)=='1'){
                    wedButton1230Clicked = true;
                    wedButton1230.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(wedBitString.charAt(8)=='1'){
                    wedButton1300Clicked = true;
                    wedButton1300.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(wedBitString.charAt(9)=='1'){
                    wedButton1330Clicked = true;
                    wedButton1330.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(wedBitString.charAt(10)=='1'){
                    wedButton1400Clicked = true;
                    wedButton1400.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(wedBitString.charAt(11)=='1'){
                    wedButton1430Clicked = true;
                    wedButton1430.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(wedBitString.charAt(12)=='1'){
                    wedButton1500Clicked = true;
                    wedButton1500.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(wedBitString.charAt(13)=='1'){
                    wedButton1530Clicked = true;
                    wedButton1530.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(wedBitString.charAt(14)=='1'){
                    wedButton1600Clicked = true;
                    wedButton1600.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(wedBitString.charAt(15)=='1'){
                    wedButton1630Clicked = true;
                    wedButton1630.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(wedBitString.charAt(16)=='1'){
                    wedButton1700Clicked = true;
                    wedButton1700.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(wedBitString.charAt(17)=='1'){
                    wedButton1730Clicked = true;
                    wedButton1730.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
            }
        });

        thuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateHideState();
                nextButton.setEnabled(true);
                nextButton.setBackgroundResource(R.drawable.availablenextbutton);
                updateShowState(thuButton);
                lastClickedDayButton = thuButton;

                if(thuBitString.charAt(0)=='1'){
                    thuButton0900Clicked = true;
                    thuButton0900.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(thuBitString.charAt(1)=='1'){
                    thuButton0930Clicked = true;
                    thuButton0930.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(thuBitString.charAt(2)=='1'){
                    thuButton1000Clicked = true;
                    thuButton1000.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(thuBitString.charAt(3)=='1'){
                    thuButton1030Clicked = true;
                    thuButton1030.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(thuBitString.charAt(4)=='1'){
                    thuButton1100Clicked = true;
                    thuButton1100.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(thuBitString.charAt(5)=='1'){
                    thuButton1130Clicked = true;
                    thuButton1130.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(thuBitString.charAt(6)=='1'){
                    thuButton1200Clicked = true;
                    thuButton1200.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(thuBitString.charAt(7)=='1'){
                    thuButton1230Clicked = true;
                    thuButton1230.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(thuBitString.charAt(8)=='1'){
                    thuButton1300Clicked = true;
                    thuButton1300.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(thuBitString.charAt(9)=='1'){
                    thuButton1330Clicked = true;
                    thuButton1330.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(thuBitString.charAt(10)=='1'){
                    thuButton1400Clicked = true;
                    thuButton1400.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(thuBitString.charAt(11)=='1'){
                    thuButton1430Clicked = true;
                    thuButton1430.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(thuBitString.charAt(12)=='1'){
                    thuButton1500Clicked = true;
                    thuButton1500.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(thuBitString.charAt(13)=='1'){
                    thuButton1530Clicked = true;
                    thuButton1530.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(thuBitString.charAt(14)=='1'){
                    thuButton1600Clicked = true;
                    thuButton1600.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(thuBitString.charAt(15)=='1'){
                    thuButton1630Clicked = true;
                    thuButton1630.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(thuBitString.charAt(16)=='1'){
                    thuButton1700Clicked = true;
                    thuButton1700.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(thuBitString.charAt(17)=='1'){
                    thuButton1730Clicked = true;
                    thuButton1730.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
            }
        });

        friButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateHideState();
                nextButton.setEnabled(true);
                nextButton.setBackgroundResource(R.drawable.availablenextbutton);
                updateShowState(friButton);
                lastClickedDayButton = friButton;

                if(friBitString.charAt(0)=='1'){
                    friButton0900Clicked = true;
                    friButton0900.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(friBitString.charAt(1)=='1'){
                    friButton0930Clicked = true;
                    friButton0930.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(friBitString.charAt(2)=='1'){
                    friButton1000Clicked = true;
                    friButton1000.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(friBitString.charAt(3)=='1'){
                    friButton1030Clicked = true;
                    friButton1030.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(friBitString.charAt(4)=='1'){
                    friButton1100Clicked = true;
                    friButton1100.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(friBitString.charAt(5)=='1'){
                    friButton1130Clicked = true;
                    friButton1130.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(friBitString.charAt(6)=='1'){
                    friButton1200Clicked = true;
                    friButton1200.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(friBitString.charAt(7)=='1'){
                    friButton1230Clicked = true;
                    friButton1230.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(friBitString.charAt(8)=='1'){
                    friButton1300Clicked = true;
                    friButton1300.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(friBitString.charAt(9)=='1'){
                    friButton1330Clicked = true;
                    friButton1330.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(friBitString.charAt(10)=='1'){
                    friButton1400Clicked = true;
                    friButton1400.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(friBitString.charAt(11)=='1'){
                    friButton1430Clicked = true;
                    friButton1430.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(friBitString.charAt(12)=='1'){
                    friButton1500Clicked = true;
                    friButton1500.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(friBitString.charAt(13)=='1'){
                    friButton1530Clicked = true;
                    friButton1530.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(friBitString.charAt(14)=='1'){
                    friButton1600Clicked = true;
                    friButton1600.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(friBitString.charAt(15)=='1'){
                    friButton1630Clicked = true;
                    friButton1630.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(friBitString.charAt(16)=='1'){
                    friButton1700Clicked = true;
                    friButton1700.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
                if(friBitString.charAt(17)=='1'){
                    friButton1730Clicked = true;
                    friButton1730.setBackgroundResource(R.drawable.unavailabletimebutton);
                }
            }
        });

        monButton0900.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monButton0900Clicked = !monButton0900Clicked;
                if(monButton0900Clicked) {
                    monButton0900.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(0, '1');
                    monBitString = modifiedBitString.toString();
                } else {
                    monButton0900.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(0, '0');
                    monBitString = modifiedBitString.toString();
                }
                //syncNextButtonState();
            }
        });

        monButton0930.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monButton0930Clicked = !monButton0930Clicked;
                if(monButton0930Clicked) {
                    monButton0930.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(1, '1');
                    monBitString = modifiedBitString.toString();
                } else {
                    monButton0930.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(1, '0');
                    monBitString = modifiedBitString.toString();
                }
                //syncNextButtonState();
            }
        });

        monButton1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monButton1000Clicked = !monButton1000Clicked;
                if (monButton1000Clicked) {
                    monButton1000.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(2, '1');
                    monBitString = modifiedBitString.toString();
                } else {
                    monButton1000.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(2, '0');
                    monBitString = modifiedBitString.toString();
                }
                //syncNextButtonState();
            }
        });

        monButton1030.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monButton1030Clicked = !monButton1030Clicked;
                if (monButton1030Clicked) {
                    monButton1030.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(3, '1');
                    monBitString = modifiedBitString.toString();
                } else {
                    monButton1030.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(3, '0');
                    monBitString = modifiedBitString.toString();
                }
                //syncNextButtonState();
            }
        });

        monButton1100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monButton1100Clicked = !monButton1100Clicked;
                if (monButton1100Clicked) {
                    monButton1100.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(4, '1');
                    monBitString = modifiedBitString.toString();
                } else {
                    monButton1100.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(4, '0');
                    monBitString = modifiedBitString.toString();
                }
                //syncNextButtonState();
            }
        });

        monButton1130.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monButton1130Clicked = !monButton1130Clicked;
                if (monButton1130Clicked) {
                    monButton1130.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(5, '1');
                    monBitString = modifiedBitString.toString();
                } else {
                    monButton1130.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(5, '0');
                    monBitString = modifiedBitString.toString();
                }
                //syncNextButtonState();
            }
        });

        monButton1200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monButton1200Clicked = !monButton1200Clicked;
                if (monButton1200Clicked) {
                    monButton1200.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(6, '1');
                    monBitString = modifiedBitString.toString();
                } else {
                    monButton1200.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(6, '0');
                    monBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        monButton1230.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monButton1230Clicked = !monButton1230Clicked;
                if (monButton1230Clicked) {
                    monButton1230.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(7, '1');
                    monBitString = modifiedBitString.toString();
                } else {
                    monButton1230.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(7, '0');
                    monBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        monButton1300.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monButton1300Clicked = !monButton1300Clicked;
                if (monButton1300Clicked) {
                    monButton1300.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(8, '1');
                    monBitString = modifiedBitString.toString();
                } else {
                    monButton1300.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(8, '0');
                    monBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        monButton1330.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monButton1330Clicked = !monButton1330Clicked;
                if (monButton1330Clicked) {
                    monButton1330.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(9, '1');
                    monBitString = modifiedBitString.toString();
                } else {
                    monButton1330.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(9, '0');
                    monBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        monButton1400.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monButton1400Clicked = !monButton1400Clicked;
                if (monButton1400Clicked) {
                    monButton1400.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(10, '1');
                    monBitString = modifiedBitString.toString();
                } else {
                    monButton1400.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(10, '0');
                    monBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        monButton1430.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monButton1430Clicked = !monButton1430Clicked;
                if (monButton1430Clicked) {
                    monButton1430.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(11, '1');
                    monBitString = modifiedBitString.toString();
                } else {
                    monButton1430.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(11, '0');
                    monBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        monButton1500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monButton1500Clicked = !monButton1500Clicked;
                if (monButton1500Clicked) {
                    monButton1500.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(12, '1');
                    monBitString = modifiedBitString.toString();
                } else {
                    monButton1500.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(12, '0');
                    monBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        monButton1530.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monButton1530Clicked = !monButton1530Clicked;
                if (monButton1530Clicked) {
                    monButton1530.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(13, '1');
                    monBitString = modifiedBitString.toString();
                } else {
                    monButton1530.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(13, '0');
                    monBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        monButton1600.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monButton1600Clicked = !monButton1600Clicked;
                if (monButton1600Clicked) {
                    monButton1600.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(14, '1');
                    monBitString = modifiedBitString.toString();
                } else {
                    monButton1600.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(14, '0');
                    monBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        monButton1630.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monButton1630Clicked = !monButton1630Clicked;
                if (monButton1630Clicked) {
                    monButton1630.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(15, '1');
                    monBitString = modifiedBitString.toString();
                } else {
                    monButton1630.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(15, '0');
                    monBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        monButton1700.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monButton1700Clicked = !monButton1700Clicked;
                if (monButton1700Clicked) {
                    monButton1700.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(16, '1');
                    monBitString = modifiedBitString.toString();
                } else {
                    monButton1700.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(16, '0');
                    monBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        monButton1730.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monButton1730Clicked = !monButton1730Clicked;
                if (monButton1730Clicked) {
                    monButton1730.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(17, '1');
                    monBitString = modifiedBitString.toString();
                } else {
                    monButton1730.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(monBitString);
                    modifiedBitString.setCharAt(17, '0');
                    monBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        tueButton0900.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tueButton0900Clicked = !tueButton0900Clicked;
                if(tueButton0900Clicked) {
                    tueButton0900.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(0, '1');
                    tueBitString = modifiedBitString.toString();
                } else {
                    tueButton0900.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(0, '0');
                    tueBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        tueButton0930.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tueButton0930Clicked = !tueButton0930Clicked;
                if(tueButton0930Clicked) {
                    tueButton0930.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(1, '1');
                    tueBitString = modifiedBitString.toString();
                } else {
                    tueButton0930.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(1, '0');
                    tueBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        tueButton1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tueButton1000Clicked = !tueButton1000Clicked;
                if (tueButton1000Clicked) {
                    tueButton1000.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(2, '1');
                    tueBitString = modifiedBitString.toString();
                } else {
                    tueButton1000.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(2, '0');
                    tueBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        tueButton1030.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tueButton1030Clicked = !tueButton1030Clicked;
                if (tueButton1030Clicked) {
                    tueButton1030.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(3, '1');
                    tueBitString = modifiedBitString.toString();
                } else {
                    tueButton1030.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(3, '0');
                    tueBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        tueButton1100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tueButton1100Clicked = !tueButton1100Clicked;
                if (tueButton1100Clicked) {
                    tueButton1100.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(4, '1');
                    tueBitString = modifiedBitString.toString();
                } else {
                    tueButton1100.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(4, '0');
                    tueBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        tueButton1130.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tueButton1130Clicked = !tueButton1130Clicked;
                if (tueButton1130Clicked) {
                    tueButton1130.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(5, '1');
                    tueBitString = modifiedBitString.toString();
                } else {
                    tueButton1130.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(5, '0');
                    tueBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        tueButton1200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tueButton1200Clicked = !tueButton1200Clicked;
                if (tueButton1200Clicked) {
                    tueButton1200.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(6, '1');
                    tueBitString = modifiedBitString.toString();
                } else {
                    tueButton1200.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(6, '0');
                    tueBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        tueButton1230.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tueButton1230Clicked = !tueButton1230Clicked;
                if (tueButton1230Clicked) {
                    tueButton1230.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(7, '1');
                    tueBitString = modifiedBitString.toString();
                } else {
                    tueButton1230.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(7, '0');
                    tueBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        tueButton1300.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tueButton1300Clicked = !tueButton1300Clicked;
                if (tueButton1300Clicked) {
                    tueButton1300.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(8, '1');
                    tueBitString = modifiedBitString.toString();
                } else {
                    tueButton1300.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(8, '0');
                    tueBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        tueButton1330.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tueButton1330Clicked = !tueButton1330Clicked;
                if (tueButton1330Clicked) {
                    tueButton1330.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(9, '1');
                    tueBitString = modifiedBitString.toString();
                } else {
                    tueButton1330.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(9, '0');
                    tueBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        tueButton1400.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tueButton1400Clicked = !tueButton1400Clicked;
                if (tueButton1400Clicked) {
                    tueButton1400.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(10, '1');
                    tueBitString = modifiedBitString.toString();
                } else {
                    tueButton1400.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(10, '0');
                    tueBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        tueButton1430.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tueButton1430Clicked = !tueButton1430Clicked;
                if (tueButton1430Clicked) {
                    tueButton1430.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(11, '1');
                    tueBitString = modifiedBitString.toString();
                } else {
                    tueButton1430.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(11, '0');
                    tueBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        tueButton1500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tueButton1500Clicked = !tueButton1500Clicked;
                if (tueButton1500Clicked) {
                    tueButton1500.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(12, '1');
                    tueBitString = modifiedBitString.toString();
                } else {
                    tueButton1500.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(12, '0');
                    tueBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        tueButton1530.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tueButton1530Clicked = !tueButton1530Clicked;
                if (tueButton1530Clicked) {
                    tueButton1530.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(13, '1');
                    tueBitString = modifiedBitString.toString();
                } else {
                    tueButton1530.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(13, '0');
                    tueBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        tueButton1600.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tueButton1600Clicked = !tueButton1600Clicked;
                if (tueButton1600Clicked) {
                    tueButton1600.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(14, '1');
                    tueBitString = modifiedBitString.toString();
                } else {
                    tueButton1600.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(14, '0');
                    tueBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        tueButton1630.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tueButton1630Clicked = !tueButton1630Clicked;
                if (tueButton1630Clicked) {
                    tueButton1630.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(15, '1');
                    tueBitString = modifiedBitString.toString();
                } else {
                    tueButton1630.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(15, '0');
                    tueBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        tueButton1700.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tueButton1700Clicked = !tueButton1700Clicked;
                if (tueButton1700Clicked) {
                    tueButton1700.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(16, '1');
                    tueBitString = modifiedBitString.toString();
                } else {
                    tueButton1700.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(16, '0');
                    tueBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        tueButton1730.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tueButton1730Clicked = !tueButton1730Clicked;
                if (tueButton1730Clicked) {
                    tueButton1730.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(17, '1');
                    tueBitString = modifiedBitString.toString();
                } else {
                    tueButton1730.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(tueBitString);
                    modifiedBitString.setCharAt(17, '0');
                    tueBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        wedButton0900.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wedButton0900Clicked = !wedButton0900Clicked;
                if(wedButton0900Clicked) {
                    wedButton0900.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(0, '1');
                    wedBitString = modifiedBitString.toString();
                } else {
                    wedButton0900.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(0, '0');
                    wedBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        wedButton0930.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wedButton0930Clicked = !wedButton0930Clicked;
                if(wedButton0930Clicked) {
                    wedButton0930.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(1, '1');
                    wedBitString = modifiedBitString.toString();
                } else {
                    wedButton0930.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(1, '0');
                    wedBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        wedButton1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wedButton1000Clicked = !wedButton1000Clicked;
                if (wedButton1000Clicked) {
                    wedButton1000.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(2, '1');
                    wedBitString = modifiedBitString.toString();
                } else {
                    wedButton1000.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(2, '0');
                    wedBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        wedButton1030.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wedButton1030Clicked = !wedButton1030Clicked;
                if (wedButton1030Clicked) {
                    wedButton1030.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(3, '1');
                    wedBitString = modifiedBitString.toString();
                } else {
                    wedButton1030.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(3, '0');
                    wedBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        wedButton1100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wedButton1100Clicked = !wedButton1100Clicked;
                if (wedButton1100Clicked) {
                    wedButton1100.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(4, '1');
                    wedBitString = modifiedBitString.toString();
                } else {
                    wedButton1100.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(4, '0');
                    wedBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        wedButton1130.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wedButton1130Clicked = !wedButton1130Clicked;
                if (wedButton1130Clicked) {
                    wedButton1130.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(5, '1');
                    wedBitString = modifiedBitString.toString();
                } else {
                    wedButton1130.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(5, '0');
                    wedBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        wedButton1200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wedButton1200Clicked = !wedButton1200Clicked;
                if (wedButton1200Clicked) {
                    wedButton1200.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(6, '1');
                    wedBitString = modifiedBitString.toString();
                } else {
                    wedButton1200.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(6, '0');
                    wedBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        wedButton1230.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wedButton1230Clicked = !wedButton1230Clicked;
                if (wedButton1230Clicked) {
                    wedButton1230.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(7, '1');
                    wedBitString = modifiedBitString.toString();
                } else {
                    wedButton1230.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(7, '0');
                    wedBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        wedButton1300.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wedButton1300Clicked = !wedButton1300Clicked;
                if (wedButton1300Clicked) {
                    wedButton1300.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(8, '1');
                    wedBitString = modifiedBitString.toString();
                } else {
                    wedButton1300.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(8, '0');
                    wedBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        wedButton1330.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wedButton1330Clicked = !wedButton1330Clicked;
                if (wedButton1330Clicked) {
                    wedButton1330.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(9, '1');
                    wedBitString = modifiedBitString.toString();
                } else {
                    wedButton1330.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(9, '0');
                    wedBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        wedButton1400.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wedButton1400Clicked = !wedButton1400Clicked;
                if (wedButton1400Clicked) {
                    wedButton1400.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(10, '1');
                    wedBitString = modifiedBitString.toString();
                } else {
                    wedButton1400.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(10, '0');
                    wedBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        wedButton1430.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wedButton1430Clicked = !wedButton1430Clicked;
                if (wedButton1430Clicked) {
                    wedButton1430.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(11, '1');
                    wedBitString = modifiedBitString.toString();
                } else {
                    wedButton1430.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(11, '0');
                    wedBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        wedButton1500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wedButton1500Clicked = !wedButton1500Clicked;
                if (wedButton1500Clicked) {
                    wedButton1500.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(12, '1');
                    wedBitString = modifiedBitString.toString();
                } else {
                    wedButton1500.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(12, '0');
                    wedBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        wedButton1530.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wedButton1530Clicked = !wedButton1530Clicked;
                if (wedButton1530Clicked) {
                    wedButton1530.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(13, '1');
                    wedBitString = modifiedBitString.toString();
                } else {
                    wedButton1530.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(13, '0');
                    wedBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        wedButton1600.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wedButton1600Clicked = !wedButton1600Clicked;
                if (wedButton1600Clicked) {
                    wedButton1600.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(14, '1');
                    wedBitString = modifiedBitString.toString();
                } else {
                    wedButton1600.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(14, '0');
                    wedBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        wedButton1630.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wedButton1630Clicked = !wedButton1630Clicked;
                if (wedButton1630Clicked) {
                    wedButton1630.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(15, '1');
                    wedBitString = modifiedBitString.toString();
                } else {
                    wedButton1630.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(15, '0');
                    wedBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        wedButton1700.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wedButton1700Clicked = !wedButton1700Clicked;
                if (wedButton1700Clicked) {
                    wedButton1700.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(16, '1');
                    wedBitString = modifiedBitString.toString();
                } else {
                    wedButton1700.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(16, '0');
                    wedBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        wedButton1730.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wedButton1730Clicked = !wedButton1730Clicked;
                if (wedButton1730Clicked) {
                    wedButton1730.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(17, '1');
                    wedBitString = modifiedBitString.toString();
                } else {
                    wedButton1730.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(wedBitString);
                    modifiedBitString.setCharAt(17, '0');
                    wedBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        thuButton0900.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thuButton0900Clicked = !thuButton0900Clicked;
                if(thuButton0900Clicked) {
                    thuButton0900.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(0, '1');
                    thuBitString = modifiedBitString.toString();
                } else {
                    thuButton0900.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(0, '0');
                    thuBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        thuButton0930.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thuButton0930Clicked = !thuButton0930Clicked;
                if(thuButton0930Clicked) {
                    thuButton0930.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(1, '1');
                    thuBitString = modifiedBitString.toString();
                } else {
                    thuButton0930.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(1, '0');
                    thuBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        thuButton1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thuButton1000Clicked = !thuButton1000Clicked;
                if (thuButton1000Clicked) {
                    thuButton1000.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(2, '1');
                    thuBitString = modifiedBitString.toString();
                } else {
                    thuButton1000.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(2, '0');
                    thuBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        thuButton1030.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thuButton1030Clicked = !thuButton1030Clicked;
                if (thuButton1030Clicked) {
                    thuButton1030.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(3, '1');
                    thuBitString = modifiedBitString.toString();
                } else {
                    thuButton1030.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(3, '0');
                    thuBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        thuButton1100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thuButton1100Clicked = !thuButton1100Clicked;
                if (thuButton1100Clicked) {
                    thuButton1100.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(4, '1');
                    thuBitString = modifiedBitString.toString();
                } else {
                    thuButton1100.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(4, '0');
                    thuBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        thuButton1130.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thuButton1130Clicked = !thuButton1130Clicked;
                if (thuButton1130Clicked) {
                    thuButton1130.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(5, '1');
                    thuBitString = modifiedBitString.toString();
                } else {
                    thuButton1130.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(5, '0');
                    thuBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        thuButton1200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thuButton1200Clicked = !thuButton1200Clicked;
                if (thuButton1200Clicked) {
                    thuButton1200.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(6, '1');
                    thuBitString = modifiedBitString.toString();
                } else {
                    thuButton1200.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(6, '0');
                    thuBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        thuButton1230.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thuButton1230Clicked = !thuButton1230Clicked;
                if (thuButton1230Clicked) {
                    thuButton1230.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(7, '1');
                    thuBitString = modifiedBitString.toString();
                } else {
                    thuButton1230.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(7, '0');
                    thuBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        thuButton1300.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thuButton1300Clicked = !thuButton1300Clicked;
                if (thuButton1300Clicked) {
                    thuButton1300.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(8, '1');
                    thuBitString = modifiedBitString.toString();
                } else {
                    thuButton1300.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(8, '0');
                    thuBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        thuButton1330.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thuButton1330Clicked = !thuButton1330Clicked;
                if (thuButton1330Clicked) {
                    thuButton1330.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(9, '1');
                    thuBitString = modifiedBitString.toString();
                } else {
                    thuButton1330.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(9, '0');
                    thuBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        thuButton1400.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thuButton1400Clicked = !thuButton1400Clicked;
                if (thuButton1400Clicked) {
                    thuButton1400.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(10, '1');
                    thuBitString = modifiedBitString.toString();
                } else {
                    thuButton1400.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(10, '0');
                    thuBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        thuButton1430.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thuButton1430Clicked = !thuButton1430Clicked;
                if (thuButton1430Clicked) {
                    thuButton1430.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(11, '1');
                    thuBitString = modifiedBitString.toString();
                } else {
                    thuButton1430.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(11, '0');
                    thuBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        thuButton1500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thuButton1500Clicked = !thuButton1500Clicked;
                if (thuButton1500Clicked) {
                    thuButton1500.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(12, '1');
                    thuBitString = modifiedBitString.toString();
                } else {
                    thuButton1500.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(12, '0');
                    thuBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        thuButton1530.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thuButton1530Clicked = !thuButton1530Clicked;
                if (thuButton1530Clicked) {
                    thuButton1530.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(13, '1');
                    thuBitString = modifiedBitString.toString();
                } else {
                    thuButton1530.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(13, '0');
                    thuBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        thuButton1600.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thuButton1600Clicked = !thuButton1600Clicked;
                if (thuButton1600Clicked) {
                    thuButton1600.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(14, '1');
                    thuBitString = modifiedBitString.toString();
                } else {
                    thuButton1600.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(14, '0');
                    thuBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        thuButton1630.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thuButton1630Clicked = !thuButton1630Clicked;
                if (thuButton1630Clicked) {
                    thuButton1630.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(15, '1');
                    thuBitString = modifiedBitString.toString();
                } else {
                    thuButton1630.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(15, '0');
                    thuBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        thuButton1700.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thuButton1700Clicked = !thuButton1700Clicked;
                if (thuButton1700Clicked) {
                    thuButton1700.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(16, '1');
                    thuBitString = modifiedBitString.toString();
                } else {
                    thuButton1700.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(16, '0');
                    thuBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        thuButton1730.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thuButton1730Clicked = !thuButton1730Clicked;
                if (thuButton1730Clicked) {
                    thuButton1730.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(17, '1');
                    thuBitString = modifiedBitString.toString();
                } else {
                    thuButton1730.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(thuBitString);
                    modifiedBitString.setCharAt(17, '0');
                    thuBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        friButton0900.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friButton0900Clicked = !friButton0900Clicked;
                if(friButton0900Clicked) {
                    friButton0900.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(0, '1');
                    friBitString = modifiedBitString.toString();
                } else {
                    friButton0900.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(0, '0');
                    friBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        friButton0930.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friButton0930Clicked = !friButton0930Clicked;
                if(friButton0930Clicked) {
                    friButton0930.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(1, '1');
                    friBitString = modifiedBitString.toString();
                } else {
                    friButton0930.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(1, '0');
                    friBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        friButton1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friButton1000Clicked = !friButton1000Clicked;
                if (friButton1000Clicked) {
                    friButton1000.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(2, '1');
                    friBitString = modifiedBitString.toString();
                } else {
                    friButton1000.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(2, '0');
                    friBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        friButton1030.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friButton1030Clicked = !friButton1030Clicked;
                if (friButton1030Clicked) {
                    friButton1030.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(3, '1');
                    friBitString = modifiedBitString.toString();
                } else {
                    friButton1030.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(3, '0');
                    friBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        friButton1100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friButton1100Clicked = !friButton1100Clicked;
                if (friButton1100Clicked) {
                    friButton1100.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(4, '1');
                    friBitString = modifiedBitString.toString();
                } else {
                    friButton1100.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(4, '0');
                    friBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        friButton1130.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friButton1130Clicked = !friButton1130Clicked;
                if (friButton1130Clicked) {
                    friButton1130.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(5, '1');
                    friBitString = modifiedBitString.toString();
                } else {
                    friButton1130.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(5, '0');
                    friBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        friButton1200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friButton1200Clicked = !friButton1200Clicked;
                if (friButton1200Clicked) {
                    friButton1200.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(6, '1');
                    friBitString = modifiedBitString.toString();
                } else {
                    friButton1200.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(6, '0');
                    friBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        friButton1230.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friButton1230Clicked = !friButton1230Clicked;
                if (friButton1230Clicked) {
                    friButton1230.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(7, '1');
                    friBitString = modifiedBitString.toString();
                } else {
                    friButton1230.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(7, '0');
                    friBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        friButton1300.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friButton1300Clicked = !friButton1300Clicked;
                if (friButton1300Clicked) {
                    friButton1300.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(8, '1');
                    friBitString = modifiedBitString.toString();
                } else {
                    friButton1300.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(8, '0');
                    friBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        friButton1330.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friButton1330Clicked = !friButton1330Clicked;
                if (friButton1330Clicked) {
                    friButton1330.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(9, '1');
                    friBitString = modifiedBitString.toString();
                } else {
                    friButton1330.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(9, '0');
                    friBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        friButton1400.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friButton1400Clicked = !friButton1400Clicked;
                if (friButton1400Clicked) {
                    friButton1400.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(10, '1');
                    friBitString = modifiedBitString.toString();
                } else {
                    friButton1400.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(10, '0');
                    friBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        friButton1430.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friButton1430Clicked = !friButton1430Clicked;
                if (friButton1430Clicked) {
                    friButton1430.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(11, '1');
                    friBitString = modifiedBitString.toString();
                } else {
                    friButton1430.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(11, '0');
                    friBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        friButton1500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friButton1500Clicked = !friButton1500Clicked;
                if (friButton1500Clicked) {
                    friButton1500.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(12, '1');
                    friBitString = modifiedBitString.toString();
                } else {
                    friButton1500.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(12, '0');
                    friBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        friButton1530.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friButton1530Clicked = !friButton1530Clicked;
                if (friButton1530Clicked) {
                    friButton1530.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(13, '1');
                    friBitString = modifiedBitString.toString();
                } else {
                    friButton1530.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(13, '0');
                    friBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        friButton1600.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friButton1600Clicked = !friButton1600Clicked;
                if (friButton1600Clicked) {
                    friButton1600.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(14, '1');
                    friBitString = modifiedBitString.toString();
                } else {
                    friButton1600.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(14, '0');
                    friBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        friButton1630.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friButton1630Clicked = !friButton1630Clicked;
                if (friButton1630Clicked) {
                    friButton1630.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(15, '1');
                    friBitString = modifiedBitString.toString();
                } else {
                    friButton1630.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(15, '0');
                    friBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        friButton1700.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friButton1700Clicked = !friButton1700Clicked;
                if (friButton1700Clicked) {
                    friButton1700.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(16, '1');
                    friBitString = modifiedBitString.toString();
                } else {
                    friButton1700.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(16, '0');
                    friBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

        friButton1730.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                friButton1730Clicked = !friButton1730Clicked;
                if (friButton1730Clicked) {
                    friButton1730.setBackgroundResource(R.drawable.unavailabletimebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(17, '1');
                    friBitString = modifiedBitString.toString();
                } else {
                    friButton1730.setBackgroundResource(R.drawable.timebutton);
                    StringBuilder modifiedBitString = new StringBuilder(friBitString);
                    modifiedBitString.setCharAt(17, '0');
                    friBitString = modifiedBitString.toString();
                }//syncNextButtonState();
            }
        });

    }


    private void showMonTimeButtons() {
        monButton0900.setVisibility(View.VISIBLE);
        monButton0930.setVisibility(View.VISIBLE);
        monButton1000.setVisibility(View.VISIBLE);
        monButton1030.setVisibility(View.VISIBLE);
        monButton1100.setVisibility(View.VISIBLE);
        monButton1130.setVisibility(View.VISIBLE);
        monButton1200.setVisibility(View.VISIBLE);
        monButton1230.setVisibility(View.VISIBLE);
        monButton1300.setVisibility(View.VISIBLE);
        monButton1330.setVisibility(View.VISIBLE);
        monButton1400.setVisibility(View.VISIBLE);
        monButton1430.setVisibility(View.VISIBLE);
        monButton1500.setVisibility(View.VISIBLE);
        monButton1530.setVisibility(View.VISIBLE);
        monButton1600.setVisibility(View.VISIBLE);
        monButton1630.setVisibility(View.VISIBLE);
        monButton1700.setVisibility(View.VISIBLE);
        monButton1730.setVisibility(View.VISIBLE);
    }

    private void hideMonTimeButtons() {
        monButton0900.setVisibility(View.INVISIBLE);
        monButton0930.setVisibility(View.INVISIBLE);
        monButton1000.setVisibility(View.INVISIBLE);
        monButton1030.setVisibility(View.INVISIBLE);
        monButton1100.setVisibility(View.INVISIBLE);
        monButton1130.setVisibility(View.INVISIBLE);
        monButton1200.setVisibility(View.INVISIBLE);
        monButton1230.setVisibility(View.INVISIBLE);
        monButton1300.setVisibility(View.INVISIBLE);
        monButton1330.setVisibility(View.INVISIBLE);
        monButton1400.setVisibility(View.INVISIBLE);
        monButton1430.setVisibility(View.INVISIBLE);
        monButton1500.setVisibility(View.INVISIBLE);
        monButton1530.setVisibility(View.INVISIBLE);
        monButton1600.setVisibility(View.INVISIBLE);
        monButton1630.setVisibility(View.INVISIBLE);
        monButton1700.setVisibility(View.INVISIBLE);
        monButton1730.setVisibility(View.INVISIBLE);
    }

    private void showTueTimeButtons() {
        tueButton0900.setVisibility(View.VISIBLE);
        tueButton0930.setVisibility(View.VISIBLE);
        tueButton1000.setVisibility(View.VISIBLE);
        tueButton1030.setVisibility(View.VISIBLE);
        tueButton1100.setVisibility(View.VISIBLE);
        tueButton1130.setVisibility(View.VISIBLE);
        tueButton1200.setVisibility(View.VISIBLE);
        tueButton1230.setVisibility(View.VISIBLE);
        tueButton1300.setVisibility(View.VISIBLE);
        tueButton1330.setVisibility(View.VISIBLE);
        tueButton1400.setVisibility(View.VISIBLE);
        tueButton1430.setVisibility(View.VISIBLE);
        tueButton1500.setVisibility(View.VISIBLE);
        tueButton1530.setVisibility(View.VISIBLE);
        tueButton1600.setVisibility(View.VISIBLE);
        tueButton1630.setVisibility(View.VISIBLE);
        tueButton1700.setVisibility(View.VISIBLE);
        tueButton1730.setVisibility(View.VISIBLE);
    }

    private void hideTueTimeButtons() {
        tueButton0900.setVisibility(View.INVISIBLE);
        tueButton0930.setVisibility(View.INVISIBLE);
        tueButton1000.setVisibility(View.INVISIBLE);
        tueButton1030.setVisibility(View.INVISIBLE);
        tueButton1100.setVisibility(View.INVISIBLE);
        tueButton1130.setVisibility(View.INVISIBLE);
        tueButton1200.setVisibility(View.INVISIBLE);
        tueButton1230.setVisibility(View.INVISIBLE);
        tueButton1300.setVisibility(View.INVISIBLE);
        tueButton1330.setVisibility(View.INVISIBLE);
        tueButton1400.setVisibility(View.INVISIBLE);
        tueButton1430.setVisibility(View.INVISIBLE);
        tueButton1500.setVisibility(View.INVISIBLE);
        tueButton1530.setVisibility(View.INVISIBLE);
        tueButton1600.setVisibility(View.INVISIBLE);
        tueButton1630.setVisibility(View.INVISIBLE);
        tueButton1700.setVisibility(View.INVISIBLE);
        tueButton1730.setVisibility(View.INVISIBLE);
    }


    private void showWedTimeButtons() {
        wedButton0900.setVisibility(View.VISIBLE);
        wedButton0930.setVisibility(View.VISIBLE);
        wedButton1000.setVisibility(View.VISIBLE);
        wedButton1030.setVisibility(View.VISIBLE);
        wedButton1100.setVisibility(View.VISIBLE);
        wedButton1130.setVisibility(View.VISIBLE);
        wedButton1200.setVisibility(View.VISIBLE);
        wedButton1230.setVisibility(View.VISIBLE);
        wedButton1300.setVisibility(View.VISIBLE);
        wedButton1330.setVisibility(View.VISIBLE);
        wedButton1400.setVisibility(View.VISIBLE);
        wedButton1430.setVisibility(View.VISIBLE);
        wedButton1500.setVisibility(View.VISIBLE);
        wedButton1530.setVisibility(View.VISIBLE);
        wedButton1600.setVisibility(View.VISIBLE);
        wedButton1630.setVisibility(View.VISIBLE);
        wedButton1700.setVisibility(View.VISIBLE);
        wedButton1730.setVisibility(View.VISIBLE);
    }

    private void hideWedTimeButtons() {
        wedButton0900.setVisibility(View.INVISIBLE);
        wedButton0930.setVisibility(View.INVISIBLE);
        wedButton1000.setVisibility(View.INVISIBLE);
        wedButton1030.setVisibility(View.INVISIBLE);
        wedButton1100.setVisibility(View.INVISIBLE);
        wedButton1130.setVisibility(View.INVISIBLE);
        wedButton1200.setVisibility(View.INVISIBLE);
        wedButton1230.setVisibility(View.INVISIBLE);
        wedButton1300.setVisibility(View.INVISIBLE);
        wedButton1330.setVisibility(View.INVISIBLE);
        wedButton1400.setVisibility(View.INVISIBLE);
        wedButton1430.setVisibility(View.INVISIBLE);
        wedButton1500.setVisibility(View.INVISIBLE);
        wedButton1530.setVisibility(View.INVISIBLE);
        wedButton1600.setVisibility(View.INVISIBLE);
        wedButton1630.setVisibility(View.INVISIBLE);
        wedButton1700.setVisibility(View.INVISIBLE);
        wedButton1730.setVisibility(View.INVISIBLE);
    }


    private void showThuTimeButtons() {
        thuButton0900.setVisibility(View.VISIBLE);
        thuButton0930.setVisibility(View.VISIBLE);
        thuButton1000.setVisibility(View.VISIBLE);
        thuButton1030.setVisibility(View.VISIBLE);
        thuButton1100.setVisibility(View.VISIBLE);
        thuButton1130.setVisibility(View.VISIBLE);
        thuButton1200.setVisibility(View.VISIBLE);
        thuButton1230.setVisibility(View.VISIBLE);
        thuButton1300.setVisibility(View.VISIBLE);
        thuButton1330.setVisibility(View.VISIBLE);
        thuButton1400.setVisibility(View.VISIBLE);
        thuButton1430.setVisibility(View.VISIBLE);
        thuButton1500.setVisibility(View.VISIBLE);
        thuButton1530.setVisibility(View.VISIBLE);
        thuButton1600.setVisibility(View.VISIBLE);
        thuButton1630.setVisibility(View.VISIBLE);
        thuButton1700.setVisibility(View.VISIBLE);
        thuButton1730.setVisibility(View.VISIBLE);
    }

    private void hideThuTimeButtons() {
        thuButton0900.setVisibility(View.INVISIBLE);
        thuButton0930.setVisibility(View.INVISIBLE);
        thuButton1000.setVisibility(View.INVISIBLE);
        thuButton1030.setVisibility(View.INVISIBLE);
        thuButton1100.setVisibility(View.INVISIBLE);
        thuButton1130.setVisibility(View.INVISIBLE);
        thuButton1200.setVisibility(View.INVISIBLE);
        thuButton1230.setVisibility(View.INVISIBLE);
        thuButton1300.setVisibility(View.INVISIBLE);
        thuButton1330.setVisibility(View.INVISIBLE);
        thuButton1400.setVisibility(View.INVISIBLE);
        thuButton1430.setVisibility(View.INVISIBLE);
        thuButton1500.setVisibility(View.INVISIBLE);
        thuButton1530.setVisibility(View.INVISIBLE);
        thuButton1600.setVisibility(View.INVISIBLE);
        thuButton1630.setVisibility(View.INVISIBLE);
        thuButton1700.setVisibility(View.INVISIBLE);
        thuButton1730.setVisibility(View.INVISIBLE);
    }


    private void showFriTimeButtons() {
        friButton0900.setVisibility(View.VISIBLE);
        friButton0930.setVisibility(View.VISIBLE);
        friButton1000.setVisibility(View.VISIBLE);
        friButton1030.setVisibility(View.VISIBLE);
        friButton1100.setVisibility(View.VISIBLE);
        friButton1130.setVisibility(View.VISIBLE);
        friButton1200.setVisibility(View.VISIBLE);
        friButton1230.setVisibility(View.VISIBLE);
        friButton1300.setVisibility(View.VISIBLE);
        friButton1330.setVisibility(View.VISIBLE);
        friButton1400.setVisibility(View.VISIBLE);
        friButton1430.setVisibility(View.VISIBLE);
        friButton1500.setVisibility(View.VISIBLE);
        friButton1530.setVisibility(View.VISIBLE);
        friButton1600.setVisibility(View.VISIBLE);
        friButton1630.setVisibility(View.VISIBLE);
        friButton1700.setVisibility(View.VISIBLE);
        friButton1730.setVisibility(View.VISIBLE);
    }

    private void hideFriTimeButtons() {
        friButton0900.setVisibility(View.INVISIBLE);
        friButton0930.setVisibility(View.INVISIBLE);
        friButton1000.setVisibility(View.INVISIBLE);
        friButton1030.setVisibility(View.INVISIBLE);
        friButton1100.setVisibility(View.INVISIBLE);
        friButton1130.setVisibility(View.INVISIBLE);
        friButton1200.setVisibility(View.INVISIBLE);
        friButton1230.setVisibility(View.INVISIBLE);
        friButton1300.setVisibility(View.INVISIBLE);
        friButton1330.setVisibility(View.INVISIBLE);
        friButton1400.setVisibility(View.INVISIBLE);
        friButton1430.setVisibility(View.INVISIBLE);
        friButton1500.setVisibility(View.INVISIBLE);
        friButton1530.setVisibility(View.INVISIBLE);
        friButton1600.setVisibility(View.INVISIBLE);
        friButton1630.setVisibility(View.INVISIBLE);
        friButton1700.setVisibility(View.INVISIBLE);
        friButton1730.setVisibility(View.INVISIBLE);
    }


    private void updateHideState() {
        // 마지막에 클릭된 버튼이 있으면 원래대로 돌려놓음
        if (lastClickedDayButton != null) {
            lastClickedDayButton.setBackgroundResource(R.drawable.daybutton);
            lastClickedDayButton.setTextColor(getResources().getColor(android.R.color.black));
            if (lastClickedDayButton == monButton) {
                hideMonTimeButtons();
            } else if (lastClickedDayButton == tueButton) {
                hideTueTimeButtons();
            } else if (lastClickedDayButton == wedButton) {
                hideWedTimeButtons();
            } else if (lastClickedDayButton == thuButton) {
                hideThuTimeButtons();
            } else if (lastClickedDayButton == friButton) {
                hideFriTimeButtons();
            }

        }
    }

    private void updateShowState(Button clickedButton) {
        // 현재 클릭한 버튼의 상태를 변경
        clickedButton.setBackgroundResource(R.drawable.dayclickedbutton);
        clickedButton.setTextColor(getResources().getColor(android.R.color.white));
        if (clickedButton == monButton) {
            showMonTimeButtons();
        } else if (clickedButton == tueButton) {
            showTueTimeButtons();
        } else if (clickedButton == wedButton) {
            showWedTimeButtons();
        } else if (clickedButton == thuButton) {
            showThuTimeButtons();
        } else if (clickedButton == friButton) {
            showFriTimeButtons();
        }
    }

//    private void syncNextButtonState() {
//        if (monButton0900Clicked || monButton0930Clicked || monButton1000Clicked
//                || monButton1030Clicked || monButton1100Clicked || monButton1130Clicked
//                || monButton1200Clicked || monButton1230Clicked || monButton1300Clicked
//                || monButton1330Clicked || monButton1400Clicked || monButton1430Clicked
//                || monButton1500Clicked || monButton1530Clicked || monButton1600Clicked
//                || monButton1630Clicked || monButton1700Clicked || monButton1730Clicked
//                || tueButton0900Clicked || tueButton0930Clicked || tueButton1000Clicked
//                || tueButton1030Clicked || tueButton1100Clicked || tueButton1130Clicked
//                || tueButton1200Clicked || tueButton1230Clicked || tueButton1300Clicked
//                || tueButton1330Clicked || tueButton1400Clicked || tueButton1430Clicked
//                || tueButton1500Clicked || tueButton1530Clicked || tueButton1600Clicked
//                || tueButton1630Clicked || tueButton1700Clicked || tueButton1730Clicked
//                || wedButton0900Clicked || wedButton0930Clicked || wedButton1000Clicked
//                || wedButton1100Clicked || wedButton1130Clicked || wedButton1200Clicked
//                || wedButton1230Clicked || wedButton1300Clicked || wedButton1330Clicked
//                || wedButton1400Clicked || wedButton1430Clicked || wedButton1500Clicked
//                || wedButton1530Clicked || wedButton1600Clicked || wedButton1630Clicked
//                || wedButton1700Clicked || wedButton1730Clicked || wedButton1030Clicked
//                || thuButton0900Clicked || thuButton0930Clicked || thuButton1000Clicked
//                || thuButton1030Clicked || thuButton1100Clicked || thuButton1130Clicked
//                || thuButton1200Clicked || thuButton1230Clicked || thuButton1300Clicked
//                || thuButton1330Clicked || thuButton1400Clicked || thuButton1430Clicked
//                || thuButton1500Clicked || thuButton1530Clicked || thuButton1600Clicked
//                || thuButton1630Clicked || thuButton1700Clicked || thuButton1730Clicked
//                || friButton0900Clicked || friButton0930Clicked || friButton1000Clicked
//                || friButton1030Clicked || friButton1100Clicked || friButton1130Clicked
//                || friButton1200Clicked || friButton1230Clicked || friButton1300Clicked
//                || friButton1330Clicked || friButton1400Clicked || friButton1430Clicked
//                || friButton1500Clicked || friButton1530Clicked || friButton1600Clicked
//                || friButton1630Clicked || friButton1700Clicked || friButton1730Clicked) {
//            // 하나라도 버튼이 클릭된 경우
//            nextButton.setEnabled(true);
//            nextButton.setBackgroundResource(R.drawable.availablenextbutton);
//        } else {
//            // 모든 버튼이 클릭되지 않은 경우
//            nextButton.setEnabled(false);
//            nextButton.setBackgroundResource(R.drawable.unavailablenextbutton);
//        }
//    }

    private void showCompleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 사용자 정의 레이아웃을 설정
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog2, null);
        builder.setView(dialogView);

        final AlertDialog dialog = builder.create();
        // 사용자 정의 레이아웃 내부의 뷰를 참조
        TextView confirmTextView = dialogView.findViewById(R.id.confirmTextView);
        Button yesButton = dialogView.findViewById(R.id.yesButton);

        // 메시지 설정
        confirmTextView.setText("저장되었습니다.");

        // "예" 버튼 동작 설정
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    //public boolean onOptionsItemSelected(MenuItem item){
        //switch(item.getItemId()){
            //case android.R.id.home:{
                //finish();
                //return true;
            //}
        //}
        //return super.onOptionsItemSelected(item);
    //}


}







