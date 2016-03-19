/**
 * @author Yaazhsoft
 *
 */
package com.fpl.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
	
	public static String toJsonString(final Object obj){
		return new Gson().toJson(obj);
	}

	public static String convertJSONString(final Object obj) {
		return "[" + convertJSONSingleStr(obj) + "]";
	}
	
	public static String convertJSONSingleStr(final Object obj) {
		return new GsonBuilder().setPrettyPrinting().create().toJson(obj);
	}
	
	public static <T> T convertPojo(final String jsonString, final Class<T> jsonClass) {
		final Gson jsonConfig = new Gson();
		return jsonConfig.fromJson(jsonString, jsonClass);
	}
}
