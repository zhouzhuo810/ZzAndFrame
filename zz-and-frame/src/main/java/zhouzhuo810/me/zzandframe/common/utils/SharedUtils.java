package zhouzhuo810.me.zzandframe.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * SharedPreferences
 * Created by zhouzhuo810 on 2017/7/25.
 */
public class SharedUtils {

    private static SharedPreferences getShared(Context context) {
        String packageName = context.getPackageName();
        String projectName = packageName.substring(packageName.lastIndexOf(".") + 1, packageName.length());
        return context.getSharedPreferences(projectName, Context.MODE_PRIVATE);
    }

    public static void putInt(Context context, String key, int value) {
        getShared(context).edit().putInt(key, value).apply();
    }

    public static int getInt(Context context, String key) {
        return getShared(context).getInt(key, 0);
    }

    public static int getInt(Context context, String key, int defValue) {
        return getShared(context).getInt(key, defValue);
    }

    public static void putLong(Context context, String key, long value) {
        getShared(context).edit().putLong(key, value).apply();
    }

    public static long getLong(Context context, String key) {
        return getShared(context).getLong(key, 0);
    }

    public static long getLong(Context context, String key, long defValue) {
        return getShared(context).getLong(key, defValue);
    }

    public static void putFloat(Context context, String key, float value) {
        getShared(context).edit().putFloat(key, value).apply();
    }

    public static float getFloat(Context context, String key) {
        return getShared(context).getFloat(key, 0f);
    }

    public static float getFloat(Context context, String key, float defValue) {
        return getShared(context).getFloat(key, defValue);
    }

    public static void putString(Context context, String key, String value) {
        getShared(context).edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key) {
        return getShared(context).getString(key, null);
    }

    public static String getString(Context context, String key, String defValue) {
        return getShared(context).getString(key, defValue);
    }

    public static void putBoolean(Context context, String key, boolean value) {
        getShared(context).edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key) {
        return getShared(context).getBoolean(key, false);
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        return getShared(context).getBoolean(key, defValue);
    }


}
