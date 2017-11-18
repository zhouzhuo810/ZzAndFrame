package zhouzhuo810.me.zzandframe.common.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.Display;
import android.widget.ImageView;

import java.util.ArrayList;

import zhouzhuo810.me.zzandframe.R;
import zhouzhuo810.me.zzandframe.common.cons.ZzConst;
import zhouzhuo810.me.zzandframe.ui.act.BaseActivity;
import zhouzhuo810.me.zzandframe.ui.act.ImagePreviewActivity;

/**
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

    public static void previewImage(Activity activity, ImageView iv, String url, int errorPicId, boolean crossFade) {
        Intent intent = new Intent(activity, ImagePreviewActivity.class);
        intent.putExtra(ZzConst.IMG_PRE_PIC_URL, url);
        intent.putExtra(ZzConst.IMG_PRE_ERROR_PIC, errorPicId);
        intent.putExtra(ZzConst.IMG_PRE_CROSS_FADE, crossFade);
        if (Build.VERSION.SDK_INT >= 21) {
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) activity,
                    iv, activity.getString(R.string.transition_product_img));
            ActivityCompat.startActivity(activity, intent, options.toBundle());
        } else {
            ((BaseActivity) activity).startActWithIntent(intent);
        }
    }

    public static void previewImages(Activity activity, ImageView iv, ArrayList<String> urls, int position, int errorPicId, boolean crossFade) {
        Intent intent = new Intent(activity, ImagePreviewActivity.class);
        intent.putExtra(ZzConst.IMG_PRE_MULTI_PIC_URL, urls);
        intent.putExtra(ZzConst.IMG_PRE_MULTI_PIC_POSITION, position);
        intent.putExtra(ZzConst.IMG_PRE_ERROR_PIC, errorPicId);
        intent.putExtra(ZzConst.IMG_PRE_CROSS_FADE, crossFade);
        if (Build.VERSION.SDK_INT >= 21) {
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) activity,
                    iv, activity.getString(R.string.transition_product_img));
            ActivityCompat.startActivity(activity, intent, options.toBundle());
        } else {
            ((BaseActivity) activity).startActWithIntent(intent);
        }
    }
}
