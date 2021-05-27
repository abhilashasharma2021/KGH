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
import com.kghapp.activity.PurchasedCourseDetailsActivity;
import com.kghapp.databinding.FragmentHomeBinding;
import com.kghapp.databinding.FragmentHomeStartBinding;


public class HomeFragment extends Fragment {
  LinearLayout ll_purchase1;
    FragmentHomeBinding binding;
    private Context context;
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentHomeBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();


        binding.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(getActivity(), PurchasedCourseDetailsActivity.class));
            }
        });

        return view;
    }
}