package com.kghapp.adapter;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.kghapp.fragment.ClassNotification;
import com.kghapp.fragment.JobnotificationFragment;
import com.kghapp.fragment.NotificationFrag;

public class TabsNotificationAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public TabsNotificationAdapter(FragmentManager fm, int NoofTabs){
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                Log.e("sdcscdsad", position+"" );
                ClassNotification classNotification = new ClassNotification();
                return classNotification;
            case 1:
                Log.e("sdcscdsad", position+"" );
                JobnotificationFragment jobnotification = new JobnotificationFragment();
                return jobnotification;


        }
        return null;
    }
}
