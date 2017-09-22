package com.my.mywebview.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.my.mywebview.R;
import com.my.mywebview.activity.customview.CustomViewMainActivity;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.JumpUtils;
import com.my.mywebview.utils.TitleHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DemoSelectActivity extends CommonBaseActivity {
    @BindView(R.id.llayout_weixin_upload_pic)
    LinearLayout llayoutWeixinUploadPic;
    @BindView(R.id.llayout_custom_drawview)
    LinearLayout llayoutCustomDrawview;
    @BindView(R.id.llayout_custom_view2)
    LinearLayout llayoutCustomView2;
    @BindView(R.id.llayout_progress_message)
    LinearLayout llayoutProgressMessage;
    private TitleHelper title;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_demo_select);
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
        title.setTextCenter(R.string.title_demo_test);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.llayout_weixin_upload_pic, R.id.llayout_custom_drawview, R.id.llayout_custom_view2,
            R.id.llayout_progress_message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llayout_weixin_upload_pic:
                JumpUtils.invokeActivity(mContext, WeiXinUploadPicActivity.class);
                break;
            case R.id.llayout_custom_drawview:
                JumpUtils.invokeActivity(mContext, CustomViewMainActivity.class);
                break;
            case R.id.llayout_custom_view2:
                JumpUtils.invokeActivity(mContext, AidlTestActivity.class);
                break;
            case R.id.llayout_progress_message:
                JumpUtils.invokeActivity(mContext, MessengerTestActivity.class);
                break;
        }
    }

}
