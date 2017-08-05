package zhouzhuo810.me.zzandframe.common.utils;

import android.icu.util.GregorianCalendar;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zhouzhuo810 on 2017/7/25.
 */
public class DateUtils {

    public static final long ONE_DAY = 24*60*60*1000;
    public static final long ONE_HOUR = 60*60*1000;
    public static final long ONE_MINUTE = 60*1000;

    public static String formatDateToYMDHMS(Date date, String splitDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy"+splitDate+"MM"+splitDate+"dd HH:mm:ss", Locale.getDefault());
        return sdf.format(date);
    }

    public static String formatDateToYMD(Date date, String split) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy"+split+"MM"+split+"dd", Locale.getDefault());
        return sdf.format(date);
    }

    public static String formatTimeHMS(Date date, String split) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH"+split+"mm"+split+"ss", Locale.getDefault());
        return sdf.format(date);
    }

    public static String millToYMDHMS(long mills, String splitDate) {
        return formatDateToYMDHMS(new Date(mills), splitDate);
    }

    public static String millToYMD(long mills, String split) {
        return formatDateToYMD(new Date(mills), split);
    }

    public static String millToHMS(long mills, String split) {
        return formatTimeHMS(new Date(mills), split);
    }

    public static long timeStrToMills(String timeStr) {
        if (timeStr == null) {
            return 0;
        }
        if (timeStr.contains(" ")) {
            String[] split = timeStr.split(" ");
            String date = split[0];
            String time = split[1];
            if (date.contains("-")) {
                String[] dates = date.split("-");
                String y = dates[0];
                String M = dates[1];
                String d = dates[2];
                String h = time.split(":")[0];
                String m = time.split(":")[1];
                String s = time.split(":")[2];
                return new Date(
                        Integer.parseInt(y)-1900,
                        Integer.parseInt(M)-1,
                        Integer.parseInt(d),
                        Integer.parseInt(h),
                        Integer.parseInt(m),
                        Integer.parseInt(s)).getTime();
            } else if (date.contains("/")) {
                String[] dates = date.split("/");
                String y = dates[0];
                String M = dates[1];
                String d = dates[2];
                String h = time.split(":")[0];
                String m = time.split(":")[1];
                String s = time.split(":")[2];
                return new Date(
                        Integer.parseInt(y)-1900,
                        Integer.parseInt(M)-1,
                        Integer.parseInt(d),
                        Integer.parseInt(h),
                        Integer.parseInt(m),
                        Integer.parseInt(s)).getTime();
            }
        }
        return 0;
    }

    public static String handleTime(String timeStr) {
        long old = timeStrToMills(timeStr);
        long n = new Date().getTime();
        long gapMills = n - old;
        long day = gapMills / ONE_DAY;
        long hour = gapMills % ONE_DAY / ONE_HOUR;
        long minute = gapMills % ONE_DAY % ONE_HOUR / ONE_MINUTE;
        long second = gapMills % ONE_DAY % ONE_HOUR % ONE_MINUTE / 1000;
        StringBuilder sb = new StringBuilder();
        if (day>0) {
            sb.append(day).append("天");
        }
        if (hour > 0) {
            sb.append(hour).append("小时");
        }
        if (minute > 0) {
            sb.append(minute).append("分钟").append("前");
        } else if (day <=0 && hour <= 0) {
            sb.append("刚刚");
        } else {
            sb.append("前");
        }
/*        if (second>0) {
            sb.append(second).append("秒");
        }
        sb.append("前");*/
        return sb.toString();
    }



}
