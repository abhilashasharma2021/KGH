package com.kghapp.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kghapp.R;
import com.kghapp.databinding.FragmentAboutBinding;
import com.kghapp.databinding.FragmentContactUsBinding;
import com.kghapp.databinding.FragmentExamBinding;


public class ExamFrag extends Fragment {
    FragmentExamBinding binding;
    private  View view;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentExamBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();
        return view;

       /* binding=FragmentExamBinding.inflate(getLayoutInflater());
        view=binding.getRoot();
        getActivity().setContentView(view);*/
      /*  txt_exam=view.findViewById(R.id.txt_exam);
       txt_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ConfirmFrag())
                        .commit();
            }
        });*/




    }
}