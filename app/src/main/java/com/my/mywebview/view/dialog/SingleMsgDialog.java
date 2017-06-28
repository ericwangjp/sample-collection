package com.my.mywebview.view.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.my.mywebview.R;


public class SingleMsgDialog extends Dialog {

	public Button commitBtn;// 确定
	public TextView contentTxt;
	public TextView versionTxt;
	protected View view;

	public SingleMsgDialog(Context context) {
		super(context);
		setDialog(context);
	}

	public SingleMsgDialog(Context context, int style) {
		super(context, style);
		setDialog(context);
	}

	@SuppressLint("InflateParams")
	private void setDialog(Context context) {
		// 点击外部不隐藏
		setCanceledOnTouchOutside(false);
		view = LayoutInflater.from(context).inflate(R.layout.dialog_single_msg, null);
		setContentView(view);
		commitBtn = (Button) view.findViewById(R.id.btn_commit_pay);// 确定
		contentTxt = (TextView) view.findViewById(R.id.txt_content);
		versionTxt = (TextView) view.findViewById(R.id.txt_content_version);
		setOwnerActivity(scanForActivity(context));
	}

	/**
	 * 转换成Activity
	 * 
	 * @param context
	 * @return
	 */
	private Activity scanForActivity(Context context) {
		if (context == null)
			return null;
		else if (context instanceof Activity)
			return (Activity) context;
		else if (context instanceof ContextWrapper)
			return scanForActivity(((ContextWrapper) context).getBaseContext());

		return null;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 居中
		// getWindow().getAttributes().gravity = Gravity.CENTER;
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
						| WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
	}
}
