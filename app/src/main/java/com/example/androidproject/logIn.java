package com.example.androidproject;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidproject.MainScreen_P;
import com.example.androidproject.MainScreen_S;
import com.example.androidproject.alarm_center_A;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class logIn extends AppCompatActivity {

    private FirebaseDatabase database=FirebaseDatabase.getInstance();
    private DatabaseReference DBReference=database.getReference();

    private static String private_key;
    int key;
    private static final String CHANNEL_ID = "CHANNEL_ID";
    private static final CharSequence CHANNEL_NAME = "CHANNEL_NAME";
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library

//출처: https://developer.android.com/training/notify-user/channels?hl=ko
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Log.d("createNotificationChannel","3");
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
            //Log.d("createNotificationChannel","4");
        }}
    String your_key;

    EditText login_key;
    Button btn_login;

    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // FCM SDK (and your app) can post notifications.
                } else {
                    // TODO: Inform user that that your app will not show notifications.
                }
            });

    private void askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) ==
                    PackageManager.PERMISSION_GRANTED) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            }}}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        login_key=findViewById(R.id.login_key);
        btn_login=findViewById(R.id.btn_login);

        askNotificationPermission();
        createNotificationChannel();

        alarm_center_A fun = new alarm_center_A();

        btn_login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                private_key=login_key.getText().toString();

                DBReference.child("private_key").child(private_key).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            //int you = snapshot.getValue(Integer.class);
                            //your_key=String.valueOf(you);
                            DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                            if(private_key.length() == 9){
                                database.child("private_key2").child(private_key).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String key = snapshot.getValue(String.class);
                                        database.child(key).child("professor_information").addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                Boolean a = snapshot.child("alarm").getValue(Boolean.class);

                                                database.child(key).child("alarm").addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        String t = snapshot.child("token").getValue(String.class);
                                                        int t2 = snapshot.child("value").getValue(int.class);
                                                        Log.d("갱신","교수 알림정보 갱신, 바뀐정보 = " + a.toString());
                                                        Log.d("갱신","교수 토큰정보 갱신, 바뀐정보 = " + t.toString());
                                                        Log.d("갱신","교수 알람값 갱신, 바뀐정보 = " + t2);
                                                        if(!t.equals("null")){
                                                            alarm_center_A fun = new alarm_center_A();
                                                            fun.alarm("테스트",private_key,key
                                                                    ,2000,1,1 );
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }
                                                });
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {}});}

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {}});
                                database.child(private_key).child("alarm").child("value").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        int t2 = snapshot.getValue(int.class);
                                        Log.d("갱신","학생 알람값 갱신, 바뀐정보 = " + t2);}

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {}});}
                            else if (private_key.length() == 10){
                                database.child("private_key2").child(private_key).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        int number = snapshot.child("value").getValue(int.class);
                                        for (int i = 1;i <= number; i ++ ){
                                            String key = snapshot.child(String.valueOf(i)).getValue(String.class);
                                            database.child(key).child("student_information").addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    Boolean a =  snapshot.child("alarm").getValue(Boolean.class);
                                                    database.child(key).child("alarm").addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            String t = snapshot.child("token").getValue(String.class);;
                                                            int t2 = snapshot.child("value").getValue(int.class);
                                                            Log.d("갱신","학생 알림정보 갱신, 바뀐정보 = " + a.toString());
                                                            Log.d("갱신","학생 토큰정보 갱신, 바뀐정보 = " + t.toString());
                                                            Log.d("갱신","학생 알람값 갱신, 바뀐정보 = " + t2);
                                                            if(!t.equals("null")){
                                                                alarm_center_A fun = new alarm_center_A();
                                                                fun.alarm("테스트",private_key,key
                                                                        ,2000,1,1 );
                                                            }
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {

                                                        }
                                                    });}

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {}});}}

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {}});
                                database.child(private_key).child("alarm").child("value").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        int t2 = snapshot.getValue(int.class);
                                        Log.d("갱신","교수 알람값 갱신, 바뀐정보 = " + t2);}

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }

                            FirebaseMessaging.getInstance().getToken()
                                    .addOnCompleteListener(new OnCompleteListener<String>() {
                                        @Override
                                        public void onComplete(@NonNull Task<String> task) {
                                            if (!task.isSuccessful()) {
                                                Log.w("msg", "Fetching FCM registration token failed", task.getException());
                                                return;}

                                            String token = task.getResult();
                                            database.child(private_key).child("alarm").child("token").setValue(token);
                                            database.child("token").child(private_key).setValue(token);}})
                            ;
                            database.child(private_key).child("alarm").child("value").addListenerForSingleValueEvent(
                                    new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                                            if(!snapshot.exists()) {
                                                database.child(private_key).child("alarm").child("value").setValue(0);}}
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {}}
                            );


                            try{  //private_key 정수 변환
                                key=Integer.parseInt(private_key);
                            } catch(NumberFormatException e){
                                System.out.println("Please enter a valid integer");
                            }
                            if(key<1000000000){  //학생 로그인
                                Intent intent=new Intent(getApplicationContext(), MainScreen_S.class);

////                                내 로그인 코드 전달(string 형태)
//                                intent.putExtra("private_key", private_key);//학생(내꺼)
////                                로그인 코드에 대한 상대 코드 값 전달
//                                intent.putExtra("your_key", your_key);//교수

                                startActivity(intent);
                            }
                            else{  //교수 로그인
                                Intent intent=new Intent(getApplicationContext(), MainScreen_P.class);

//                                intent.putExtra("private_key", private_key);

                                startActivity(intent);
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "로그인 코드가 잘못되었습니다.", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




            }
        });
    }

    public static String getPrivate_key() { return private_key; }
}