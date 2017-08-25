package zhouzhuo810.me.zzandframe.common.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.RequiresApi;

/**
 * Android 复制粘贴工具
 * Created by zhouzhuo810 on 2017/8/12.
 */
public class CopyUtils {

    public static void copyUrl(Context context,CharSequence label, String url) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(ClipData.newRawUri(label, Uri.parse(url)));
    }

    public static void copyUri(Context context,CharSequence label, Uri uri) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(ClipData.newRawUri(label, uri));
    }

    @RequiresApi(value = 16)
    public static void copyHtml(Context context,CharSequence label, String text, String html) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(ClipData.newHtmlText(label,text, html));
    }

    public static void copyPlainText(Context context, CharSequence label, String text) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(ClipData.newPlainText(label, text));
    }

    public static void copyIntent(Context context,CharSequence label, Intent intent) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(ClipData.newIntent(label, intent));
    }

    public static CharSequence getCopyPlainText(Context context) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        return cm.getPrimaryClip().getItemAt(0).getText();
    }

    public static Intent getCopyIntent(Context context) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        return cm.getPrimaryClip().getItemAt(0).getIntent();
    }

    @RequiresApi(value = 16)
    public static CharSequence getCopyHtmlText(Context context) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        return cm.getPrimaryClip().getItemAt(0).getHtmlText();
    }

    public static Uri getCopyUri(Context context) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        return cm.getPrimaryClip().getItemAt(0).getUri();
    }

}
