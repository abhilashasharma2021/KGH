package com.kghapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kghapp.R;
import com.kghapp.activity.IntroductionActivity;
import com.kghapp.activity.LoginActivity;
import com.kghapp.databinding.FragmentAboutBinding;
import com.kghapp.databinding.FragmentProfileBinding;


public class AboutFragment extends Fragment {


    FragmentAboutBinding binding;
    private  View view;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentAboutBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();
        return view;
    }
}