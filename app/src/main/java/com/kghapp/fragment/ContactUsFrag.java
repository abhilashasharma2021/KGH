package com.kghapp.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kghapp.R;
import com.kghapp.databinding.FragmentAboutBinding;
import com.kghapp.databinding.FragmentClassNotificationBinding;
import com.kghapp.databinding.FragmentContactUsBinding;


public class ContactUsFrag extends Fragment {

    FragmentContactUsBinding binding;
    private  View view;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentContactUsBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();
        return view;
    }
}