package com.kghapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.kghapp.R;
import com.kghapp.activity.HomeActivity;
import com.kghapp.adapter.TabsNotificationAdapter;

public class NotificationFrag extends Fragment {
    TabLayout tab_layout;
    ViewPager view_pager;
    ImageView ivback;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_notification, container, false);
        tab_layout =view. findViewById(R.id.tab_layout);
        view_pager = view.findViewById(R.id.view_pager);
        ivback = view.findViewById(R.id.ivback);


        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getActivity(), HomeActivity.class));
               getActivity().finish();
            }
        });
        tab_layout.addTab(tab_layout.newTab().setText("Class Notification "));
        tab_layout.addTab(tab_layout.newTab().setText("Job Notification"));
        TabsNotificationAdapter tabsNotificationAdapter = new TabsNotificationAdapter(getActivity().getSupportFragmentManager(), tab_layout.getTabCount());
        view_pager.setAdapter(tabsNotificationAdapter);
        view_pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_layout));
        tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                view_pager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        return  view;
    }
}