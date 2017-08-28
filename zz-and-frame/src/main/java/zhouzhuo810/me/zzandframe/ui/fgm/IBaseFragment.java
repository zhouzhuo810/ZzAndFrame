package zhouzhuo810.me.zzandframe.ui.fgm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;

import zhouzhuo810.me.zzandframe.ui.act.BaseActivity;

/**
 * IBaseFragment
 * Created by zhouzhuo810 on 2017/7/25.
 */
public interface IBaseFragment {

    int getLayoutId();

    void initView();

    void initData();

    void initEvent();

    void resume();

    /**
     * 开始刷新
     * @param refresh SwipeRefreshLayout
     */
    void startRefresh(SwipeRefreshLayout refresh);

    /**
     * 停止刷新
     * @param refresh SwipeRefreshLayout
     */
    void stopRefresh(SwipeRefreshLayout refresh);

    /**
     * 进入动画
     * @return resId
     */
    int inAnimation();

    /**
     * 关闭动画
     * @return resId
     */
    int outAnimation();

    /**
     * 打开Activity
     * @param intent intent
     */
    void startActWithIntent(Intent intent);

    /**
     * 打开Activity，带返回结果
     * @param intent intent
     * @param requestCode 请求码
     */
    void startActWithIntentForResult(Intent intent, int requestCode);

    void saveState(Bundle outState);

    void restoreState(@Nullable Bundle savedInstanceState);

    void destroyView();

    BaseActivity getBaseAct();
}
