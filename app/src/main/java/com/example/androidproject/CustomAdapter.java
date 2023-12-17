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

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private ArrayList<Record_list> cList;

    //===== 뷰홀더 클래스 =====================================================
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView when;
        private TextView who;
        private TextView what;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.when = (TextView) itemView.findViewById(R.id.when);
            this.who = (TextView) itemView.findViewById(R.id.who);
            this.what = (TextView) itemView.findViewById(R.id.what);
        }
    }
    //========================================================================

    //----- 생성자 --------------------------------------
    // 생성자를 통해서 데이터를 전달받도록 함
    public CustomAdapter (ArrayList<Record_list> dataSet) {

        this.cList=dataSet;
    }
    //--------------------------------------------------

    @NonNull
    @Override   // ViewHolder 객체를 생성하여 리턴한다.
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override   // ViewHolder안의 내용을 position에 해당되는 데이터로 교체한다.
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //holder.when.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        //holder.who.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        //holder.what.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

        holder.when.setGravity(Gravity.LEFT);
        holder.who.setGravity(Gravity.LEFT);
        holder.what.setGravity(Gravity.LEFT);

        if(cList.get(position).getDate_hour()%1 != 0) {
            holder.when.setText(String.valueOf(cList.get(position).getDate_year()) + "-"
                    + String.valueOf(cList.get(position).getDate_month()) + "-"
                    + String.valueOf(cList.get(position).getDate_day()) +
                    "(" + cList.get(position).getDate_week() + ")" +
                    " " + (int)(cList.get(position).getDate_hour())+":30");
        }
        else{
            holder.when.setText(String.valueOf(cList.get(position).getDate_year()) + "-"
                    + String.valueOf(cList.get(position).getDate_month()) + "-"
                    + String.valueOf(cList.get(position).getDate_day()) +
                    "(" + cList.get(position).getDate_week() + ")" +
                    " " + (int)(cList.get(position).getDate_hour())+":00");
        }
        holder.who.setText(cList.get(position).getProfessor_name());
        holder.what.setText(cList.get(position).getCounseling_form());
    }

    @Override   // 전체 데이터의 갯수를 리턴한다.
    public int getItemCount() {
        return (cList != null ? cList.size() : 0);
    }
}