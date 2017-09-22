package com.my.mywebview.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.utils.log.LogManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessengerTestActivity extends CommonBaseActivity {
    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.llayout_container)
    LinearLayout llayoutContainer;
    private TitleHelper title;
    private boolean isConnect;//服务连接状态
    private Messenger clientMessenger;
    private int no; //添加textview顺序以及ID值
    //最好换成HandlerThread的形式
    private Messenger handleServerMessenger = new Messenger(new Handler(){
        @Override
        public void handleMessage(Message msgFromServer) {
            switch (msgFromServer.what) {
                case 1000:
                    LogManager.i("tvid值(msgFromServer.arg1)：",msgFromServer.arg1);
                    TextView resultTv = (TextView) findViewById(msgFromServer.arg1);
                    resultTv.setText(resultTv.getText()+"服务端返回==》"+msgFromServer.arg2);
                    break;
            }
            super.handleMessage(msgFromServer);
        }
    });

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            clientMessenger = new Messenger(service);
            isConnect = true;
            tvState.setText("已连接成功！");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            clientMessenger = null;
            isConnect = false;
            tvState.setText("连接已断开！");
        }
    };

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_messenger_test);
        ButterKnife.bind(this);
//        绑定服务
        bindMessengerService();
    }

    /**
     * 绑定服务
     */
    private void bindMessengerService() {
        Intent intent = new Intent();
        intent.setAction("com.wjp.aidl.servermessenger");
        intent.setPackage("com.wjp.myaidl");
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
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
        title.setTextCenter(R.string.title_messenger);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick(R.id.btn_add)
    public void onViewClicked() {
        int a = no++;
        int b = (int) (Math.random()*100);

        TextView textView = new TextView(this);
        textView.setText(a+"+"+b+"等待返回...");
        textView.setId(a);
        LogManager.i("tvid设置值(no++)：",a);
        llayoutContainer.addView(textView);

        Message messageFromClient = Message.obtain(null,1000,a,b);
        messageFromClient.replyTo = handleServerMessenger;
        if(isConnect){
            try {
//                向服务端发送消息
                clientMessenger.send(messageFromClient);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
