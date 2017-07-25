package zhouzhuo810.me.zzandframedemo;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import zhouzhuo810.me.zzandframe.ui.act.BaseActivity;
import zhouzhuo810.me.zzandframe.ui.widget.TitleBar;

public class MainActivity extends BaseActivity {

    private LinearLayout activityMain;
    private SwipeRefreshLayout refresh;
    private ListView lv;
    private TitleBar titleBar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean defaultBack() {
        return false;
    }

    @Override
    public void initView() {
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        activityMain = (LinearLayout) findViewById(R.id.activity_main);
        refresh = (SwipeRefreshLayout) findViewById(R.id.refresh);
        lv = (ListView) findViewById(R.id.lv);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });

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

    @Override
    public void setEditListener(EditText et, View ivClear) {

    }
}
