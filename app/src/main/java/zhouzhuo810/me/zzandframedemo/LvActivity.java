package zhouzhuo810.me.zzandframedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import zhouzhuo810.me.zzandframe.common.utils.ToastUtils;
import zhouzhuo810.me.zzandframe.ui.act.BaseActivity;
import zhouzhuo810.me.zzandframe.ui.widget.IFooterCreator;
import zhouzhuo810.me.zzandframe.ui.widget.TitleBar;
import zhouzhuo810.me.zzandframe.ui.widget.ZzLvRefreshLayout;
import zhouzhuo810.me.zzandframedemo.adapter.LvAdapter;
import zhouzhuo810.me.zzandframedemo.bean.LvBean;

/**
 * Created by admin on 2017/7/26.
 */
public class LvActivity extends BaseActivity {

    private ZzLvRefreshLayout refresh;
    private ListView lv;
    private TitleBar titleBar;
    private LvAdapter adapter;
    private List<LvBean> list;

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
        refresh = (ZzLvRefreshLayout) findViewById(R.id.refresh);
        lv = (ListView) findViewById(R.id.lv);
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
        adapter = new LvAdapter(this, list);
        lv.setAdapter(adapter);
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
                List<LvBean> test= new ArrayList<LvBean>();
                for (int i = 0; i < 10; i++) {
                    LvBean bean = new LvBean();
                    bean.setName("TEST"+i);
                    bean.setPhone("1231452"+i);
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
                    LvBean bean = new LvBean();
                    bean.setName("TEST"+i);
                    bean.setPhone("1231452"+i);
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
