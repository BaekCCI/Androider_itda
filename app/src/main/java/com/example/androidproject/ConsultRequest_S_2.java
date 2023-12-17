package com.example.androidproject;

import androidx.annotation.NonNull;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.app.ReservationApplication;
import com.example.app.Reservation;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ConsultRequest_S_2 extends AppCompatActivity {
    ImageButton backBtn;
    EditText questionEditText;
    private String selectedType;
    private Reservation reservation;
    private Button faceConsultButton;
    private Button zoomConsultButton;
    private Button callConsultButton;
    private Button lastClickedButton;
    private Button reservationButton;
    private Button beforeButton;
    private DatabaseReference databaseReference3;
    private List<MonthlyData2> monthsList2 = new ArrayList<>();

    logIn loginInstance = new logIn();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultrequest_s_2);

        //Toolbar record_toolbar=findViewById(R.id.toolbar);
        //record_toolbar.setTitleTextColor(Color.WHITE);
        //setSupportActionBar(record_toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("상담 신청");

        reservation = getIntent().getParcelableExtra("reservation");
        //Log.d("reservationDebug", "studentName: " + reservation.getStudentName() + ", Date: " + reservation.getDate_day() + ", Time: " + reservation.getTime());

        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        backBtn = findViewById(R.id.backBtn);
        faceConsultButton = findViewById(R.id.faceConsultButton);
        zoomConsultButton = findViewById(R.id.zoomConsultButton);
        callConsultButton = findViewById(R.id.callConsultButton);
        reservationButton = findViewById(R.id.reservationButton);
        beforeButton = findViewById(R.id.beforeButton);
        questionEditText = findViewById(R.id.questionEditText);


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

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), ConsultRequest_S_1.class);
                startActivity(intent);
            }
        });



        faceConsultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(faceConsultButton);
                updateNextButtonState();
            }
        });

        zoomConsultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(zoomConsultButton);
                updateNextButtonState();
            }
        });

        callConsultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateButtonState(callConsultButton);
                updateNextButtonState();
            }
        });

        beforeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultRequest_S_2.this, ConsultRequest_S_1.class);
                startActivity(intent);
            }
        });

        reservationButton.setEnabled(false);
        reservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showReservationDialog();
            }
        });
    }

    private void updateButtonState(Button clickedButton) {
        // 마지막에 클릭된 버튼이 있으면 원래대로 돌려놓음
        if (lastClickedButton != null) {
            lastClickedButton.setBackgroundResource(R.drawable.whitebutton);
        }

        // 현재 클릭한 버튼의 상태를 변경
        clickedButton.setBackgroundResource(R.drawable.green1button);

        // 마지막에 클릭된 버튼을 현재 클릭한 버튼으로 업데이트
        lastClickedButton = clickedButton;
    }
    private void updateNextButtonState() {
        reservationButton.setEnabled(lastClickedButton != null);
        reservationButton.setBackgroundResource(R.drawable.availablenextbutton);
    }

    private void showReservationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 사용자 정의 레이아웃을 설정
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog, null);
        builder.setView(dialogView);

        final AlertDialog dialog = builder.create();
        // 사용자 정의 레이아웃 내부의 뷰를 참조
        TextView confirmTextView = dialogView.findViewById(R.id.confirmTextView);
        Button yesButton = dialogView.findViewById(R.id.yesButton);
        Button noButton = dialogView.findViewById(R.id.noButton);

        // 메시지 설정
        confirmTextView.setText("상담을 예약하시겠습니까?");

        // "예" 버튼 동작 설정
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lastClickedButton==faceConsultButton) selectedType="대면상담";
                else if(lastClickedButton==callConsultButton) selectedType="통화상담";
                else if(lastClickedButton==zoomConsultButton) selectedType="화상상담";
                reservation.setType(selectedType);
                String question = questionEditText.getText().toString();
                reservation.setQuestion(question);
                reservation.setState("완료");
//                Log.d("ReservationDebug", "studentName: " + reservation.getStudentName() + ", Date: " + reservation.getDate() + ", Time: " + reservation.getTime() +
//                        ", Type: " + reservation.getType() );
                ReservationApplication reservationApplication = (ReservationApplication) getApplication();
                reservationApplication.setCurrentReservation(reservation, loginInstance.getPrivate_key());


                for (MonthlyData2 monthlyData2 : monthsList2) {
                    if (reservation.getStringMonth().equals(monthlyData2.getMonth2())) {
                        List<String> dayDataList = monthlyData2.getDayData2();
                        for (int i = 0; i < dayDataList.size(); i++) {
                            String dayData = dayDataList.get(i);
                            if (reservation.getDate_day() == i + 1) {
                                if(reservation.getTime()==9){
                                    StringBuilder modifiedBitString = new StringBuilder(dayData);
                                    modifiedBitString.setCharAt(0, '1');
                                    dayData = modifiedBitString.toString();
                                }
                                if(reservation.getTime()==9.5){
                                    StringBuilder modifiedBitString = new StringBuilder(dayData);
                                    modifiedBitString.setCharAt(1, '1');
                                    dayData = modifiedBitString.toString();
                                }
                                if(reservation.getTime()==10){
                                    StringBuilder modifiedBitString = new StringBuilder(dayData);
                                    modifiedBitString.setCharAt(2, '1');
                                    dayData = modifiedBitString.toString();
                                }
                                if(reservation.getTime()==10.5){
                                    StringBuilder modifiedBitString = new StringBuilder(dayData);
                                    modifiedBitString.setCharAt(3, '1');
                                    dayData = modifiedBitString.toString();
                                }
                                if(reservation.getTime()==11){
                                    StringBuilder modifiedBitString = new StringBuilder(dayData);
                                    modifiedBitString.setCharAt(4, '1');
                                    dayData = modifiedBitString.toString();
                                }
                                if(reservation.getTime()==11.5){
                                    StringBuilder modifiedBitString = new StringBuilder(dayData);
                                    modifiedBitString.setCharAt(5, '1');
                                    dayData = modifiedBitString.toString();
                                }
                                if(reservation.getTime()==12){
                                    StringBuilder modifiedBitString = new StringBuilder(dayData);
                                    modifiedBitString.setCharAt(6, '1');
                                    dayData = modifiedBitString.toString();
                                }
                                if(reservation.getTime()==12.5){
                                    StringBuilder modifiedBitString = new StringBuilder(dayData);
                                    modifiedBitString.setCharAt(7, '1');
                                    dayData = modifiedBitString.toString();
                                }
                                if(reservation.getTime()==13){
                                    StringBuilder modifiedBitString = new StringBuilder(dayData);
                                    modifiedBitString.setCharAt(8, '1');
                                    dayData = modifiedBitString.toString();
                                }
                                if(reservation.getTime()==13.5){
                                    StringBuilder modifiedBitString = new StringBuilder(dayData);
                                    modifiedBitString.setCharAt(9, '1');
                                    dayData = modifiedBitString.toString();
                                }
                                if(reservation.getTime()==14){
                                    StringBuilder modifiedBitString = new StringBuilder(dayData);
                                    modifiedBitString.setCharAt(10, '1');
                                    dayData = modifiedBitString.toString();
                                }
                                if(reservation.getTime()==14.5){
                                    StringBuilder modifiedBitString = new StringBuilder(dayData);
                                    modifiedBitString.setCharAt(11, '1');
                                    dayData = modifiedBitString.toString();
                                }
                                if(reservation.getTime()==15){
                                    StringBuilder modifiedBitString = new StringBuilder(dayData);
                                    modifiedBitString.setCharAt(12, '1');
                                    dayData = modifiedBitString.toString();
                                }
                                if(reservation.getTime()==15.5){
                                    StringBuilder modifiedBitString = new StringBuilder(dayData);
                                    modifiedBitString.setCharAt(13, '1');
                                    dayData = modifiedBitString.toString();
                                }
                                if(reservation.getTime()==16){
                                    StringBuilder modifiedBitString = new StringBuilder(dayData);
                                    modifiedBitString.setCharAt(14, '1');
                                    dayData = modifiedBitString.toString();
                                }
                                if(reservation.getTime()==16.5){
                                    StringBuilder modifiedBitString = new StringBuilder(dayData);
                                    modifiedBitString.setCharAt(15, '1');
                                    dayData = modifiedBitString.toString();
                                }
                                if(reservation.getTime()==17){
                                    StringBuilder modifiedBitString = new StringBuilder(dayData);
                                    modifiedBitString.setCharAt(16, '1');
                                    dayData = modifiedBitString.toString();
                                }
                                if(reservation.getTime()==17.5){
                                    StringBuilder modifiedBitString = new StringBuilder(dayData);
                                    modifiedBitString.setCharAt(17, '1');
                                    dayData = modifiedBitString.toString();
                                }
                                logIn loginInstance = new logIn();
                                String private_key = loginInstance.getPrivate_key();

                                DatabaseReference database = FirebaseDatabase.getInstance().getReference();

                                int finalI = i;
                                database.child("private_key").child(private_key).addListenerForSingleValueEvent(

                                        new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                GenericTypeIndicator<Long> t = new GenericTypeIndicator<Long>() {};
                                                int temp = snapshot.getValue(t).intValue();

                                                Log.d("상담신청", temp + " + " + "String.valueOf(temp)");
                                                alarm_center_A fun = new alarm_center_A();

                                                fun.alarm("테스트",private_key,String.valueOf(temp)
                                                        ,2000,1,1 );
                                                fun.alarm("상담신청",private_key,String.valueOf(temp)
                                                        ,reservation.getDate_year(),reservation.getDate_month(), finalI + 1);

                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                //업로드
                                databaseReference3.child(monthlyData2.getMonth2()).child(String.valueOf(i + 1)).setValue(dayData);

                            }
                        }
                    }
                }

                showCompleteDialog();
                dialog.dismiss();


            }
        });

        // "아니오" 버튼 동작 설정
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        // 다이얼로그 보이기 전에 모서리가 적용된 Window를 설정
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

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
        confirmTextView.setText("상담 예약을 완료하였습니다.");

        // "예" 버튼 동작 설정
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsultRequest_S_2.this, MainScreen_S.class);
                startActivity(intent);
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