package zhouzhuo810.me.zzandframedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import zhouzhuo810.me.zzandframe.common.utils.ToastUtils;
import zhouzhuo810.me.zzandframe.ui.act.BaseActivity;
import zhouzhuo810.me.zzandframe.ui.widget.IFooterCreator;
import zhouzhuo810.me.zzandframe.ui.widget.MarkView;
import zhouzhuo810.me.zzandframe.ui.widget.TitleBar;
import zhouzhuo810.me.zzandframe.ui.widget.ZzLvRefreshLayout;
import zhouzhuo810.me.zzandframe.ui.widget.ZzRvRefreshLayout;
import zhouzhuo810.me.zzandframedemo.adapter.LvAdapter;
import zhouzhuo810.me.zzandframedemo.adapter.RvAdapter;
import zhouzhuo810.me.zzandframedemo.bean.LvBean;
import zhouzhuo810.me.zzandframedemo.bean.RvBean;

/**
 * Created by admin on 2017/7/26.
 */
public class RvActivity extends BaseActivity {

    private TitleBar titleBar;
    private RvAdapter adapter;
    private List<RvBean> list;

    private ZzRvRefreshLayout refresh;
    private RecyclerView rv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rv;
    }

    @Override
    public boolean defaultBack() {
        return false;
    }

    @Override
    public void initView() {
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        refresh = (ZzRvRefreshLayout) findViewById(R.id.refresh);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        refresh.setFooterCreator(new IFooterCreator() {
            @Override
            public int getFooterLayoutId() {
                return R.layout.text_footer_layout;
            }

            @Override
            public int getFooterTextViewId() {
                return R.id.tv;
            }

            @Override
            public int getProgressBarId() {
                return R.id.pb;
            }

            @Override
            public String getNormalText() {
                return "上拉加载更多";
            }

            @Override
            public String getLoadingText() {
                return "拼命加载中";
            }

            @Override
            public String getNoNeedLoadText() {
                return "已经是全部数据了";
            }

            @Override
            public void onFooterClick(ProgressBar pb, TextView tv) {
                ToastUtils.showLongToast("ok");
            }
        });
        list = new ArrayList<>();
        adapter = new RvAdapter(this, list);
        rv.setAdapter(adapter);
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
                List<RvBean> test = new ArrayList<RvBean>();
                for (int i = 0; i < 10; i++) {
                    RvBean bean = new RvBean();
                    bean.setName("TEST" + i);
                    bean.setPhone("1231452" + i);
                    test.add(bean);
                }
                list = test;

                //在UI线程中更新UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateAll(list);
                        stopRefresh(refresh);
                        refresh.showFooter();
                    }
                });
            }
        }.start();
    }

    /**
     * 模拟网络请求
     */
    private void loadData() {
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

                for (int i = 0; i < 10; i++) {
                    RvBean bean = new RvBean();
                    bean.setName("TEST" + i);
                    bean.setPhone("1231452" + i);
                    list.add(bean);
                }
                //在UI线程中更新UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateAll(list);
                        refresh.stopLoad();
                    }
                });
            }
        }.start();
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

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });

        refresh.setOnLoadListener(new ZzLvRefreshLayout.OnLoadListener() {
            @Override
            public void onLoad(ProgressBar pb, TextView tvFooter) {
                loadData();
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
