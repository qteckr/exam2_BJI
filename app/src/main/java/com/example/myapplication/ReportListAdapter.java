package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReportListAdapter extends RecyclerView.Adapter<ReportListAdapter.CustomViewHolder> {

    Context mContext;
    LayoutInflater mLayoutInflater;

    ArrayList<objListReport> arrayReport;

    public ReportListAdapter(ArrayList<objListReport> arrayReport, Context context) {
        this.mContext = context;
        this.arrayReport = arrayReport;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.m_tv_title.setText(String.valueOf(arrayReport.get(position).getMenu()));

        holder.m_tv_content.setText(String.valueOf(arrayReport.get(position).getCnt()));
    }

    @Override
    public int getItemCount() {
        if (arrayReport != null) {
            return arrayReport.size();
        }
        return 0;
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView m_tv_title;
        TextView m_tv_content;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.m_tv_title = itemView.findViewById(R.id.tv_title);
            this.m_tv_content = itemView.findViewById(R.id.tv_content);
        }
    }

}
