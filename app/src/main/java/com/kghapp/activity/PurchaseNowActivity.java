package com.kghapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.kghapp.R;
import com.kghapp.databinding.ActivityAllCourseShowBinding;
import com.kghapp.databinding.ActivityPurchaseBinding;
import com.kghapp.others.AppConstats;
import com.kghapp.others.SharedHelper;

public class PurchaseNowActivity extends AppCompatActivity {
ActivityPurchaseBinding binding;
String getPuchasedCourseName="",getPuchasedCourseMedium="",getPuchasedCourseOfflineCost="",getPuchasedCourseOnlineCost="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityPurchaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getPuchasedCourseName = SharedHelper.getKey(getApplicationContext(), AppConstats.PuchasedCourseName);
        getPuchasedCourseMedium = SharedHelper.getKey(getApplicationContext(), AppConstats.PuchasedCourseMedium);
        getPuchasedCourseOfflineCost = SharedHelper.getKey(getApplicationContext(), AppConstats.PuchasedCourseOfflineCost);
        getPuchasedCourseOnlineCost = SharedHelper.getKey(getApplicationContext(), AppConstats.PuchasedCourseOnlineCost);


        Log.e("PurchaseNowActivity", "getPuchasedCourseName: " +getPuchasedCourseName);
        Log.e("PurchaseNowActivity", "getPuchasedCourseMedium: " +getPuchasedCourseMedium);
        Log.e("PurchaseNowActivity", "getPuchasedCourseOfflineCost: " +getPuchasedCourseOfflineCost);
        Log.e("PurchaseNowActivity", "getPuchasedCourseOnlineCost: " +getPuchasedCourseOnlineCost);
       binding. btnPurchaseNow.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(PurchaseNowActivity.this, PaymentActivity.class));
           }
       });

    }
}