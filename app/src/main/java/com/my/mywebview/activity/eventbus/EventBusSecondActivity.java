package com.my.mywebview.activity.eventbus;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.model.MessageEvent;
import com.my.mywebview.utils.TitleHelper;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventBusSecondActivity extends CommonBaseActivity {

    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.btn_send)
    Button btnSend;

    private TitleHelper title;

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_event_bus_second);
        ButterKnife.bind(this);
    }

    @Override
    protected void initTitleBar() {
        super.initTitleBar();
        title = new TitleHelper(this);
        title.setPicLeft(R.mipmap.ic_back);
        title.setTextLeft(R.string.common_back);
        title.setTextCenter(R.string.title_eventbus_2);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.btn_send,R.id.btn_send_sticky})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                EventBus.getDefault().post(new MessageEvent("我是来自后面的普通消息"));
                finish();
                break;
            case R.id.btn_send_sticky:
                EventBus.getDefault().postSticky(new MessageEvent("我是来自后面的粘滞消息"));
                finish();
                break;
            default:
                break;
        }
    }
}
