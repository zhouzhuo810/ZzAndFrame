package zhouzhuo810.me.zzandframe.common.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 吐司工具
 * Created by zhouzhuo810 on 2017/7/25.
 */
public class ToastUtils {

    /**
     * 弹出短吐司
     * @param context Context
     * @param msg 消息
     */
    public static void showShortToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹出长吐司
     * @param context Context
     * @param msg 消息
     */
    public static void showLongToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
