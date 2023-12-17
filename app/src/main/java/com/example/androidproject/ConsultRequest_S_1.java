package com.example.androidproject;

import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.app.Reservation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ConsultRequest_S_1 extends AppCompatActivity {
    ImageButton backBtn;
    private Calendar selectedCalendar;
    private Calendar currentCalendar;
    private int selectedDate_year;
    private int selectedDate_month;
    private int selectedDate_day;
    private float selectedTime;
    private String selectedDate_week;
    private CalendarView calendarView;
    private Button button0900;
    private Button button0930;
    private Button button1000;
    private Button button1030;
    private Button button1100;
    private Button button1130;
    private Button button1200;
    private Button button1230;
    private Button button1300;
    private Button button1330;
    private Button button1400;
    private Button button1430;
    private Button button1500;
    private Button button1530;
    private Button button1600;
    private Button button1630;
    private Button button1700;
    private Button button1730;
    private Button lastClickedButton;
    private Button nextButton;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference2;
    private DatabaseReference databaseReference3;
    private String monValue;
    private String tueValue;
    private String wedValue;
    private String thuValue;
    private String friValue;
    private List<MonthlyData> monthsList = new ArrayList<>();
    private List<MonthlyData2> monthsList2 = new ArrayList<>();

    private String StringMonth;
    private String StringDay;
    private String StringTime;




    private boolean button0900Clicked = false;
    private boolean button0930Clicked = false;
    private boolean button1000Clicked = false;
    private boolean button1030Clicked = false;
    private boolean button1100Clicked = false;
    private boolean button1130Clicked = false;
    private boolean button1200Clicked = false;
    private boolean button1230Clicked = false;
    private boolean button1300Clicked = false;
    private boolean button1330Clicked = false;
    private boolean button1400Clicked = false;
    private boolean button1430Clicked = false;
    private boolean button1500Clicked = false;
    private boolean button1530Clicked = false;
    private boolean button1600Clicked = false;
    private boolean button1630Clicked = false;
    private boolean button1700Clicked = false;
    private boolean button1730Clicked = false;
    private boolean lastButtonisClicked;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultrequest_s_1);

        databaseReference = FirebaseDatabase.getInstance().getReference("2021145818/Timetable/fixation");
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
                        monValue = value;
                    }
                    else if("tuesday".equals(key)){
                        tueValue = value;
                    }
                    else if("wednesday".equals(key)){
                        wedValue = value;
                    }
                    else if("thursday".equals(key)){
                        thuValue = value;
                    }
                    else if("friday".equals(key)){
                        friValue = value;
                    }


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // 데이터 읽기가 취소되었을 때 호출됩니다.
                Log.e("MainActivity", "Error: " + databaseError.getMessage());
            }
        });

        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("2021145818/Timetable/fluctuations");
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // dataSnapshot에 "fluctuations" 노드의 데이터가 통째로 들어있습니다.
                for (DataSnapshot monthSnapshot : dataSnapshot.getChildren()) {
                    // 각 월에 대한 노드에 접근할 수 있습니다.
                    String month = monthSnapshot.getKey(); // 월을 가져옴 (예: "11", "12")
                    List<String> monthData = new ArrayList<>();



                    // 각 월에 있는 "d1"부터 "d31"까지의 데이터에 접근합니다.
                    for (DataSnapshot daySnapshot : monthSnapshot.getChildren()) {


                        // 여기에서 데이터를 사용할 수 있습니다.
                        String data = daySnapshot.getValue(String.class);
                        // 해당 월과 일에 대한 데이터를 사용합니다.
                        monthData.add(data);

                    }
                    MonthlyData monthlyData = new MonthlyData(month, monthData);
                    monthsList.add(monthlyData);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // 데이터 읽기가 취소되면 호출됩니다.
            }
        });

        databaseReference3 = FirebaseDatabase.getInstance().getReference().child("2021145818/Timetable/fluctuations2");

        databaseReference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // dataSnapshot에 "fluctuations" 노드의 데이터가 통째로 들어있습니다.
                for (DataSnapshot monthSnapshot : dataSnapshot.getChildren()) {
                    // 각 월에 대한 노드에 접근할 수 있습니다.
                    String month2 = monthSnapshot.getKey(); // 월을 가져옴 (예: "11", "12")
                    List<String> monthData2 = new ArrayList<>();



                    // 각 월에 있는 "d1"부터 "d31"까지의 데이터에 접근합니다.
                    for (DataSnapshot daySnapshot : monthSnapshot.getChildren()) {


                        // 여기에서 데이터를 사용할 수 있습니다.
                        String data = daySnapshot.getValue(String.class);
                        // 해당 월과 일에 대한 데이터를 사용합니다.
                        monthData2.add(data);

                    }
                    MonthlyData2 monthlyData2 = new MonthlyData2(month2, monthData2);
                    monthsList2.add(monthlyData2);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // 데이터 읽기가 취소되면 호출됩니다.
            }
        });


        //Toolbar record_toolbar=findViewById(R.id.toolbar);
        //record_toolbar.setTitleTextColor(Color.WHITE);
        //setSupportActionBar(record_toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("상담 신청");

        backBtn = findViewById(R.id.backBtn);
        calendarView = findViewById(R.id.calendarView);
        nextButton = findViewById(R.id.nextButton);
        button0900 = findViewById(R.id.button0900);
        button0930 = findViewById(R.id.button0930);
        button1000 = findViewById(R.id.button1000);
        button1030 = findViewById(R.id.button1030);
        button1100 = findViewById(R.id.button1100);
        button1130 = findViewById(R.id.button1130);
        button1200 = findViewById(R.id.button1200);
        button1230 = findViewById(R.id.button1230);
        button1300 = findViewById(R.id.button1300);
        button1330 = findViewById(R.id.button1330);
        button1400 = findViewById(R.id.button1400);
        button1430 = findViewById(R.id.button1430);
        button1500 = findViewById(R.id.button1500);
        button1530 = findViewById(R.id.button1530);
        button1600 = findViewById(R.id.button1600);
        button1630 = findViewById(R.id.button1630);
        button1700 = findViewById(R.id.button1700);
        button1730 = findViewById(R.id.button1730);

        button0900.setVisibility(View.INVISIBLE);
        button0930.setVisibility(View.INVISIBLE);
        button1000.setVisibility(View.INVISIBLE);
        button1030.setVisibility(View.INVISIBLE);
        button1100.setVisibility(View.INVISIBLE);
        button1130.setVisibility(View.INVISIBLE);
        button1200.setVisibility(View.INVISIBLE);
        button1230.setVisibility(View.INVISIBLE);
        button1300.setVisibility(View.INVISIBLE);
        button1330.setVisibility(View.INVISIBLE);
        button1400.setVisibility(View.INVISIBLE);
        button1430.setVisibility(View.INVISIBLE);
        button1500.setVisibility(View.INVISIBLE);
        button1530.setVisibility(View.INVISIBLE);
        button1600.setVisibility(View.INVISIBLE);
        button1630.setVisibility(View.INVISIBLE);
        button1700.setVisibility(View.INVISIBLE);
        button1730.setVisibility(View.INVISIBLE);

        // 과거의 날짜 모두 비활성화
        currentCalendar = Calendar.getInstance(); // 현재 날짜 가져오는 메서드
        calendarView.setMinDate(currentCalendar.getTimeInMillis()); // 현재 날짜 이전 날짜 비활성화

        //현재로부터 3달 뒤까지만 접근 가능
        Calendar maxDate = (Calendar) currentCalendar.clone();
        maxDate.add(Calendar.MONTH, 3);
        // CalendarView에 최대 날짜를 설정합니다.
        calendarView.setMaxDate(maxDate.getTimeInMillis());



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                nextButton.setEnabled(false);
                nextButton.setBackgroundResource(R.drawable.unavailablenextbutton);
                showTimeButtons();
                Log.d("Value", "monValue : " + monValue + ", " + "tueValue : " + tueValue + ", "
                        + "wedValue : " + wedValue + ", " + "thuValue : " + thuValue + ", "
                        + "friValue : " + friValue);


                // 선택한 날짜를 문자열로 변환
                //selectedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month + 1, dayOfMonth);

                // 캘린더 객체 생성 및 선택한 날짜 설정
                selectedCalendar = Calendar.getInstance();
                selectedCalendar.set(year, month, dayOfMonth);

                selectedDate_year = selectedCalendar.get(Calendar.YEAR);
                selectedDate_month = selectedCalendar.get(Calendar.MONTH) + 1; // 월은 0부터 시작하므로 1을 더해줍니다.
                selectedDate_day = selectedCalendar.get(Calendar.DAY_OF_MONTH);

                StringMonth = String.valueOf(selectedDate_month);
                StringDay = String.valueOf(selectedDate_day);

                // 선택한 날짜의 요일을 가져오기
                int selectedDateOfWeek = selectedCalendar.get(Calendar.DAY_OF_WEEK);





                // 현재 날짜와 선택한 날짜 비교
                if (isSameDate(selectedCalendar, currentCalendar)
                        || selectedDateOfWeek==1 || selectedDateOfWeek==7) {
                    // 현재 날짜와 선택한 날짜가 같은 경우의 동작 추가
                    Log.d("DateComparison", "Selected date is the same as the current date.");
                    button0900.setBackgroundResource(R.drawable.unavailabletimebutton);
                    button0900.setEnabled(false);
                    button0930.setBackgroundResource(R.drawable.unavailabletimebutton);
                    button0930.setEnabled(false);
                    button1000.setBackgroundResource(R.drawable.unavailabletimebutton);
                    button1000.setEnabled(false);
                    button1030.setBackgroundResource(R.drawable.unavailabletimebutton);
                    button1030.setEnabled(false);
                    button1100.setBackgroundResource(R.drawable.unavailabletimebutton);
                    button1100.setEnabled(false);
                    button1130.setBackgroundResource(R.drawable.unavailabletimebutton);
                    button1130.setEnabled(false);
                    button1200.setBackgroundResource(R.drawable.unavailabletimebutton);
                    button1200.setEnabled(false);
                    button1230.setBackgroundResource(R.drawable.unavailabletimebutton);
                    button1230.setEnabled(false);
                    button1300.setBackgroundResource(R.drawable.unavailabletimebutton);
                    button1300.setEnabled(false);
                    button1330.setBackgroundResource(R.drawable.unavailabletimebutton);
                    button1330.setEnabled(false);
                    button1400.setBackgroundResource(R.drawable.unavailabletimebutton);
                    button1400.setEnabled(false);
                    button1430.setBackgroundResource(R.drawable.unavailabletimebutton);
                    button1430.setEnabled(false);
                    button1500.setBackgroundResource(R.drawable.unavailabletimebutton);
                    button1500.setEnabled(false);
                    button1530.setBackgroundResource(R.drawable.unavailabletimebutton);
                    button1530.setEnabled(false);
                    button1600.setBackgroundResource(R.drawable.unavailabletimebutton);
                    button1600.setEnabled(false);
                    button1630.setBackgroundResource(R.drawable.unavailabletimebutton);
                    button1630.setEnabled(false);
                    button1700.setBackgroundResource(R.drawable.unavailabletimebutton);
                    button1700.setEnabled(false);
                    button1730.setBackgroundResource(R.drawable.unavailabletimebutton);
                    button1730.setEnabled(false);

                    nextButton.setBackgroundResource(R.drawable.unavailablenextbutton);
                    nextButton.setEnabled(false);

                    // 여기에 특정 동작을 수행하면 됩니다.
                    // 예: 다른 버튼을 활성화하거나 메시지를 출력하는 등의 동작
                } else {


                    button0900.setBackgroundResource(R.drawable.timebutton);
                    button0900.setEnabled(true);
                    button0930.setBackgroundResource(R.drawable.timebutton);
                    button0930.setEnabled(true);
                    button1000.setBackgroundResource(R.drawable.timebutton);
                    button1000.setEnabled(true);
                    button1030.setBackgroundResource(R.drawable.timebutton);
                    button1030.setEnabled(true);
                    button1100.setBackgroundResource(R.drawable.timebutton);
                    button1100.setEnabled(true);
                    button1130.setBackgroundResource(R.drawable.timebutton);
                    button1130.setEnabled(true);
                    button1200.setBackgroundResource(R.drawable.timebutton);
                    button1200.setEnabled(true);
                    button1230.setBackgroundResource(R.drawable.timebutton);
                    button1230.setEnabled(true);
                    button1300.setBackgroundResource(R.drawable.timebutton);
                    button1300.setEnabled(true);
                    button1330.setBackgroundResource(R.drawable.timebutton);
                    button1330.setEnabled(true);
                    button1400.setBackgroundResource(R.drawable.timebutton);
                    button1400.setEnabled(true);
                    button1430.setBackgroundResource(R.drawable.timebutton);
                    button1430.setEnabled(true);
                    button1500.setBackgroundResource(R.drawable.timebutton);
                    button1500.setEnabled(true);
                    button1530.setBackgroundResource(R.drawable.timebutton);
                    button1530.setEnabled(true);
                    button1600.setBackgroundResource(R.drawable.timebutton);
                    button1600.setEnabled(true);
                    button1630.setBackgroundResource(R.drawable.timebutton);
                    button1630.setEnabled(true);
                    button1700.setBackgroundResource(R.drawable.timebutton);
                    button1700.setEnabled(true);
                    button1730.setBackgroundResource(R.drawable.timebutton);
                    button1730.setEnabled(true);

                    if(selectedDateOfWeek==2){
                        selectedDate_week = "월";
                        if(monValue.charAt(0)=='1'){
                            button0900.setEnabled(false);
                            button0900.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(monValue.charAt(1)=='1'){
                            button0930.setEnabled(false);
                            button0930.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(monValue.charAt(2)=='1'){
                            button1000.setEnabled(false);
                            button1000.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(monValue.charAt(3)=='1'){
                            button1030.setEnabled(false);
                            button1030.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(monValue.charAt(4)=='1'){
                            button1100.setEnabled(false);
                            button1100.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(monValue.charAt(5)=='1'){
                            button1130.setEnabled(false);
                            button1130.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(monValue.charAt(6)=='1'){
                            button1200.setEnabled(false);
                            button1200.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(monValue.charAt(7)=='1'){
                            button1230.setEnabled(false);
                            button1230.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(monValue.charAt(8)=='1'){
                            button1300.setEnabled(false);
                            button1300.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(monValue.charAt(9)=='1'){
                            button1330.setEnabled(false);
                            button1330.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(monValue.charAt(10)=='1'){
                            button1400.setEnabled(false);
                            button1400.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(monValue.charAt(11)=='1'){
                            button1430.setEnabled(false);
                            button1430.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(monValue.charAt(12)=='1'){
                            button1500.setEnabled(false);
                            button1500.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(monValue.charAt(13)=='1'){
                            button1530.setEnabled(false);
                            button1530.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(monValue.charAt(14)=='1'){
                            button1600.setEnabled(false);
                            button1600.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(monValue.charAt(15)=='1'){
                            button1630.setEnabled(false);
                            button1630.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(monValue.charAt(16)=='1'){
                            button1700.setEnabled(false);
                            button1700.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(monValue.charAt(17)=='1'){
                            button1730.setEnabled(false);
                            button1730.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                    }
                    if(selectedDateOfWeek==3){
                        selectedDate_week = "화";
                        if(tueValue.charAt(0)=='1'){
                            button0900.setEnabled(false);
                            button0900.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(tueValue.charAt(1)=='1'){
                            button0930.setEnabled(false);
                            button0930.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(tueValue.charAt(2)=='1'){
                            button1000.setEnabled(false);
                            button1000.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(tueValue.charAt(3)=='1'){
                            button1030.setEnabled(false);
                            button1030.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(tueValue.charAt(4)=='1'){
                            button1100.setEnabled(false);
                            button1100.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(tueValue.charAt(5)=='1'){
                            button1130.setEnabled(false);
                            button1130.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(tueValue.charAt(6)=='1'){
                            button1200.setEnabled(false);
                            button1200.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(tueValue.charAt(7)=='1'){
                            button1230.setEnabled(false);
                            button1230.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(tueValue.charAt(8)=='1'){
                            button1300.setEnabled(false);
                            button1300.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(tueValue.charAt(9)=='1'){
                            button1330.setEnabled(false);
                            button1330.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(tueValue.charAt(10)=='1'){
                            button1400.setEnabled(false);
                            button1400.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(tueValue.charAt(11)=='1'){
                            button1430.setEnabled(false);
                            button1430.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(tueValue.charAt(12)=='1'){
                            button1500.setEnabled(false);
                            button1500.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(tueValue.charAt(13)=='1'){
                            button1530.setEnabled(false);
                            button1530.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(tueValue.charAt(14)=='1'){
                            button1600.setEnabled(false);
                            button1600.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(tueValue.charAt(15)=='1'){
                            button1630.setEnabled(false);
                            button1630.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(tueValue.charAt(16)=='1'){
                            button1700.setEnabled(false);
                            button1700.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(tueValue.charAt(17)=='1'){
                            button1730.setEnabled(false);
                            button1730.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                    }
                    if(selectedDateOfWeek==4){
                        selectedDate_week = "수";
                        if(wedValue.charAt(0)=='1'){
                            button0900.setEnabled(false);
                            button0900.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(wedValue.charAt(1)=='1'){
                            button0930.setEnabled(false);
                            button0930.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(wedValue.charAt(2)=='1'){
                            button1000.setEnabled(false);
                            button1000.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(wedValue.charAt(3)=='1'){
                            button1030.setEnabled(false);
                            button1030.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(wedValue.charAt(4)=='1'){
                            button1100.setEnabled(false);
                            button1100.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(wedValue.charAt(5)=='1'){
                            button1130.setEnabled(false);
                            button1130.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(wedValue.charAt(6)=='1'){
                            button1200.setEnabled(false);
                            button1200.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(wedValue.charAt(7)=='1'){
                            button1230.setEnabled(false);
                            button1230.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(wedValue.charAt(8)=='1'){
                            button1300.setEnabled(false);
                            button1300.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(wedValue.charAt(9)=='1'){
                            button1330.setEnabled(false);
                            button1330.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(wedValue.charAt(10)=='1'){
                            button1400.setEnabled(false);
                            button1400.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(wedValue.charAt(11)=='1'){
                            button1430.setEnabled(false);
                            button1430.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(wedValue.charAt(12)=='1'){
                            button1500.setEnabled(false);
                            button1500.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(wedValue.charAt(13)=='1'){
                            button1530.setEnabled(false);
                            button1530.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(wedValue.charAt(14)=='1'){
                            button1600.setEnabled(false);
                            button1600.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(wedValue.charAt(15)=='1'){
                            button1630.setEnabled(false);
                            button1630.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(wedValue.charAt(16)=='1'){
                            button1700.setEnabled(false);
                            button1700.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(wedValue.charAt(17)=='1'){
                            button1730.setEnabled(false);
                            button1730.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                    }
                    if(selectedDateOfWeek==5){
                        selectedDate_week = "목";
                        if(thuValue.charAt(0)=='1'){
                            button0900.setEnabled(false);
                            button0900.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(thuValue.charAt(1)=='1'){
                            button0930.setEnabled(false);
                            button0930.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(thuValue.charAt(2)=='1'){
                            button1000.setEnabled(false);
                            button1000.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(thuValue.charAt(3)=='1'){
                            button1030.setEnabled(false);
                            button1030.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(thuValue.charAt(4)=='1'){
                            button1100.setEnabled(false);
                            button1100.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(thuValue.charAt(5)=='1'){
                            button1130.setEnabled(false);
                            button1130.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(thuValue.charAt(6)=='1'){
                            button1200.setEnabled(false);
                            button1200.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(thuValue.charAt(7)=='1'){
                            button1230.setEnabled(false);
                            button1230.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(thuValue.charAt(8)=='1'){
                            button1300.setEnabled(false);
                            button1300.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(thuValue.charAt(9)=='1'){
                            button1330.setEnabled(false);
                            button1330.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(thuValue.charAt(10)=='1'){
                            button1400.setEnabled(false);
                            button1400.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(thuValue.charAt(11)=='1'){
                            button1430.setEnabled(false);
                            button1430.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(thuValue.charAt(12)=='1'){
                            button1500.setEnabled(false);
                            button1500.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(thuValue.charAt(13)=='1'){
                            button1530.setEnabled(false);
                            button1530.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(thuValue.charAt(14)=='1'){
                            button1600.setEnabled(false);
                            button1600.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(thuValue.charAt(15)=='1'){
                            button1630.setEnabled(false);
                            button1630.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(thuValue.charAt(16)=='1'){
                            button1700.setEnabled(false);
                            button1700.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(thuValue.charAt(17)=='1'){
                            button1730.setEnabled(false);
                            button1730.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                    }
                    if(selectedDateOfWeek==6){
                        selectedDate_week = "금";
                        if(friValue.charAt(0)=='1'){
                            button0900.setEnabled(false);
                            button0900.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(friValue.charAt(1)=='1'){
                            button0930.setEnabled(false);
                            button0930.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(friValue.charAt(2)=='1'){
                            button1000.setEnabled(false);
                            button1000.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(friValue.charAt(3)=='1'){
                            button1030.setEnabled(false);
                            button1030.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(friValue.charAt(4)=='1'){
                            button1100.setEnabled(false);
                            button1100.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(friValue.charAt(5)=='1'){
                            button1130.setEnabled(false);
                            button1130.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(friValue.charAt(6)=='1'){
                            button1200.setEnabled(false);
                            button1200.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(friValue.charAt(7)=='1'){
                            button1230.setEnabled(false);
                            button1230.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(friValue.charAt(8)=='1'){
                            button1300.setEnabled(false);
                            button1300.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(friValue.charAt(9)=='1'){
                            button1330.setEnabled(false);
                            button1330.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(friValue.charAt(10)=='1'){
                            button1400.setEnabled(false);
                            button1400.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(friValue.charAt(11)=='1'){
                            button1430.setEnabled(false);
                            button1430.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(friValue.charAt(12)=='1'){
                            button1500.setEnabled(false);
                            button1500.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(friValue.charAt(13)=='1'){
                            button1530.setEnabled(false);
                            button1530.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(friValue.charAt(14)=='1'){
                            button1600.setEnabled(false);
                            button1600.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(friValue.charAt(15)=='1'){
                            button1630.setEnabled(false);
                            button1630.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(friValue.charAt(16)=='1'){
                            button1700.setEnabled(false);
                            button1700.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                        if(friValue.charAt(17)=='1'){
                            button1730.setEnabled(false);
                            button1730.setBackgroundResource(R.drawable.unavailabletimebutton);
                        }
                    }

                    String selectedMonth = String.valueOf(month + 1);
                    for (MonthlyData monthlyData : monthsList){
                        if (selectedMonth.equals(monthlyData.getMonth())){
                            List<String> dayDataList = monthlyData.getDayData();
                            for (int i = 0; i < dayDataList.size(); i++) {
                                String dayData = dayDataList.get(i);
                                if (selectedDate_day == i + 1) { // i는 0부터 시작하므로 1을 더해줍니다.
                                    if(dayData.charAt(0)=='1'){
                                        button0900.setEnabled(false);
                                        button0900.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData.charAt(1)=='1'){
                                        button0930.setEnabled(false);
                                        button0930.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData.charAt(2)=='1'){
                                        button1000.setEnabled(false);
                                        button1000.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData.charAt(3)=='1'){
                                        button1030.setEnabled(false);
                                        button1030.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData.charAt(4)=='1'){
                                        button1100.setEnabled(false);
                                        button1100.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData.charAt(5)=='1'){
                                        button1130.setEnabled(false);
                                        button1130.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData.charAt(6)=='1'){
                                        button1200.setEnabled(false);
                                        button1200.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData.charAt(7)=='1'){
                                        button1230.setEnabled(false);
                                        button1230.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData.charAt(8)=='1'){
                                        button1300.setEnabled(false);
                                        button1300.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData.charAt(9)=='1'){
                                        button1330.setEnabled(false);
                                        button1330.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData.charAt(10)=='1'){
                                        button1400.setEnabled(false);
                                        button1400.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData.charAt(11)=='1'){
                                        button1430.setEnabled(false);
                                        button1430.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData.charAt(12)=='1'){
                                        button1500.setEnabled(false);
                                        button1500.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData.charAt(13)=='1'){
                                        button1530.setEnabled(false);
                                        button1530.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData.charAt(14)=='1'){
                                        button1600.setEnabled(false);
                                        button1600.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData.charAt(15)=='1'){
                                        button1630.setEnabled(false);
                                        button1630.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData.charAt(16)=='1'){
                                        button1700.setEnabled(false);
                                        button1700.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData.charAt(17)=='1'){
                                        button1730.setEnabled(false);
                                        button1730.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    break; // 일치하는 날짜를 찾았으므로 루프를 종료합니다.
                                }
                            }
                        }
                    }

                    for (MonthlyData2 monthlyData2 : monthsList2){
                        if (selectedMonth.equals(monthlyData2.getMonth2())){
                            List<String> dayDataList2 = monthlyData2.getDayData2();
                            for (int i = 0; i < dayDataList2.size(); i++) {
                                String dayData2 = dayDataList2.get(i);
                                if (selectedDate_day == i + 1) { // i는 0부터 시작하므로 1을 더해줍니다.
                                    if(dayData2.charAt(0)=='1'){
                                        button0900.setEnabled(false);
                                        button0900.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData2.charAt(1)=='1'){
                                        button0930.setEnabled(false);
                                        button0930.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData2.charAt(2)=='1'){
                                        button1000.setEnabled(false);
                                        button1000.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData2.charAt(3)=='1'){
                                        button1030.setEnabled(false);
                                        button1030.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData2.charAt(4)=='1'){
                                        button1100.setEnabled(false);
                                        button1100.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData2.charAt(5)=='1'){
                                        button1130.setEnabled(false);
                                        button1130.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData2.charAt(6)=='1'){
                                        button1200.setEnabled(false);
                                        button1200.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData2.charAt(7)=='1'){
                                        button1230.setEnabled(false);
                                        button1230.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData2.charAt(8)=='1'){
                                        button1300.setEnabled(false);
                                        button1300.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData2.charAt(9)=='1'){
                                        button1330.setEnabled(false);
                                        button1330.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData2.charAt(10)=='1'){
                                        button1400.setEnabled(false);
                                        button1400.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData2.charAt(11)=='1'){
                                        button1430.setEnabled(false);
                                        button1430.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData2.charAt(12)=='1'){
                                        button1500.setEnabled(false);
                                        button1500.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData2.charAt(13)=='1'){
                                        button1530.setEnabled(false);
                                        button1530.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData2.charAt(14)=='1'){
                                        button1600.setEnabled(false);
                                        button1600.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData2.charAt(15)=='1'){
                                        button1630.setEnabled(false);
                                        button1630.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData2.charAt(16)=='1'){
                                        button1700.setEnabled(false);
                                        button1700.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    if(dayData2.charAt(17)=='1'){
                                        button1730.setEnabled(false);
                                        button1730.setBackgroundResource(R.drawable.unavailabletimebutton);
                                    }
                                    break; // 일치하는 날짜를 찾았으므로 루프를 종료합니다.
                                }
                            }
                        }
                    }













                }




            }
        });










        button0900.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button0900);
                updateNextButtonState();
            }
        });
        button0930.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button0930);
                updateNextButtonState();
            }
        });
        button1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1000);
                updateNextButtonState();
            }
        });
        button1030.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1030);
                updateNextButtonState();
            }
        });
        button1100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1100);
                updateNextButtonState();
            }
        });
        button1130.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1130);
                updateNextButtonState();
            }
        });
        button1200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1200);
                updateNextButtonState();
            }
        });
        button1230.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1230);
                updateNextButtonState();
            }
        });
        button1300.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1300);
                updateNextButtonState();
            }
        });
        button1330.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1330);
                updateNextButtonState();
            }
        });
        button1400.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1400);
                updateNextButtonState();
            }
        });
        button1430.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1430);
                updateNextButtonState();
            }
        });
        button1500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1500);
                updateNextButtonState();
            }
        });
        button1530.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1530);
                updateNextButtonState();
            }
        });
        button1600.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1600);
                updateNextButtonState();
            }
        });
        button1630.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1630);
                updateNextButtonState();
            }
        });
        button1700.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1700);
                updateNextButtonState();
            }
        });
        button1730.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(button1730);
                updateNextButtonState();
            }
        });

        nextButton.setEnabled(false);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lastClickedButton==button0900) selectedTime=9;
                else if(lastClickedButton==button0930) selectedTime=9.5f;
                else if(lastClickedButton==button1000) selectedTime=10;
                else if(lastClickedButton==button1030) selectedTime=10.5f;
                else if(lastClickedButton==button1100) selectedTime=11;
                else if(lastClickedButton==button1130) selectedTime=11.5f;
                else if(lastClickedButton==button1200) selectedTime=12;
                else if(lastClickedButton==button1230) selectedTime=12.5f;
                else if(lastClickedButton==button1300) selectedTime=13;
                else if(lastClickedButton==button1330) selectedTime=13.5f;
                else if(lastClickedButton==button1400) selectedTime=14;
                else if(lastClickedButton==button1430) selectedTime=14.5f;
                else if(lastClickedButton==button1500) selectedTime=15;
                else if(lastClickedButton==button1530) selectedTime=15.5f;
                else if(lastClickedButton==button1600) selectedTime=16;
                else if(lastClickedButton==button1630) selectedTime=16.5f;
                else if(lastClickedButton==button1700) selectedTime=17;
                else if(lastClickedButton==button1730) selectedTime=17.5f;
                Reservation reservation = new Reservation();
                reservation.setDate_year(selectedDate_year);
                reservation.setDate_month(selectedDate_month);
                reservation.setStringMonth(StringMonth);
                reservation.setStringDay(StringDay);

                reservation.setDate_day(selectedDate_day);
                reservation.setTime(selectedTime);
                reservation.setDate_week(selectedDate_week);
                //reservation.setStudentName("Christiano Ronaldo");

                Intent intent = new Intent(ConsultRequest_S_1.this, ConsultRequest_S_2.class);
                intent.putExtra("reservation", reservation);
                startActivity(intent);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainScreen_S.class);
                startActivity(intent);
            }
        });



    }



    private void showTimeButtons() {
        button0900.setVisibility(View.VISIBLE);
        button0930.setVisibility(View.VISIBLE);
        button1000.setVisibility(View.VISIBLE);
        button1030.setVisibility(View.VISIBLE);
        button1100.setVisibility(View.VISIBLE);
        button1130.setVisibility(View.VISIBLE);
        button1200.setVisibility(View.VISIBLE);
        button1230.setVisibility(View.VISIBLE);
        button1300.setVisibility(View.VISIBLE);
        button1330.setVisibility(View.VISIBLE);
        button1400.setVisibility(View.VISIBLE);
        button1430.setVisibility(View.VISIBLE);
        button1500.setVisibility(View.VISIBLE);
        button1530.setVisibility(View.VISIBLE);
        button1600.setVisibility(View.VISIBLE);
        button1630.setVisibility(View.VISIBLE);
        button1700.setVisibility(View.VISIBLE);
        button1730.setVisibility(View.VISIBLE);
    }
    private void updateButtonState(Button clickedButton) {
        // 마지막에 클릭된 버튼이 있으면 원래대로 돌려놓음
        if(lastClickedButton != null && lastClickedButton.isEnabled()){
            lastClickedButton.setBackgroundResource(R.drawable.timebutton);

        }

        // 현재 클릭한 버튼의 상태를 변경
        clickedButton.setBackgroundResource(R.drawable.clickedtimebutton);

        // 마지막에 클릭된 버튼을 현재 클릭한 버튼으로 업데이트
        lastClickedButton = clickedButton;
    }


    private void updateNextButtonState() {
        nextButton.setEnabled(lastClickedButton != null);
        nextButton.setBackgroundResource(R.drawable.availablenextbutton);

    }

    private boolean isSameDate(Calendar calendar1, Calendar calendar2) {
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) &&
                calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH);
    }

    private void syncNextButtonState() {
        if (lastButtonisClicked) {
            // 하나라도 버튼이 클릭된 경우
            nextButton.setEnabled(true);
            nextButton.setBackgroundResource(R.drawable.availablenextbutton);
        } else {
            // 모든 버튼이 클릭되지 않은 경우
            nextButton.setEnabled(false);
            nextButton.setBackgroundResource(R.drawable.unavailablenextbutton);
        }
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
