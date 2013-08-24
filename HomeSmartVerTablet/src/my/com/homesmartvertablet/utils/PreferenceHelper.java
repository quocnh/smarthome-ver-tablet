package my.com.homesmartvertablet.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceHelper {
	protected static SharedPreferences settingPreferences;
	protected static PreferenceHelper instance = new PreferenceHelper();
	
	public static PreferenceHelper getInstance(Context context) {
		init(context);
		return instance;
	}
	
	public static void putString(String key, String value) {
		Editor editor = settingPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getString(String key, String defaultValue) {
		return settingPreferences.getString(key, defaultValue);
	}

	public static void init(Context context) {
		if (settingPreferences == null) {
			settingPreferences = context.getSharedPreferences(
					"elderApplication", Context.MODE_PRIVATE);
		}
	}
	public static void putInteger(String key, int value) {
		if (settingPreferences != null) {
			Editor editor = settingPreferences.edit();
			editor.putInt(key, value);
			editor.commit();
		}
	}

	public static Integer getInteger(String key, int defaultValue) {
		if (settingPreferences != null) {
			return settingPreferences.getInt(key, defaultValue);
		} else
			return defaultValue;
	}
}
