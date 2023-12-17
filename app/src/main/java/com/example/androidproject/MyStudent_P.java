package com.example.androidproject;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class MyStudent_P extends AppCompatActivity {
    ImageButton backBtn;
    ImageView myImg;
    TextView name,schoolNum, school, status, subMajor, phone, email;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage storage;
    Notification.Action.Builder getIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mystudent_p);

        firebaseDatabase = FirebaseDatabase.getInstance();

        Bundle extras=getIntent().getExtras();
        String getStudentKey=extras.getString("key");

        databaseReference = firebaseDatabase.getReference(getStudentKey);

        storage=FirebaseStorage.getInstance();
        backBtn=findViewById(R.id.backBtn);
        myImg=findViewById(R.id.myImg);
        name = findViewById(R.id.name);
        schoolNum=findViewById(R.id.schoolNum);
        school=findViewById(R.id.school);
        status=findViewById(R.id.status);
        subMajor=findViewById(R.id.subMajor);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);


        databaseReference.child("student_information").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // 데이터가 존재하는지 확인
                if (dataSnapshot.exists()) {
                    // 데이터 검색 및 TextViews 업데이트
                    String firebaseName = dataSnapshot.child("name").getValue(String.class);
                    String firebaseSchoolNum = String.valueOf(dataSnapshot.child("student_number").getValue());
                    String firebaseSchool = dataSnapshot.child("belong").getValue(String.class);
                    String firebaseStatus = dataSnapshot.child("state").getValue(String.class);
                    String firebaseSubMajor = dataSnapshot.child("subMajor").getValue(String.class);
                    String firebasePhone = dataSnapshot.child("phone_number").getValue(String.class);
                    String firebaseMail = dataSnapshot.child("email").getValue(String.class);

                    // 검색된 데이터로 TextViews 업데이트
                    name.setText(firebaseName);
                    schoolNum.setText(firebaseSchoolNum);
                    school.setText(firebaseSchool);
                    status.setText(firebaseStatus);
                    subMajor.setText(firebaseSubMajor);
                    phone.setText(firebasePhone);
                    email.setText(firebaseMail);



                    // 프로필 이미지 URL을 가져와서 이미지 설정
                    String imageUrl = dataSnapshot.child("profileImageUrl").getValue(String.class);
                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        // 이미지 URL이 존재하면 이미지를 다운로드하여 설정
                        downloadImageAndSetToImageView(imageUrl);
                    }
                }
            }
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // 오류 처리
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Student_List.class);
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