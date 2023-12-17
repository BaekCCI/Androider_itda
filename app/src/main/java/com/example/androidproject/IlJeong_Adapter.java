package com.example.androidproject;


import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Dictionary;

public class IlJeong_Adapter extends RecyclerView.Adapter<IlJeong_Adapter.ViewHolder>{

    private ArrayList<IlJeong_list> cList;

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
    public IlJeong_Adapter(ArrayList<IlJeong_list> dataSet) {
        this.cList = dataSet;
    }
    //--------------------------------------------------

    @NonNull
    @Override
    // ViewHolder 객체를 생성하여 리턴한다.
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("IlJeong_Adapter", "onCreateViewHolder: View Holder Created");

        // 새로운 ViewHolder를 생성하기 전에 상태가 "취소"인 경우 뷰를 생성하지 않고 빈 ViewHolder를 반환
        if (cList.get(viewType).getState() != null && cList.get(viewType).getState().equals("취소")) {
            View emptyView = new View(parent.getContext());
            emptyView.setLayoutParams(new ViewGroup.LayoutParams(0, 0)); // 크기를 0으로 설정하여 뷰가 보이지 않게 함
            ViewHolder emptyViewHolder = new ViewHolder(emptyView);
            return emptyViewHolder;
        }

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    // ViewHolder 안의 내용을 position에 해당되는 데이터로 교체한다.
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("IlJeong_Adapter", "onBindViewHolder: Position " + position);

        // "state"가 "취소"이면 뷰를 숨김
        if (cList.get(position).getState() != null && cList.get(position).getState().equals("취소")) {
            holder.itemView.setVisibility(View.GONE); // 해당 뷰를 숨김
            return; // 뷰를 숨기면서 메서드를 종료
        } else {
            holder.itemView.setVisibility(View.VISIBLE);

            //holder.when.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            //holder.who.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
            //holder.what.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);

            holder.when.setGravity(Gravity.LEFT);
            holder.who.setGravity(Gravity.LEFT);
            holder.what.setGravity(Gravity.LEFT);

            if(cList.get(position).getDate_hour()%1 != 0){
                holder.when.setText(cList.get(position).getDate_year()+"-"+cList.get(position).getDate_month()+"-"+cList.get(position).getDate_day()+"("+cList.get(position).getDate_week()+") "+(int)(cList.get(position).getDate_hour())+":30");
            } else {
                holder.when.setText(cList.get(position).getDate_year()+"-"+cList.get(position).getDate_month()+"-"+cList.get(position).getDate_day()+"("+cList.get(position).getDate_week()+") "+(int)(cList.get(position).getDate_hour())+":00");
            }
            holder.who.setText(cList.get(position).getProfessor_name());
            holder.what.setText(cList.get(position).getCounseling_form());
        }
    }

    @Override
    // 전체 데이터의 갯수를 리턴한다.
    public int getItemCount() {
        return (null != cList ? cList.size() : 0);
    }
}