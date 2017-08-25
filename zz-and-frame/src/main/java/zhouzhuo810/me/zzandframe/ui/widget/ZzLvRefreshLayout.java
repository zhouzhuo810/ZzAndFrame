package zhouzhuo810.me.zzandframe.ui.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

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

    private int mTouchSlop;
    private int downY;

    private boolean isLoading = false;

    public ZzLvRefreshLayout(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public ZzLvRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public void startLoad() {
        showFooter();
        if (!isLoading) {
            isLoading = true;
            if (pb != null) {
                pb.setVisibility(VISIBLE);
            }
            if (tv != null) {
                if (footerCreator != null) {
                    tv.setText(footerCreator.getLoadingText());
                }
            }
        }
    }

    @Override
    public void stopLoad() {
        showFooter();
        if (isLoading) {
            isLoading = false;
            if (pb != null) {
                pb.setVisibility(GONE);
            }
            if (tv != null) {
                if (footerCreator != null) {
                    tv.setText(footerCreator.getNormalText());
                }
            }
        }
    }

    @Override
    public void noNeedLoad() {
        showFooter();
        isLoading = false;
        if (pb != null) {
            pb.setVisibility(GONE);
        }
        if (tv != null) {
            if (footerCreator != null) {
                tv.setText(footerCreator.getNoNeedLoadText());
            }
        }
    }

    @Override
    public void hideFooter() {
        if (footerView != null && lv != null) {
            if (lv.getFooterViewsCount() == 1) {
                lv.removeFooterView(footerView);
            }
        }
    }

    @Override
    public void showFooter() {
        if (footerView != null && lv != null) {
            if (lv.getFooterViewsCount() == 0) {
                lv.addFooterView(footerView);
            }
        }
    }

    public interface OnLoadListener {
        void onLoad(ProgressBar pb, TextView tvFooter);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                float disY = downY - ev.getRawY();
                if (disY > mTouchSlop) {
                    if (canLoad()) {
                        if (onLoadListener != null) {
                            startLoad();
                            onLoadListener.onLoad(pb, tv);
                        }
                    }
                }
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    private boolean canLoad() {
        return isBottom() && !isLoading && !isRefreshing();
    }

    private boolean isBottom() {
        if (lv != null && lv.getAdapter() != null) {
            return lv.getLastVisiblePosition() == (lv.getAdapter().getCount() - 1);
        }
        return false;
    }

    public void setFooterCreator(final IFooterCreator footerCreator) {
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
        AutoUtils.auto(footerView);
        footerView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                footerCreator.onFooterClick(pb, tv);
            }
        });
        if (footerCreator.getProgressBarId() != 0) {
            pb = (ProgressBar) footerView.findViewById(footerCreator.getProgressBarId());
        }
        if (footerCreator.getFooterTextViewId() != 0) {
            tv = (TextView) footerView.findViewById(footerCreator.getFooterTextViewId());
            tv.setText(footerCreator.getNormalText());
        }
    }

    public void setOnLoadListener(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }


}
