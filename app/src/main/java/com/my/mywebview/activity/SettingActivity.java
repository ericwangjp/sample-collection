package com.my.mywebview.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.jpush.PushSetActivity;
import com.my.mywebview.jpush.PushTimeSettingActivity;
import com.my.mywebview.utils.JumpUtils;
import com.my.mywebview.utils.PreferencesUtils;
import com.my.mywebview.utils.TitleHelper;
import com.my.mywebview.utils.ToastUtils;
import com.my.mywebview.utils.log.LogManager;

import cn.jpush.android.api.JPushInterface;

public class SettingActivity extends CommonBaseActivity {
	private TitleHelper title;
	private Button btnSwitch;
	private LinearLayout llayoutInit,llayoutId,llayoutPushSet,llayoutHighSet,llayoutPicture;

	@Override
	protected void initContentView(Bundle savedInstanceState) {
		super.initContentView(savedInstanceState);
		setContentView(R.layout.activity_setting);
		btnSwitch=(Button) findViewById(R.id.btn_push_switch);
		llayoutInit=(LinearLayout) findViewById(R.id.llayout_init_root);
		llayoutId=(LinearLayout) findViewById(R.id.llayout_id_root);
		llayoutPushSet=(LinearLayout) findViewById(R.id.llayout_push_set);
		llayoutHighSet=(LinearLayout) findViewById(R.id.llayout_push_high_set);
		llayoutPicture=(LinearLayout) findViewById(R.id.llayout_picture);
	}
	@Override
	protected void initTitleBar() {
		super.initTitleBar();
		title = new TitleHelper(this);
		title.setPicLeft(R.mipmap.ic_back);
		title.setTextLeft(R.string.common_back);
		title.setTextCenter(R.string.title_activity_setting);
		title.getLeftTextView().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
	@Override
	protected void initData() {
		super.initData();
		LogManager.i("取出的值", PreferencesUtils.get(mContext, "switch", true));
		if((Boolean) PreferencesUtils.get(mContext, "switch", true)){
			btnSwitch.setSelected(true);
		}else{
			btnSwitch.setSelected(false);
		}
	}
	@Override
	protected void onListener() {
		super.onListener();
		llayoutInit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 初始化 JPush。如果已经初始化，但没有登录成功，则执行重新登录。
				JPushInterface.init(getApplicationContext());
				ToastUtils.show(mContext, R.string.toast_init_success);
			}
		});
		llayoutId.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 获取 JPush ID
				String rid = JPushInterface.getRegistrationID(getApplicationContext());
				if (!rid.isEmpty()) {
					ToastUtils.show(mContext, rid);
				} else {
					ToastUtils.show(mContext, "Get registration fail, JPush init failed!");
				}
			}
		});
		btnSwitch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if(btnSwitch.isSelected()){
					btnSwitch.setSelected(false);
					PreferencesUtils.put(mContext, "switch",btnSwitch.isSelected());
					//关闭推送
					JPushInterface.stopPush(getApplicationContext());
					ToastUtils.show(mContext, R.string.toast_push_close);
				}else{
					btnSwitch.setSelected(true);
					PreferencesUtils.put(mContext, "switch",btnSwitch.isSelected());
					//打开推送
					JPushInterface.resumePush(getApplicationContext());
					ToastUtils.show(mContext, R.string.toast_push_open);
				}
			}
		});
		llayoutPushSet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				JumpUtils.invokeActivity(mContext, PushSetActivity.class);
			}
		});
		llayoutHighSet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				JumpUtils.invokeActivity(mContext, PushTimeSettingActivity.class);
			}
		});
		llayoutPicture.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				JumpUtils.invokeActivity(mContext, FunctionSelectActivity.class);
			}
		});
	}
}
