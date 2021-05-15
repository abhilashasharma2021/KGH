package com.kghapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.kghapp.R;
import com.kghapp.databinding.ActivityForgotPassBinding;
import com.kghapp.databinding.ActivityLoginBinding;

public class ForgotPassActivity extends AppCompatActivity {
    ActivityForgotPassBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityForgotPassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}