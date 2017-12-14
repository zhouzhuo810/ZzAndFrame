package zhouzhuo810.me.zzandframedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import zhouzhuo810.me.zzandframe.common.utils.ToastUtils;
import zhouzhuo810.me.zzandframe.ui.act.BaseActivity;
import zhouzhuo810.me.zzandframe.ui.widget.MarkView;
import zhouzhuo810.me.zzandframe.ui.widget.TitleBar;

/**
 * Created by zhouzhuo810 on 2017/12/14.
 */

public class DialogActivity extends BaseActivity{

    private TitleBar titleBar;
    private Button btnTwoBtnIos;

    private void assignViews() {
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        btnTwoBtnIos = (Button) findViewById(R.id.btn_two_btn_ios);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_dialog;
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

        btnTwoBtnIos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTwoBtnDialogIOSStyle("模式切换", "确认切换到标准显示模式？", "确定", "取消", -1, -1, false, new OnIOSTwoBtnEditClick() {
                    @Override
                    public void onLeftClick() {
                        ToastUtils.showCustomBgToast("left");
                    }

                    @Override
                    public void onRightClick() {
                        ToastUtils.showCustomBgToast("right");
                    }
                });
            }
        });
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
