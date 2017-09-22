package com.my.mywebview.base;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.my.mywebview.R;
import com.my.mywebview.application.ApplicationControl;
import com.my.mywebview.handler.HandlerImpl;
import com.my.mywebview.handler.UIHandler;
import com.my.mywebview.jpush.JPushUtil;
import com.my.mywebview.utils.ActivityStackManager;
import com.my.mywebview.utils.NetworkUtils;
import com.my.mywebview.utils.ToastUtils;
import com.my.mywebview.utils.log.LogManager;
import com.my.mywebview.view.dialog.SingleMsgDialog;
import com.squareup.leakcanary.RefWatcher;

/**
 * activity基类
 */
public abstract class CommonBaseActivity extends FragmentActivity {

	protected final String TAG = getClass().getSimpleName();
	protected static UIHandler handler = new UIHandler(Looper.getMainLooper());
	// 当前上下文
	protected Context mContext = null;
	// 意图跳转
	protected Intent mIntent = null;
	// 页面参数
	protected Bundle paras = null;
	// 监听HOME键
	//	protected HomeWatcherReceiver receiver;
	// 设备指纹值,可以放在父类与自他子类共享
	protected String fingerData;
	// 加载圈
	//	public CommonLoadingDialog loadingDialog = null;
	public static final String KEY_MESSAGE = "message";
	public static final String KEY_EXTRAS = "extras";
	public static final String MESSAGE_RECEIVED_ACTION = "JPUSH_MESSAGE_RECEIVED_ACTION";
	private MessageReceiver mMessageReceiver;
	private SingleMsgDialog singleMsgDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBase();
		initContentView(savedInstanceState);
		setHandler();
		checkNet();
		initTitleBar();
		initData();
		onListener();
	}

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		setStatusBar();
	}

	/**
	 * 用于设置沉浸式顶部颜色统一
	 */
	@SuppressLint("ResourceAsColor")
	protected void setStatusBar() {
		// StatusBarUtil.setColor(this, R.color.colorFFFFFF);
	}

	/**
	 * 初始化UI，setContentView等
	 * 
	 * @param savedInstanceState
	 *            缓存
	 */
	protected  void initContentView(Bundle savedInstanceState){}

	/**
	 * 初始化 TitleBar
	 */
	protected  void initTitleBar(){}

	/**
	 * 初始化数据
	 */
	protected  void initData(){
		//		if(!StringUtils.isBlank(ApplicationController.userInfo.userNo)){
		uploadFXLogFile();
		//		}
	}

	/**
	 * 事件监听
	 */
	protected  void onListener(){}

	/**
	 * 让子类处理消息
	 * 
	 * @param msg
	 *            消息
	 */
	protected  void handler(Message msg){}

	/**
	 * 设置基础信息
	 */
	private void setBase() {

		// 无标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// 输入框默认隐藏
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		// 当前上下文
		mContext = this;
		// 添加Activity到堆栈
		ActivityStackManager.getAppManager().addActivity(this);
		// 获取 Intent
		mIntent = getIntent();
		// 页面参数
		paras = mIntent.getExtras();
		// 判断paras为空
		if (paras == null) {
			paras = new Bundle();
		}
		// 监听home键广播
		//		receiver = HomeWatcherReceiver.getInstance();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		//		registerReceiver(receiver, filter);
		//		if (loadingDialog == null) {
		//			loadingDialog = new CommonLoadingDialog(this);
		//			// loadingDialog.setMessage("加载中...");
		//		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		//		MobclickAgent.onResume(this);
		registerMessageReceiver();
		checkNet();
		setHandler();
	}
	/**
	 * 触发上传崩溃日志
	 */
	private void uploadFXLogFile(){
		boolean isWifiOnly = true;//only wifi mode can upload
		//		LogCollector.upload(isWifiOnly);//upload at the right time
		//		LogManager.i("-->LogCollector触发上传！");
	}

	public void onClick(View view) {}

	/**
	 * View 解绑
	 * 
	 * @param view
	 *            根布局
	 */
	public void unbindDrawables(View view) {
		if (view.getBackground() != null) {
			view.getBackground().setCallback(null);
		}
		if (view instanceof ViewGroup && !(view instanceof AdapterView)) {
			for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
				unbindDrawables(((ViewGroup) view).getChildAt(i));
			}
			((ViewGroup) view).removeAllViews();
		}
	}

	/**
	 * activity.findViewById()
	 * 
	 * @param id
	 *            控件id
	 */
	//	@SuppressWarnings("unchecked")
	//	public <T extends View> T f(int id) {
	//		return (T) V.f(this, id);
	//	}

	/**
	 * activity.findViewById()
	 * 
	 * @param activity
	 *            页面
	 * @param id
	 *            控件id
	 */
	//	@SuppressWarnings("unchecked")
	//	public <T extends View> T f(Activity activity, int id) {
	//		return (T) V.f(activity, id);
	//	}

	/**
	 * rootView.findViewById()
	 * 
	 * @param rootView
	 *            根布局
	 * @param id
	 *            控件id
	 */
	//	@SuppressWarnings("unchecked")
	//	public <T extends View> T f(View rootView, int id) {
	//		return (T) V.f(rootView, id);
	//	}

	/**
	 * 检查网络状态
	 */
	private void checkNet() {
		if (!NetworkUtils.checkNet(mContext)) {
			hideLoadingDialog();
			ToastUtils.show(mContext, R.string.toast_please_check_net);
		}
	}

	/**
	 * 隐藏加载圈
	 */
	public void hideLoadingDialog() {
		//		if (loadingDialog.isShowing()) {
		//			loadingDialog.dismiss();
		//		}
	}


	/**
	 * 设置Handler
	 */
	@SuppressLint("HandlerLeak")
	private void setHandler() {
		handler.setHandler(new HandlerImpl() {
			public void handleMessage(Message msg) {
				/** 有消息就提交给子类实现 */
				handler(msg);
			}
		});
	}



	protected void onPause() {
		super.onPause();
		//		MobclickAgent.onPause(this);
		// MR_ApplicationController.cancelPendingRequests( TAG );
	}

	/**
	 * 横竖屏切换，键盘等
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onStop() {
		super.onStop();
		unregisterReceiver(mMessageReceiver);
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		/** 弹出框取消 */
		ToastUtils.cancel();
		hideLoadingDialog();
		ActivityStackManager.getAppManager().finishActivity(this);
		//		//解绑服务
		//		unbindService(connection);
		//		//停止服务
		//		Intent stopIntent =new Intent(this,DialogService.class);
		//		stopService(stopIntent);
//		内存泄露监测
		RefWatcher refWatcher = ApplicationControl.getRefWatcher(this);
		refWatcher.watch(this);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		/** 弹出框取消 */
		 ToastUtils.cancel();
		//		MR_ApplicationController.cancelPendingRequests(TAG);
	}
	private void registerMessageReceiver() {
		if(mMessageReceiver==null){
			mMessageReceiver = new MessageReceiver();
		}
		IntentFilter filter = new IntentFilter();
		filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
		filter.addAction(MESSAGE_RECEIVED_ACTION);
		registerReceiver(mMessageReceiver, filter);
	}
	public class MessageReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
				String messge = intent.getStringExtra(KEY_MESSAGE);
				String extras = intent.getStringExtra(KEY_EXTRAS);
				StringBuilder showMsg = new StringBuilder();
				showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
				if (!JPushUtil.isEmpty(extras)) {
					showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
				}
				LogManager.i("base接收到的消息", showMsg.toString());
				//根据推送消息内容弹出登出框
				if(messge.contains("loginout")){
					showMsgDialog(context);
					//					Intent startIntent =new Intent(CommonBaseActivity.this,DialogService.class);
					//					startService(startIntent);//启动服务
					//					LogManager.i("启动服务！", "*********************");
					//							Intent startIntent =new Intent(CommonBaseActivity.this,DialogService.class);
					//							bindService(startIntent, connection, BIND_AUTO_CREATE);//绑定服务
					//							LogManager.i("绑定服务！", "*********************");
				}
			}
		}
	}
	/**
	 * 弹出退出登录的弹框
	 * @param context 
	 */
	protected void showMsgDialog(Context context) {
		if(singleMsgDialog==null){
			singleMsgDialog = new SingleMsgDialog(context, R.style.DialogCommon);
		}
		singleMsgDialog.contentTxt.setText(getString(R.string.txt_login_again));
		singleMsgDialog.commitBtn.setText(R.string.common_sure);
		singleMsgDialog.setCancelable(false);
		singleMsgDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);//将弹出框设置为全局
		singleMsgDialog.show();
		singleMsgDialog.commitBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				singleMsgDialog.dismiss();
			}
		});
	}
}
