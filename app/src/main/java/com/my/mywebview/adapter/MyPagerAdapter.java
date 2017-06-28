package com.my.mywebview.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by WJP on 2017/4/7.
 */

public class MyPagerAdapter extends PagerAdapter{
    private List<View>dataSource;
    private Context myContext;
    public MyPagerAdapter() {
    }
    public MyPagerAdapter(Context context, List<View>data) {
        this.myContext=context;
        this.dataSource=data;
    }

    @Override
    public int getCount() {
        return dataSource==null?0:dataSource.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView(dataSource.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
//        return super.instantiateItem(container, position);
        container.addView(dataSource.get(position), 0);//添加页卡
        return dataSource.get(position);
    }
}
