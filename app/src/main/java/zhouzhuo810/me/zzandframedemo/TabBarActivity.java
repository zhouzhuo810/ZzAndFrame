package zhouzhuo810.me.zzandframedemo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.animation.LinearInterpolator;
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

        tabBar.getLl0().setVisibility(View.INVISIBLE);
        tabBar.getLl1().setVisibility(View.INVISIBLE);
        tabBar.getLl2().setVisibility(View.INVISIBLE);
        tabBar.getLl3().setVisibility(View.INVISIBLE);
        tabBar.getLl4().setVisibility(View.INVISIBLE);

        titleBar.getLlLeft().setVisibility(View.INVISIBLE);
        titleBar.getLlRight().setVisibility(View.INVISIBLE);
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
                showAnim();
            }
        });

        tabBar.setOnTabBarClickListener(new TabBar.OnTabBarClick() {
            @Override
            public void onTabClick(ImageView iv, TextView tv, int position, boolean changed) {
                tvName.setText(tv.getText().toString());
            }
        });


    }

    private void showAnim() {

        ObjectAnimator anim1 = ObjectAnimator.ofFloat(tabBar.getLl0(), "y",
                tabBar.getLl0().getHeight(), 0);
        anim1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                tabBar.getLl0().setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(tabBar.getLl1(), "y",
                tabBar.getLl1().getHeight(), 0);
        anim2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                tabBar.getLl1().setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(tabBar.getLl2(), "y",
                tabBar.getLl2().getHeight(), 0);
        anim3.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                tabBar.getLl2().setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(tabBar.getLl3(), "y",
                tabBar.getLl3().getHeight(), 0);
        anim4.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                tabBar.getLl3().setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        ObjectAnimator anim5 = ObjectAnimator.ofFloat(tabBar.getLl4(), "y",
                tabBar.getLl4().getHeight(), 0);
        anim5.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                tabBar.getLl4().setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(100);
        animSet.setInterpolator(new LinearInterpolator());
        //两个动画同时执行
        animSet.playSequentially(anim1, anim2, anim3, anim4, anim5);
        animSet.start();


    }

    private void showAnim2() {
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(titleBar.getLlLeft(), "x",
                -titleBar.getLlLeft().getWidth(), 0);
        anim1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                titleBar.getLlLeft().setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        float x = titleBar.getLlRight().getX();
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(titleBar.getLlRight(), "x",
                x + titleBar.getLlRight().getWidth(), x);
        anim2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                titleBar.getLlRight().setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(200);
        animSet.setInterpolator(new FastOutSlowInInterpolator());
        //两个动画同时执行
        animSet.playSequentially(anim1, anim2);
        animSet.start();
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
        new Handler()
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showAnim();
                        showAnim2();
                    }
                }, 2000);

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
