package zhouzhuo810.me.zzandframedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import zhouzhuo810.me.zzandframe.common.cons.ZzConst;
import zhouzhuo810.me.zzandframe.ui.act.BaseActivity;
import zhouzhuo810.me.zzandframe.ui.act.ImagePreviewActivity;
import zhouzhuo810.me.zzandframe.ui.act.MultiImagePreviewActivity;
import zhouzhuo810.me.zzandframe.ui.widget.TitleBar;

/**
 * Created by zhouzhuo810 on 2017/11/16.
 */
public class PreviewPicActivity extends BaseActivity {

    private TitleBar titleBar;
    private Button btnSinglePic;
    private Button btnMultiPic;

    private void assignViews() {
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        btnSinglePic = (Button) findViewById(R.id.btn_single_pic);
        btnMultiPic = (Button) findViewById(R.id.btn_multi_pic);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_preview;
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

        btnSinglePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreviewPicActivity.this, ImagePreviewActivity.class);
                intent.putExtra(ZzConst.IMG_PRE_PIC_URL, "http://p1.so.qhmsg.com/dmfd/326_204_/t01ad675c2d6620f626.jpg");
                startActWithIntent(intent);
            }
        });

        btnMultiPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreviewPicActivity.this, MultiImagePreviewActivity.class);
                intent.putExtra(ZzConst.IMG_PRE_MULTI_PIC_URL, getPics());
                startActWithIntent(intent);
            }
        });
    }

    private ArrayList<String> getPics() {
        ArrayList<String> data = new ArrayList<>();
        data.add("http://p1.so.qhmsg.com/dmfd/326_204_/t01ad675c2d6620f626.jpg");
        data.add("http://p4.so.qhmsg.com/dmfd/326_204_/t01e7b8da6f7881d669.jpg");
        data.add("http://p2.so.qhimgs1.com/dmfd/326_204_/t016b83625d3693e592.jpg");
        data.add("http://p0.so.qhimgs1.com/dmfd/326_204_/t019a8f0f4789b91170.jpg");
        data.add("http://p1.so.qhimgs1.com/dmfd/326_204_/t01da7b6be4c2e7485d.jpg");
        data.add("http://p4.so.qhimgs1.com/dmfd/326_204_/t016122ec45c5fdc5fa.jpg");
        data.add("http://p0.so.qhmsg.com/dmfd/326_204_/t015ba6df95da81c21f.jpg");
        data.add("http://p0.so.qhimgs1.com/dmfd/326_204_/t012c852c8f981d6a00.jpg");
        data.add("http://p4.so.qhimgs1.com/dmfd/326_204_/t01bc3afb43b885f79a.jpg");
        data.add("http://p2.so.qhmsg.com/dmfd/326_204_/t016f319fe697b397a7.jpg");
        data.add("http://p4.so.qhmsg.com/dmfd/326_204_/t01260296831eb4e1c9.jpg");
        data.add("http://p2.so.qhimgs1.com/dmfd/326_204_/t01f70e3d434b658072.jpg");
        data.add("http://p1.so.qhimgs1.com/dmfd/326_204_/t01ff382f9ac4ff3b41.jpg");
        data.add("http://p3.so.qhimgs1.com/dmfd/326_204_/t0136c51e038b8eb443.jpg");
        data.add("http://p4.so.qhimgs1.com/dmfd/326_204_/t01510b700b852c19ea.jpg");
        data.add("http://p2.so.qhmsg.com/dmfd/326_204_/t01ee2021295046db97.jpg");
        data.add("http://p2.so.qhimgs1.com/dmfd/326_204_/t011827d91512e21f1e.jpg");
        data.add("http://p4.so.qhimgs1.com/dmfd/326_204_/t01e0d10a1d9c967b6a.jpg");
        data.add("http://p5.so.qhimgs1.com/dmfd/326_204_/t014dee2f747c97027b.jpg");
        data.add("http://p0.so.qhmsg.com/dmfd/326_204_/t015937ffe391b99cbf.jpg");
        data.add("http://p2.so.qhimgs1.com/dmfd/326_204_/t01c8781008ac43b8b2.jpg");
        data.add("http://p0.so.qhimgs1.com/dmfd/326_204_/t0157cf838838b73380.jpg");
        data.add("http://p2.so.qhimgs1.com/dmfd/326_204_/t01486d9595e7e72a7e.jpg");
        data.add("http://p1.so.qhmsg.com/dmfd/326_204_/t016ddb799a12726bc6.jpg");
        data.add("http://p4.so.qhimgs1.com/dmfd/326_204_/t01cbfab89caa120e9a.jpg");
        data.add("http://p0.so.qhmsg.com/dmfd/326_204_/t01fd0af7e89abd4615.jpg");
        data.add("http://p2.so.qhimgs1.com/dmfd/326_204_/t0155c730d796084202.jpg");
        data.add("http://p0.so.qhmsg.com/dmfd/326_204_/t01007ed5e564c9c99f.jpg");
        data.add("http://p1.so.qhimgs1.com/dmfd/326_204_/t0127d36bd652e5f6ed.jpg");
        return data;
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
