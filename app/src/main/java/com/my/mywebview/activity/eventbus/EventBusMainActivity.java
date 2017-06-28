package com.my.mywebview.activity.eventbus;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.model.MessageEvent;
import com.my.mywebview.utils.JumpUtils;
import com.my.mywebview.utils.TitleHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventBusMainActivity extends CommonBaseActivity {

    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.btn_jump)
    Button btnJump;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.btn_subscribe)
    Button btnSubscribe;
    @BindView(R.id.tv_sticky_subscribe)
    TextView tvStickySubscribe;

    private TitleHelper title;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_event_bus_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_eventbus_1);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
//        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleReceivedMsg(MessageEvent messageEvent) {
        String msg = messageEvent.getMessage();
        tvResult.setText(msg);
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void setStickyMsg(MessageEvent messageEvent){
        String msg = messageEvent.getMessage();
        tvStickySubscribe.setText(msg);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.btn_jump, R.id.btn_subscribe})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_jump:
                JumpUtils.invokeActivity(this, EventBusSecondActivity.class);
                break;
            case R.id.btn_subscribe:
//                接收到的粘滞消息会一直存在内存中，除非杀掉APP重新进入，所以可以在订阅粘滞消息前，
//                判断之前的粘滞消息是否为空，不为空可以先移除所有粘滞消息
                if(EventBus.getDefault().isRegistered(this)){
                    return;
                }
                EventBus.getDefault().register(this);
                break;
        }
    }
}
