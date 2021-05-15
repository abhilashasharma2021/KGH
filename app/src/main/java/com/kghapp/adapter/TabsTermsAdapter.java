package com.kghapp.adapter;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.kghapp.fragment.ClassNotification;
import com.kghapp.fragment.PrivacyPolicyFrag;
import com.kghapp.fragment.RefundPolicyFrag;
import com.kghapp.fragment.TermsConditionFrag;

public class TabsTermsAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;


    public TabsTermsAdapter(FragmentManager fm, int NoofTabs){
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
                TermsConditionFrag termsConditionFrag = new TermsConditionFrag();
                return termsConditionFrag;
            case 1:
                Log.e("sdcscdsad", position+"" );
                PrivacyPolicyFrag privacyPolicyFrag = new PrivacyPolicyFrag();
                return privacyPolicyFrag;

            case 2:
                Log.e("sdcscdsad", position+"" );
                RefundPolicyFrag refundPolicyFrag = new RefundPolicyFrag();
                return refundPolicyFrag;


        }
        return null;
    }

}
