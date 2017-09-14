package zhouzhuo810.me.zzandframedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Date;

import zhouzhuo810.me.zzandframe.common.utils.DateUtils;
import zhouzhuo810.me.zzandframe.common.utils.NoticeUtils;
import zhouzhuo810.me.zzandframe.common.utils.SharedUtils;
import zhouzhuo810.me.zzandframe.common.utils.ToastUtils;
import zhouzhuo810.me.zzandframe.ui.act.BaseActivity;
import zhouzhuo810.me.zzandframe.ui.widget.TitleBar;

public class MainActivity extends BaseActivity {

    private TitleBar titleBar;
    private Button btnLv;
    private Button btnRv;
    private Button btnNotice;
    private Button btnToast;
    private Button btnMark;
    private Button btnTabBar;

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
        btnLv = (Button) findViewById(R.id.btn_lv);
        btnRv = (Button) findViewById(R.id.btn_rv);
        btnNotice = (Button) findViewById(R.id.btn_notice);
        btnToast = (Button) findViewById(R.id.btn_toast);
        btnMark = (Button) findViewById(R.id.btn_mark);
        btnTabBar = (Button) findViewById(R.id.btn_tab_bar);
    }

    @Override
    public void initData() {

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
                ToastUtils.showShortToast(tvTitle.getText().toString().trim());
                NoticeUtils.hideNormalNotice(MyApplication.getBaseInstance());
            }

            @Override
            public void onRightClick(ImageView ivRight, TextView tvRight) {
                ToastUtils.showShortToast(tvRight.getText().toString().trim());
                showNormalNotice(tvRight.getText().toString());
            }
        });

        btnLv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LvActivity.class);
                startActWithIntent(intent);
            }
        });
        btnRv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RvActivity.class);
                startActWithIntent(intent);
            }
        });

        btnMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MarkActivity.class);
                startActWithIntent(intent);
            }
        });

        btnTabBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TabBarActivity.class);
                startActWithIntent(intent);
            }
        });
    }

    private void showNormalNotice(String s) {
        NoticeUtils.showNormalNotice(MyApplication.getBaseInstance(), s,
                s, true, false, R.mipmap.ic_launcher, true, true, MainActivity.class);
    }


    @Override
    public void resume() {
        String time = DateUtils.handleTime("2017-08-05 12:40:00");
        Log.e("XXX", time);
        SharedUtils.putString(this, "time", time);
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
