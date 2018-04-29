package com.example.charbel.dota.services;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Charbel on 28/04/2018.
 */

public class HeroFragAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mfragmentList = new ArrayList<>();
    private final List<String> mfragmentTitleList = new ArrayList<>();

    public HeroFragAdapter(FragmentManager fm) {
        super(fm);

    }

    private void addFragment(Fragment frag, String title){
        mfragmentList.add(frag);
        mfragmentTitleList.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mfragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mfragmentList.size();
    }
}
