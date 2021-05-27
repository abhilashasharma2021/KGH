package com.kghapp.adapter;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.kghapp.fragment.ClassNotification;
import com.kghapp.fragment.CourseMaterialFrag;
import com.kghapp.fragment.CourseVideoFrag;
import com.kghapp.fragment.JobnotificationFragment;

public class PurchasedTabCourseAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public PurchasedTabCourseAdapter(FragmentManager fm, int NoofTabs){
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
                CourseVideoFrag courseVideoFrag = new CourseVideoFrag();
                return courseVideoFrag;
            case 1:
                Log.e("sdcscdsad", position+"" );
                CourseMaterialFrag courseMaterialFrag = new CourseMaterialFrag();
                return courseMaterialFrag;


        }
        return null;
    }
}
