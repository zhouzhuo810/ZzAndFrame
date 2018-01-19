package zhouzhuo810.me.zzandframe.common.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * Created by zhouzhuo810 on 2017/7/25.
 */
public class ApkUtils {

    /**
     * 安装 App(支持 8.0)
     * <p>8.0 需添加权限
     * {@code <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />}</p>
     *
     * @param context       上下文
     * @param applicationId applicationId
     * @param filePath      文件路径
     * @param fileName      文件名称
     */
    public static void installApk(Context context, String applicationId, String filePath, String fileName) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT > 23) {
            //FIX ME by ZZ : 7.0
            Uri uri = FileProvider.getUriForFile(context, applicationId + ".provider",
                    new File(filePath + File.separator + fileName));
            //这flag很关键
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            intent.setDataAndType(Uri.fromFile(new File(filePath + File.separator + fileName)),
                    "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }


    /**
     * 安装 App(支持 8.0)
     * <p>8.0 需添加权限
     * {@code <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES" />}</p>
     *
     * @param context   上下文
     * @param authority applicationId + .provider
     * @param filePath  文件路径
     * @param fileName  文件名称
     */
    public static void installApkWithAuthority(Context context, String authority, String filePath, String fileName) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT > 23) {
            //FIX ME by ZZ : 7.0
            Uri uri = FileProvider.getUriForFile(context, authority,
                    new File(filePath + File.separator + fileName));
            //这flag很关键
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            intent.setDataAndType(Uri.fromFile(new File(filePath + File.separator + fileName)),
                    "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

}
