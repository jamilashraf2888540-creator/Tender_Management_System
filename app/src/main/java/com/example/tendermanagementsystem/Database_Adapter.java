package com.example.tendermanagementsystem.Database;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tendermanagementsystem.R;
import com.example.tendermanagementsystem.TenderModel;

import java.util.ArrayList;

public class Database_Adapter extends RecyclerView.Adapter<Database_Adapter.ViewHolder> {

    private ArrayList<TenderModel> tendersList;
    private OnItemClickListener listener;

    // واجهة للتعامل مع الضغط على العنصر والانتقال لتفاصيله
    public interface OnItemClickListener {
        void onItemClick(TenderModel tender);
    }

    public Database_Adapter(ArrayList<TenderModel> tendersList, OnItemClickListener listener) {
        this.tendersList = tendersList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // نستخدم تصميم البطاقة الجديد بدل simple_list_item_2
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tender, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TenderModel tender = tendersList.get(position);

        holder.tvTitle.setText(tender.getTitle());
        holder.tvBudget.setText("الميزانية: " + tender.getBudget());
        holder.tvDepartment.setText(tender.getDepartment());
        holder.tvClosing.setText("ينتهي: " + tender.getClosingDate());

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(tender);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tendersList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvBudget, tvDepartment, tvClosing;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvItemTitle);
            tvBudget = itemView.findViewById(R.id.tvItemBudget);
            tvDepartment = itemView.findViewById(R.id.tvItemDepartment);
            tvClosing = itemView.findViewById(R.id.tvItemClosing);
        }
    }
}
