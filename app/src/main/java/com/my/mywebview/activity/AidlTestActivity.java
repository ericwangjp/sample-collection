package com.my.mywebview.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.utils.ToastUtils;
import com.my.mywebview.utils.log.LogManager;
import com.wjp.myaidl.ICalAIDL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AidlTestActivity extends CommonBaseActivity {
    @BindView(R.id.btn_bind)
    Button btnBind;
    @BindView(R.id.btn_unbind)
    Button btnUnbind;
    @BindView(R.id.btn_add)
    Button btnAdd;
    @BindView(R.id.btn_min)
    Button btnMin;
    private TitleHelper title;
    private ICalAIDL myAIDL;
    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogManager.e("client","onServiceConnected");
            myAIDL=ICalAIDL.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogManager.e("client","onServiceDisconnected");
            myAIDL=null;
        }
    };

    @Override
    protected void initContentView(Bundle savedInstanceState) {
        super.initContentView(savedInstanceState);
        setContentView(R.layout.activity_aidl_test);
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
        title.setTextCenter(R.string.title_aidl_message);
        title.getLeftTextView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.btn_bind, R.id.btn_unbind, R.id.btn_add, R.id.btn_min})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_bind://绑定服务
                Intent intent=new Intent();
                intent.setAction("com.wjp.aidl.remote");
//                有些时候我们使用Service的时需要采用隐式意图启动的方式，
// 但是Android 5.0一出来后，其中有个特性就是Service Intent  must be explitict，
// 也就是说从Lollipop开始，service服务必须采用显式意图方式启动
                intent.setPackage("com.wjp.myaidl");
                bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind:
                if(serviceConnection!=null)
                unbindService(serviceConnection);
                break;
            case R.id.btn_add:
                if(myAIDL!=null){
                    try {
                        int result=myAIDL.add(12,12);
                        ToastUtils.show(this,result+"");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }else{
                    ToastUtils.show(this,"服务已被异常杀死，请重新绑定服务");
                }
                break;
            case R.id.btn_min:
                if(myAIDL!=null){
                    try {
                        int result=myAIDL.min(50,30);
                        ToastUtils.show(this,result+"");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }else{
                    ToastUtils.show(this,"服务已被异常杀死，请重新绑定服务");
                }
                break;
        }
    }
}
