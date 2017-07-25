package zhouzhuo810.me.zzandframe.ui.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Refresh For ListView (support LoadMore).
 * Created by admin on 2017/7/25.
 */
public class ZzLvRefreshLayout extends SwipeRefreshLayout{

    private ListView lv;
    private OnLoadListener onLoadListener;

    public interface OnLoadListener{
        void onLoad(ProgressBar pb, TextView tvFooter);
    }

    interface IRefresh {
        void startLoad(); //文字变为拼命加载中

        void stopLoad();  //文字变为上拉加载更多

        void noNeedLoad(); //文字变为已经是全部数据了

        void hideFooter(); //隐藏Footer

        void showFooter(); //显示Footer
    }

    public void setOnLoadListener(OnLoadListener onLoadListener) {
        this.onLoadListener = onLoadListener;
    }

    public ZzLvRefreshLayout(Context context) {
        super(context);
    }

    public ZzLvRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



}
