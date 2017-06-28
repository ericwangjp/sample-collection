package com.my.mywebview.utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.my.mywebview.R;

import java.util.Locale;


/**
 * 系统信息工具类
 */
public class DeviceUtils {

	/**
	 * 获取系统语言
	 * 
	 * @param context
	 * @return
	 */
	public static String getLanguage(Context context) {
		Locale locale = context.getResources().getConfiguration().locale;
		String language = locale.getLanguage(); // 获得语言码
		return language;
	}

	/**
	 * 获取手机网络运营商
	 * 
	 * @param context
	 */
	public static String getNetworkOperatorName(Context context) {

		String networkOperatorName = ((TelephonyManager) ServiceManager
				.getTelephonyManager(context)).getNetworkOperatorName();
		switch (networkOperatorName) {
			case "46000":
			case "46002":
			case "46007":
				networkOperatorName = context.getResources().getString(
						R.string.label_china_mobile_communication_corp);
				break;
			case "46001":
				networkOperatorName = context.getResources().getString(R.string.label_china_unicom);
				break;
			case "46003":
				networkOperatorName = context.getResources().getString(
						R.string.label_china_telecommunications);
				break;
			default:
				networkOperatorName = "";
				break;
		}
		return networkOperatorName;
	}

	/**
	 * 获取系统版本
	 * 
	 * @return 形如2.3.3
	 */
	public static String getSystemVersion() {
		return Build.VERSION.RELEASE;
	}

	/**
	 * 获取手机型号
	 */
	public static String getDeviceMerchant() {
		return Build.MODEL;
	}

	/**
	 * 获取手机IMEI码
	 * 
	 * @param context
	 */
	public static String getPhoneIMEI(Context context) {

		String deviceId = ((TelephonyManager) ServiceManager.getTelephonyManager(context))
				.getDeviceId();
		if (TextUtils.isEmpty(deviceId)) {
			deviceId = Settings.Secure.getString(context.getContentResolver(),
					Settings.Secure.ANDROID_ID);
		}
		return deviceId;
	}
}
