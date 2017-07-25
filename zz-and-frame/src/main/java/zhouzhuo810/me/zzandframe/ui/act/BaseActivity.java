package zhouzhuo810.me.zzandframe.ui.act;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;

import com.zhy.autolayout.AutoLayoutActivity;

/**
 * BaseActivity
 * Created by admin on 2017/7/25.
 */
public abstract class BaseActivity extends AutoLayoutActivity implements IBaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        initView();
        initData();
        initEvent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        restoreState(savedInstanceState);
    }

    @Override
    public void startRefresh(final SwipeRefreshLayout refresh) {
        if (refresh != null) {
            refresh.post(new Runnable() {
                @Override
                public void run() {
                    refresh.setRefreshing(true);
                }
            });
        }
    }

    @Override
    public void stopRefresh(SwipeRefreshLayout refresh) {
        if (refresh != null) {
            refresh.setRefreshing(false);
        }
    }

    @Override
    public void onBackPressed() {
        if (defaultBack()) {
            super.onBackPressed();
        } else {
            closeAct();
        }
    }

    @Override
    public void closeAct() {
        finish();
        overridePendingTransition(inAnimation(), outAnimation());
    }

    @Override
    public int inAnimation() {
        return android.R.anim.slide_in_left;
    }

    @Override
    public int outAnimation() {
        return android.R.anim.slide_out_right;
    }

    @Override
    public void closeAllAct() {

    }

    @Override
    public void startActWithIntent(Intent intent) {
        startActivity(intent);
        overridePendingTransition(inAnimation(), outAnimation());
    }

    @Override
    public void startActWithIntentForResult(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
        overridePendingTransition(inAnimation(), outAnimation());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy();
    }
}
