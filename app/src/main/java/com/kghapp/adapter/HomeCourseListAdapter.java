package com.kghapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kghapp.databinding.RowhomestartlayoutBinding;
import com.kghapp.model.HomeCourseListModel;


import java.util.ArrayList;
import java.util.List;

public class HomeCourseListAdapter extends RecyclerView.Adapter<HomeCourseListAdapter.MyViewHolder>{


    private Context mContext;
    private ArrayList<HomeCourseListModel> homestartList;

    public HomeCourseListAdapter(Context mContext, ArrayList<HomeCourseListModel> homestartList) {
        this.mContext = mContext;
        this.homestartList = homestartList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowhomestartlayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        HomeCourseListModel modelObject = homestartList.get(position);
        holder.rowhomestartlayoutBinding.txMedium.setText(modelObject.getCourseMedium());
        holder.rowhomestartlayoutBinding.txCourseName.setText(modelObject.getCourseName());
        holder.rowhomestartlayoutBinding.txDuration.setText(modelObject.getCourseDuration());
        holder.rowhomestartlayoutBinding.txPrice.setText(modelObject.getCoursePrice());
        holder.rowhomestartlayoutBinding.ivCourse.setImageResource(modelObject.getCourseImage());
    }

    @Override
    public int getItemCount() {
        return homestartList == null ? 0 : homestartList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowhomestartlayoutBinding rowhomestartlayoutBinding;
        public MyViewHolder(RowhomestartlayoutBinding rowhomestartlayoutBinding) {
            super(rowhomestartlayoutBinding.getRoot());
            this.rowhomestartlayoutBinding = rowhomestartlayoutBinding;
        }

    }
}
