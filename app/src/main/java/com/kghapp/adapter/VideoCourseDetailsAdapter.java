package com.kghapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kghapp.R;
import com.kghapp.activity.CourseDetailsActivity;
import com.kghapp.activity.VideoCourseDetailsActivity;
import com.kghapp.databinding.RowVideoCourseDetailsLayoutBinding;
import com.kghapp.databinding.RowhomestartlayoutBinding;
import com.kghapp.model.HomeCourseListModel;
import com.kghapp.model.VideoCourseDetailsModel;
import com.kghapp.others.AppConstats;
import com.kghapp.others.SharedHelper;

import java.util.ArrayList;

public class VideoCourseDetailsAdapter extends RecyclerView.Adapter<VideoCourseDetailsAdapter.MyViewHolder>{


    private Context mContext;
    private ArrayList<VideoCourseDetailsModel> videotList;

    public VideoCourseDetailsAdapter(Context mContext, ArrayList<VideoCourseDetailsModel> videotList) {
        this.mContext = mContext;
        this.videotList = videotList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowVideoCourseDetailsLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        VideoCourseDetailsModel modelObject = videotList.get(position);


        if (modelObject !=null){
            holder.rowVideoCourseDetailsLayoutBinding.txTittle.setText(modelObject.getCourseName());



            Log.e("VedioAdapter", "onBindViewHolder: " +modelObject.getCoursePath() + modelObject.getThumbnail());



            try {
                Glide.with(mContext).load(modelObject.getCoursePath() + modelObject.getThumbnail())
                        .override(250, 250)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.rowVideoCourseDetailsLayoutBinding.ivVideo);
            } catch (Exception e) {

            }


           /* Bitmap thumbnail = ThumbnailUtils.createVideoThumbnail(modelObject.getCoursePath() + modelObject.getThumbnail(), MediaStore.Video.Thumbnails.FULL_SCREEN_KIND);
            holder.rowVideoCourseDetailsLayoutBinding.ivVideo.setImageBitmap(thumbnail);
*/
            Log.e("VedioAdapter", "onBindViewHolder: " +modelObject.getCoursePath() + modelObject.getCourseFile());
            holder.rowVideoCourseDetailsLayoutBinding.rlContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.rowVideoCourseDetailsLayoutBinding.rlContainer.setBackgroundColor(mContext.getResources().getColor(R.color.viewgrey));
                    SharedHelper.putKey(mContext, AppConstats.COURSEVIDEOPATH, modelObject.getCoursePath());
                    SharedHelper.putKey(mContext, AppConstats.COURSEVIDEOFile, modelObject.getCourseFile());
                    mContext.startActivity(new Intent(mContext, VideoCourseDetailsActivity.class));

                }
            });


        }


    }

    @Override
    public int getItemCount() {
        return videotList == null ? 0 : videotList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowVideoCourseDetailsLayoutBinding rowVideoCourseDetailsLayoutBinding;
        public MyViewHolder(RowVideoCourseDetailsLayoutBinding rowVideoCourseDetailsLayoutBinding) {
            super(rowVideoCourseDetailsLayoutBinding.getRoot());
            this.rowVideoCourseDetailsLayoutBinding = rowVideoCourseDetailsLayoutBinding;
        }

    }
}
