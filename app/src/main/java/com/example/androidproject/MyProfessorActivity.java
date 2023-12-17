package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MyProfessorActivity extends AppCompatActivity {
    ImageButton backBtn;
    ImageView myImg;
    TextView name, laboratory,school,major, phone, mail, zoom;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage storage;
    String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myprofessor_layout);

        firebaseDatabase = FirebaseDatabase.getInstance();

        logIn loginPageInstance = new logIn();
        code = loginPageInstance.getPrivate_key();
        databaseReference = firebaseDatabase.getReference(code);

        storage=FirebaseStorage.getInstance();

        backBtn=findViewById(R.id.backBtn);
        myImg=findViewById(R.id.myImg);
        laboratory =findViewById(R.id.laboratory);
        phone=findViewById(R.id.phone);
        mail=findViewById(R.id.mail);
        zoom =findViewById(R.id.zoom);
        name = findViewById(R.id.name);
        school=findViewById(R.id.school);
        major=findViewById(R.id.major);

        // ValueEventListener 추가하여 Firebase에서 데이터 검색
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 데이터가 존재하는지 확인
                if (dataSnapshot.exists()) {
                    // 데이터 검색 및 TextViews 업데이트
                    String firebaseName = dataSnapshot.child("professor_information").child("name").getValue(String.class);
                    String firebaseSchool = String.valueOf(dataSnapshot.child("professor_information").child("belong").getValue());
                    String firebaseMajor = dataSnapshot.child("professor_information").child("major").getValue(String.class);
                    String firebaseLaboratory = dataSnapshot.child("professor_information").child("laboratory_location").getValue(String.class);
                    String firebasePhone = dataSnapshot.child("professor_information").child("phone_number").getValue(String.class);
                    String firebaseEmail = dataSnapshot.child("professor_information").child("email").getValue(String.class);
                    String firebaseZoom = dataSnapshot.child("professor_information").child("Zoom_link").getValue(String.class);

                    // 검색된 데이터로 TextViews 업데이트
                    name.setText(firebaseName);
                    school.setText(firebaseSchool);
                    major.setText(firebaseMajor);
                    laboratory.setText(firebaseLaboratory);
                    phone.setText(firebasePhone);
                    mail.setText(firebaseEmail);
                    zoom.setText(firebaseZoom);

                    // 프로필 이미지 URL을 가져와서 이미지 설정
                    String imageUrl = dataSnapshot.child("professor_information").child("profileImageUrl").getValue(String.class);

                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        // 이미지 URL이 존재하면 이미지를 다운로드하여 설정
                        downloadImageAndSetToImageView(imageUrl);
                    }
                    Boolean lastAlarmStatus = dataSnapshot.child("professor_information").child("alarm").getValue(Boolean.class);
                    // lastAlarmStatus 값에 따라 스위치 상태를 설정
                }
            }
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // 오류 처리
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
    private void downloadImageAndSetToImageView(String imageUrl) {
        // Firebase Storage 레퍼런스 설정
        StorageReference storageRef = storage.getReferenceFromUrl(imageUrl);

        // 이미지 다운로드
        final long ONE_MEGABYTE = 1024 * 1024;
        storageRef.getBytes(ONE_MEGABYTE)
                .addOnSuccessListener(bytes -> {
                    // 이미지 다운로드 성공
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    myImg.setImageBitmap(bitmap);
                })
                .addOnFailureListener(e -> {
                    // 이미지 다운로드 실패
                   });
    }
}