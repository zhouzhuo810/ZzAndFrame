package zhouzhuo810.me.zzandframedemo;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import zhouzhuo810.me.zzandframe.common.utils.ToastUtils;
import zhouzhuo810.me.zzandframe.ui.act.BaseActivity;
import zhouzhuo810.me.zzandframe.ui.widget.TitleBar;

/**
 * Created by zhouzhuo810 on 2017/11/17.
 */

public class PhotoActivity extends BaseActivity {
    private TitleBar titleBar;
    private Button btnAlbum;
    private Button btnCamera;

    private void assignViews() {
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        btnAlbum = (Button) findViewById(R.id.btn_album);
        btnCamera = (Button) findViewById(R.id.btn_camera);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_photo;
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

        btnAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhoto(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"ZzAndFrame", true);
            }
        });


        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"ZzAndFrame", true);
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
    public void onPhotoTaked(File file, String filePath) {
        ToastUtils.showCustomBgToast(filePath);
    }

    @Override
    public void onPhotoChoosed(File file, String filePath) {
        ToastUtils.showCustomBgToast(filePath);
    }

    @Override
    public void onPhotoCroped(File file, String filePath) {

    }

    @Override
    public void saveState(Bundle outState) {

    }

    @Override
    public void restoreState(@Nullable Bundle savedInstanceState) {

    }
}
