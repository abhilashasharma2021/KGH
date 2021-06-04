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

import com.kghapp.activity.PurchasedCourseDetailsActivity;
import com.kghapp.databinding.RechomecourselayoutBinding;

import com.kghapp.model.HomeCoursePurchasedModel;


import java.util.ArrayList;

public class HomePurchasedCourseAdapter extends RecyclerView.Adapter<HomePurchasedCourseAdapter.MyViewHolder>{


    private Context mContext;
    private ArrayList<HomeCoursePurchasedModel> homestartList;

    public HomePurchasedCourseAdapter(Context mContext, ArrayList<HomeCoursePurchasedModel> homestartList) {
        this.mContext = mContext;
        this.homestartList = homestartList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RechomecourselayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        HomeCoursePurchasedModel modelObject = homestartList.get(position);

        Log.e("gjnghmnh", "onBindViewHolder: " +modelObject.getCourseMedium());
        Log.e("gjnghmnh", "onBindViewHolder: " +modelObject.getCourseName());
        Log.e("gjnghmnh", "onBindViewHolder: " +modelObject.getCoursePath() + modelObject.getCourseImage());

        holder.rechomecourselayoutBinding.txMedium.setText(modelObject.getCourseMedium());
        holder.rechomecourselayoutBinding.txCourseName.setText(modelObject.getCourseName());

        try {
            Glide.with(mContext).load(modelObject.getCoursePath() + modelObject.getCourseImage())
                    .placeholder(R.drawable.dummy).override(250, 250)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.rechomecourselayoutBinding.ivCourse);
        } catch (Exception e) {

        }

        holder.rechomecourselayoutBinding.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, PurchasedCourseDetailsActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return homestartList == null ? 0 : homestartList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RechomecourselayoutBinding rechomecourselayoutBinding;
        public MyViewHolder(RechomecourselayoutBinding rechomecourselayoutBinding) {
            super(rechomecourselayoutBinding.getRoot());
            this.rechomecourselayoutBinding = rechomecourselayoutBinding;
        }

    }
}
