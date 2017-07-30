package zhouzhuo810.me.zzandframedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import zhouzhuo810.me.zzandframe.ui.act.BaseActivity;
import zhouzhuo810.me.zzandframe.ui.widget.MarkView;
import zhouzhuo810.me.zzandframe.ui.widget.TitleBar;

/**
 * Created by admin on 2017/7/29.
 */

public class MarkActivity extends BaseActivity {

    private TitleBar titleBar;
    private MarkView mark;
    private MarkView mark1;
    private Button btnDec;
    private Button btnAdd;
    private Button btnOval;
    private Button btnRect;
    private Button btnPoint;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mark;
    }

    @Override
    public boolean defaultBack() {
        return false;
    }

    @Override
    public void initView() {
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        mark = (MarkView) findViewById(R.id.mark);
        mark1 = (MarkView) findViewById(R.id.mark1);
        btnDec = (Button) findViewById(R.id.btn_dec);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnOval = (Button) findViewById(R.id.btn_oval);
        btnRect = (Button) findViewById(R.id.btn_rect);
        btnPoint = (Button) findViewById(R.id.btn_point);

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

        btnDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mark.setMarkNumber(mark.getMarkNumber()-1).update();
                mark1.setMarkNumber(mark1.getMarkNumber()-1).update();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mark.setMarkNumber(mark.getMarkNumber()+1).update();
                mark1.setMarkNumber(mark1.getMarkNumber()+1).update();
            }
        });

        btnOval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mark.setBgShape(MarkView.MarkShape.OVAL).update();
                mark1.setBgShape(MarkView.MarkShape.OVAL).update();
            }
        });

        btnRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mark.setBgShape(MarkView.MarkShape.RECT).update();
                mark1.setBgShape(MarkView.MarkShape.RECT).update();
            }
        });

        btnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mark.setBgShape(MarkView.MarkShape.POINT).update();
                mark1.setBgShape(MarkView.MarkShape.POINT).update();
            }
        });


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
