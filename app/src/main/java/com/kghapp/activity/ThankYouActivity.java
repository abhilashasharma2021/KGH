package com.kghapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kghapp.R;
import com.kghapp.databinding.ActivityPaymentBinding;
import com.kghapp.databinding.ActivityThankYouBinding;

public class ThankYouActivity extends AppCompatActivity {
ActivityThankYouBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityThankYouBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding. btView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThankYouActivity.this, HomeActivity.class));
            }
        });
    }
}