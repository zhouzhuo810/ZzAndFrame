package zhouzhuo810.me.zzandframe.common.utils;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;

/**
 *
 * Created by zhouzhuo810 on 2017/7/25.
 */
public class DisplayUtils {

    public static int getScreenWidth(Activity activity) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else {
            defaultDisplay.getSize(point);
        }
        return point.x;
    }

    public static int getScreenHeight(Activity activity) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else {
            defaultDisplay.getSize(point);
        }
        return point.y;
    }
}
