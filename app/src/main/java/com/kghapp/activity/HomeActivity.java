package com.kghapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kghapp.R;
import com.kghapp.databinding.ActivityHomeBinding;
import com.kghapp.fragment.AboutFragment;
import com.kghapp.fragment.ExamFrag;
import com.kghapp.fragment.HomeFragment;
import com.kghapp.fragment.HomeStartFrag;
import com.kghapp.fragment.NotificationFrag;
import com.kghapp.fragment.ProfileFrag;

import com.kghapp.others.SharedHelper;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
ActivityHomeBinding binding;
    private Context context;
    private View view;
    String stLaunchStatus="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding=ActivityHomeBinding.inflate(getLayoutInflater());
        view=binding.getRoot();
        setContentView(view);
        context=this;


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment())
                        .commit();
            }
        });


        binding.customBottomBar.inflateMenu(R.menu.bottom_menu);
        binding.customBottomBar.setOnNavigationItemSelectedListener(this);
        binding.customBottomBar.getMenu().getItem(0).setCheckable(false);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_exam:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ExamFrag())
                        .commit();

                break;

            case R.id.action_noti:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NotificationFrag())
                        .commit();
                break;

            case R.id.action_pro:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFrag())
                        .commit();
                binding.customBottomBar.getMenu().getItem(0).setCheckable(true);
                break;

            case R.id.action_home:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment())
                        .commit();

                break;

        }

        return true;
    }
}