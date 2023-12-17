package com.example.androidproject;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Dictionary;

public class CustomAdapter_feedback_p extends RecyclerView.Adapter<CustomAdapter_feedback_p.ViewHolder>{

    private ArrayList<feedback_list> cList;

    //===== 뷰홀더 클래스 =====================================================
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView feedback;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.feedback = (TextView) itemView.findViewById(R.id.feedback);
        }
    }
    //========================================================================

    //----- 생성자 --------------------------------------
    // 생성자를 통해서 데이터를 전달받도록 함
    public CustomAdapter_feedback_p (ArrayList<feedback_list> dataSet) {
        this.cList=dataSet;
    }
    //--------------------------------------------------

    @NonNull
    @Override   // ViewHolder 객체를 생성하여 리턴한다.
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_feedback_p, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override   // ViewHolder안의 내용을 position에 해당되는 데이터로 교체한다.
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.feedback.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);

        holder.feedback.setGravity(Gravity.LEFT);

        holder.feedback.setText(cList.get(position).getReview());
    }

    @Override   // 전체 데이터의 갯수를 리턴한다.
    public int getItemCount() {
        return (null != cList ? cList.size() : 0);
    }
}