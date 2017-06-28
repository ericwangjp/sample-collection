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

public class DoublePromptDialog extends Dialog {

	public Button cancelBtn;// 取消
	public Button commitBtn;// 确定
	public TextView contentTxt;
	protected View view;
	public TextView versionTxt;

	public DoublePromptDialog(Context context) {
		super(context);
		setDialog(context);
	}

	public DoublePromptDialog(Context context, int style) {
		super(context, style);
		setDialog(context);
	}

	@SuppressLint("InflateParams")
	private void setDialog(Context context) {
		// 点击外部不隐藏
		setCanceledOnTouchOutside(false);
		view = LayoutInflater.from(context).inflate(R.layout.dialog_delete_item, null);
		setContentView(view);
		cancelBtn = (Button) view.findViewById(R.id.btn_cancel);
		commitBtn = (Button) view.findViewById(R.id.btn_delete);// 确定
		contentTxt = (TextView) view.findViewById(R.id.txt_content);
		versionTxt = (TextView) view.findViewById(R.id.txt_content_version);
		commitBtn.setTextColor(context.getResources().getColor(R.color.color0096ED));
//		commitBtn.setTextColor(context.getResources().getColor(R.color.colorEC403C));
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
