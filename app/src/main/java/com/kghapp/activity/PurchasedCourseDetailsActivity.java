package com.kghapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.kghapp.R;
import com.kghapp.adapter.TabsNotificationAdapter;
import com.kghapp.databinding.ActivityAllCourseShowBinding;
import com.kghapp.databinding.ActivityPurchasedCourseDetailsBinding;

public class PurchasedCourseDetailsActivity extends AppCompatActivity {
ActivityPurchasedCourseDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityPurchasedCourseDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.tablayout.addTab(binding.tablayout.newTab().setText("Course Videos "));
        binding.tablayout.addTab(binding.tablayout.newTab().setText("Course Materials"));
        TabsNotificationAdapter tabsNotificationAdapter = new TabsNotificationAdapter(this.getSupportFragmentManager(), binding.tablayout.getTabCount());
        binding.viewPager.setAdapter(tabsNotificationAdapter);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tablayout));
        binding.tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}