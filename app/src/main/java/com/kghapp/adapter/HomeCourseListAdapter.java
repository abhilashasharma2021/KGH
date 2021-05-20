package com.kghapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kghapp.R;
import com.kghapp.activity.AllCourseShowActivity;
import com.kghapp.activity.CourseDetailsActivity;
import com.kghapp.databinding.RowhomestartlayoutBinding;
import com.kghapp.model.HomeCourseListModel;
import com.kghapp.others.AppConstats;
import com.kghapp.others.SharedHelper;


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
        holder.rowhomestartlayoutBinding.txPrice.setText("₹"+ modelObject.getCoursePrice());

        String duration_new=modelObject.getCourseDuration();
        int duration = Integer.parseInt(duration_new);

        if(duration<30){
            holder.rowhomestartlayoutBinding.txDuration.setText(modelObject.getCourseDuration() + "Days");
        }
        else  {
           int month=duration/30;
           String durations=String.valueOf(month);
            Log.e("HomeCourseListAdapter", "onBindViewHolder: " +month);
            holder.rowhomestartlayoutBinding.txDuration.setText(durations +"Months");
        }


        try {
            Glide.with(mContext).load(modelObject.getCoursePath() + modelObject.getCourseImage())
                    .placeholder(R.drawable.dummy).override(250, 250)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.rowhomestartlayoutBinding.ivCourse);
        } catch (Exception e) {

        }

        holder.rowhomestartlayoutBinding.rlContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedHelper.putKey(mContext, AppConstats.COURSEID, modelObject.getCourseId());
                mContext.startActivity(new Intent(mContext, CourseDetailsActivity.class));
            }
        });


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
