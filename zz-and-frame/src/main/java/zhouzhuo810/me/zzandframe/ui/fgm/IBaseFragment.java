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

    /**
     * 返回Fragment的布局id
     *
     * @return layoutId
     */
    int getLayoutId();

    /**
     * 初始化视图，一般做findViewById以及初始化设置
     */
    void initView();

    /**
     * 加载和填充数据
     */
    void initData();

    /**
     * 设置点击，长按等监听事件
     */
    void initEvent();

    void resume();

    /**
     * 开始刷新
     *
     * @param refresh SwipeRefreshLayout
     */
    void startRefresh(SwipeRefreshLayout refresh);

    /**
     * 停止刷新
     *
     * @param refresh SwipeRefreshLayout
     */
    void stopRefresh(SwipeRefreshLayout refresh);

    /**
     * 启动Activity进入动画
     *
     * @return resId
     */
    int openInAnimation();

    /**
     * 启动Activity退出动画
     *
     * @return resId
     */
    int openOutAnimation();

    /**
     * 关闭Activity进入动画
     *
     * @return resId
     */
    int closeInAnimation();

    /**
     * 关闭Activity退出动画
     *
     * @return resId
     */
    int closeOutAnimation();


    /**
     * 打开Activity
     *
     * @param intent intent
     */
    void startActWithIntent(Intent intent);

    /**
     * 打开Activity，带返回结果
     *
     * @param intent      intent
     * @param requestCode 请求码
     */
    void startActWithIntentForResult(Intent intent, int requestCode);

    void saveState(Bundle outState);

    void restoreState(@Nullable Bundle savedInstanceState);

    void destroyView();

    /**
     * 获取强转为BaseActivity类型的getActivity()对象
     *
     * @return act
     */
    BaseActivity getBaseAct();
}
