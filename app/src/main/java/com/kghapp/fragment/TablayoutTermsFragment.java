package com.kghapp.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.kghapp.R;
import com.kghapp.adapter.TabsNotificationAdapter;
import com.kghapp.adapter.TabsTermsAdapter;
import com.kghapp.databinding.FragmentTablayoutTermsBinding;
import com.kghapp.databinding.FragmentTermsConditionBinding;


public class TablayoutTermsFragment extends Fragment {

FragmentTablayoutTermsBinding binding;
    private  View view;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentTablayoutTermsBinding.inflate(getLayoutInflater(),container,false);
        view=binding.getRoot();
        context=getActivity();

        binding.tabLayout.addTab( binding.tabLayout.newTab().setText("Terms & Conditions "));
        binding.tabLayout.addTab( binding.tabLayout.newTab().setText("Privacy Policy"));
        binding.tabLayout.addTab( binding.tabLayout.newTab().setText("Refund Policy"));
        TabsTermsAdapter tabsTermsAdapter = new TabsTermsAdapter(getActivity().getSupportFragmentManager(), binding.tabLayout.getTabCount());
        binding.viewPager.setAdapter(tabsTermsAdapter);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener( binding.tabLayout));
        binding.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        return view;
    }
}