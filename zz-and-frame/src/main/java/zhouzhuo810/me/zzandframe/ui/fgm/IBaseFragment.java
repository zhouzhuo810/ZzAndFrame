package zhouzhuo810.me.zzandframe.ui.fgm;

import android.os.Bundle;
import android.support.annotation.Nullable;

import zhouzhuo810.me.zzandframe.ui.act.BaseActivity;

/**
 * IBaseFragment
 * Created by admin on 2017/7/25.
 */

public interface IBaseFragment {

    int getLayoutId();

    void initView();

    void initData();

    void initEvent();

    void resume();

    void saveState(Bundle outState);

    void restoreState(@Nullable Bundle savedInstanceState);

    void destroyView();

    BaseActivity getBaseAct();
}
