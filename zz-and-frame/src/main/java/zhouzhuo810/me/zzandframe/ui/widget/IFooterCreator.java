package zhouzhuo810.me.zzandframe.ui.widget;

import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by admin on 2017/7/31.
 */

public interface IFooterCreator {
    int getFooterLayoutId();
    int getFooterTextViewId();
    int getProgressBarId();
    String getNormalText();
    String getLoadingText();
    String getNoNeedLoadText();
    void onFooterClick(ProgressBar pb, TextView tv);
}
