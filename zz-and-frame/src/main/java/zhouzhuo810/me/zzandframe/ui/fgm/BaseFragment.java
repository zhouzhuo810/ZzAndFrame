package zhouzhuo810.me.zzandframe.ui.fgm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.autolayout.utils.AutoUtils;

import zhouzhuo810.me.zzandframe.R;
import zhouzhuo810.me.zzandframe.ui.act.BaseActivity;

/**
 * BaseFragment
 * Created by zhouzhuo810 on 2017/7/25.
 */
public abstract class BaseFragment extends Fragment implements IBaseFragment {

    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
    public int closeInAnimation() {
        return R.anim.zaf_slide_in_left;
    }

    @Override
    public int closeOutAnimation() {
        return R.anim.zaf_side_out_right;
    }

    @Override
    public int openInAnimation() {
        return R.anim.zaf_slide_in_right;
    }

    @Override
    public int openOutAnimation() {
        return R.anim.zaf_side_out_left;
    }

    @Override
    public void startRefresh(SwipeRefreshLayout refreshLayout) {
        if (refreshLayout != null) {
            refreshLayout.setRefreshing(false);
            refreshLayout.setProgressViewOffset(false, 0, AutoUtils.getPercentHeightSize(24));
            refreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void stopRefresh(SwipeRefreshLayout refreshLayout) {
        if (refreshLayout != null && refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }


    @Override
    public void startActWithIntent(Intent intent) {
        startActivity(intent);
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).overridePendingTransition(openInAnimation(), openOutAnimation());
        }
    }

    @Override
    public void startActWithIntentForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).overridePendingTransition(openInAnimation(), openOutAnimation());
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        saveState(outState);
    }

    @Override
    public BaseActivity getBaseAct() {
        if (getActivity() == null) {
            return null;
        }
        return (BaseActivity) getActivity();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        restoreState(savedInstanceState);
    }
}
