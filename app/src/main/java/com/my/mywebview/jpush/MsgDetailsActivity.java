package com.my.mywebview.jpush;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.my.mywebview.R;
import com.my.mywebview.base.CommonBaseActivity;
import com.my.mywebview.utils.TitleHelper;

import cn.jpush.android.api.JPushInterface;

public class MsgDetailsActivity extends CommonBaseActivity {
	private TextView tvTitle,tvContent;
	private TitleHelper title;
	@Override
	protected void initContentView(Bundle savedInstanceState) {
		super.initContentView(savedInstanceState);
		setContentView(R.layout.activity_msg_details);
		tvTitle=(TextView) findViewById(R.id.tv_title);
		tvContent=(TextView) findViewById(R.id.tv_content);
	}
	@Override
	protected void initTitleBar() {
		super.initTitleBar();
		title = new TitleHelper(this);
		title.setPicLeft(R.mipmap.ic_back);
		title.setTextLeft(R.string.common_back);
		title.setTextCenter(R.string.txt_msg_details);
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
		Intent intent = getIntent();
		if (null != intent) {
			Bundle bundle = getIntent().getExtras();
			String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
			String content = bundle.getString(JPushInterface.EXTRA_ALERT);
			tvTitle.setText("标题："+title);
			tvContent.setText("详情："+content);
		}
	}

}
