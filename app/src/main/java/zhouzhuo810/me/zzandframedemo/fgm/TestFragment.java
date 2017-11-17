package zhouzhuo810.me.zzandframedemo.fgm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import zhouzhuo810.me.zzandframe.ui.fgm.BaseFragment;
import zhouzhuo810.me.zzandframedemo.R;

/**
 * Created by zhouzhuo810 on 2017/11/17.
 */

public class TestFragment extends BaseFragment {

    private TextView tv;

    private void assignViews() {
        tv = (TextView) rootView.findViewById(R.id.tv);
    }

    public static TestFragment newInstance(String name) {
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        TestFragment fragment = new TestFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fgm_test;
    }

    @Override
    public void initView() {
        assignViews();
    }

    @Override
    public void initData() {
        String name = getArguments().getString("name");
        tv.setText(name);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void saveState(Bundle outState) {

    }

    @Override
    public void restoreState(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void destroyView() {

    }
}
