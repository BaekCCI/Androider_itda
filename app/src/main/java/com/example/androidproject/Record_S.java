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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Record_S extends AppCompatActivity {

    private ArrayList<Record_list> mArrayList;
    private CustomAdapter mAdapter;
    ImageButton backBtn;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;

    logIn loginInstance = new logIn();

    String msg;
    long count;
    String mykey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_s);
        backBtn=findViewById(R.id.backBtn);

        //Toolbar record_toolbar=findViewById(R.id.feedback_p_toolbar);
        //record_toolbar.setTitleTextColor(Color.WHITE);
        //setSupportActionBar(record_toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("상담 내역");

//        Bundle extras=getIntent().getExtras();
//        mykey=extras.getString("private_key");

        mykey=loginInstance.getPrivate_key();   //로그인부터 해서 intent로 값 넘겨받기
        databaseReference = database.getReference(mykey);


//        ---------------------------recyclerView---------------------------
        RecyclerView mRecyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mArrayList=new ArrayList<>();

        mAdapter=new CustomAdapter(mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration=new
                DividerItemDecoration(mRecyclerView.getContext(), mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);


        databaseReference.child("history").child("value").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                count=snapshot.getValue(Integer.class);
                mArrayList.clear();
                for(int i=1; i<=count+1; i++){
                    String icount=String.valueOf(i);
                    databaseReference.child("history").child(icount).child("content").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    mArrayList.clear();
                            if (snapshot.exists()) {
//                    for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                Record_list read_list = snapshot.getValue(Record_list.class);
                                Record_list call_list = new Record_list(read_list.getDate_year(), read_list.getDate_month(), read_list.getDate_day(), read_list.getDate_hour(), read_list.getDate_week()
                                        , read_list.getProfessor_name(), read_list.getCounseling_form(), read_list.getReview());
                                mArrayList.add(call_list);

                                mAdapter.notifyDataSetChanged();
                            } else {}
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //recyclerview 각 항목 터치 이벤트
        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener(){
            public void onClick(View view, int position){
                Record_list list_click=mArrayList.get(position);

                Intent intent=new Intent(getBaseContext(), feedback_s.class);

                if(list_click.getDate_hour()%1 != 0) {
                    intent.putExtra("time", list_click.getDate_year() + "-"
                            + list_click.getDate_month() + "-"
                            + list_click.getDate_day()
                            + "(" + list_click.getDate_week() + ")"
                            + " " + (int)list_click.getDate_hour() + ":30");
                }
                else{
                    intent.putExtra("time", list_click.getDate_year() + "-"
                            + list_click.getDate_month() + "-"
                            + list_click.getDate_day()
                            + "(" + list_click.getDate_week() + ")"
                            + " " + (int)list_click.getDate_hour() + ":00");
                }
                intent.putExtra("professor", list_click.getProfessor_name());
                intent.putExtra("kind", list_click.getCounseling_form());
                intent.putExtra("f", list_click.getReview());

                intent.putExtra("year", list_click.getDate_year());
                intent.putExtra("month", list_click.getDate_month());
                intent.putExtra("day", list_click.getDate_day());

//                intent.putExtra("private_key", mykey);

                startActivity(intent);
            }

            public void onLongClick(View view, int position){
//                Toast.makeText(Record_S.this, "asdf", Toast.LENGTH_SHORT).show();
            }
        }));

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainScreen_S.class);
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
        private Record_S.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Record_S.ClickListener clickListener) {
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
                //finish();
                //return true;
            //}
        //}
        //return super.onOptionsItemSelected(item);
    //}

}