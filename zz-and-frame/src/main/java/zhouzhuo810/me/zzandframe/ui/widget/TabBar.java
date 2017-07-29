package zhouzhuo810.me.zzandframe.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
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

    private int position = 0;

    private OnTabBarClick onTabBarClick;

    private int tabCount = 5;

    public enum TabCount {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE
    }

    interface OnTabBarClick {
        void onTabClick(ImageView iv, TextView tv, int position, boolean changed);
    }

    public TabBar(Context context) {
        super(context);
        init(context, null);
    }

    public TabBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TabBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(value = 21)
    public TabBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (isInEditMode()) {
            return;
        }
        View root = LayoutInflater.from(context).inflate(R.layout.tab_bar_layout, this, false);
        ll0 = (LinearLayout) root.findViewById(R.id.ll0);
        iv0 = (ImageView) root.findViewById(R.id.iv0);
        tv0 = (TextView) root.findViewById(R.id.tv0);
        ll1 = (LinearLayout) root.findViewById(R.id.ll1);
        iv1 = (ImageView) root.findViewById(R.id.iv1);
        tv1 = (TextView) root.findViewById(R.id.tv1);
        ll2 = (LinearLayout) root.findViewById(R.id.ll2);
        iv2 = (ImageView) root.findViewById(R.id.iv2);
        tv2 = (TextView) root.findViewById(R.id.tv2);
        ll3 = (LinearLayout) root.findViewById(R.id.ll3);
        iv3 = (ImageView) root.findViewById(R.id.iv3);
        tv3 = (TextView) root.findViewById(R.id.tv3);
        ll4 = (LinearLayout) root.findViewById(R.id.ll4);
        iv4 = (ImageView) root.findViewById(R.id.iv4);
        tv4 = (TextView) root.findViewById(R.id.tv4);
        initAttrs(context, attrs);
        initEvent();
        addView(root);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.TabBar);
            tabCount = t.getInt(R.styleable.TabBar_tb_tabCount, 5);
            CharSequence[] textArray = t.getTextArray(R.styleable.TabBar_tb_tabNames);
            if (textArray != null) {
                if (tabCount == textArray.length) {
                    for (int i = 0; i < textArray.length; i++) {
                        switch (tabCount) {
                            case 1:
                                if (i == 0) {
                                    tv0.setText(textArray[0]);
                                }
                                setVisible(ll1, false);
                                setVisible(ll2, false);
                                setVisible(ll3, false);
                                setVisible(ll4, false);
                                break;
                            case 2:
                                if (i == 0) {
                                    tv0.setText(textArray[0]);
                                }
                                if (i == 1) {
                                    tv1.setText(textArray[1]);
                                }
                                setVisible(ll2, false);
                                setVisible(ll3, false);
                                setVisible(ll4, false);
                                break;
                            case 3:
                                if (i == 0) {
                                    tv0.setText(textArray[0]);
                                }
                                if (i == 1) {
                                    tv1.setText(textArray[1]);
                                }
                                if (i == 2) {
                                    tv2.setText(textArray[2]);
                                }
                                setVisible(ll3, false);
                                setVisible(ll4, false);
                                break;
                            case 4:
                                if (i == 0) {
                                    tv0.setText(textArray[0]);
                                }
                                if (i == 1) {
                                    tv1.setText(textArray[1]);
                                }
                                if (i == 2) {
                                    tv2.setText(textArray[2]);
                                }
                                if (i == 3) {
                                    tv3.setText(textArray[3]);
                                }
                                setVisible(ll4, false);
                                break;
                            case 5:
                                if (i == 0) {
                                    tv0.setText(textArray[0]);
                                }
                                if (i == 1) {
                                    tv1.setText(textArray[1]);
                                }
                                if (i == 2) {
                                    tv2.setText(textArray[2]);
                                }
                                if (i == 3) {
                                    tv3.setText(textArray[3]);
                                }
                                if (i == 4) {
                                    tv4.setText(textArray[4]);
                                }
                                break;
                        }

                    }
                } else {
                    throw new RuntimeException("Tab名称和Tab数量不一致");
                }
            }
            t.recycle();
        }
    }

    private void setVisible(View view, boolean visible) {
        if (view != null) {
            view.setVisibility(visible ? VISIBLE : GONE);
        }
    }

    private void initEvent() {
        ll0.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onTabBarClick != null) {
                    onTabBarClick.onTabClick(iv0, tv0, 0, position != 0);
                }
                position = 0;
            }
        });
        ll1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onTabBarClick != null) {
                    onTabBarClick.onTabClick(iv1, tv1, 1, position != 1);
                }
                position = 1;
            }
        });
        ll2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onTabBarClick != null) {
                    onTabBarClick.onTabClick(iv2, tv2, 2, position != 2);
                }
                position = 2;
            }
        });
        ll3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onTabBarClick != null) {
                    onTabBarClick.onTabClick(iv3, tv3, 3, position != 3);
                }
                position = 3;
            }
        });
        ll4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onTabBarClick != null) {
                    onTabBarClick.onTabClick(iv4, tv4, 4, position != 4);
                }
                position = 4;
            }
        });
    }

    public void setOnTabBarClickListener(OnTabBarClick onTabBarClick) {
        this.onTabBarClick = onTabBarClick;
    }
}
