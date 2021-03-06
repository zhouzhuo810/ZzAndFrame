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

import java.io.File;
import java.util.Date;

import zhouzhuo810.me.zzandframe.common.utils.DateUtils;
import zhouzhuo810.me.zzandframe.common.utils.NoticeUtils;
import zhouzhuo810.me.zzandframe.common.utils.SharedUtils;
import zhouzhuo810.me.zzandframe.common.utils.ToastUtils;
import zhouzhuo810.me.zzandframe.ui.act.BaseActivity;
import zhouzhuo810.me.zzandframe.ui.widget.MarkView;
import zhouzhuo810.me.zzandframe.ui.widget.TitleBar;

public class MainActivity extends BaseActivity {

    private TitleBar titleBar;
    private Button btnNotice;
    private Button btnToast;
    private Button btnMark;
    private Button btnTabBar;
    private Button btnPreview;
    private Button btnPhoto;
    private Button btnPager;
    private Button btnDialog;

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
        btnNotice = (Button) findViewById(R.id.btn_notice);
        btnToast = (Button) findViewById(R.id.btn_toast);
        btnMark = (Button) findViewById(R.id.btn_mark);
        btnTabBar = (Button) findViewById(R.id.btn_tab_bar);
        btnPreview = (Button) findViewById(R.id.btn_preview);
        btnPhoto = (Button) findViewById(R.id.btn_photo);
        btnPager = (Button) findViewById(R.id.btn_pager);
        btnDialog = (Button) findViewById(R.id.btn_dialog);
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
                ToastUtils.showShortToast(tvTitle.getText().toString().trim());
                NoticeUtils.hideNormalNotice(MyApplication.getBaseInstance());
            }

            @Override
            public void onRightClick(ImageView ivRight, MarkView mv, TextView tvRight) {
                ToastUtils.showShortToast(tvRight.getText().toString().trim());
                showNormalNoticeWithCustomVoice(tvRight.getText().toString());

            }

        });

        btnNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalNotice(btnNotice.getText().toString());
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

        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PreviewPicActivity.class);
                startActWithIntent(intent);
            }
        });

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PhotoActivity.class);
                startActWithIntent(intent);
            }
        });

        btnPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PagerActivity.class);
                startActWithIntent(intent);
            }
        });

        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DialogActivity.class);
                startActWithIntent(intent);
            }
        });


    }

    private void showNormalNotice(String s) {
        NoticeUtils.showNormalNotice(MyApplication.getBaseInstance(), s,
                s, true, false, R.mipmap.ic_launcher, true, true, MainActivity.class);
    }

    private void showNormalNoticeWithCustomVoice(String s) {
        NoticeUtils.showNormalNoticeWithCustomVoice(MyApplication.getBaseInstance(), s,
                s, true, false, R.mipmap.ic_launcher, true, R.raw.msg_sound, false, MainActivity.class);
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
