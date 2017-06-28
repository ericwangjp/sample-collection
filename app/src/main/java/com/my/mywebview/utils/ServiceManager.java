package com.my.mywebview.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;

/**
 * 获得系统服务管理器
 */
public class ServiceManager {

	private static ConnectivityManager cm;
	private static TelephonyManager telephonyManager;

	private ServiceManager() {
	};


	/**
	 * 获得TelephonyManager
	 * 
	 * @param context
	 * @return
	 */
	public static TelephonyManager getTelephonyManager(Context context) {
		if (telephonyManager == null) {
			synchronized (ServiceManager.class) {
				if (telephonyManager == null) {
					telephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
				}
			}
		}
		return telephonyManager;
	}
	
	/**
	 * 获得ConnectivityManager
	 * 
	 * @param context
	 * @return
	 */
	public static ConnectivityManager getConnectivityManager(Context context) {

		if (cm == null) {
			synchronized (ServiceManager.class) {
				if (cm == null) {
					cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
				}
			}
		}
		return cm;
	}


}
