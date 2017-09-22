package com.my.mywebview.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.my.mywebview.application.ApplicationControl;
import com.my.mywebview.handler.HandlerImpl;
import com.my.mywebview.handler.UIHandler;
import com.my.mywebview.utils.ToastUtils;
import com.squareup.leakcanary.RefWatcher;


/**
 * Fragment 基类
 */
@SuppressLint("NewApi")
public abstract class CommonBaseFragment extends Fragment {

	protected final String TAG = getClass().getSimpleName();
	protected static UIHandler handler = new UIHandler(Looper.getMainLooper());
	protected View thisView;
	protected Context mContext;
	protected Bundle paras;

	/** 请求API 用于判断接口标签 */
	protected String requestServiceAPI = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		setBase();
		thisView = initContentView(inflater, container, savedInstanceState);
		setHandler();
		initData();
		onListener();
		return thisView;
	}

	/**
	 * 设置基础信息
	 */
	private void setBase() {
		if (mContext == null) {
			mContext = this.getActivity();
		}
		/** 判断paras为空 */
		if (paras == null) {
			paras = new Bundle();
		}
	}

	/**
	 * 初始化UI
	 * 
	 * @param inflater
	 * @param container
	 * @param savedInstanceState
	 */
	protected abstract View initContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

	/** 初始化数据 */
	public void initData(){};

	/** 事件监听 */
	protected void onListener(){};

	/**
	 * 让子类处理消息
	 * 
	 * @param msg
	 */
	protected void handler(Message msg){};

	/**
	 * 设置Handler
	 */
	private void setHandler() {
		handler.setHandler(new HandlerImpl() {
			public void handleMessage(Message msg) {
				/** 有消息就提交给子类实现 */
				handler(msg);
			}
		});
	}

	public void setParas(Bundle paras) {
		this.paras = paras;
		// this.initData();
	}

	public Bundle getParas() {
		return paras;
	}

	/**
	 * 获取服务器数据
	 * 
	 * @param ServiceAPI
	 * @param params
	 */
//	public void getData(String ServiceType, Map<String, String> params, IRequestListener requestListener) {
//		String webUrl = Config.getConfigWebUrl() + ServiceType + Config.getConfigWebUrlSuffix();
//		/** 判断是否有网 */
//		if (NetworkUtils.checkNet(mContext)) {
//			/** 执行网络请求 */
//			HttpUtils.request((Activity) mContext, ServiceType, requestListener, params, webUrl, TAG);
//		} else {
//			ToastUtils.show(mContext, getResources().getString(R.string.toast_no_internet));
//		}
//	}

	
	/**
	 * 初始化 BaseFragment
	 * 
	 * @param tag
	 * @return
	 */
//	public static CommonBaseFragment getInstance(String tag) {
//
//		CommonBaseFragment fragment = null;
//		if (TextUtils.equals(tag, FragmentConstant.STR_FRAGMENT_FINANCE)) {
//			fragment =  FinanceFragment.getInstance();
//		} else if (TextUtils.equals(tag, FragmentConstant.STR_FRAGMENT_HOME)) {
//			fragment =  HomeFragment.getInstance();
//		} else if (TextUtils.equals(tag, FragmentConstant.STR_FRAGMENT_SHOPPING)) {
//			fragment =  ShoppingFragment.getInstance();
//		} else if (TextUtils.equals(tag, FragmentConstant.STR_FRAGMENT_MY)) {
//			fragment =  MyFragment.getInstance();
//		}else if (TextUtils.equals(tag, FragmentConstant.STR_FRAGMENT_LIFE)) {
//			fragment =  LifeFragment.getInstance();
//		}
//		return fragment;
//	}
	
	/**
	 * activity.findViewById()
	 * 
	 */
//	public <T extends View> T f(Activity activity, int id) {
//		return (T) V.f(activity, id);
//	}

	/**
	 * rootView.findViewById()
	 * 
	 */
//	public <T extends View> T f(int id) {
//		return (T) V.f(thisView, id);
//	}

	/**
	 * rootView.findViewById()
	 * 
	 */
	@SuppressWarnings("unchecked")
//	public <T extends View> T f(View rootView, int id) {
//		return (T) V.f(rootView, id);
//	}

	

	@Override
	public void onDestroy() {
		super.onDestroy();
		/** 弹出框取消 */
		ToastUtils.cancel();
//		MR_ApplicationController.cancelPendingRequests(TAG);
//		内存泄露监测
		RefWatcher refWatcher = ApplicationControl.getRefWatcher(getActivity());
		refWatcher.watch(this);
	}
	
//	@SuppressLint({ "NewApi", "Override" })
//	@Override
//	public void onRequestPermissionsResult(int requestCode,
//	        String permissions[], int[] grantResults) {
//		for (int i = 0; i < permissions.length; i++) {
//			if (grantResults[i] == -1) {
//				if (!shouldShowRequestPermissionRationale(permissions[i])) {
//					ToastUtils.show(mContext,getString(R.string.txt_phone_permission_remind));
//				}
//			}
//		}
//	}
}
