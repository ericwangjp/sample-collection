package com.my.mywebview.utils.log;

import java.io.File;

import android.text.TextUtils;

/**
 * 日志打印
 * 
 * 1、添加对文件的支持 2、增加对XML的支持 3、添加对任意参数的支持 4、增加对无限长字符串支持
 */
public class LogManager {

	public static final String DEFAULT_MESSAGE = "app execute";
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	public static final String NULL_TIPS = "Log with null object";
	public static final String PARAM = "Param";
	public static final String NULL = "null";
	public static final String TAG_DEFAULT = "LogManager";
	public static final String SUFFIX = ".java";
	public static final String PREFIX = "[MR-INFO] >>>";

	public static final int JSON_INDENT = 4;

	public static final int V = 0x1;
	public static final int D = 0x2;
	public static final int I = 0x3;
	public static final int W = 0x4;
	public static final int E = 0x5;
	public static final int A = 0x6;
	public static final int JSON = 0x7;
	public static final int XML = 0x8;

	private static boolean IS_SHOW_LOG = true;
	private static final int STACK_TRACE_INDEX = 5;

	public static void init(boolean a) {
		IS_SHOW_LOG = a;
	}

	public static void v() {
		printLog(V, null, DEFAULT_MESSAGE);
	}

	public static void v(Object a) {
		printLog(V, null, a);
	}

	public static void v(String a, Object... b) {
		printLog(V, a, b);
	}

	public static void d() {
		printLog(D, null, DEFAULT_MESSAGE);
	}

	public static void d(Object a) {
		printLog(D, null, a);
	}

	public static void d(String a, Object... b) {
		printLog(D, a, b);
	}

	public static void i() {
		printLog(I, null, DEFAULT_MESSAGE);
	}

	public static void i(Object a) {
		printLog(I, null, a);
	}

	public static void i(String a, Object... b) {
		printLog(I, a, b);
	}

	public static void w() {
		printLog(W, null, DEFAULT_MESSAGE);
	}

	public static void w(Object a) {
		printLog(W, null, a);
	}

	public static void w(String a, Object... b) {
		printLog(W, a, b);
	}

	public static void e() {
		printLog(E, null, DEFAULT_MESSAGE);
	}

	public static void e(Object a) {
		printLog(E, null, a);
	}

	public static void e(String a, Object... b) {
		printLog(E, a, b);
	}

	public static void a() {
		printLog(A, null, DEFAULT_MESSAGE);
	}

	public static void a(Object a) {
		printLog(A, null, a);
	}

	public static void a(String a, Object... b) {
		printLog(A, a, b);
	}

	public static void json(String a) {
		printLog(JSON, null, a);
	}

	public static void json(String a, String b) {
		printLog(JSON, a, b);
	}

	public static void xml(String a) {
		printLog(XML, null, a);
	}

	public static void xml(String a, String b) {
		printLog(XML, a, b);
	}

	public static void file(File a, Object b) {
		printFile(null, a, null, b);
	}

	public static void file(String a, File b, Object c) {
		printFile(a, b, null, c);
	}

	public static void file(String a, File b, String c, Object d) {
		printFile(a, b, c, d);
	}

	private static void printLog(int a, String b, Object... c) {

		if (!IS_SHOW_LOG) {
			return;
		}

		String[] contents = wrapperContent(b, c);
		String d = contents[0];
		String e = contents[1];
		String f = contents[2];

		switch (a) {
		case V:
		case D:
		case I:
		case W:
		case E:
		case A:
			BaseLog.printDefault(a, d, f + e);
			break;
		case JSON:
			JsonLog.printJson(d, e, f);
			break;
		case XML:
			XmlLog.printXml(d, e, f);
			break;
		}
	}

	private static void printFile(String a, File b, String c, Object d) {

		if (!IS_SHOW_LOG) {
			return;
		}

		String[] contents = wrapperContent(a, d);
		String e = contents[0];
		String f = contents[1];
		String g = contents[2];

		FileLog.printFile(e, b, c, g, f);
	}

	private static String[] wrapperContent(String a, Object... b) {

		StackTraceElement[] c = Thread.currentThread().getStackTrace();

		StackTraceElement d = c[STACK_TRACE_INDEX];
		String e = d.getClassName();
		String[] f = e.split("\\.");
		if (f.length > 0) {
			e = f[f.length - 1] + SUFFIX;
		}
		String g = d.getMethodName();
		int h = d.getLineNumber();

		if (h < 0) {
			h = 0;
		}

		String i = g.substring(0, 1).toUpperCase() + g.substring(1);

		String j = (a == null ? e : a);
		if (TextUtils.isEmpty(j)) {
			j = TAG_DEFAULT;
		}
		String k = (b == null) ? NULL_TIPS : getObjectsString(b);
		String l = "[ (" + e + ":" + h + ")#" + i + " ] ";

		return new String[] { j, k, l };
	}

	private static String getObjectsString(Object... objects) {

		if (objects.length > 1) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("\n");
			for (int i = 0; i < objects.length; i++) {
				Object object = objects[i];
				if (object == null) {
					stringBuilder.append(PARAM).append("[").append(i).append("]").append(" = ").append(NULL).append("\n");
				} else {
					stringBuilder.append(PARAM).append("[").append(i).append("]").append(" = ").append(object.toString()).append("\n");
				}
			}
			return stringBuilder.toString();
		} else {
			Object object = objects[0];
			return object == null ? NULL : object.toString();
		}
	}

}
