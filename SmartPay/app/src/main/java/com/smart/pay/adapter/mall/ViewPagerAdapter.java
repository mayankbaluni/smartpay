package com.smart.pay.adapter.mall;

/**
 * Created by Sandeep Londhe on 13-12-2018.
 *
 * @Email :  sandeeplondhe54@gmail.com
 * @Author :  https://twitter.com/mesandeeplondhe
 * @Skype :  sandeeplondhe54
 */


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.smart.pay.fragments.mall.SellerDetailMapViewFragmenet;
import com.smart.pay.fragments.mall.SellerDetailReviewFragment;
import com.smart.pay.fragments.mall.SellerDetailServiceListFragment;


public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String sellerId;

    public ViewPagerAdapter(FragmentManager fm, int NumOfTabs, String sellerId) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.sellerId = sellerId;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                SellerDetailServiceListFragment tab1 = new SellerDetailServiceListFragment(sellerId);
                return tab1;

//            case 1:
//                SellerDetailMapViewFragmenet tab2 = new SellerDetailMapViewFragmenet(sellerId);
//                return tab2;

            case 2:
                SellerDetailReviewFragment tab3 = new SellerDetailReviewFragment(sellerId);
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}