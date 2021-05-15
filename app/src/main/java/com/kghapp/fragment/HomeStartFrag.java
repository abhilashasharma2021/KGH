package com.kghapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kghapp.R;
import com.kghapp.activity.LoginActivity;
import com.kghapp.adapter.HomeCourseListAdapter;
import com.kghapp.databinding.FragmentHomeStartBinding;
import com.kghapp.model.HomeCourseListModel;

import java.util.ArrayList;
import java.util.List;


public class HomeStartFrag extends Fragment {

    FragmentHomeStartBinding binding;
    private Context context;
    private View view;

    private HomeCourseListAdapter adapter;
    private ArrayList<HomeCourseListModel> courseList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentHomeStartBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();

        binding.llLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });



        adapter = new HomeCourseListAdapter(context, courseList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2,RecyclerView.VERTICAL,false);
        binding.rvHomeStart.setLayoutManager(mLayoutManager);
        binding.rvHomeStart.setAdapter(adapter);

        getData();
        return view;
    }

    private void getData() {

        HomeCourseListModel a = new HomeCourseListModel("A P P S C- Group II", "6 Months","₹ 6499","Telugu Medium", R.drawable.dummy);
        for (int i = 0; i <4 ; i++) {
            courseList.add(a);
        }



    }
}