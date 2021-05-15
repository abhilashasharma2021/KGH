package com.kghapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;

import com.kghapp.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {
ActivitySignUpBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding=ActivitySignUpBinding.inflate(getLayoutInflater());
       setContentView(binding.getRoot());
        String textNew = "<font color=#000>Already a User?</font> <font color=#FF9801>Login Now</font>";
        binding.txtAlready.setText(Html.fromHtml(textNew));
    }
}