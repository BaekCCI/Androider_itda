package com.example.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Student_List extends AppCompatActivity {

    private ArrayList<StudentList_list> mArrayList;
    private Adapter_student_list mAdapter;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;


    String mykey;
    int count;
    ImageButton backBtn;
    List<Integer> yourkeyList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        //Toolbar toolbar=findViewById(R.id.toolbar);
        //toolbar.setTitleTextColor(Color.WHITE);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("나의 학생 목록");

        mykey="2021145818";
        databaseReference=database.getReference(mykey);
        backBtn = findViewById(R.id.backBtn);

//        ----------------------recyclerView----------------------------------------------------
        RecyclerView mRecyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mArrayList=new ArrayList<>();

        mAdapter=new Adapter_student_list(mArrayList, this);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration=new
                DividerItemDecoration(mRecyclerView.getContext(), mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener(){
            public void onClick(View view, int position){
                StudentList_list list_click=mArrayList.get(position);

                Intent intent=new Intent(getBaseContext(), MyStudent_P.class);                //intent할 클래스명으로 바꾸기
                intent.putExtra("key", String.valueOf(list_click.getPrivate_key()));
                startActivity(intent);
            }

            public void onLongClick(View view, int position){}
        }));

//        -------------------------------------------------------------------------------------
        databaseReference.child("student_private_key").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                yourkeyList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    int temp=dataSnapshot.getValue(Integer.class);
                    yourkeyList.add(temp);
                }

                if(!yourkeyList.isEmpty()){
                    for(int i=0; i<yourkeyList.size(); i++){
                        int element= yourkeyList.get(i);

                        for(int x=1; x<=yourkeyList.size(); x++) {
                            String j = String.valueOf(x);
                            databaseReference.child("student_information").child(j).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.exists()) {
                                        StudentList_list list = snapshot.getValue(StudentList_list.class);
                                        if (element == list.getPrivate_key()) {
                                            StudentList_list call_list = new StudentList_list(list.getProfileImageUrl(), list.getName(), list.getPrivate_key());
                                            mArrayList.add(call_list);

                                            mAdapter.notifyDataSetChanged();
                                        }

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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

    //    -----------------------------------recyclerview 클릭 이벤트----------------------------------
    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private Student_List.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Student_List.ClickListener clickListener) {
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



    //    툴바 뒤로가기-----------------------------------------------------------------------
    //public boolean onOptionsItemSelected(MenuItem item){
        //switch(item.getItemId()){
            //case android.R.id.home:{
                //Intent intent = new Intent(Student_List.this, MainScreen_P.class);
                //startActivity(intent);

                //return true;
            //}
        //}
        //return super.onOptionsItemSelected(item);
    //}
}