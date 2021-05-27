package com.kghapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kghapp.R;
import com.kghapp.activity.HomeActivity;
import com.kghapp.databinding.FragmentClassNotificationBinding;
import com.kghapp.databinding.FragmentConfirmBinding;


public class ConfirmFrag extends Fragment {

    FragmentConfirmBinding binding;
    private  View view;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentConfirmBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();

        binding.btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(getActivity(), HomeActivity.class));
            }
        });
        return view;
    }
}