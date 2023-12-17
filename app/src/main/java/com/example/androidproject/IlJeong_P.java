package com.example.androidproject;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.ReservationApplication;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class IlJeong_P extends AppCompatActivity {

    private ArrayList<IlJeong_list_p> cList;
    private IlJeong_Adapter_p mAdapter;
    ImageButton backBtn;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    logIn loginInstance = new logIn();
    String getcode = loginInstance.getPrivate_key();
    DatabaseReference databaseReference = database.getReference(getcode);

    String nameP;
    DatabaseReference DBReference=database.getReference();
    long countH;
    boolean anyValidItem = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_il_jeong_p);
        backBtn = findViewById(R.id.backBtn);

        //Toolbar record_toolbar=findViewById(R.id.feedback_p_toolbar);
        //record_toolbar.setTitleTextColor(Color.WHITE);
        //setSupportActionBar(record_toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("상담 일정");


//        Button btnInsert=(Button)findViewById(R.id.btn);

//        ---------------------------recyclerView---------------------------
        RecyclerView mRecyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        cList=new ArrayList<>();

        mAdapter=new IlJeong_Adapter_p(cList);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration=new
                DividerItemDecoration(mRecyclerView.getContext(), mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);



        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener(){
            public void onClick(View view, int position){
                IlJeong_list_p list_click=cList.get(position);
//                        Toast.makeText(getApplicationContext(),
//                                list_click.getC_when() + ' ' + list_click.getC_who() + ' ' +
//                                list_click.getC_what(), Toast.LENGTH_LONG).show();

                Intent intent=new Intent(getBaseContext(), DetailIlJeongP.class);

                intent.putExtra("dd", list_click.getDate_day());
                intent.putExtra("dh", list_click.getDate_hour());
                intent.putExtra("dm", list_click.getDate_month());
                intent.putExtra("dw", list_click.getDate_week());
                intent.putExtra("dy", list_click.getDate_year());
                intent.putExtra("pm", list_click.getStudent_name());
                intent.putExtra("pn", list_click.getStudent_number());
                intent.putExtra("pk", list_click.getStudent_key());
                intent.putExtra("cl", list_click.getClassification());
                intent.putExtra("cc", list_click.getCounseling_content());
                intent.putExtra("cf", list_click.getCounseling_form());
                intent.putExtra("cg", list_click.getCounseling_group());
                intent.putExtra("st", list_click.getState());
                intent.putExtra("qu", list_click.getQuestion());
                startActivity(intent);
            }

            public void onLongClick(View view, int position){

                AlertDialog.Builder builder=new AlertDialog.Builder(IlJeong_P.this);

                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog, null);
                builder.setView(dialogView);

                final AlertDialog dialog = builder.create();
                // 사용자 정의 레이아웃 내부의 뷰를 참조
                TextView confirmTextView = dialogView.findViewById(R.id.confirmTextView);
                Button yesButton = dialogView.findViewById(R.id.yesButton);
                Button noButton = dialogView.findViewById(R.id.noButton);

                // 메시지 설정
                confirmTextView.setText("상담이 완료되었습니까?");
                dialog.show();

                yesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IlJeong_list_p list_click=cList.get(position);

                        logIn loginInstance = new logIn();
                        String private_key = loginInstance.getPrivate_key();

                        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

                        alarm_center_A fun = new alarm_center_A();

                        LocalDate date = LocalDate.now();
                        fun.alarm("테스트",private_key,list_click.getStudent_key()
                                ,2000,1,1 );
                        fun.alarm("후기작성",private_key,list_click.getStudent_key()
                                ,list_click.getDate_year(),list_click.getDate_month(),list_click.getDate_day() );



                        databaseReference.child("professor_information").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                nameP=snapshot.child("name").getValue(String.class);

                                DBReference.child(list_click.getStudent_key()).child("history").child("value").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot Csnapshot) {
                                        countH = Csnapshot.getValue(Integer.class);

                                        Record_list sHistory=new Record_list(list_click.getDate_year(), list_click.getDate_month(), list_click.getDate_day(), list_click.getDate_hour(), list_click.getDate_week(),
                                                nameP, list_click.getCounseling_form(), "");
                                        DBReference.child(list_click.getStudent_key()).child("history").child(String.valueOf(countH+1)).child("content").setValue(sHistory);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                                //Record_list sHistory=new Record_list(list_click.getDate_year(), list_click.getDate_month(), list_click.getDate_day(), list_click.getDate_hour(), list_click.getDate_week(),
                                //        nameP, list_click.getCounseling_form(), "");
                                //DBReference.child(list_click.getStudent_key()).child("history").child(String.valueOf(countH+1)).child("content").setValue(sHistory);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });




                        Record_list_p pHistory=new Record_list_p(list_click.getDate_year(), list_click.getDate_month(), list_click.getDate_day(), list_click.getDate_hour(), list_click.getDate_week(),
                                list_click.getStudent_name(), list_click.getCounseling_form());
                        //Record_list sHistory=new Record_list(list_click.getDate_year(), list_click.getDate_month(), list_click.getDate_day(), list_click.getDate_hour(), list_click.getDate_week(),
                        //      nameP, list_click.getCounseling_form(), "");

                        databaseReference.child("history").child("2023_2").child(list_click.getStudent_key()).child("content").setValue(pHistory);
                        //DBReference.child(list_click.getStudent_key()).child("history").child(String.valueOf(countH)).child("content").setValue(sHistory);

                        databaseReference.child("Schedule_Management").child(list_click.getStudent_key()).child("content").child("state").setValue("취소");
                        DBReference.child(list_click.getStudent_key()).child("Schedule_Management").child("2021145818").child("content").child("state").setValue("취소");
                        //cList.remove(cList.get(position));

                        Toast.makeText(IlJeong_P.this, "상담 내역에 저장되었습니다.", Toast.LENGTH_SHORT).show();

                        DBReference.child(list_click.getStudent_key()).child("history").child("value").setValue(countH+1);

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




//                IlJeong_list_p list_click=cList.get(position);
//                databaseReference.child("professor_information").addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        nameP=snapshot.child("name").getValue(String.class);
//
//                        DBReference.child(list_click.getStudent_key()).child("history").child("value").addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot Csnapshot) {
//                                countH = Csnapshot.getValue(Integer.class);
//
//                                Record_list sHistory=new Record_list(list_click.getDate_year(), list_click.getDate_month(), list_click.getDate_day(), list_click.getDate_hour(), list_click.getDate_week(),
//                                        nameP, list_click.getCounseling_form(), "");
//                                DBReference.child(list_click.getStudent_key()).child("history").child(String.valueOf(countH+1)).child("content").setValue(sHistory);
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//
//                            }
//                        });
//
//                        //Record_list sHistory=new Record_list(list_click.getDate_year(), list_click.getDate_month(), list_click.getDate_day(), list_click.getDate_hour(), list_click.getDate_week(),
//                        //        nameP, list_click.getCounseling_form(), "");
//                        //DBReference.child(list_click.getStudent_key()).child("history").child(String.valueOf(countH+1)).child("content").setValue(sHistory);
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
//
//
//
//                Record_list_p pHistory=new Record_list_p(list_click.getDate_year(), list_click.getDate_month(), list_click.getDate_day(), list_click.getDate_hour(), list_click.getDate_week(),
//                        list_click.getStudent_name(), list_click.getCounseling_form());
//                //Record_list sHistory=new Record_list(list_click.getDate_year(), list_click.getDate_month(), list_click.getDate_day(), list_click.getDate_hour(), list_click.getDate_week(),
//                //      nameP, list_click.getCounseling_form(), "");
//
//                databaseReference.child("history").child("2023_2").child(list_click.getStudent_key()).child("content").setValue(pHistory);
//                //DBReference.child(list_click.getStudent_key()).child("history").child(String.valueOf(countH)).child("content").setValue(sHistory);
//                Toast.makeText(IlJeong_P.this, "상담 내역에 저장되었습니다.", Toast.LENGTH_SHORT).show();
//
//                DBReference.child(list_click.getStudent_key()).child("history").child("value").setValue(countH+1);
//


            }
        }));
//        ---------------------------recyclerView---------------------------


        databaseReference.child("Schedule_Management").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cList.clear();
                for (DataSnapshot scheduleSnapshot : snapshot.getChildren()) {
                    // 각 노드의 content 노드에 대한 참조를 가져옵니다.
                    DataSnapshot contentSnapshot = scheduleSnapshot.child("content");

                    IlJeong_list_p read_list = contentSnapshot.getValue(IlJeong_list_p.class);
                    if (read_list != null && !"취소".equals(read_list.getState())) {
                        anyValidItem = true;
                        Log.d("FirebaseData", "Data: " + read_list.toString());
                        IlJeong_list_p call_list = new IlJeong_list_p(
                                read_list.getDate_day(),
                                read_list.getDate_hour(),
                                read_list.getDate_month(),
                                read_list.getDate_week(),
                                read_list.getDate_year(),
                                read_list.getStudent_name(),
                                read_list.getStudent_number(),
                                read_list.getStudent_key(),
                                read_list.getClassification(),
                                read_list.getCounseling_content(),
                                read_list.getCounseling_form(),
                                read_list.getCounseling_group(),
                                read_list.getState(),
                                read_list.getQuestion()
                        );
                        cList.add(call_list);
                    } else {
                    }
                }
                if(!anyValidItem){
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // 에러 처리
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainScreen_P.class);
                startActivity(intent);
            }
        });



    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }



    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private IlJeong_P.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final IlJeong_P.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
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