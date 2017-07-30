package zhouzhuo810.me.zzandframe.common.utils;

import android.content.Context;
import android.widget.Toast;

import zhouzhuo810.me.zzandframe.ui.app.BaseApplication;

/**
 * 吐司工具
 * Created by zhouzhuo810 on 2017/7/25.
 */
public class ToastUtils {

    /**
     * 弹出短吐司
     * @param msg 消息
     */
    public static void showShortToast(String msg) {
        Toast.makeText(BaseApplication.getBaseInstance(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹出长吐司
     * @param msg 消息
     */
    public static void showLongToast(String msg) {
        Toast.makeText(BaseApplication.getBaseInstance(), msg, Toast.LENGTH_LONG).show();
    }
}
