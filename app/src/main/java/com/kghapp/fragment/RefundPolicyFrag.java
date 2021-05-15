package com.kghapp.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kghapp.R;
import com.kghapp.databinding.FragmentRefundPolicyBinding;
import com.kghapp.databinding.FragmentTermsConditionBinding;


public class RefundPolicyFrag extends Fragment {

  FragmentRefundPolicyBinding binding;
    private  View view;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentRefundPolicyBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();
        return view;

    }
}