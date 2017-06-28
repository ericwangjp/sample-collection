package com.my.mywebview.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * 应用程序工具类
 */
public class ApplicationUtils {


	/**
	 * 获取应用程序名称
	 * 
	 * @param context
	 * @return
	 */
	public static String getAppName(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
			int labelRes = packageInfo.applicationInfo.labelRes;
			return context.getResources().getString(labelRes);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取当前应用程序的versionName
	 * 
	 * @param context
	 * @return
	 */
	public static String getAppVersionName(Context context) {
		String version = "0";
		try {
			version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			throw new RuntimeException(DeviceUtils.class.getName() + "the application not found");
		}
		return version;
	}

	/**
	 * 获取当前应用程序的versionCode
	 * 
	 * @param context
	 * @return
	 */
	public static int getAppVersionCode(Context context) {
		int version = 0;
		try {
			version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			throw new RuntimeException(DeviceUtils.class.getName() + "the application not found");
		}
		return version;
	}

}