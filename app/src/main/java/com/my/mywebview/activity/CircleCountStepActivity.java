package com.my.mywebview.activity;

import android.os.Bundle;
import android.view.View;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.view.CircleAnimStatisticalView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CircleCountStepActivity extends CommonBaseActivity {
    @BindView(R.id.circle_count_view)
    CircleAnimStatisticalView circleCountView;
    private TitleHelper title;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_circle_count_step);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        super.initData();
        circleCountView=new CircleAnimStatisticalView(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_circle_counter);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
