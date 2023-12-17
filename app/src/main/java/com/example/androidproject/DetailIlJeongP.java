package com.example.androidproject;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailIlJeongP extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, SdatabaseReference, flucdatabaseReference;

    TextView day_db, pro_db, kind_db;
    TextView questionBox;
    Button cancel;
    ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iljeong_detail_p);

        //Toolbar record_toolbar=findViewById(R.id.toolbar2);
        //record_toolbar.setTitleTextColor(Color.WHITE);
        //setSupportActionBar(record_toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("상담 일정");

        backBtn = findViewById(R.id.backBtn);

        day_db = findViewById(R.id.time_when);
        pro_db = findViewById(R.id.pro_db);
        kind_db = findViewById(R.id.kind_db);
        questionBox = findViewById(R.id.questionbox);
        questionBox.setText("");

        Intent intent = getIntent();
        if(intent != null){
            int day = intent.getIntExtra("dd", 0);
            float hour = intent.getFloatExtra("dh", 0);
            String week = intent.getStringExtra("dw");
            int month = intent.getIntExtra("dm", 0);
            int year = intent.getIntExtra("dy", 0);
            String professorName = intent.getStringExtra("pm");
            String form = intent.getStringExtra("cf");
            String question = intent.getStringExtra("qu");

            if(hour%1 != 0)
            {
                day_db.setText(String.format("%d-%02d-%02d", year, month, day) + "("+week+")" + " "+ (int)hour + ":30");
            }else{
                day_db.setText(String.format("%d-%02d-%02d", year, month, day) + "("+week+")" + " "+ (int)hour + ":00");
            }
            pro_db.setText(professorName);
            kind_db.setText(form);
            questionBox.setText(question);
        }

        firebaseDatabase = FirebaseDatabase.getInstance();
        logIn loginInstance = new logIn();
        String getcode = loginInstance.getPrivate_key();
        databaseReference = firebaseDatabase.getReference(getcode);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), IlJeong_P.class);
                startActivity(intent);
            }
        });



        cancel = findViewById(R.id.cancel);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // LayoutInflater를 사용하여 XML 레이아웃 파일을 뷰로 변환
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.cancel_popup, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(DetailIlJeongP.this);
                builder.setView(dialogView);

                // 다이얼로그 생성
                final AlertDialog alertDialog = builder.create();

                // "아니요" 버튼 처리
                Button noButton = dialogView.findViewById(R.id.noButton);
                noButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // "아니요" 버튼을 눌렀을 때 다이얼로그 닫기
                        alertDialog.dismiss();
                    }
                });

                Button yesButton = dialogView.findViewById(R.id.yesButton);

                yesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LayoutInflater inflater = getLayoutInflater();
                        View yesdialogView = inflater.inflate(R.layout.cancel_yes, null);

                        AlertDialog.Builder yesbuilder = new AlertDialog.Builder(DetailIlJeongP.this);
                        yesbuilder.setView(yesdialogView);

                        // 다이얼로그 생성
                        final AlertDialog yesalertDialog = yesbuilder.create();
                        Intent intent1 = getIntent();
                        int month = intent1.getIntExtra("dm", 0 );
                        float hour = intent1.getFloatExtra("dh", 0);
                        int day = intent1.getIntExtra("dd", 0);
                        int year = intent1.getIntExtra("dy", 0);
                        String tag = null;
                        Log.d(tag, "month is" + hour);

                        Button ok = yesdialogView.findViewById(R.id.OK);

                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String studentkey = getIntent().getStringExtra("pk");
                                if(studentkey != null){
                                    // "state" 노드의 값을 "취소"로 설정
                                    databaseReference.child("Schedule_Management").child(studentkey).child("content").child("state").setValue("취소")
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Intent intent = new Intent(DetailIlJeongP.this, MainScreen_P.class);
                                                    startActivity(intent);
                                                    yesalertDialog.dismiss();
                                                    alertDialog.dismiss();
                                                    finish();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    // 오류가 발생하면 필요에 따라 Toast를 표시하거나 오류를 처리합니다.
                                                    Toast.makeText(DetailIlJeongP.this, "상태 업데이트 실패", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                    SdatabaseReference = firebaseDatabase.getReference(studentkey);
                                    SdatabaseReference.child("Schedule_Management/2021145818/content").child("state").setValue("취소");

                                    flucdatabaseReference = firebaseDatabase.getReference("2021145818/Timetable/fluctuations2").child(String.valueOf(month)).child(String.valueOf(day));
                                    Log.d("FirebaseData22", flucdatabaseReference.toString());
                                    logIn loginInstance = new logIn();
                                    String private_key = loginInstance.getPrivate_key();

                                    alarm_center_A fun = new alarm_center_A();
                                    fun.alarm("테스트",private_key,studentkey
                                            ,2000,1,1 );
                                    fun.alarm("상담취소",private_key,studentkey
                                            ,year,month,day );

                                    flucdatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            if(snapshot.exists()){

                                                String fluc = snapshot.getValue(String.class);
                                                Log.d(fluc, "Professor fluc is"+ fluc);
                                                if(fluc != null && !fluc.isEmpty()){
                                                    if(snapshot.exists()){
                                                        if(hour == 9){
                                                            fluc = "0" + fluc.substring(1);
                                                        }
                                                        if(hour == 9.5){
                                                            fluc = fluc.substring(0,1) + "0" + fluc.substring(2);
                                                        }
                                                        if(hour == 10){
                                                            fluc = fluc.substring(0,2) + "0" + fluc.substring(3);
                                                        }
                                                        if(hour == 10.5){
                                                            fluc = fluc.substring(0,3) + "0" + fluc.substring(4);
                                                        }
                                                        if(hour == 11){
                                                            fluc = fluc.substring(0,4) + "0" + fluc.substring(5);
                                                        }
                                                        if(hour == 11.5){
                                                            fluc = fluc.substring(0,5) + "0" + fluc.substring(6);
                                                        }
                                                        if(hour == 12){
                                                            fluc = fluc.substring(0,6) + "0" + fluc.substring(7);
                                                        }
                                                        if(hour == 12.5){
                                                            fluc = fluc.substring(0,7) + "0" + fluc.substring(8);
                                                        }
                                                        if(hour == 13){
                                                            fluc = fluc.substring(0,8) + "0" + fluc.substring(9);
                                                        }
                                                        if(hour == 13.5){
                                                            fluc = fluc.substring(0,9) + "0" + fluc.substring(10);
                                                        }
                                                        if(hour == 14){
                                                            fluc = fluc.substring(0,10) + "0" + fluc.substring(11);
                                                        }
                                                        if(hour == 14.5){
                                                            fluc = fluc.substring(0,11) + "0" + fluc.substring(12);
                                                        }
                                                        if(hour == 15){
                                                            fluc = fluc.substring(0,12) + "0" + fluc.substring(13);
                                                        }
                                                        if(hour == 15.5){
                                                            fluc = fluc.substring(0,13) + "0" + fluc.substring(14);
                                                        }
                                                        if(hour == 16){
                                                            fluc = fluc.substring(0,14) + "0" + fluc.substring(15);
                                                        }
                                                        if(hour == 16.5){
                                                            fluc = fluc.substring(0,15) + "0" + fluc.substring(16);
                                                        }
                                                        if(hour == 17){
                                                            fluc = fluc.substring(0,16) + "0" + fluc.substring(17);
                                                        }
                                                        if(hour == 17.5){
                                                            fluc = fluc.substring(0,17) + "0";
                                                        }
                                                    }
                                                }
                                                flucdatabaseReference.setValue(fluc);
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                }
                                //

                            }
                        });
                        yesalertDialog.show();
                    }
                });

                // 다이얼로그 표시
                alertDialog.show();
            }
        });
    }

    //@Override
    //public boolean onOptionsItemSelected(MenuItem item) {
    //switch (item.getItemId()) {
    //case android.R.id.home:
    //finish(); // 뒤로가기 버튼 눌렀을 때 현재 액티비티 종료
    //return true;
    //default:
    //return super.onOptionsItemSelected(item);
    //}
    //}
}