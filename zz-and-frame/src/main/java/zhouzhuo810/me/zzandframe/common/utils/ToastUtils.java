package zhouzhuo810.me.zzandframe.common.utils;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.utils.AutoUtils;

import zhouzhuo810.me.zzandframe.R;
import zhouzhuo810.me.zzandframe.ui.app.BaseApplication;

/**
 * 吐司工具
 * Created by zhouzhuo810 on 2017/7/25.
 */
public class ToastUtils {

    private static Toast toast;
    private static int bgColor = 0xff438cff;
    private static int textColorId = 0xffffffff;

    private ToastUtils() {
    }

    public static void init(Context context, int bgColorId, int textColorId) {
        toast = null;
        ToastUtils.bgColor = context.getResources().getColor(bgColorId);
        ToastUtils.textColorId = context.getResources().getColor(textColorId);
    }

    public static void showCustomBgToast(String msg) {
        LayoutInflater inflater = (LayoutInflater) BaseApplication.getBaseInstance().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.toast_custom, null);
        AutoUtils.auto(view);
        TextView message = (TextView) view.findViewById(R.id.toast_tv);
        GradientDrawable bg = (GradientDrawable) message.getBackground();
        bg.setColor(bgColor);
        message.setTextColor(textColorId);
        message.setText(msg);
        if (toast == null) {
            toast = new Toast(BaseApplication.getBaseInstance());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 200);
        }
        toast.setView(view);
        toast.show();
    }

    public static void hideCustomBgToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

    /**
     * 弹出短吐司
     *
     * @param msg 消息
     */
    public static void showShortToast(String msg) {
        Toast.makeText(BaseApplication.getBaseInstance(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹出长吐司
     *
     * @param msg 消息
     */
    public static void showLongToast(String msg) {
        Toast.makeText(BaseApplication.getBaseInstance(), msg, Toast.LENGTH_LONG).show();
    }
}
