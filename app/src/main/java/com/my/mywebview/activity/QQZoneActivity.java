package com.my.mywebview.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.view.gradationscrollview.GradationScrollView;


public class QQZoneActivity extends CommonBaseActivity implements GradationScrollView.ScrollViewListener{
    private TitleHelper title;
    private GradationScrollView scrollView;
    private ListView listView;
//    private TextView textView;
    private int height;
    private ImageView ivBanner;
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_qqzone);

        scrollView = (GradationScrollView) findViewById(R.id.scrollview);
        listView = (ListView) findViewById(R.id.listview);
//        textView = (TextView) findViewById(R.id.textview);
        ivBanner = (ImageView) findViewById(R.id.iv_banner);

        ivBanner.setFocusable(true);
        ivBanner.setFocusableInTouchMode(true);
        ivBanner.requestFocus();

    }

    @Override
    protected void initData() {
        super.initData();
        initScrollViewListener();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(QQZoneActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.data));
        listView.setAdapter(adapter);
    }
    /**
     * 获取顶部图片高度后，设置滚动监听
     */
    private void initScrollViewListener() {
        ViewTreeObserver vto = ivBanner.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ivBanner.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height = ivBanner.getHeight();

                scrollView.setScrollViewListener(QQZoneActivity.this);
            }
        });
    }

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 0) {   //设置标题的背景颜色
//            textView.setBackgroundColor(Color.argb((int) 0, 144,151,166));

//            title.setBGByColor(Color.argb((int) 0, 144,151,166));
            title.setTextColorLeft(Color.TRANSPARENT);
            title.setTextColorCenter(Color.TRANSPARENT);
        } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / height;
            float alpha = (255 * scale);
//            textView.setTextColor(Color.argb((int) alpha, 255,255,255));
//            textView.setBackgroundColor(Color.argb((int) alpha, 144,151,166));
            title.setTextColorCenter(Color.argb((int) alpha, 0,0,0));
            title.setTextColorLeft(Color.argb((int) alpha, 0,0,0));
            title.setBGByColor(Color.argb((int) alpha, 144,151,166));
        } else {    //滑动到banner下面设置普通颜色
//            textView.setBackgroundColor(Color.argb((int) 255, 144,151,166));
            title.setBGByColor(Color.argb((int) 255, 255,255,255));
        }
    }
    protected void initTitleBar() {
        title = new TitleHelper(this);
        title.setLineViewGone();
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextColorLeft(Color.TRANSPARENT);
        title.setTextCenter(R.string.title_nav_change);
        title.setTextColorCenter(Color.TRANSPARENT);
        title.setBGByColor(Color.TRANSPARENT);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
