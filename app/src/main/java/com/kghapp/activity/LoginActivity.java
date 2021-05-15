package com.kghapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.kghapp.R;
import com.kghapp.databinding.ActivityLoginBinding;
import com.kghapp.databinding.ActivitySignUpBinding;

public class LoginActivity extends AppCompatActivity {
ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

          String text = "<font color=#000>Forgot Password ?</font> <font color=#FF9801>Click here</font>";
            binding.txtforgotPass.setText(Html.fromHtml(text));

        String textNew = "<font color=#000>New User?</font> <font color=#FF9801>Register here</font>";
        binding.txtNew.setText(Html.fromHtml(textNew));

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }
        });

        binding.txtNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
        binding.txtforgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPassActivity.class));
            }
        });

    }
}