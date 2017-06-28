package com.my.mywebview.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by WJP on 2017/4/10.
 */

public class ClipViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment>fragmentLists;
    private FragmentManager fragmentManager;
    private Context context;
    public ClipViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public ClipViewPagerAdapter(FragmentManager fm, Context context, List<Fragment>fragmentLists) {
        super(fm);
        this.context=context;
        this.fragmentLists=fragmentLists;
        this.fragmentManager=fm;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentLists.get(position);
    }

    @Override
    public int getCount() {
        return fragmentLists == null ? 0 : fragmentLists.size();
    }
}
