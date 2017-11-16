package zhouzhuo810.me.zzandframe.common.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;

import java.util.ArrayList;

/**
 * Created by zhouzhuo810 on 2017/11/16.
 */

public class PhoneUtils {

    /**
     * 直接打电话
     *
     * @param context
     * @param phone
     */
    public static void call(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        context.startActivity(intent);
    }


    /**
     * 拨号界面
     *
     * @param context
     * @param phone
     */
    public static void dial(Context context, String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phone);
        intent.setData(data);
        context.startActivity(intent);
    }

    /**
     * 发短信
     *
     * @param context
     * @param phone
     * @param msg
     */
    public static void sendSMS(Context context, String phone, String msg) {
        if (Build.VERSION.SDK_INT >= 19) {
            SmsManager sys = SmsManager.getDefault();
            ArrayList<String> strings = sys.divideMessage(msg);
            for (String string : strings) {
                sys.sendTextMessage(phone, null, string, null, null);
            }
        } else {
            Uri smsToUri = Uri.parse("smsto:" + phone);
            Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
            intent.putExtra("sms_body", msg);
            context.startActivity(intent);
        }
    }
}
