package com.kghapp.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kghapp.R;
import com.kghapp.activity.SignUpActivity;
import com.kghapp.databinding.FragmentExamBinding;
import com.kghapp.databinding.FragmentQuesExamBinding;
import com.kghapp.others.AppConstats;
import com.kghapp.others.SharedHelper;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;


public class QuesExamFrag extends Fragment {

FragmentQuesExamBinding binding;
    private  View view;
    private Context context;
    long remainingtime;
    long totalTimer=120000;
    CountDown timer;
    String RemainTime="";
    long ms;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentQuesExamBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();

        RemainTime = SharedHelper.getKey(getActivity(), AppConstats.RemainTime);


        Log.e("vxfbfbzdgsfs", RemainTime);
        if (RemainTime.equals("")){
            timer = new CountDown(totalTimer, 1000);
            timer.start();
        }else {
            remainingtime= Long.parseLong(RemainTime);
            timer = new CountDown(remainingtime, 1000);
            timer.start();
        }

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ConfirmFrag()).commit();
            }
        });



        return view;
    }


    //countdown class
    public class CountDown extends CountDownTimer {

        public CountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
             ms = millisUntilFinished;


            String text = String.format("%d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)),
                    TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));


           binding.txTimer.setText(text);





            binding.btnPause.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               timer.cancel();
               binding.btnRestart.setVisibility(View.VISIBLE);
               binding.btnPause.setVisibility(View.GONE);
           }
       });
       binding.btnRestart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.e("ddfsdfsdfdf", totalTimer+"");
               Log.e("ddfsdfsdfdf", remainingtime+"");
               binding.btnRestart.setVisibility(View.GONE);
               binding.btnPause.setVisibility(View.VISIBLE);
               timer = new CountDown(ms, 1000);
               timer.start();
           }
       });


        }

        @Override
        public void onFinish() {
            binding.txTimer.setText("00:00");

            SharedHelper.putKey(getActivity(), AppConstats.RemainTime, "");
        }
   }

    @Override
    public void onPause() {
        super.onPause();
        RemainTime= String.valueOf(ms);

        SharedHelper.putKey(getActivity(), AppConstats.RemainTime, RemainTime);
    }

    @Override
    public void onStop() {
        super.onStop();
        RemainTime= String.valueOf(ms);

        SharedHelper.putKey(getActivity(), AppConstats.RemainTime, RemainTime);
    }
}