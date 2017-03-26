package com.yubing.googleplay.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yubing.googleplay.factory.FragmentFactory;

/**
 * Created by yu on 2017/3/26.
 */

public class MainAdapter extends FragmentPagerAdapter {
    private final String[] dataList;

    public MainAdapter(String[] dataList, FragmentManager fm) {
        super(fm);
        this.dataList=dataList;
    }

    /**
     * 返回对应位置的Fragment
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {

        return FragmentFactory.getInstance().getFragment(position);
    }

    @Override
    public int getCount() {
        return dataList.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return dataList[position];
    }
}
