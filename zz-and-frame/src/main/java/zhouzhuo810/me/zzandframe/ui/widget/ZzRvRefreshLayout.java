package zhouzhuo810.me.zzandframe.ui.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Refresh For RecyclerView (support LoadMore).
 * Created by zhouzhuo810 on 2017/7/25.
 */
public class ZzRvRefreshLayout extends SwipeRefreshLayout implements IRefresh{

    private RecyclerView rv;
    private OnLoadListener onLoadListener;

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

    }

    @Override
    public void showFooter() {

    }

    public interface OnLoadListener{
        void onLoad(ProgressBar pb, TextView tvFooter);
    }

    public void setOnLoadListener(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }

    public ZzRvRefreshLayout(Context context) {
        super(context);
    }

    public ZzRvRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



}
