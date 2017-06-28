package com.my.mywebview.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class UIHandler extends Handler {

	/** 回调接口，消息传递给注册者 */
	private HandlerImpl handler;

	public UIHandler(Looper looper) {
		super(looper);
	}

	public UIHandler(Looper looper, HandlerImpl handler) {
		super(looper);
		this.handler = handler;
	}

	public void setHandler(HandlerImpl handler) {
		this.handler = handler;
	}

	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		if (handler != null) {
			/** 有消息，就传递 */
			handler.handleMessage(msg);
		}
	}
}
