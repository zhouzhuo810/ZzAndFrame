package zhouzhuo810.me.zzandframe.ui.widget;

/**
 * Created by admin on 2017/7/31.
 */

public interface IRefresh {
    void startLoad(); //文字变为拼命加载中

    void stopLoad();  //文字变为上拉加载更多

    void noNeedLoad(); //文字变为已经是全部数据了

    void hideFooter(); //隐藏Footer

    void showFooter(); //显示Footer
}
