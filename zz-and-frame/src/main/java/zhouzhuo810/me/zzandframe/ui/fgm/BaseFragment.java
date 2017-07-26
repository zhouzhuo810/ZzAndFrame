package zhouzhuo810.me.zzandframe.ui.fgm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zhouzhuo810.me.zzandframe.ui.act.BaseActivity;

/**
 * BaseFragment
 * Created by zhouzhuo810 on 2017/7/25.
 */
public abstract class BaseFragment extends Fragment implements IBaseFragment{

    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);

        initView();

        initData();

        initEvent();

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        resume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        destroyView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveState(outState);
    }

    @Override
    public BaseActivity getBaseAct() {
        return (BaseActivity) getActivity();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        restoreState(savedInstanceState);
    }
}
