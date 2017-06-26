package com.cloud.xc.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by cloud on 2017/6/22.
 */

public class MainPagerAdapter<T extends Fragment> extends FragmentPagerAdapter {

    private List<T> mFragments;
    private String[] title;

    public MainPagerAdapter(FragmentManager fm, List<T> mFragments, String[] title) {
        super(fm);
        this.mFragments = mFragments;
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
