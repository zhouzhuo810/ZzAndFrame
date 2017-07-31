package zhouzhuo810.me.zzandframe.ui.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import zhouzhuo810.me.zzandframe.R;

/**
 * Refresh For ListView (support LoadMore).
 * Created by zhouzhuo810 on 2017/7/25.
 */
public class ZzLvRefreshLayout extends SwipeRefreshLayout implements IRefresh {

    private ListView lv;
    private OnLoadListener onLoadListener;
    private IFooterCreator footerCreator;
    private View footerView;
    private ProgressBar pb;
    private TextView tv;

    public ZzLvRefreshLayout(Context context) {
        super(context);
    }

    public ZzLvRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void startLoad() {
    }

    @Override
    public void stopLoad() {

    }

    @Override
    public void noNeedLoad() {

    }

    @Override
    public void hideFooter() {
        if (footerView != null) {
            footerView.setVisibility(GONE);
        }
    }

    @Override
    public void showFooter() {
        if (footerView != null) {
            footerView.setVisibility(VISIBLE);
        }
    }

    public interface OnLoadListener {
        void onLoad(ProgressBar pb, TextView tvFooter);
    }

    public void setFooterCreator(IFooterCreator footerCreator) {
        View child = getChildAt(1);
        if (child instanceof ListView) {
            if (lv == null) {
                lv = (ListView) child;
            }
        } else {
            throw new RuntimeException("the child must instanceof ListView.");
        }

        this.footerCreator = footerCreator;
        if (footerCreator == null) {
            throw new IllegalArgumentException("should not be null.");
        }
        this.footerView = LayoutInflater.from(getContext()).inflate(footerCreator.getFooterLayoutId(), lv, false);
        if (footerCreator.getProgressBarId() != 0) {
            pb = (ProgressBar) footerView.findViewById(footerCreator.getProgressBarId());
        }
        if (footerCreator.getFooterTextViewId() != 0) {
            tv = (TextView) footerView.findViewById(footerCreator.getFooterTextViewId());
        }
        if (lv != null) {
            lv.addFooterView(footerView);
            hideFooter();
        }
    }

    public void setOnLoadListener(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }


}
