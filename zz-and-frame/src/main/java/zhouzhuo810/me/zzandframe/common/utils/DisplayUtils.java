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
import zhouzhuo810.me.zzandframe.ui.act.MultiImagePreviewActivity;

/**
 * Created by zhouzhuo810 on 2017/7/25.
 */
public class DisplayUtils {

    /**
     * 获取屏幕宽度
     *
     * @param activity act
     * @return 宽度
     */
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

    /**
     * 获取屏幕高度
     *
     * @param activity act
     * @return 高度
     */
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

    /**
     * 大图预览
     * <p>
     * <p>需要注册如下Activity <br/><br/>
     * {@code  <activity android:name="zhouzhuo810.me.zzandframe.ui.act.ImagePreviewActivity" />} </p>
     *
     * @param activity   act
     * @param iv         小图对应ImageView
     * @param url        图片路径，支持本地path或网络url
     * @param errorPicId 加载失败时id
     * @param crossFade  是否使用过渡动画
     */
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

    /**
     * 多图预览
     * <p>
     * <p>需要注册如下Activity <br/><br/>
     * {@code  <activity android:name="zhouzhuo810.me.zzandframe.ui.act.MultiImagePreviewActivity" />} </p>
     *
     * @param activity   act
     * @param iv         点击的图片对应的ImageView
     * @param urls       本地图片path或网络图片url集合
     * @param position   点击图片的position
     * @param errorPicId 加载失败时id
     * @param crossFade  是否使用过渡动画
     */
    public static void previewImages(Activity activity, ImageView iv, ArrayList<String> urls, int position, int errorPicId, boolean crossFade) {
        Intent intent = new Intent(activity, MultiImagePreviewActivity.class);
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
