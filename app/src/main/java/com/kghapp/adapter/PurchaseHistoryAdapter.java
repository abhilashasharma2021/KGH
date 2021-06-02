package com.kghapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kghapp.R;
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

        if (modelObject !=null){
            holder.rowpurchaselayoutBinding.txMedium.setText(modelObject.getCourseMedium());
            holder.rowpurchaselayoutBinding.txCourseName.setText(modelObject.getCourseName());


            String duration_new=modelObject.getCourseDuration();
            int duration = Integer.parseInt(duration_new);

            if(duration<30){
                holder.rowpurchaselayoutBinding.txDuration.setText(modelObject.getCourseDuration() + "Days");
            }
            else  {
                int month=duration/30;
                String durations=String.valueOf(month);
                Log.e("HomeCourseListAdapter", "onBindViewHolder: " +month);
                holder.rowpurchaselayoutBinding.txDuration.setText(durations +"Months");
            }



            /*course type me 0 online h 1 offline h*/
            String courseType=modelObject.getCourseType();
            if (courseType.equals("0")){
                holder.rowpurchaselayoutBinding.txPrice.setText("₹"+ modelObject.getCoursePrice());

            }
            else {
                holder.rowpurchaselayoutBinding.txPrice.setText("₹"+ modelObject.getOffline_cost());
            }


            Log.e("PurchaseHistoryAdapter", "onBindViewHolder: " +modelObject.getCoursePath() + modelObject.getCourseImage());
            try {
                Glide.with(mContext).load(modelObject.getCoursePath() + modelObject.getCourseImage())
                        .placeholder(R.drawable.dummy).override(250, 250)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.rowpurchaselayoutBinding.ivCourse);
            } catch (Exception e) {

            }


        }



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
