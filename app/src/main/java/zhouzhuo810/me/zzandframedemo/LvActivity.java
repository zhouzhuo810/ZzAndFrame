package zhouzhuo810.me.zzandframedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import zhouzhuo810.me.zzandframe.ui.act.BaseActivity;
import zhouzhuo810.me.zzandframe.ui.widget.TitleBar;

/**
 * Created by admin on 2017/7/26.
 */
public class LvActivity extends BaseActivity {

    private SwipeRefreshLayout refresh;
    private ListView lv;
    private TitleBar titleBar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_lv;
    }

    @Override
    public boolean defaultBack() {
        return false;
    }

    @Override
    public void initView() {
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        refresh = (SwipeRefreshLayout) findViewById(R.id.refresh);
        lv = (ListView) findViewById(R.id.lv);
    }

    @Override
    public void initData() {
        getData();
    }

    /**
     * 模拟网络请求
     */
    private void getData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                //模拟网络请求
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //在UI线程中更新UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        stopRefresh(refresh);
                    }
                });
            }
        }.start();
    }

    @Override
    public void initEvent() {
        titleBar.setOnTitleClickListener(new TitleBar.OnTitleClick() {
            @Override
            public void onLeftClick(ImageView ivLeft, TextView tvLeft) {
                closeAct();
            }

            @Override
            public void onTitleClick(TextView tvTitle) {

            }

            @Override
            public void onRightClick(ImageView ivRight, TextView tvRight) {

            }
        });

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
    }

    @Override
    public void resume() {
        startRefresh(refresh);
        getData();
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
