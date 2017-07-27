package zhouzhuo810.me.zzandframe.ui.widget;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import zhouzhuo810.me.zzandframe.R;

/**
 * 底部Tab
 * Created by admin on 2017/7/27.
 */
public class TabBar extends LinearLayout {

    private LinearLayout ll0;
    private ImageView iv0;
    private TextView tv0;
    private LinearLayout ll1;
    private ImageView iv1;
    private TextView tv1;
    private LinearLayout ll2;
    private ImageView iv2;
    private TextView tv2;
    private LinearLayout ll3;
    private ImageView iv3;
    private TextView tv3;
    private LinearLayout ll4;
    private ImageView iv4;
    private TextView tv4;

    interface OnTabBarClick {
        void onTabClick(ImageView iv, TextView tv, int position, boolean changed);
    }

    public TabBar(Context context) {
        super(context);
    }

    public TabBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(value = 21)
    public TabBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs) {
        if (isInEditMode()) {
            return;
        }
        View root = LayoutInflater.from(context).inflate(R.layout.tab_bar_layout, null);
        ll0 = (LinearLayout) findViewById(R.id.ll0);
        iv0 = (ImageView) findViewById(R.id.iv0);
        tv0 = (TextView) findViewById(R.id.tv0);
        ll1 = (LinearLayout) findViewById(R.id.ll1);
        iv1 = (ImageView) findViewById(R.id.iv1);
        tv1 = (TextView) findViewById(R.id.tv1);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
        iv2 = (ImageView) findViewById(R.id.iv2);
        tv2 = (TextView) findViewById(R.id.tv2);
        ll3 = (LinearLayout) findViewById(R.id.ll3);
        iv3 = (ImageView) findViewById(R.id.iv3);
        tv3 = (TextView) findViewById(R.id.tv3);
        ll4 = (LinearLayout) findViewById(R.id.ll4);
        iv4 = (ImageView) findViewById(R.id.iv4);
        tv4 = (TextView) findViewById(R.id.tv4);
        initAttrs(context, attrs);
        initEvent();
        addView(root);
    }

    private void initAttrs(Context context, AttributeSet attrs) {

    }

    private void initEvent() {

    }


}
