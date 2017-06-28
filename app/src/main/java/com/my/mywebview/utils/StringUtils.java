package com.my.mywebview.utils;

import android.text.TextUtils;


/**
 * 字符串工具类
 */
public class StringUtils {

	public static final String EMPTY = "";
	public static final int INDEX_NOT_FOUND = -1;

	/**
	 * null 转为""
	 *
	 * @param v
	 * @return
	 */
	public static String string(Object v) {
		if (v == null || "null".equals(v))
			return "";
		String value = "";
		try {
			value = String.valueOf(v);
		} catch (Exception e) {
		}
		return value;
	}

	/**
	 * 为空判断
	 *
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}


	/**
	 * 为 " "
	 *
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 不为 " "
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !StringUtils.isBlank(str);
	}
	
	/**
	 * 获取含 * 的手机号
	 */
	public static String getHidePhoneNum(String phoneNum) {
		if (phoneNum == null || phoneNum.length() != 11) {
			return "";
		}
		return phoneNum.substring(0, 3) + "****" + phoneNum.substring(phoneNum.length() - 4, phoneNum.length());
	}

	/**
	 * 身份证号加*号展示
	 *
	 * @param IDCardNo
	 *            身份证号
	 * @return
	 */
	public static String getHideIDCardNo(String IDCardNo) {

		String XH = "";
		String XIDcardNum = "";
		if (IDCardNo == null || StringUtils.isEmpty(IDCardNo)) {
			return "";
		}
		if (IDCardNo.length() >= 15) {
			for (int i = 0; i < IDCardNo.length() - 8; i++) {
				XH += "*";
			}
			XIDcardNum = IDCardNo.substring(0, 4) + XH + IDCardNo.substring(IDCardNo.length() - 4, IDCardNo.length());
		} else {
			XIDcardNum = IDCardNo;
		}
		return XIDcardNum;
	}
	

	/**
	 * 获取含 * 的银行卡号
	 *
	 * @param bankCardNum
	 * @return
	 */
	public static String getHideBankCardNum(String bankCardNum) {

		String XH = "";
		String XBnkNum = "";
		if (bankCardNum == null || StringUtils.isEmpty(bankCardNum)) {
			return "";
		}
		if (bankCardNum.length() >= 16) {
			for (int i = 0; i < bankCardNum.length() - 10; i++) {
				XH += "*";
			}
			XBnkNum = bankCardNum.substring(0, 6) + XH + bankCardNum.substring(bankCardNum.length() - 4, bankCardNum.length());
		} else {
			XBnkNum = bankCardNum;
		}
		return XBnkNum;

	}
	/**
	 * 是否是0的判断
	 * @param string
	 * @return
	 */
	public static boolean isZero(String string){
		if (TextUtils.equals("0", string)
				|| TextUtils.equals("0.0", string)
				|| TextUtils.equals("0.00", string)
				|| TextUtils.equals("0.", string)
				|| TextUtils.equals(".0", string)
				|| TextUtils.equals(".00", string)) {
			return true;
		}
			return false;
	}

}
