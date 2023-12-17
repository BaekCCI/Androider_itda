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
import android.util.Log;
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


public class IlJeong_S extends AppCompatActivity {

    private ArrayList<IlJeong_list> cList;
    private IlJeong_Adapter mAdapter;
    ImageButton backBtn;

    logIn loginInstance = new logIn();
    String getcode = loginInstance.getPrivate_key();
    private DatabaseReference databaseReference;

    boolean anyValidItem = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_il_jeong_s);
        backBtn = findViewById(R.id.backBtn);

        try{
            if(getcode != null){
                databaseReference = FirebaseDatabase.getInstance().getReference(getcode);
                if(databaseReference == null){
                    throw new NullPointerException("databaseReference가 null입니다.");
                }
            } else{
                Toast.makeText(IlJeong_S.this, "getcode가 null입니다.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(IlJeong_S.this, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }



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

        mAdapter=new IlJeong_Adapter(cList);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration=new
                DividerItemDecoration(mRecyclerView.getContext(), mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainScreen_S.class);
                startActivity(intent);
            }
        });



        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), mRecyclerView, new ClickListener(){
            public void onClick(View view, int position){
                IlJeong_list list_click=cList.get(position);
//                        Toast.makeText(getApplicationContext(),
//                                list_click.getC_when() + ' ' + list_click.getC_who() + ' ' +
//                                list_click.getC_what(), Toast.LENGTH_LONG).show();

                Intent intent=new Intent(getBaseContext(), DetailIlJeong_s.class);

                intent.putExtra("dd", list_click.getDate_day());
                intent.putExtra("dh", list_click.getDate_hour());
                intent.putExtra("dm", list_click.getDate_month());
                intent.putExtra("dw", list_click.getDate_week());
                intent.putExtra("dy", list_click.getDate_year());
                intent.putExtra("pm", list_click.getProfessor_name());
                intent.putExtra("pn", list_click.getProfessor_number());
                intent.putExtra("cl", list_click.getClassification());
                intent.putExtra("cc", list_click.getCounseling_content());
                intent.putExtra("cf", list_click.getCounseling_form());
                intent.putExtra("cg", list_click.getCounseling_group());
                intent.putExtra("st", list_click.getState());
                intent.putExtra("qu", list_click.getQuestion());
                startActivity(intent);
            }

            public void onLongClick(View view, int position){}
        }));
//        ---------------------------recyclerView---------------------------


        databaseReference.child("Schedule_Management/2021145818/content").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cList.clear();
                IlJeong_list read_list = snapshot.getValue(IlJeong_list.class);
                if (snapshot.exists()) {
                    if(read_list != null && !"취소".equals(read_list.getState())){
                        anyValidItem = true;
                        Log.d("FirebaseData", "Data: " + read_list.toString());
                        IlJeong_list call_list = new IlJeong_list(read_list.getDate_day(), read_list.getDate_hour(), read_list.getDate_month(), read_list.getDate_week(), read_list.getDate_year(), read_list.getProfessor_name(), read_list.getProfessor_number(), read_list.getClassification(), read_list.getCounseling_content(), read_list.getCounseling_form(), read_list.getCounseling_group(), read_list.getState(), read_list.getQuestion());
                        cList.add(call_list);
                    }

                } else {
                }
                if(!anyValidItem){
                }
                mAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }



    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private IlJeong_S.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final IlJeong_S.ClickListener clickListener) {
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