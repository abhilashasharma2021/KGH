package com.kghapp.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kghapp.R;
import com.kghapp.databinding.FragmentPrivacyPolicyBinding;
import com.kghapp.databinding.FragmentRefundPolicyBinding;

public class PrivacyPolicyFrag extends Fragment {
FragmentPrivacyPolicyBinding binding;
    private  View view;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentPrivacyPolicyBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();
        return view;
    }
}