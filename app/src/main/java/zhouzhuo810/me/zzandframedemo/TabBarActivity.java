package zhouzhuo810.me.zzandframedemo;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import zhouzhuo810.me.zzandframe.ui.act.BaseActivity;
import zhouzhuo810.me.zzandframe.ui.widget.MarkView;
import zhouzhuo810.me.zzandframe.ui.widget.TabBar;
import zhouzhuo810.me.zzandframe.ui.widget.TitleBar;

/**
 * Created by admin on 2017/7/29.
 */
public class TabBarActivity extends BaseActivity {

    private TitleBar titleBar;
    private TabBar tabBar;

    private int[] pressIcons = {
            R.drawable.ic_chat,
            R.drawable.ic_contact,
            R.drawable.ic_find,
            R.drawable.ic_me,
            R.drawable.ic_me
    };
    private int[] normalIcons = {
            R.drawable.ic_chat_normal,
            R.drawable.ic_contact_normal,
            R.drawable.ic_find_normal,
            R.drawable.ic_me_normal,
            R.drawable.ic_me_normal
    };
    private TextView tvName;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tab_bar;
    }

    @Override
    public boolean defaultBack() {
        return false;
    }

    @Override
    public void initView() {
        titleBar = (TitleBar) findViewById(R.id.title_bar);
        tvName = (TextView) findViewById(R.id.tv_name);
        tabBar = (TabBar) findViewById(R.id.tab_bar);
    }

    @Override
    public void initData() {
        tabBar.setNormalIconRes(normalIcons)
                .setPressIconRes(pressIcons)
                .showMarkViewAt(0)
                .setMarkNumberAtPosition(0, 9)
                .showMarkViewAt(1)
                .setMarkShapeAtPosition(1, MarkView.MarkShape.POINT)
                .showMarkViewAt(2)
                .setMarkNumberAtPosition(2, 100)
                .showMarkViewAt(3)
                .setMarkShapeAtPosition(3, MarkView.MarkShape.RECT)
                .setMaxMarkNumberAtPosition(3, 40)
                .setMarkNumberAtPosition(3, 41)
                .showMarkViewAt(4)
                .setMarkNumberAtPosition(4, 50)
                .update();

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

        tabBar.setOnTabBarClickListener(new TabBar.OnTabBarClick() {
            @Override
            public void onTabClick(ImageView iv, TextView tv, int position, boolean changed) {
                tvName.setText(tv.getText().toString());
            }
        });


    }

    /**
     * 底部弹出
     *
     * @param view
     */
    private void verticalRun(final View view) {
        ValueAnimator animator = ValueAnimator.ofFloat(-view.getHeight(), 0);
        animator.setTarget(view);
        animator.setDuration(1000).start();
//      animator.setInterpolator(value)
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setTranslationY((Float) animation.getAnimatedValue());
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
