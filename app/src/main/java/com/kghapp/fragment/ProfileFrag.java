package com.kghapp.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kghapp.R;
import com.kghapp.activity.HomeActivity;
import com.kghapp.activity.SplashActivity;
import com.kghapp.adapter.HomeCourseListAdapter;
import com.kghapp.adapter.PurchaseHistoryAdapter;
import com.kghapp.databinding.FragmentPrivacyPolicyBinding;
import com.kghapp.databinding.FragmentProfileBinding;
import com.kghapp.model.HomeCourseListModel;
import com.kghapp.others.AppConstats;
import com.kghapp.others.SharedHelper;

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

        binding.rlLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               logout();
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

     /*   HomeCourseListModel a = new HomeCourseListModel("A P P S C- Group II", "6 Months","₹ 6499","Telugu Medium", R.drawable.dummy);
        for (int i = 0; i <4 ; i++) {
            courseList.add(a);
        }
*/


    }

    private void logout(){


        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.logout_dialog);
        Button btn_yes = dialog.findViewById(R.id.btn_yes);
        Button btn_no = dialog.findViewById(R.id.btn_no);

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedHelper.putKey(getActivity(), AppConstats.USERID, "");
                startActivity(new Intent(getActivity(), SplashActivity.class));
                getActivity().finish();
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }
    }
