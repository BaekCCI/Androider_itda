package com.example.androidproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.graphics.Color;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyPg_S extends AppCompatActivity {
    Button  editBtn, editCompleteBtn, logoutBtn;
    ImageButton changeImgBtn, backBtn;
    ImageView myImg;
    EditText editPhone, editMail;
    TextView status, subMajor, phone, mail, name, schoolNum, school;
    Switch onOff;
    Dialog editdialog, logoutdialog, logoutcheckdialog, imgselectdialog;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, databaseReference1;
    FirebaseStorage storage;
    String Storage_path;
    String Image_FileName;
    String editedPhone, editedMail;
    String getcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypg_s);

        firebaseDatabase = FirebaseDatabase.getInstance();

        logIn loginPageInstance = new logIn();
        getcode = loginPageInstance.getPrivate_key();
        databaseReference = firebaseDatabase.getReference(getcode);

        Storage_path=getcode+"/";
        Image_FileName="profile_image";
        storage=FirebaseStorage.getInstance();

        backBtn=findViewById(R.id.backBtn);
        changeImgBtn=findViewById(R.id.changeImgBtn);
        myImg=findViewById(R.id.myImg);
        editBtn=findViewById(R.id.editBtn);
        editCompleteBtn=findViewById(R.id.editCompleteBtn);
        logoutBtn=findViewById(R.id.logoutBtn);
        status=findViewById(R.id.status);
        name = findViewById(R.id.name);
        schoolNum = findViewById(R.id.schoolNum);
        school = findViewById(R.id.mySchool);
        status = findViewById(R.id.status);
        subMajor = findViewById(R.id.subMajor);
        phone=findViewById(R.id.phone);
        mail=findViewById(R.id.mail);
        editPhone=findViewById(R.id.editPhone);
        editMail=findViewById(R.id.editMail);
        onOff=findViewById(R.id.onOff);

        editdialog=new Dialog(MyPg_S.this);
        editdialog.setContentView(R.layout.editdialog);
        logoutdialog=new Dialog(MyPg_S.this);
        logoutdialog.setContentView(R.layout.logoutdialog);
        logoutcheckdialog=new Dialog(MyPg_S.this);
        logoutcheckdialog.setContentView(R.layout.logoutcheckdialog);
        imgselectdialog=new Dialog(MyPg_S.this);
        imgselectdialog.setContentView(R.layout.imgselectdialog);


        // ValueEventListener 추가하여 Firebase에서 데이터 검색
        databaseReference.child("student_information").addListenerForSingleValueEvent(new ValueEventListener() {
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
                    String firebaseEmail = dataSnapshot.child("email").getValue(String.class);

                    // 검색된 데이터로 TextViews 업데이트
                    name.setText(firebaseName);
                    schoolNum.setText(firebaseSchoolNum);
                    school.setText(firebaseSchool);
                    status.setText(firebaseStatus);
                    subMajor.setText(firebaseSubMajor);
                    phone.setText(firebasePhone);
                    editPhone.setText(firebasePhone);
                    mail.setText(firebaseEmail);
                    editMail.setText(firebaseEmail);

                    // 프로필 이미지 URL을 가져와서 이미지 설정
                    String imageUrl = dataSnapshot.child("profileImageUrl").getValue(String.class);
                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        // 이미지 URL이 존재하면 이미지를 다운로드하여 설정
                        downloadImageAndSetToImageView(imageUrl);
                    }
                    Boolean lastAlarmStatus = dataSnapshot.child("alarm").getValue(Boolean.class);
                    // lastAlarmStatus 값에 따라 스위치 상태를 설정
                    if (lastAlarmStatus != null) {
                        onOff.setChecked(lastAlarmStatus);
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
                Intent intent=new Intent(getApplicationContext(), MainScreen_S.class);
                startActivity(intent);
            }
        });
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setTextStatus(1);
            }
        });
        editCompleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextStatus(0);
                showEditDialog();
                editedPhone = editPhone.getText().toString();
                editedMail = editMail.getText().toString();

                databaseReference.child("student_information").child("phone_number").setValue(editedPhone);
                databaseReference.child("student_information").child("email").setValue(editedMail);
                UpdateToProfessorEdit();
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog();
            }
        });

        onOff.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    databaseReference.child("student_information").child("alarm").setValue(true);
                }
                else {
                    databaseReference.child("student_information").child("alarm").setValue(false);
                }
            }
        });
        changeImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showImgSelectDialog();
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
    public void UpdateToProfessorEdit(){
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String privateKey_P =String.valueOf(dataSnapshot.child("professor_private_key").getValue(Integer.class));
                databaseReference1 = firebaseDatabase.getReference(privateKey_P);

                databaseReference1.child("student_information").addListenerForSingleValueEvent(new ValueEventListener() {@Override
                public void onDataChange(DataSnapshot Snapshot) {
                    for (DataSnapshot studentSnapshot : Snapshot.getChildren()) {
                        String studentPrivateKey = String.valueOf(studentSnapshot.child("private_key").getValue(Integer.class));

                        if (getcode.equals(studentPrivateKey)) {
                            // 원하는 작업 수행
                            databaseReference1.child("student_information").child(studentSnapshot.getKey()).child("phone_number").setValue(editedPhone);
                            databaseReference1.child("student_information").child(studentSnapshot.getKey()).child("email").setValue(editedMail);

                            break;
                        }
                    }
                }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // 데이터 읽기가 실패했을 때 호출
                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // 데이터 읽기가 실패했을 때 호출됩니다.
            }
        });
    }
    public void UpdateToProfessorImage(String imageUrl){
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String privateKey_P =String.valueOf(dataSnapshot.child("professor_private_key").getValue(Integer.class));

                databaseReference1 = firebaseDatabase.getReference(privateKey_P);
                databaseReference1.child("student_information").addListenerForSingleValueEvent(new ValueEventListener() {@Override
                public void onDataChange(DataSnapshot Snapshot) {
                    for (DataSnapshot studentSnapshot : Snapshot.getChildren()) {
                        String studentPrivateKey = String.valueOf(studentSnapshot.child("private_key").getValue(Integer.class));

                        if (getcode.equals(studentPrivateKey)) {
                            // 원하는 작업 수행
                            databaseReference1.child("student_information").child(studentSnapshot.getKey()).child("profileImageUrl").setValue(imageUrl);

                            break;
                        }
                    }
                }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // 데이터 읽기가 실패했을 때 호출
                        System.out.println("Error: " + databaseError.getMessage());
                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // 데이터 읽기가 실패했을 때 호출됩니다.
                System.out.println("Error: " + databaseError.getMessage());
            }
        });
    }


    public void showEditDialog(){
        editdialog.show();
        Button okBtn = editdialog.findViewById(R.id.okBtn);
        okBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                editdialog.dismiss();
            }
        });
    }
    public void setTextStatus(int i){
        if(i==1){
            editBtn.setVisibility(View.INVISIBLE);
            editCompleteBtn.setVisibility(View.VISIBLE);
            status.setTextColor(Color.parseColor("#c2c2c2"));
            subMajor.setTextColor(Color.parseColor("#c2c2c2"));
            phone.setVisibility(View.INVISIBLE);
            mail.setVisibility(View.INVISIBLE);
            editPhone.setVisibility(View.VISIBLE);
            editMail.setVisibility(View.VISIBLE);
        }
        else{
            editBtn.setVisibility(View.VISIBLE);
            editCompleteBtn.setVisibility(View.INVISIBLE);
            status.setTextColor(Color.BLACK);
            subMajor.setTextColor(Color.BLACK);
            phone.setVisibility(View.VISIBLE);
            mail.setVisibility(View.VISIBLE);
            editPhone.setVisibility(View.INVISIBLE);
            editMail.setVisibility(View.INVISIBLE);
            phone.setText(editPhone.getText());
            mail.setText(editMail.getText());
        }
    }
    public void showLogoutDialog(){
        logoutdialog.show();
        Button yesBtn = logoutdialog.findViewById(R.id.yesBtn);
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutdialog.dismiss();
                logoutcheckdialog.show();
                Button okBtn = logoutcheckdialog.findViewById(R.id.okBtn);
                okBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        logIn loginInstance = new logIn();
                        String private_key = loginInstance.getPrivate_key();
                        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                        database.child(private_key).child("alarm").child("token").setValue("null");
                        logoutcheckdialog.dismiss();
                        Intent intent = new Intent(getApplicationContext(), logIn.class);
                        startActivity(intent);

                    }
                });
            }
        });
        Button noBtn = logoutdialog.findViewById(R.id.noBtn);
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutdialog.dismiss();
            }
        });
    }
    public void showImgSelectDialog(){
        imgselectdialog.getWindow().setGravity(Gravity.BOTTOM);
        imgselectdialog.show();
        Button backBtn = imgselectdialog.findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgselectdialog.dismiss();
            }
        });
        Button gallery = imgselectdialog.findViewById(R.id.gallery);
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityResult.launch(intent);
                imgselectdialog.dismiss();
            }
        });
        Button camera = imgselectdialog.findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgselectdialog.dismiss();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                activityResultPicture.launch(intent);
            }
        });
        Button defaultImg = imgselectdialog.findViewById(R.id.defaultImg);
        defaultImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String defaultImageUrl = "gs://alpha-e962a.appspot.com/profile.jpg";
                databaseReference.child("student_information").child("profileImageUrl").setValue(defaultImageUrl);
                UpdateToProfessorImage(defaultImageUrl);
                myImg.setImageResource(R.drawable.profile); // 이미지 뷰를 기본 이미지로 설정
                imgselectdialog.dismiss();
            }
        });
    }
    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==RESULT_OK && result.getData()!=null){
                        Uri uri = result.getData().getData();

                        try{
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                            myImg.setImageBitmap(bitmap);
                            uploadImageToFirebaseStorage(uri);
                        }
                        catch(FileNotFoundException e){
                            e.printStackTrace();
                        }
                        catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
    );
    private void uploadImageToFirebaseStorage(Uri imageUri) {
        // Firebase Storage 레퍼런스 설정
        StorageReference storageRef = storage.getReference().child(Storage_path + Image_FileName);

        // 이미지 업로드
        storageRef.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    // 이미지 업로드 성공
                    storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        // 다운로드 URL을 얻은 후, 이를 사용하여 이미지 표시 또는 데이터베이스에 저장
                        String imageUrl = uri.toString();
                        // 여기서 imageUrl을 사용하여 필요한 작업을 수행하세요.
                        // 데이터베이스에 저장
                        databaseReference.child("student_information").child("profileImageUrl").setValue(imageUrl);
                        UpdateToProfessorImage(imageUrl);
                    });
                })
                .addOnFailureListener(e -> {
                    // 이미지 업로드 실패
                });
    }

    ActivityResultLauncher<Intent> activityResultPicture = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>(){
                public void onActivityResult(ActivityResult result){
                    if(result.getResultCode()==RESULT_OK && result.getData()!=null) {
                        Bundle extras = result.getData().getExtras();

                        Bitmap bitmap = (Bitmap)extras.get("data");
                        myImg.setImageBitmap(bitmap);

                        uploadBitmapToFirebaseStorage(bitmap);
                    }
                }
            });

    private void uploadBitmapToFirebaseStorage(Bitmap bitmap) {
        // Firebase Storage 레퍼런스 설정
        StorageReference storageRef = storage.getReference().child(Storage_path + Image_FileName);

        // 비트맵을 JPEG 형식으로 변환
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        // 이미지 업로드
        storageRef.putBytes(data)
                .addOnSuccessListener(taskSnapshot -> {
                    // 이미지 업로드 성공
                    storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        // 다운로드 URL을 얻은 후, 이를 사용하여 이미지 표시 또는 데이터베이스에 저장
                        String imageUrl = uri.toString();
                        // 여기서 imageUrl을 사용하여 필요한 작업을 수행하세요.
                        // 데이터베이스에 저장
                        databaseReference.child("student_information").child("profileImageUrl").setValue(imageUrl);
                        UpdateToProfessorImage(imageUrl);
                    });
                })
                .addOnFailureListener(e -> {
                    // 이미지 업로드 실패
                });
    }
}