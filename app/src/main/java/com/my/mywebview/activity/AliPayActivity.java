package com.my.mywebview.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.log.LogManager;
import com.my.mywebview.view.gradationscrollview.GradationScrollView;
import com.my.mywebview.view.gradationscrollview.GradationScrollView.ScrollViewListener;

public class AliPayActivity extends CommonBaseActivity implements ScrollViewListener{
    private GradationScrollView scrollView;
    private ListView listView;
    private int height1,height2;
    private RelativeLayout rlayoutUpTitle;
    private LinearLayout llayoutRoot,llayoutHeader1,llayoutHeader1_1,llayoutDown,llayoutTitleHead;
    private ImageView imgHeader1_1,imgHeader1_2,imgHeader1_3;
    private EditText edtHeader1_1;
    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_ali_pay);

        scrollView = (GradationScrollView) findViewById(R.id.gra_scrollview);
        listView = (ListView) findViewById(R.id.listview);
        rlayoutUpTitle = (RelativeLayout) findViewById(R.id.rlayout_title_uproot);
        llayoutRoot = (LinearLayout) findViewById(R.id.llayout_root);
        llayoutHeader1 = (LinearLayout) findViewById(R.id.header1);
        llayoutHeader1_1 = (LinearLayout) findViewById(R.id.llayout_header1_1);
        llayoutDown = (LinearLayout) findViewById(R.id.llayout_title_down);
        llayoutTitleHead = (LinearLayout) findViewById(R.id.llayout_total_head);
        imgHeader1_1= (ImageView) findViewById(R.id.img_header_1_1);
        imgHeader1_2= (ImageView) findViewById(R.id.img_header1_2);
        imgHeader1_3= (ImageView) findViewById(R.id.img_header1_3);
        edtHeader1_1= (EditText) findViewById(R.id.edt_header1_1);
    }

    @Override
    protected void initData() {
        super.initData();
        initScrollViewListener();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AliPayActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.data));
        listView.setAdapter(adapter);
    }

    private void initScrollViewListener() {
        ViewTreeObserver vto1 = llayoutHeader1.getViewTreeObserver();
        vto1.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llayoutHeader1.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height1 = llayoutHeader1.getHeight();

                scrollView.setScrollViewListener(AliPayActivity.this);
            }
        });
        ViewTreeObserver vto2 = llayoutTitleHead.getViewTreeObserver();
        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llayoutTitleHead.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                height2 = llayoutTitleHead.getHeight();

                scrollView.setScrollViewListener(AliPayActivity.this);
            }
        });
    }

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 0) {   //设置标题的背景颜色
            llayoutHeader1_1.setBackgroundColor(Color.argb( 255, 193, 226, 229));
            imgHeader1_1.setAlpha(1.0f);
            imgHeader1_2.setAlpha(1.0f);
            imgHeader1_3.setAlpha(1.0f);
            edtHeader1_1.setAlpha(1.0f);
            llayoutDown.setAlpha(1.0f);
            rlayoutUpTitle.setVisibility(View.GONE);
        } else if (y > 0 && y <= height1) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
            float scale = (float) y / height1;
            float alpha = (255 * scale);
            llayoutHeader1_1.setBackgroundColor(Color.argb( (int)(1-alpha), 193, 226, 229));
            LogManager.i("alpha",alpha);
            LogManager.i("scale1",scale);
            imgHeader1_1.setAlpha(1-scale);
            imgHeader1_2.setAlpha(1-scale);
            imgHeader1_3.setAlpha(1-scale);
            edtHeader1_1.setAlpha(1-scale);
//            title.setTextColorCenter(Color.argb((int) alpha, 0,0,0));
//            title.setTextColorLeft(Color.argb((int) alpha, 0,0,0));
//            title.setBGByColor(Color.argb((int) alpha, 144,151,166));
        } else if (y > 0 && y <= height2){//滑动距离小于整个头部的高度时，设置下半部背景和字体颜色颜色透明度渐变
            float scale2 = (float) y / height2;
            float alpha = (255 * scale2);
//            llayoutHeader1_1.setBackgroundColor(Color.argb( (int)(1-alpha), 193, 226, 229));
//            LogManager.i("alpha",alpha);
//            LogManager.i("scale",scale);
//            imgHeader1_1.setAlpha(1-scale);
//            imgHeader1_2.setAlpha(1-scale);
//            imgHeader1_3.setAlpha(1-scale);
//            edtHeader1_1.setAlpha(1-scale);
            LogManager.i("scale2",scale2);
            llayoutDown.setAlpha(1-scale2);
            rlayoutUpTitle.setVisibility(View.VISIBLE);
            rlayoutUpTitle.setAlpha(scale2);
        }else {    //滑动到banner下面设置普通颜色
//            title.setBGByColor(Color.argb((int) 255, 255,255,255));
            llayoutHeader1_1.setBackgroundColor(Color.argb( (int) 255, 255,255,255));
            imgHeader1_1.setAlpha(0f);
            imgHeader1_2.setAlpha(0f);
            imgHeader1_3.setAlpha(0f);
            edtHeader1_1.setAlpha(0f);
            llayoutDown.setAlpha(0f);
            rlayoutUpTitle.setAlpha(1f);

        }
    }
}
