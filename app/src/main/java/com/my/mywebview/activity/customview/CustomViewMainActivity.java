package com.my.mywebview.activity.customview;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.JumpUtils;
import com.my.mywebview.utils.TitleHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomViewMainActivity extends CommonBaseActivity {
    @BindView(R.id.llayout_customview_1)
    LinearLayout llayoutCustomview1;
    @BindView(R.id.llayout_customview_2)
    LinearLayout llayoutCustomview2;
    @BindView(R.id.llayout_customview_3)
    LinearLayout llayoutCustomview3;
    @BindView(R.id.llayout_customview_4)
    LinearLayout llayoutCustomview4;
    @BindView(R.id.llayout_customview_5)
    LinearLayout llayoutCustomview5;
    private TitleHelper title;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_custom_view_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_custom_draw);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.llayout_customview_1, R.id.llayout_customview_2, R.id.llayout_customview_3,
            R.id.llayout_customview_4,R.id.llayout_customview_5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llayout_customview_1:
                JumpUtils.invokeActivity(mContext, CustomDrawViewActivity.class);
                break;
            case R.id.llayout_customview_2:
                JumpUtils.invokeActivity(mContext, CustomView2Activity.class);
                break;
            case R.id.llayout_customview_3:
                JumpUtils.invokeActivity(mContext, CustomView3Activity.class);
                break;
            case R.id.llayout_customview_4:
                JumpUtils.invokeActivity(mContext, CustomView4Activity.class);
                break;
            case R.id.llayout_customview_5:
                JumpUtils.invokeActivity(mContext, CustomView5Activity.class);
                break;
        }
    }

}
