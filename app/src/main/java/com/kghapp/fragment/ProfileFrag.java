package com.kghapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kghapp.R;
import com.kghapp.activity.HomeActivity;
import com.kghapp.adapter.HomeCourseListAdapter;
import com.kghapp.adapter.PurchaseHistoryAdapter;
import com.kghapp.databinding.FragmentPrivacyPolicyBinding;
import com.kghapp.databinding.FragmentProfileBinding;
import com.kghapp.model.HomeCourseListModel;

import java.util.ArrayList;


public class ProfileFrag extends Fragment {

   FragmentProfileBinding binding;
    private  View view;
    private Context context;
    private PurchaseHistoryAdapter adapter;
    private ArrayList<HomeCourseListModel> courseList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentProfileBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();
        binding.ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HomeActivity.class));
                getActivity().finish();
            }
        });
        String textNew = "<font color=#BED3CBCB>Do you want to ?</font> <font color=#FF9801>Change Password</font>";
        binding.txtChange.setText(Html.fromHtml(textNew));


        adapter = new PurchaseHistoryAdapter(context, courseList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(),2,RecyclerView.VERTICAL,false);
        binding.rvHistory.setLayoutManager(mLayoutManager);
        binding.rvHistory.setAdapter(adapter);

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