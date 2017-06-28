package com.my.mywebview.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.my.mywebview.R;
import com.my.mywebview.activity.recyclerview.RcvGalleryActivity;
import com.my.mywebview.activity.recyclerview.RcvGridViewActivity;
import com.my.mywebview.activity.recyclerview.RcvListViewActivity;
import com.my.mywebview.activity.recyclerview.StickyHeaderActivity;
import com.my.mywebview.activity.recyclerview.WaterFallActivity;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.JumpUtils;
import com.my.mywebview.utils.TitleHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewActivity extends CommonBaseActivity {
    @BindView(R.id.rcv_listview)
    Button rcvListview;
    @BindView(R.id.rcv_gridview)
    Button rcvGridview;
    @BindView(R.id.rcv_waterfall)
    Button rcvWaterfall;
    @BindView(R.id.rcv_stickyheader)
    Button rcvStickyheader;
    @BindView(R.id.rcv_gallery)
    Button rcvGallery;
    private TitleHelper title;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_recycleview_style);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onListener() {
        super.onListener();
        rcvListview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, RcvListViewActivity.class);
            }
        });
        rcvGridview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, RcvGridViewActivity.class);
            }
        });
        rcvWaterfall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, WaterFallActivity.class);
            }
        });
        rcvStickyheader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, StickyHeaderActivity.class);
            }
        });
        rcvGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext, RcvGalleryActivity.class);
            }
        });
    }

}
