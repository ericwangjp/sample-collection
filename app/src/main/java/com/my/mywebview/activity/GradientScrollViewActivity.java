package com.my.mywebview.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.JumpUtils;
import com.my.mywebview.utils.TitleHelper;

public class GradientScrollViewActivity extends CommonBaseActivity {
    private TitleHelper title;
    private Button btnQQzone,btnBanner,btnAlipay,btnTaoBao;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_gradient_scroll_view);
        btnQQzone= (Button) findViewById(R.id.btn_qq);
        btnBanner= (Button) findViewById(R.id.btn_banner);
        btnAlipay= (Button) findViewById(R.id.btn_alipay);
        btnTaoBao= (Button) findViewById(R.id.btn_taobao);
    }
    protected void initTitleBar() {
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_nav_change);
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
        btnQQzone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext,QQZoneActivity.class);
            }
        });
        btnBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext,BannerQQZoneActivity.class);
            }
        });
        btnAlipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext,AliPayActivity.class);
            }
        });
        btnTaoBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtils.invokeActivity(mContext,TaoBaoDetailsActivity.class);
            }
        });
    }
}
