package com.example.androidproject;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class alarm_center_A extends AppCompatActivity {
    public static Context mContext;
    ImageButton backBtn;
    public static class professor_information{

        public int private_key = 1;

        public String name = "name";
        public String laboratory_location = "laboratory_location";
        public String belong = "belong";
        public String phone_number = "phone_number";
        public String email = "email";
        public String Zoom_link = "Zoom_link";
        public String major = "major";
        public String profileImageUrl = "profileImageUrl";
        public Boolean alarm = true;
        public professor_information(int private_key, String name, String laboratory_location,String belong,
                                     String phone_number, String email, String Zoom_link, Boolean alarm
                ,String major, String profileImageUrl){
            this.private_key = private_key;
            this.name = name;
            this.laboratory_location = laboratory_location;
            this.belong = belong;
            this.phone_number = phone_number;
            this.email = email;
            this.Zoom_link = Zoom_link;
            this.alarm = alarm;
            this.major = major;
            this.profileImageUrl = profileImageUrl;
        }
        private void dummy(){}
        public professor_information(){}
    }
    public static class student_information{
        public int private_key = 1;
        public String name = "name";
        public int student_number = 1;
        public String state = "state";
        public String belong = "belong";
        public String subMajor = "subMajor";
        public String profileImageUrl = "profileImageUrl";
        public String phone_number = "phone_number";
        public String email = "email";
        public Boolean alarm = true;
        public student_information(int private_key, String name, int student_number
                , String belong, String subMajor, String phone_number, String email,boolean alarm
                , String state, String profileImageUrl){
            this.private_key = private_key;
            this.name = name;
            this.student_number = student_number;
            this.belong = belong;
            this.subMajor = subMajor;
            this.phone_number = phone_number;
            this.email = email;
            this.alarm = alarm;
            this.state = state;
            this.profileImageUrl = profileImageUrl;
        }

        public void dummy(){}
        public student_information(){}
    }
    public static class alarm_notify {

        public String title = "title";
        public String msg = "msg";

        public alarm_notify(String title, String msg) {
            this.title = title;
            this.msg = msg;
        }

        public alarm_notify() {
        }
    }

    ListView lv_alarm;
    SimpleAdapter simpleAdapter;

    public class alarm {
        public String Classification;
        public String sender_id;
        public String recipient_id;
        public String msg1;
        public String msg2;
        public String date;
        public String title;

        public alarm(String Classification, String sender_id, String recipient_id
                , String msg1, String msg2, String date, String title) {
            this.Classification = Classification;
            this.sender_id = sender_id;
            this.recipient_id = recipient_id;
            this.msg1 = msg1;
            this.msg2 = msg2;
            this.date = date;
            this.title = title;
        }

        public alarm() {}
    }
    public static final String TAG = "YOUR-TAG-NAME";
    public static final String CHANNEL_ID = "CHANNEL_ID";
    public static final CharSequence CHANNEL_NAME = "CHANNEL_NAME";
    // Declare the launcher at the top of your Activity/Fragment:
    public static final String FCM_MESSAGE_URL = "https://fcm.googleapis.com/fcm/send";
    public static final String SERVER_KEY = "AAAADl46TdM:APA91bHHAFkqqd__9NiRq6UmX2occYSS2DvRTOsW5hrakumLgP3bWBjYSzAkoGqLBCYyqyrPMtivligsemZVhf2JoryIPRPO94889X0J_B3LdxSu64idd2bNO3VlPMS6pfEb2tQuDRMP";
    private void sendPostToFCM(final String token, final String title, final String message) {
        //출처:https://m.blog.naver.com/eo930827/221632468403
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        //주의
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    JSONObject notification = new JSONObject();
                    JSONObject root = new JSONObject();
//                    notification.put("body", message);
//                    notification.put("title", title);
//                    root.put("notification", notification);
                    JSONObject data2 = new JSONObject();
                    data2.put("notifid", 2025090001);
                    data2.put("msg", message);
                    data2.put("title2", title);
                    root.put("to", token);
                    root.put("data", data2);
                    root.put("priority", "high");
                    //root.put("time_to_live", 8640);
                    URL url = new URL(FCM_MESSAGE_URL);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.addRequestProperty("Authorization", "key=" + SERVER_KEY);
                    conn.setRequestProperty("Accept", "application/json");
                    conn.setRequestProperty("Content-type", "application/json");
                    OutputStream os = conn.getOutputStream();
                    os.write(root.toString().getBytes("utf-8"));
                    os.flush();
                    conn.getResponseCode();
                    Log.d("sendmessage", "송신 절차 완료");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }



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
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.alarm_center);
        askNotificationPermission();

        backBtn = findViewById(R.id.backBtn);
        mContext = this;

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }});
        lv_alarm = findViewById(R.id.lv_alarm);
        ArrayList<HashMap<String, String>> datas = new ArrayList<HashMap<String, String>>();
        final HashMap<String, String>[] map = new HashMap[]{new HashMap<String, String>()};

        logIn loginInstance = new logIn();
        String private_key = loginInstance.getPrivate_key();//개인키
        String token = String.valueOf(FirebaseMessaging.getInstance().getToken());
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child(private_key).child("alarm").child("value").addListenerForSingleValueEvent(
                new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        GenericTypeIndicator<Long> t = new GenericTypeIndicator<Long>() {};
                        int temp = snapshot.getValue(t).intValue();
                        Log.d("test40","first test iteration " + String.valueOf(temp));}

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}});
        int temp2 = 0;

        database.child(private_key).child("alarm").child("value").addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Log.d("tag2",private_key + " + ");
                        GenericTypeIndicator<Long> t = new GenericTypeIndicator<Long>() {};
                        int temp = snapshot.getValue(t).intValue();
                        Log.d("tag20","second iteraton " + private_key + " + "  + String.valueOf(temp));
                        String iter;
                        for(int i = temp; i >= 1; i--) {
                            //Log.d("iter",String.valueOf(i));
                            iter = Integer.toString(i);
                            DatabaseReference database2 = FirebaseDatabase.getInstance().getReference();
                            database2.child(private_key).child("alarm").child(iter).child("notify")
                                    .addListenerForSingleValueEvent(
                                            new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    alarm_notify ala = snapshot.getValue(alarm_notify.class);

                                                    map[0].put("title", ala.title);
                                                    map[0].put("msg", ala.msg);
                                                    datas.add(map[0]);
                                                    map[0] = new HashMap<>();

                                                    simpleAdapter.notifyDataSetChanged();}

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {}});}

                        simpleAdapter = new SimpleAdapter(alarm_center_A.this, datas, android.R.layout.simple_list_item_2,
                                new String[]{"title", "msg"},
                                new int[]{android.R.id.text1, android.R.id.text2});
                        lv_alarm.setAdapter(simpleAdapter);


                        lv_alarm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                if(Objects.equals(datas.get(position).get("title"), "경고")){
                                    //학생이 상담신청하는 클래스
                                    Intent intent = new Intent(getApplicationContext(),student_information.class);
                                    startActivity(intent);
                                }
                                else if (Objects.equals(datas.get(position).get("title"), "상담취소") && private_key.length() == 9) {

                                    //학생이 상담신청하는 클래스
                                    Intent intent = new Intent(getApplicationContext(), ConsultRequest_S_1.class);
                                    startActivity(intent);
                                }
                                else if (Objects.equals(datas.get(position).get("title"), "상담취소") && private_key.length() == 10) {

                                    //교수 상담일정 확인창으로
                                    Intent intent = new Intent(getApplicationContext(),IlJeong_P.class);
                                    startActivity(intent);
                                }
                                else if (Objects.equals(datas.get(position).get("title"), "상담신청") && private_key.length() == 9) {
                                    //교수 상담일정 확인창으로
                                    Intent intent = new Intent(getApplicationContext(),IlJeong_S.class);
                                    startActivity(intent);
                                }
                                else if (Objects.equals(datas.get(position).get("title"), "상담신청") && private_key.length() == 10) {
                                    //교수 상담일정 확인창으로
                                    Intent intent = new Intent(getApplicationContext(),IlJeong_P.class);
                                    startActivity(intent);
                                }
                                else if (Objects.equals(datas.get(position).get("title"), "후기작성")) {
                                    // 후기작성 클래스로
                                    Intent intent = new Intent(getApplicationContext(),Record_S.class);
                                    startActivity(intent);}}});}
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {}});

        lv_alarm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        //alarm("상담취소","2021145818","187740328",2023,12,02);
    }

    public void alarm4 (Boolean student_professor, String Classification, String sender_name, String receiver_name,  String token,
                        String key, int date_year, int date_month, int date_hour){
        LocalDate date = LocalDate.now();
        int date_year2 = date.getYear();
        int date_month2 = date.getMonthValue();
        int date_hour2 = date.getDayOfMonth();


        if(student_professor) {//to student
            DatabaseReference database11 = FirebaseDatabase.getInstance().getReference();
            database11.child(key).child("alarm").child("value").addListenerForSingleValueEvent(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            GenericTypeIndicator<Long> t = new GenericTypeIndicator<Long>() {};
                            int temp = snapshot.getValue(t).intValue();
                            Log.d("알람4(alarm_value)", "교수 -> 학생   sender_name = " + sender_name + " receiver_key = " + key + "alarm_value = " + temp);
                            Log.d("tag21","alarm function + key = " + key +" iteratoin value = " + String.valueOf(temp));

                            //}
                            if(Objects.equals(Classification, "경고")){
                                String msg = "상담을 신청하지 않아" + sender_name +
                                        "교수님이 경고를 보냈습니다"+ '\n' +" 빠른 시일 내에 상담을 신청해주시기 바랍니다."
                                        + '\n' + date_year2 + "." + date_month2 + "." + date_hour2 + ".";
                                alarm_notify ala = new alarm_notify("경고",msg);
                                DatabaseReference database12 = FirebaseDatabase.getInstance().getReference();
                                database12.child(key).child("alarm").child(Integer.toString(temp+1)).child("notify").setValue(ala);
                                database12.child(key).child("alarm").child("value").setValue(temp+1);
                                database12.child(key).child("student_information").addListenerForSingleValueEvent(

                                        new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                Boolean b = snapshot.child("alarm").getValue(Boolean.class);

                                                if(b && !Objects.equals(token, "null") && !sender_name.equals("A")){
                                                    sendPostToFCM(token, "경고" , msg);}}
                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {}});}
                            else if(Objects.equals(Classification, "상담취소")){

                                String msg = receiver_name + "님이 신청하신 상담("
                                        + Integer.toString(date_year) + ". " +Integer.toString(date_month) + "."
                                        + Integer.toString(date_hour) +")이 취소되었습니다." + '\n' + "다시 신청해주시기 바랍니다."
                                        + '\n' + date_year2 + "." + date_month2 + "." + date_hour2 + ".";
                                alarm_notify ala = new alarm_notify("상담취소",msg);
                                DatabaseReference database12 = FirebaseDatabase.getInstance().getReference();
                                database12.child(key).child("alarm").child(Integer.toString(temp+1)).child("notify").setValue(ala);
                                database12.child(key).child("alarm").child("value").setValue(temp+1);
                                database12.child(key).child("student_information").addListenerForSingleValueEvent(

                                        new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                Boolean b = snapshot.child("alarm").getValue(Boolean.class);
                                                Log.d("tag51","교수 -> 학생 상담취소 알람" + token + b.toString());
                                                if(b&& !Objects.equals(token, "null") && !sender_name.equals("A")){
                                                    sendPostToFCM(token, "상담취소" , msg);}}

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {}});}
                            else if(Objects.equals(Classification, "테스트")){
                                alarm_notify ala = new alarm_notify("테스트","테스트");
                                sendPostToFCM(token, "후기작성" , "테스트");}
                            else if(Objects.equals(Classification, "후기작성")){
                                String msg = sender_name + "교수님과의 상담이" +
                                        "완료되었습니다." + '\n' + "상담 후기를 작성해 주시기 바랍니다."
                                        + '\n' + date_year2 + "." + date_month2 + "." + date_hour2 + ".";
                                alarm_notify ala = new alarm_notify("후기작성",msg);
                                DatabaseReference database12 = FirebaseDatabase.getInstance().getReference();
                                database12.child(key).child("alarm").child(Integer.toString(temp+1)).child("notify").setValue(ala);
                                database12.child(key).child("alarm").child("value").setValue(temp+1);
                                database12.child(key).child("student_information").addListenerForSingleValueEvent(

                                        new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                Boolean b = snapshot.child("alarm").getValue(Boolean.class);
                                                Log.d("tag52-1","교수 -> 학생 상담취소 알람 token = " + token + "Boolean = " + b.toString());
                                                if(b&& !Objects.equals(token, "null") && !sender_name.equals("A")){}}

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {}});
                                database12.child(key).child("student_information").addListenerForSingleValueEvent(

                                        new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                Boolean b = snapshot.child("alarm").getValue(Boolean.class);
                                                Log.d("tag52-2","교수 -> 학생 상담취소 알람 token = " + token + "Boolean = " + b.toString());
                                                if(b&& !Objects.equals(token, "null") && !sender_name.equals("A")){
                                                    sendPostToFCM(token, "후기작성" , msg);}}

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {}});}}
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {}});}
        else{//to professor
            DatabaseReference database11 = FirebaseDatabase.getInstance().getReference();
            database11.child(key).child("alarm").child("value")
                    .addListenerForSingleValueEvent(
                            new ValueEventListener() {

                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    GenericTypeIndicator<Long> t = new GenericTypeIndicator<Long>() {};
                                    int temp = snapshot.getValue(t).intValue();
                                    Log.d("알람4(alarm_value)", "학생 -> 교수   sender_name = " + sender_name + "   receiver_key = " + key + "alarm_value = " + temp);
                                    Log.d("tag21","alarm function + key = " + key +" iteratoin value = " + String.valueOf(temp));

                                    if(Objects.equals(Classification, "상담취소")){
                                        String msg = sender_name + "이(가) 신청한 상담("
                                                + Integer.toString(date_year) + ". " +Integer.toString(date_month) + "."
                                                + Integer.toString(date_hour) +")이 취소되었습니다."
                                                + '\n' + date_year2 + "." + date_month2 + "." + date_hour2 + ".";
                                        alarm_notify ala = new alarm_notify("상담취소",msg);
                                        DatabaseReference database12 = FirebaseDatabase.getInstance().getReference();
                                        database12.child(key).child("alarm").child(Integer.toString(temp+1)).child("notify").setValue(ala);
                                        database12.child(key).child("alarm").child("value").setValue(temp+1);
                                        database12.child(key).child("professor_information").addListenerForSingleValueEvent(
                                                new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        Boolean b = snapshot.child("alarm").getValue(Boolean.class);
                                                        Log.d("tag53","학생 -> 교수 상담취소 알람 token = " + token + "Boolean = " + b.toString());
                                                        if(b&& !Objects.equals(token, "null") && !sender_name.equals("A")){
                                                            Log.d("tag53-2","학생 -> 교수 상담취소 알람 token = " + token  + "sender_name = "+ sender_name);
                                                            sendPostToFCM(token, "상담취소" , msg);}}

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {}});}
                                    else if(Objects.equals(Classification, "테스트")){
                                        alarm_notify ala = new alarm_notify("테스트","테스트");
                                        sendPostToFCM(token, "후기작성" , "테스트");}
                                    else if (Objects.equals(Classification, "상담신청")) {
                                        String msg = sender_name + "이(가) 상담을 신청했습니다. 일시:("
                                                + Integer.toString(date_year) + ". " +Integer.toString(date_month) + "." + Integer.toString(date_hour) +")"
                                                + '\n' + date_year2 + "." + date_month2 + "." + date_hour2 + ".";
                                        alarm_notify ala = new alarm_notify("상담신청",msg);
                                        DatabaseReference database12 = FirebaseDatabase.getInstance().getReference();
                                        database12.child(key).child("alarm").child(Integer.toString(temp+1)).child("notify").setValue(ala);
                                        database12.child(key).child("alarm").child("value").setValue(temp+1);
                                        database12.child(key).child("professor_information").addListenerForSingleValueEvent(

                                                new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        Boolean b = snapshot.child("alarm").getValue(Boolean.class);
                                                        Log.d("tag54","학생 -> 교수 상담신청 알람 token = " + token + "Boolean = " + b.toString() + "sender_name = "+ sender_name);
                                                        if(b&& !Objects.equals(token, "null") && !sender_name.equals("A")){
                                                            Log.d("tag54-2","학생 -> 교수 상담신청 알람 token = " + token + "Boolean = " + b.toString() + "sender_name = "+ sender_name + "token = "  + token);
                                                            sendPostToFCM(token, "상담신청" , msg);
                                                        }}

                                                    @Override
                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }});}}

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(alarm_center_A.this, "메시지를 받는 사람의 알람 초기설정이 되지 않았습니다."
                                            , Toast.LENGTH_SHORT).show();
                                }});}

    }

    public void alarm3(Boolean student_professor, String Classification, String sender_name, String token, String key,
                       int date_year, int date_month, int date_hour){//학생이름 가져오기



        if(student_professor) {//professor to student
            DatabaseReference database10 = FirebaseDatabase.getInstance().getReference();
            database10.child(key).child("student_information")
                    .addListenerForSingleValueEvent(
                            new ValueEventListener() {//추가
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String student_name = snapshot.child("name").getValue(String.class);
                                    alarm4 (student_professor, Classification, sender_name, student_name , token
                                            , key, date_year, date_month,date_hour);}

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(alarm_center_A.this, "메시지를 받는 사람의 이름 정보를 찾을 수 없습니다."
                                            , Toast.LENGTH_SHORT).show();
                                }});

        }
        else{//student to professor
            DatabaseReference database10 = FirebaseDatabase.getInstance().getReference();
            database10.child(key).child("professor_information")
                    .addListenerForSingleValueEvent(
                            new ValueEventListener() {//교수이름 가져오기
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String professor_name = snapshot.child("name").getValue(String.class);
                                    alarm4 (student_professor, Classification, sender_name, professor_name , token
                                            , key, date_year, date_month,date_hour);}
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(alarm_center_A.this, "메시지를 받는 사람의 이름 정보를 찾을 수 없습니다."
                                            , Toast.LENGTH_SHORT).show();
                                }});}


    }


    public void alarm2(Boolean student_professor, String Classification, String sender_name, String key,
                       int date_year, int date_month, int date_hour){

        DatabaseReference database10 = FirebaseDatabase.getInstance().getReference();
        database10.child(key).child("alarm").child("token")
                .addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Log.d("tag11", key);
                                GenericTypeIndicator<String> t = new GenericTypeIndicator<String>() {};
                                String temp = snapshot.getValue(t);
                                Log.d("알람2(token)", "공통   sender_name = " + sender_name + " (key)receiver_key = " + key + "token = " + temp);

                                alarm3 (student_professor, Classification, sender_name, temp
                                        , key, date_year, date_month, date_hour);}

                            @Override public void onCancelled(@NonNull DatabaseError error) {
                                Toast.makeText(alarm_center_A.this, "메시지를 받는 사람의 토큰 정보를 찾을 수 없습니다."
                                        , Toast.LENGTH_SHORT).show();
                            }});
    }

    public void alarm1(Boolean student_professor, String Classification, String sender_key, String receiver_key,
                       int date_year, int date_month, int date_hour){//sender이름 가져오기
        if(student_professor) {//professor to student
            DatabaseReference database10 = FirebaseDatabase.getInstance().getReference();
            database10.child(sender_key).child("professor_information")
                    .addListenerForSingleValueEvent(
                            new ValueEventListener() {//추가
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String professor_name = snapshot.child("name").getValue(String.class);
                                    Log.d("알람1(sender_name)", "교수 -> 학생   sender_key = " + sender_key + "receiver_key = " + receiver_key + "professor_name = " + professor_name);
                                    alarm2(student_professor, Classification, professor_name, receiver_key
                                            , date_year, date_month, date_hour);}

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(alarm_center_A.this, "메시지를 받는 사람의 이름 정보를 찾을 수 없습니다."
                                            , Toast.LENGTH_SHORT).show();
                                }});

        }
        else{//student to professor
            DatabaseReference database10 = FirebaseDatabase.getInstance().getReference();
            database10.child(sender_key).child("student_information")
                    .addListenerForSingleValueEvent(
                            new ValueEventListener() {//이름 가져오기
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String student_name = snapshot.child("name").getValue(String.class);
                                    Log.d("알람1(sender_name)", "학생 -> 교수   sender_key = " + sender_key + "receiver_key = " + receiver_key + "student_name = " + student_name);
                                    alarm2 (student_professor, Classification, student_name, receiver_key
                                            , date_year, date_month, date_hour);}

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(alarm_center_A.this, "메시지를 받는 사람의 이름 정보를 찾을 수 없습니다."
                                            , Toast.LENGTH_SHORT).show();
                                }});}
    }
    public void alarm(String Classification, String sender_key, String receiver_key,
                      int date_year, int date_month, int date_hour){//sender이름 가져오기
        //professor to student

        if(!Objects.equals(sender_key, "111111111") || !Objects.equals(receiver_key, "1111111111")) {
            if (sender_key.length() > receiver_key.length()) {//professor to student
                Log.d("알람_시작", "교수 -> 학생   sender_key = " + sender_key + "receiver_key = " + receiver_key);
                alarm1(true, Classification, sender_key, receiver_key
                        , date_year, date_month, date_hour);
            } else {//student to professor
                Log.d("알람_시작", "학생 -> 교수   sender_key = " + sender_key + "receiver_key = " + receiver_key);
                alarm1(false, Classification, sender_key, receiver_key
                        , date_year, date_month, date_hour);}}}
}