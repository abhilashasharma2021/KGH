package com.kghapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kghapp.databinding.RowhomestartlayoutBinding;
import com.kghapp.databinding.RowpurchaselayoutBinding;
import com.kghapp.model.HomeCourseListModel;

import java.util.ArrayList;

public class PurchaseHistoryAdapter extends RecyclerView.Adapter<PurchaseHistoryAdapter.MyViewHolder>{


    private Context mContext;
    private ArrayList<HomeCourseListModel> homestartList;

    public PurchaseHistoryAdapter(Context mContext, ArrayList<HomeCourseListModel> homestartList) {
        this.mContext = mContext;
        this.homestartList = homestartList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowpurchaselayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        HomeCourseListModel modelObject = homestartList.get(position);
        holder.rowpurchaselayoutBinding.txMedium.setText(modelObject.getCourseMedium());
        holder.rowpurchaselayoutBinding.txCourseName.setText(modelObject.getCourseName());
        holder.rowpurchaselayoutBinding.txDuration.setText(modelObject.getCourseDuration());
        holder.rowpurchaselayoutBinding.txPrice.setText(modelObject.getCoursePrice());
        holder.rowpurchaselayoutBinding.ivCourse.setImageResource(modelObject.getCourseImage());
    }

    @Override
    public int getItemCount() {
        return homestartList == null ? 0 : homestartList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowpurchaselayoutBinding rowpurchaselayoutBinding;
        public MyViewHolder(RowpurchaselayoutBinding rowpurchaselayoutBinding) {
            super(rowpurchaselayoutBinding.getRoot());
            this.rowpurchaselayoutBinding = rowpurchaselayoutBinding;
        }

    }
}
