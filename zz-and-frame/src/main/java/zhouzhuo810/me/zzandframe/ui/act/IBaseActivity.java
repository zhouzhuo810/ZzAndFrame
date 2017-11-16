package zhouzhuo810.me.zzandframe.ui.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * IBaseActivity
 * Created by zhouzhuo810 on 2017/7/25.
 */
public interface IBaseActivity {

    public interface OnTwoBtnClick {
        void onOk();

        void onCancel();
    }

    public interface OnTwoBtnEditClick {
        void onOk(String content);

        void onCancel();
    }

    public interface OnItemClick {
        void onItemClick(int position, String content);
    }

    public interface OnOneBtnClickListener {
        void onProgress(TextView tvProgress, ProgressBar pb);

        void onOK();
    }


    /**
     * 获取布局id
     * @return id
     */
    int getLayoutId();


    /**
     * 初始化视图
     */
    void initView();

    /**
     * 初始化数据
     */
    void initData();

    /**
     * 初始化监听事件
     */
    void initEvent();

    /**
     * 默认返回
     * @return 是否默认返回
     */
    boolean defaultBack();

    /**
     * onResume
     */
    void resume();

    /**
     * onPause
     */
    void pause();

    /**
     * onDestroy
     */
    void destroy();

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

    void saveState(Bundle outState);

    void restoreState(@Nullable Bundle savedInstanceState);

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
     * 关闭Activity
     */
    void closeAct();

    /**
     * 关闭所有Activity
     */
    void closeAllAct();

    /**
     * 设置EditText和一键清空的View的对应关系
     * @param et EditText
     * @param ivClear View
     */
    void setEditListener(EditText et, View ivClear);
}
