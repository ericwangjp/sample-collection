package com.my.mywebview.utils;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * activity 堆栈管理
 */
public class ActivityStackManager {

	private static Stack<Activity> activityStack;
	private static ActivityStackManager instance = null;

	private ActivityStackManager() {
	}

	/**
	 * 单一实例
	 * 
	 * @return instance
	 */
	public static ActivityStackManager getAppManager() {
		if (instance == null) {
			instance = new ActivityStackManager();
		}
		return instance;
	}

	/**
	 * 添加Activity到堆栈
	 * 
	 * @param activity
	 */
	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new Stack<Activity>();
		}
		activityStack.add(activity);
	}

	/**
	 * 获取当前Activity（堆栈中最后一个压入的）
	 * 
	 * @return
	 */
	public Activity currentActivity() {
		Activity activity = activityStack.lastElement();
		return activity;
	}

	/**
	 * 结束当前Activity（堆栈中最后一个压入的）
	 */
	public void finishActivity() {
		Activity activity = activityStack.lastElement();
		finishActivity(activity);
	}

	/**
	 * 结束指定的Activity
	 * 
	 * @param activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			activityStack.remove(activity);
			activity.finish();
			activity = null;
		}
	}

	/**
	 * 结束指定类名的Activity
	 * 
	 * @param cls
	 */
	public void finishActivity(Class<?> cls) {
		for (Activity activity : activityStack) {
			if (activity.getClass().equals(cls)) {
				finishActivity(activity);
			}
		}
	}

	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity() {
		for (int i = 0, size = activityStack.size(); i < size; i++) {
			if (null != activityStack.get(i)) {
				activityStack.get(i).finish();
			}
		}
		activityStack.clear();
	}

	/**
	 * 退出应用程序
	 * 
	 * @param context
	 */
	public void AppExit(Context context) {
		try {
			finishAllActivity();
			// 网络信号监听关闭
//			if (ServiceUtils.isServiceWork(context,NetworkSpeedService.class.getName())) {
//				context.stopService(new Intent(context, NetworkSpeedService.class));
//			}
			// 杀掉，这个应用程序的进程，释放 内存
			int id = android.os.Process.myPid();
			if (id != 0) {
				android.os.Process.killProcess(id);
			}
		} catch (Exception e) {
		}
	}
}
