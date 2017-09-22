package com.my.mywebview.activity.customview;

import android.os.Bundle;
import android.view.View;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.view.customview.CustomProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomView2Activity extends CommonBaseActivity {
    @BindView(R.id.customPB)
    CustomProgressBar customPB;
    private TitleHelper title;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_custom_view2);
        ButterKnife.bind(this);
        if(customPB==null){
            customPB=new CustomProgressBar(this);
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        customPB.destroyThread();
    }
}
