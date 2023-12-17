package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MainScreen_P extends AppCompatActivity {

    TextView message, name;
    Button alert, myPage, iljeongCheck, history, ilJeong, myProfessor, schedule;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, MdatabaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen_p);

        name = (TextView) findViewById(R.id.Name);

        message = (TextView) findViewById(R.id.Message);
        alert = findViewById(R.id.alert);
        iljeongCheck = findViewById(R.id.IljeongButton);
        history = findViewById(R.id.History);
        ilJeong = findViewById(R.id.Iljeong);
        myProfessor = findViewById(R.id.MyProfessor);
        myPage = findViewById(R.id.MyPage);
        schedule = findViewById(R.id.Schedule);

        firebaseDatabase = FirebaseDatabase.getInstance();
        logIn loginInstance = new logIn();
        String getcode = loginInstance.getPrivate_key();

        databaseReference = firebaseDatabase.getReference(getcode);
        MdatabaseReference = firebaseDatabase.getReference(getcode);



        databaseReference.child("professor_information").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String firebaseName = snapshot.child("name").getValue(String.class);

                    name.setText(firebaseName);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        MdatabaseReference.child("Schedule_Management").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot scheduleSnapshot : snapshot.getChildren()) {
                        String state = scheduleSnapshot.child("content/state").getValue(String.class);

                        // 여기서 각 state 값에 따른 처리를 수행
                        if (state != null) {
                            if (state.equals("완료")) {
                                message.setText("예약된 상담이 있습니다.");
                                break;
                            } else if (state.equals("취소")) {
                                message.setText("예약된 상담이 없습니다.");
                            }
                        } else {
                            // 'state' 값이 없을 때의 처리
                            message.setText("상담 상태를 가져올 수 없습니다.");
                        }
                    }
                } else {
                    // 스케줄 정보가 없을 때의 처리
                    message.setText("상담 일정이 없습니다.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // 에러 처리
            }
        });

        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen_P.this, alarm_center_A.class);
                startActivity(intent);

            }
        });

        iljeongCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen_P.this, IlJeong_P.class);
                startActivity(intent);

            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen_P.this, Record_p.class);
                startActivity(intent);

            }
        });

        ilJeong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen_P.this, feedback_p.class);
                startActivity(intent);

            }
        });

        myProfessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen_P.this, Student_List.class);
                startActivity(intent);

            }
        });

        myPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen_P.this, MyPg_P.class);
                startActivity(intent);

            }
        });

        schedule.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MainScreen_P.this, FixedSchedule_P.class);
                startActivity(intent);
            }
        });
    }
}