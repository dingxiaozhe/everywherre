package com.example.administrator.everywherretrip.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.administrator.everywherretrip.base.BaseFragment;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentPagerAdapter{
private ArrayList<BaseFragment> list;
    public FragmentAdapter(FragmentManager fm,ArrayList<BaseFragment> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
