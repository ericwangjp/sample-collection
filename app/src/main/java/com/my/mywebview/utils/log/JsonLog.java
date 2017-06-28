package com.my.mywebview.utils.log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * Json Log
 */
public class JsonLog {

    public static void printJson(String tag, String msg, String headString) {

        String message;

        tag = LogManager.PREFIX + tag;
        
        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(LogManager.JSON_INDENT);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(LogManager.JSON_INDENT);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        PrintUtil.printLine(tag, true);
        message = headString + LogManager.LINE_SEPARATOR + message;
        String[] lines = message.split(LogManager.LINE_SEPARATOR);
        for (String line : lines) {
        	   Log.e(tag, "║ " + line);
        }
        PrintUtil.printLine(tag, false);
    }
}
