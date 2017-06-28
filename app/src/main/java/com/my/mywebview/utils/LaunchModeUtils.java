package com.my.mywebview.utils;

import android.content.Context;
import android.text.TextUtils;

public class LaunchModeUtils {

	/** 启动-模式 */
	public static final int LMODE_NEW_INSTALL = 1; // 首次安装-首次启动
	public static final int LMODE_UPDATE = 2;// 覆盖安装-首次启动
	public static final int LMODE_AGAIN = 3;// 已安装-二次启动
	private int launchMode = LMODE_AGAIN;

	private static LaunchModeUtils instance;

	public static LaunchModeUtils getInstance() {
		if (instance == null)
			instance = new LaunchModeUtils();

		return instance;
	}

	/**
	 * 标记打开app
	 */
	public void markOpenApp(Context context) {

		String lastVersion = (String) PreferencesUtils.get(context, "lastVersion", "");
		String thisVersion = ApplicationUtils.getAppVersionName(context);
		// 首次启动
		if (TextUtils.isEmpty(lastVersion)) {
			launchMode = LMODE_NEW_INSTALL;
			PreferencesUtils.put(context, "lastVersion", thisVersion);
		}
		// 更新
		else if (!thisVersion.equals(lastVersion)) {
			launchMode = LMODE_UPDATE;
			PreferencesUtils.put(context, "lastVersion", thisVersion);
		}
		// 二次启动(版本未变)
		else {
			launchMode = LMODE_AGAIN;
		}
	}

	/**
	 * 获取启动模式
	 * 
	 * @return
	 */
	public int getLaunchMode() {
		return launchMode;
	}

	/**
	 * 首次打开,新安装、覆盖(版本号不同)
	 * 
	 * @return
	 */
	public boolean isFirstOpen() {
		
		return launchMode != LMODE_AGAIN;
	}
}