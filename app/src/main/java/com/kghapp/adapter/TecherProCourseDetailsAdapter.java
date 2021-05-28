package com.kghapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kghapp.R;
import com.kghapp.activity.VideoCourseDetailsActivity;
import com.kghapp.databinding.RowTeacherproCourseDetailsLayoutBinding;
import com.kghapp.databinding.RowVideoCourseDetailsLayoutBinding;
import com.kghapp.model.TeacherProCourseDetailsModel;
import com.kghapp.model.VideoCourseDetailsModel;
import com.kghapp.others.AppConstats;
import com.kghapp.others.SharedHelper;

import java.util.ArrayList;

public class TecherProCourseDetailsAdapter extends RecyclerView.Adapter<TecherProCourseDetailsAdapter.MyViewHolder>{


    private Context mContext;
    private ArrayList<TeacherProCourseDetailsModel> teacherList;

    public TecherProCourseDetailsAdapter(Context mContext, ArrayList<TeacherProCourseDetailsModel> teacherList) {
        this.mContext = mContext;
        this.teacherList = teacherList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowTeacherproCourseDetailsLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TeacherProCourseDetailsModel modelObject = teacherList.get(position);

        if (modelObject !=null){

            Log.e("fdhbfgng", "onBindViewHolder: " +modelObject.getTeacherName());
            Log.e("fdhbfgng", "onBindViewHolder: " +modelObject.getQualification());
            holder.rowTeacherproCourseDetailsLayoutBinding.txTName.setText(modelObject.getTeacherName());
            holder.rowTeacherproCourseDetailsLayoutBinding.txtgrad.setText(modelObject.getQualification());

            String description=modelObject.getExeprience();




            String plainText = Html.fromHtml(description).toString();
            holder.rowTeacherproCourseDetailsLayoutBinding.txExper.setText(plainText);


            Log.e("tgjfrngf", "onBindViewHolder: " +modelObject.getPath() + modelObject.getImage());

            try {
                Glide.with(mContext).load(modelObject.getPath() + modelObject.getImage())
                        .placeholder(R.drawable.techer_dummy).override(250, 250)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.rowTeacherproCourseDetailsLayoutBinding.ivProfile);
            } catch (Exception e) {

            }


        }



    }

    @Override
    public int getItemCount() {
        return teacherList == null ? 0 : teacherList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowTeacherproCourseDetailsLayoutBinding rowTeacherproCourseDetailsLayoutBinding;
        public MyViewHolder(RowTeacherproCourseDetailsLayoutBinding rowTeacherproCourseDetailsLayoutBinding) {
            super(rowTeacherproCourseDetailsLayoutBinding.getRoot());
            this.rowTeacherproCourseDetailsLayoutBinding = rowTeacherproCourseDetailsLayoutBinding;
        }

    }
}
