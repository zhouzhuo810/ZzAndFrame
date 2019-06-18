package zhouzhuo810.me.zzandframedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zhouzhuo810.me.zzandframe.ui.act.BaseActivity;
import zhouzhuo810.me.zzandframe.ui.widget.MarkView;
import zhouzhuo810.me.zzandframe.ui.widget.TitleBar;
import zhouzhuo810.me.zzandframe.ui.widget.ZzViewPager;
import zhouzhuo810.me.zzandframe.ui.widget.zzpagerindicator.ZzPagerIndicator;
import zhouzhuo810.me.zzandframe.ui.widget.zzpagerindicator.adapter.ZzFragmentPagerAdapter;
import zhouzhuo810.me.zzandframedemo.fgm.TestFragment;

/**
 * ZzPagerIndicator 示例
 * Created by zhouzhuo810 on 2017/11/17.
 */
public class PagerActivity extends BaseActivity {
    
    private TitleBar titleBar;
    private ZzPagerIndicator indicator;
    private ZzViewPager viewPager;
    private TabLayout mTabLayout;
    
    private void assignViews() {
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        indicator = (ZzPagerIndicator) findViewById(R.id.indicator);
        mTabLayout = findViewById(R.id.tab_layout);
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
                if (indicator.getTabTextIconOrientation() == ZzPagerIndicator.TabOrientation.HORIZONTAL) {
                    indicator.setTabTextIconOrientation(ZzPagerIndicator.TabOrientation.VERTICAL);
                    tvRight.setText("垂直");
                } else {
                    indicator.setTabTextIconOrientation(ZzPagerIndicator.TabOrientation.HORIZONTAL);
                    tvRight.setText("水平");
                }
            }
            
        });
        
        int[] iconNormal = {
            R.drawable.ic_chat_normal, R.drawable.ic_contact_normal, R.drawable.ic_find_normal,
            R.drawable.ic_chat_normal, R.drawable.ic_contact_normal, R.drawable.ic_find_normal,
            R.drawable.ic_chat_normal, R.drawable.ic_contact_normal, R.drawable.ic_find_normal,
            R.drawable.ic_chat_normal, R.drawable.ic_contact_normal, R.drawable.ic_find_normal
        };
        int[] iconSelected = {
            R.drawable.ic_chat, R.drawable.ic_contact, R.drawable.ic_find,
            R.drawable.ic_chat, R.drawable.ic_contact, R.drawable.ic_find,
            R.drawable.ic_chat, R.drawable.ic_contact, R.drawable.ic_find,
            R.drawable.ic_chat, R.drawable.ic_contact, R.drawable.ic_find
        };
        String[] titles = {"聊天", "联系人", "发现", "聊天", "联系人", "发现", "聊天", "联系人", "发现", "聊天", "联系人", "发现"};
        
        List<Fragment> fgms = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            fgms.add(TestFragment.newInstance(titles[i]));
        }
        viewPager.setAdapter(new ZzFragmentPagerAdapter(getSupportFragmentManager(), fgms, titles, iconSelected, iconNormal));
        
        mTabLayout.setupWithViewPager(viewPager);
        
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
