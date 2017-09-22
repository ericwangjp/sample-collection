package com.my.mywebview.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.utils.log.LogManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LeakCanaryActivity extends CommonBaseActivity {
    @BindView(R.id.btn_leak_canary)
    Button btnLeakCanary;
    private TitleHelper title;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_leak_canary);
        ButterKnife.bind(this);
    }


    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_leak_canary);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick(R.id.btn_leak_canary)
    public void onViewClicked() {
        LogManager.i("****************任务开始********************");
        start();
    }
//开启一个线程启动任务模拟内存泄露
    private void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(20000);

            }
        }).start();
    }
}
