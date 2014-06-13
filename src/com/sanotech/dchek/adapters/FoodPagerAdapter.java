package com.sanotech.dchek.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sanotech.dchek.fragments.CommonFoodFragment;
import com.sanotech.dchek.fragments.CustomeFoodFragment;
 
public class FoodPagerAdapter extends FragmentPagerAdapter  {
 
    public FoodPagerAdapter(FragmentManager fm) {
        super(fm);
    }
 
    @Override
    public Fragment getItem(int index) {
 
        switch (index) {
        case 0:
            Fragment commontFragment = new CommonFoodFragment();
        	
            return commontFragment;
        case 1:
            // Games fragment activity
           // return new GamesFragment();
        	//break;
        case 2:
            // Movies fragment activity
        	Fragment costomeFragment = new CustomeFoodFragment();
        	
            return costomeFragment;
        }
 
        return null;
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 3;
    }
 
}