package com.kghapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kghapp.R;
import com.kghapp.databinding.ActivityHomeBinding;
import com.kghapp.databinding.ActivityIntroductionBinding;
import com.kghapp.fragment.AboutFragment;
import com.kghapp.fragment.ContactUsFrag;
import com.kghapp.fragment.ExamFrag;
import com.kghapp.fragment.HelpFrag;
import com.kghapp.fragment.HomeFragment;
import com.kghapp.fragment.HomeStartFrag;
import com.kghapp.fragment.NotificationFrag;
import com.kghapp.fragment.TablayoutTermsFragment;

public class IntroductionActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
ActivityIntroductionBinding binding;
    private Context context;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityIntroductionBinding.inflate(getLayoutInflater());
        view=binding.getRoot();
        setContentView(view);
        context=this;

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeStartFrag())
                    .commit();
        }

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeStartFrag())
                        .commit();
            }
        });


        binding.customBottomBar.inflateMenu(R.menu.bottomintro_menu);
        binding.customBottomBar.setOnNavigationItemSelectedListener(this);
        binding.customBottomBar.getMenu().getItem(0).setCheckable(false);



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_About:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment())
                        .commit();
                binding.customBottomBar.getMenu().getItem(0).setCheckable(true);
                break;

            case R.id.action_tc:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TablayoutTermsFragment())
                        .commit();

                break;
            case R.id.action_Home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeStartFrag())
                        .commit();

                break;

            case R.id.action_contact:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContactUsFrag())
                        .commit();

                break;

            case R.id.action_help:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HelpFrag())
                        .commit();

                break;

        }

        return true;
    }
}