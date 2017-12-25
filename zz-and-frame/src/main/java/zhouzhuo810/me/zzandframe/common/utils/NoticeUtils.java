package zhouzhuo810.me.zzandframe.common.utils;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

/**
 * Created by zhouzhuo810 on 2017/7/25.
 */
public class NoticeUtils {

    public static final int NOTICE_NORMAL = 0x01;
    public static final int NOTICE_PROGRESS = 0x02;

    public static void showNormalNotice(Context context, String title,
                                        String content, boolean autoCancel,
                                        boolean onGoing, int smallIcon,
                                        boolean voice, boolean vibrate,
                                        Class cls) {
        NotificationCompat.Builder b = new NotificationCompat.Builder(context);
        Notification notification = b.setContentTitle(title)
                .setContentText(content)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(autoCancel)
                .setOngoing(onGoing)
                .setSmallIcon(smallIcon)
                .setDefaults(voice ? Notification.DEFAULT_SOUND : Notification.DEFAULT_ALL)
                .setVibrate(vibrate ? new long[]{200, 200} : null)
                .build();
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(NOTICE_NORMAL, notification);
    }

    public static void showNormalNoticeWithCustomVoice(Context context, String title,
                                                       String content, boolean autoCancel,
                                                       boolean onGoing, int smallIcon,
                                                       boolean voice, int voiceId, boolean vibrate,
                                                       Class cls) {
        NotificationCompat.Builder b = new NotificationCompat.Builder(context);
        b.setContentTitle(title)
                .setContentText(content)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(autoCancel)
                .setOngoing(onGoing)
                .setSmallIcon(smallIcon)
                .setVibrate(vibrate ? new long[]{200, 200} : null);
        if (voice) {
            b.setSound(Uri.parse("android.resource://" + SystemUtils.getPackageInfo(context).packageName + "/" + voiceId));
        }
        Notification notification = b.build();
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(NOTICE_NORMAL, notification);
    }

    public static void hideNormalNotice(Context context) {
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.cancel(NOTICE_NORMAL);
    }

    public static void showProgressNotice(Context context, String title, int progress, int smallIcon, boolean autoCancel,
                                          boolean onGoing, Intent intent) {

        NotificationCompat.Builder b = new NotificationCompat.Builder(context);
        b.setContentTitle(title)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(autoCancel)
                .setProgress(100, progress, false)
                .setOngoing(onGoing)
                .setSmallIcon(smallIcon)
                .setVibrate(null)
                .setSound(null);
        if (intent != null) {
            PendingIntent pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            b.setContentIntent(pi);
        }
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(NOTICE_PROGRESS, b.build());
    }

    public static void hideProgressNotice(Context context) {
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.cancel(NOTICE_PROGRESS);
    }


}
