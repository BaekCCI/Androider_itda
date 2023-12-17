package com.example.androidproject;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.media.Image;
import android.os.AsyncTask;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Dictionary;

public class Adapter_student_list extends RecyclerView.Adapter<Adapter_student_list.ViewHolder>{

    private ArrayList<StudentList_list> cList;
    private Context mContext;

    static FirebaseStorage storage;

    //===== 뷰홀더 클래스 =====================================================
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView s_name;
        private ImageView s_photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.s_name = (TextView) itemView.findViewById(R.id.s_name);
            this.s_photo=(ImageView) itemView.findViewById(R.id.s_photo);

            s_photo.setBackground(new ShapeDrawable(new OvalShape()));
            s_photo.setClipToOutline(true);

            storage=FirebaseStorage.getInstance();
        }
    }
    //========================================================================

    //----- 생성자 --------------------------------------
    // 생성자를 통해서 데이터를 전달받도록 함
    public Adapter_student_list (ArrayList<StudentList_list> dataSet, Context mContext) {
        this.cList=dataSet;
        this.mContext=mContext;
    }
    //--------------------------------------------------

    @NonNull
    @Override   // ViewHolder 객체를 생성하여 리턴한다.
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_student_list, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    //manifest에서 인터넷 연결하기
    //gradle(Module:app)에서 glide 설정
    @Override   // ViewHolder안의 내용을 position에 해당되는 데이터로 교체한다.
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.s_name.setGravity(Gravity.LEFT);
        holder.s_name.setText(cList.get(position).getName());

        StorageReference storageRef = storage.getReferenceFromUrl(cList.get(position).getProfileImageUrl());

        // 이미지 다운로드
        final long ONE_MEGABYTE = 1024 * 1024;
        storageRef.getBytes(ONE_MEGABYTE)
                .addOnSuccessListener(bytes -> {
                    // 이미지 다운로드 성공
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    holder.s_photo.setImageBitmap(bitmap);
                })
                .addOnFailureListener(e -> {
                    // 이미지 다운로드 실패
//                    Toast.makeText(MyPg_P.this, "이미지 다운로드에 실패했습니다.", Toast.LENGTH_SHORT).show();
                });


    }

    @Override   // 전체 데이터의 갯수를 리턴한다.
    public int getItemCount() {
        return (null != cList ? cList.size() : 0);
    }


}