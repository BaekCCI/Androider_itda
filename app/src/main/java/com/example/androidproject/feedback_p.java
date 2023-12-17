package com.example.androidproject;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
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
import java.util.List;

public class feedback_p extends AppCompatActivity {

    String mykey;
    List<Integer> yourkeyList=new ArrayList<>();
    int element;
    long count;
    String element_s;
    ImageButton backBtn;

    private ArrayList<feedback_list> mArrayList;
    private CustomAdapter_feedback_p mAdapter;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_p);
        backBtn = findViewById(R.id.backBtn);

        //Toolbar record_toolbar = findViewById(R.id.feedback_p_toolbar);
        //record_toolbar.setTitleTextColor(Color.WHITE);
        //setSupportActionBar(record_toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("상담 후기");

//        Bundle extras=getIntent().getExtras();
//        mykey=extras.getString("private_key");
        mykey="2021145818";  //로그인부터해서 intent로 값 넘겨받기
        databaseReference = database.getReference(mykey);


//        ---------------------------recyclerView---------------------------
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mArrayList = new ArrayList<>();

        mAdapter = new CustomAdapter_feedback_p(mArrayList);
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new
                DividerItemDecoration(mRecyclerView.getContext(), mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);


//        ---------------------------recyclerView---------------------------

        databaseReference.child("review").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                count=snapshot.getChildrenCount();
                mArrayList.clear();
                for(int i=0; i<=count; i++){
                    String icount=String.valueOf(i);
                    databaseReference.child("review").child(icount).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                feedback_list read_list = snapshot.getValue(feedback_list.class);
                                feedback_list call_list = new feedback_list(read_list.getKey(), read_list.getReview());

                                if(call_list.getReview()!=null && !call_list.getReview().equals("")) {
                                    mArrayList.add(call_list);
                                }
                                mAdapter.notifyDataSetChanged();
                            } else {
                            }
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

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainScreen_P.class);
                startActivity(intent);
            }
        });

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