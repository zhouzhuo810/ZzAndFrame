package zhouzhuo810.me.zzandframe.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * SharedPreferences
 * <p>
 * Created by zhouzhuo810 on 2017/7/25.
 */
public class SharedUtils {

    private static SharedPreferences getShared(Context context) {
        String packageName = context.getPackageName();
        String projectName = packageName.substring(packageName.lastIndexOf(".") + 1, packageName.length());
        return context.getSharedPreferences(projectName, Context.MODE_PRIVATE);
    }

    /**
     * 保存int类型键值对
     *
     * @param context 上下文
     * @param key     键
     * @param value   值
     */
    public static void putInt(Context context, String key, int value) {
        getShared(context).edit().putInt(key, value).apply();
    }

    /**
     * 获取int类型键值对
     *
     * @param context 上下文
     * @param key     键
     * @return 值
     */
    public static int getInt(Context context, String key) {
        return getShared(context).getInt(key, 0);
    }


    /**
     * 获取int类型键值对
     *
     * @param context  上下文
     * @param key      键
     * @param defValue 默认值
     * @return 值
     */
    public static int getInt(Context context, String key, int defValue) {
        return getShared(context).getInt(key, defValue);
    }

    /**
     * 保存long类型键值对
     *
     * @param context 上下文
     * @param key     键
     * @param value   值
     */
    public static void putLong(Context context, String key, long value) {
        getShared(context).edit().putLong(key, value).apply();
    }

    /**
     * 获取long类型键值对
     *
     * @param context 上下文
     * @param key     键
     * @return 值
     */
    public static long getLong(Context context, String key) {
        return getShared(context).getLong(key, 0);
    }

    /**
     * 获取long类型键值对
     *
     * @param context  上下文
     * @param key      键
     * @param defValue 默认值
     * @return 值
     */
    public static long getLong(Context context, String key, long defValue) {
        return getShared(context).getLong(key, defValue);
    }

    /**
     * 保存float类型键值对
     *
     * @param context 上下文
     * @param key     键
     * @param value   值
     */
    public static void putFloat(Context context, String key, float value) {
        getShared(context).edit().putFloat(key, value).apply();
    }

    /**
     * 获取float类型键值对
     *
     * @param context 上下文
     * @param key     键
     * @return 值
     */
    public static float getFloat(Context context, String key) {
        return getShared(context).getFloat(key, 0f);
    }

    /**
     * 获取float类型键值对
     *
     * @param context  上下文
     * @param key      键
     * @param defValue 默认值
     * @return 值
     */
    public static float getFloat(Context context, String key, float defValue) {
        return getShared(context).getFloat(key, defValue);
    }

    /**
     * 保存string类型键值对
     *
     * @param context 上下文
     * @param key     键
     * @param value   值
     */
    public static void putString(Context context, String key, String value) {
        getShared(context).edit().putString(key, value).apply();
    }

    /**
     * 获取string类型键值对
     *
     * @param context 上下文
     * @param key     键
     * @return 值
     */
    public static String getString(Context context, String key) {
        return getShared(context).getString(key, null);
    }

    /**
     * 获取string类型键值对
     *
     * @param context  上下文
     * @param key      键
     * @param defValue 默认值
     * @return 值
     */
    public static String getString(Context context, String key, String defValue) {
        return getShared(context).getString(key, defValue);
    }

    /**
     * 保存boolean类型键值对
     *
     * @param context 上下文
     * @param key     键
     * @param value   值
     */
    public static void putBoolean(Context context, String key, boolean value) {
        getShared(context).edit().putBoolean(key, value).apply();
    }

    /**
     * 获取boolean类型键值对
     *
     * @param context 上下文
     * @param key     键
     * @return 值
     */
    public static boolean getBoolean(Context context, String key) {
        return getShared(context).getBoolean(key, false);
    }

    /**
     * 获取boolean类型键值对
     *
     * @param context  上下文
     * @param key      键
     * @param defValue 默认值
     * @return 值
     */
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        return getShared(context).getBoolean(key, defValue);
    }

    /**
     * 清空所有key value
     *
     * @param context 上下文
     */
    public static void clearAll(Context context) {
        getShared(context).edit().clear().apply();
    }

}
