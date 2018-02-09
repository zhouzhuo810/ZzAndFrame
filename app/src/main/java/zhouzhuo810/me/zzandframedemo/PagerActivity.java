package zhouzhuo810.me.zzandframedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import zhouzhuo810.me.zzandframe.ui.act.BaseActivity;
import zhouzhuo810.me.zzandframe.ui.widget.MarkView;
import zhouzhuo810.me.zzandframe.ui.widget.TitleBar;
import zhouzhuo810.me.zzandframe.ui.widget.ZzViewPager;
import zhouzhuo810.me.zzandframe.ui.widget.zzpagerindicator.ZzPagerIndicator;
import zhouzhuo810.me.zzandframedemo.fgm.TestFragment;

/**
 * ZzPagerIndicator 示例
 * Created by zhouzhuo810 on 2017/11/17.
 */
public class PagerActivity extends BaseActivity {

    private TitleBar titleBar;
    private ZzPagerIndicator indicator;
    private ZzViewPager viewPager;

    private void assignViews() {
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        indicator = (ZzPagerIndicator) findViewById(R.id.indicator);
        viewPager = (ZzViewPager) findViewById(R.id.view_pager);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_pager;
    }

    @Override
    public void initView() {
        assignViews();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        titleBar.setOnTitleClickListener(new TitleBar.OnTitleClick() {

            @Override
            public void onLeftClick(ImageView ivLeft, MarkView mv, TextView tvLeft) {
                closeAct();
            }

            @Override
            public void onTitleClick(TextView tvTitle) {

            }

            @Override
            public void onRightClick(ImageView ivRight, MarkView mv, TextView tvRight) {

            }

        });

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                return TestFragment.newInstance("fragment" + position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "fragment"+position;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        indicator.setViewPager(viewPager);
        indicator.setCurrentItem(0, false);
    }

    @Override
    public boolean defaultBack() {
        return false;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void saveState(Bundle outState) {

    }

    @Override
    public void restoreState(@Nullable Bundle savedInstanceState) {

    }
}
