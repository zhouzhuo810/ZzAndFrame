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
     * 安装apk
     */
    public static void installApk(Context context, String applicationId, String filePath, String fileName) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT > 23) {
            //FIX ME by ZZ : 7.0
            Uri uri = FileProvider.getUriForFile(context, applicationId+".provider",
                    new File(filePath + File.separator + fileName));
            //这flag很关键
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
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
