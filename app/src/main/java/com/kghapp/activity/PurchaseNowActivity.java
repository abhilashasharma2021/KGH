package com.kghapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kghapp.R;
import com.kghapp.databinding.ActivityAllCourseShowBinding;
import com.kghapp.databinding.ActivityPurchaseBinding;

public class PurchaseNowActivity extends AppCompatActivity {
ActivityPurchaseBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityPurchaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



       binding. btnPurchaseNow.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(PurchaseNowActivity.this, HomeActivity.class));
           }
       });

    }
}