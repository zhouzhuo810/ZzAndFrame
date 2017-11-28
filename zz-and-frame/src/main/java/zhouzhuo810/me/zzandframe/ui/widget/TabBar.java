package zhouzhuo810.me.zzandframe.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import zhouzhuo810.me.zzandframe.R;

/**
 * 底部Tab
 * Created by admin on 2017/7/27.
 */
public class TabBar extends LinearLayout {

    private RelativeLayout ll0;
    private ImageView iv0;
    private TextView tv0;
    private View line0;
    private RelativeLayout ll1;
    private ImageView iv1;
    private TextView tv1;
    private View line1;
    private RelativeLayout ll2;
    private ImageView iv2;
    private TextView tv2;
    private View line2;
    private RelativeLayout ll3;
    private ImageView iv3;
    private TextView tv3;
    private View line3;
    private RelativeLayout ll4;
    private ImageView iv4;
    private TextView tv4;
    private View line4;

    private int position = 0;

    private OnTabBarClick onTabBarClick;

    private int tabCount = 5;

    private int[] pressIcons;
    private int[] normalIcons;
    private int textSize;
    private int textColorNormal;
    private int textColorPress;
    private MarkView mv0;
    private MarkView mv1;
    private MarkView mv2;
    private MarkView mv3;
    private MarkView mv4;
    private boolean showMarkView;
    private boolean showImg;
    private boolean showText;
    private boolean showUnderLine;

    public enum TabCount {
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE
    }

    public interface OnTabBarClick {
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
        ll0 = (RelativeLayout) root.findViewById(R.id.rl0);
        iv0 = (ImageView) root.findViewById(R.id.iv0);
        tv0 = (TextView) root.findViewById(R.id.tv0);
        mv0 = (MarkView) root.findViewById(R.id.mv0);
        line0 = root.findViewById(R.id.line_0);
        ll1 = (RelativeLayout) root.findViewById(R.id.rl1);
        iv1 = (ImageView) root.findViewById(R.id.iv1);
        tv1 = (TextView) root.findViewById(R.id.tv1);
        mv1 = (MarkView) root.findViewById(R.id.mv1);
        line1 = root.findViewById(R.id.line_1);
        ll2 = (RelativeLayout) root.findViewById(R.id.rl2);
        iv2 = (ImageView) root.findViewById(R.id.iv2);
        tv2 = (TextView) root.findViewById(R.id.tv2);
        mv2 = (MarkView) root.findViewById(R.id.mv2);
        line2 = root.findViewById(R.id.line_2);
        ll3 = (RelativeLayout) root.findViewById(R.id.rl3);
        iv3 = (ImageView) root.findViewById(R.id.iv3);
        tv3 = (TextView) root.findViewById(R.id.tv3);
        mv3 = (MarkView) root.findViewById(R.id.mv3);
        line3 = root.findViewById(R.id.line_3);
        ll4 = (RelativeLayout) root.findViewById(R.id.rl4);
        iv4 = (ImageView) root.findViewById(R.id.iv4);
        tv4 = (TextView) root.findViewById(R.id.tv4);
        mv4 = (MarkView) root.findViewById(R.id.mv4);
        line4 = root.findViewById(R.id.line_4);
        initAttrs(context, attrs);
        initEvent();
        addView(root);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.TabBar);
            int imageSize = t.getDimensionPixelSize(R.styleable.TabBar_tb_imageSize, 60);
            setImageSize(imageSize);
            textSize = t.getDimensionPixelSize(R.styleable.TabBar_tb_textSize, 40);
            int markPointSize = t.getDimensionPixelSize(R.styleable.TabBar_tb_markPointSize, 24);
            setMarkPointSize(markPointSize);
            int markTextSize = t.getDimensionPixelSize(R.styleable.TabBar_tb_markTextSize, 34);
            setMarkTextSize(markTextSize);
            int markTextColor = t.getColor(R.styleable.TabBar_tb_markTextColor, 0xffffffff);
            setMarkTextColor(markTextColor);
            int markBgColor = t.getColor(R.styleable.TabBar_tb_markBgColor, 0xffff0000);
            setMarkBgColor(markBgColor);
            showMarkView = t.getBoolean(R.styleable.TabBar_tb_showMarkView, false);
            showImg = t.getBoolean(R.styleable.TabBar_tb_show_img, true);
            showText = t.getBoolean(R.styleable.TabBar_tb_show_text, true);
            showUnderLine = t.getBoolean(R.styleable.TabBar_tb_show_underline, false);

            setVisible(mv0, showMarkView);
            setVisible(mv1, showMarkView);
            setVisible(mv2, showMarkView);
            setVisible(mv3, showMarkView);
            setVisible(mv4, showMarkView);

            setVisible(tv0, showText);
            setVisible(tv1, showText);
            setVisible(tv2, showText);
            setVisible(tv3, showText);
            setVisible(tv4, showText);

            setVisible(iv0, showImg);
            setVisible(iv1, showImg);
            setVisible(iv2, showImg);
            setVisible(iv3, showImg);
            setVisible(iv4, showImg);

            textColorNormal = t.getColor(R.styleable.TabBar_tb_textColorNormal, 0x7f999999);
            textColorPress = t.getColor(R.styleable.TabBar_tb_textColorPress, 0xff000000);
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
        textSize = AutoUtils.getPercentWidthSize(textSize);
        tv0.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        tv1.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    public TabBar setImageSize(int imageSize) {
        int size = AutoUtils.getPercentWidthSize(imageSize);
        iv0.getLayoutParams().width = size;
        iv0.getLayoutParams().height = size;
        iv1.getLayoutParams().width = size;
        iv1.getLayoutParams().height = size;
        iv2.getLayoutParams().width = size;
        iv2.getLayoutParams().height = size;
        iv3.getLayoutParams().width = size;
        iv3.getLayoutParams().height = size;
        iv4.getLayoutParams().width = size;
        iv4.getLayoutParams().height = size;
        return this;
    }

    public TabBar setMarkPointSize(int pointSizePx) {
        mv0.setPointSize(pointSizePx);
        mv1.setPointSize(pointSizePx);
        mv2.setPointSize(pointSizePx);
        mv3.setPointSize(pointSizePx);
        mv4.setPointSize(pointSizePx);
        return this;
    }

    public TabBar setMarkTextSize(int textSizePx) {
        mv0.setTextSizeInPx(textSizePx);
        mv1.setTextSizeInPx(textSizePx);
        mv2.setTextSizeInPx(textSizePx);
        mv3.setTextSizeInPx(textSizePx);
        mv4.setTextSizeInPx(textSizePx);
        return this;
    }

    public TabBar setMarkTextColor(int color) {
        mv0.setTextColor(color);
        mv1.setTextColor(color);
        mv2.setTextColor(color);
        mv3.setTextColor(color);
        mv4.setTextColor(color);
        return this;
    }

    public TabBar setMarkTextColorRes(int colorRes) {
        mv0.setTextColorRes(colorRes);
        mv1.setTextColorRes(colorRes);
        mv2.setTextColorRes(colorRes);
        mv3.setTextColorRes(colorRes);
        mv4.setTextColorRes(colorRes);
        return this;
    }

    public TabBar setMarkBgColor(int color) {
        mv0.setBgColor(color);
        mv1.setBgColor(color);
        mv2.setBgColor(color);
        mv3.setBgColor(color);
        mv4.setBgColor(color);
        return this;
    }

    public TabBar setMarkBgColorRes(int colorRes) {
        mv0.setBgColorRes(colorRes);
        mv1.setBgColorRes(colorRes);
        mv2.setBgColorRes(colorRes);
        mv3.setBgColorRes(colorRes);
        mv4.setBgColorRes(colorRes);
        return this;
    }

    public TabBar showMarkViewAt(int position) {
        switch (position) {
            case 0:
                mv0.setVisibility(VISIBLE);
                break;
            case 1:
                mv1.setVisibility(VISIBLE);
                break;
            case 2:
                mv2.setVisibility(VISIBLE);
                break;
            case 3:
                mv3.setVisibility(VISIBLE);
                break;
            case 4:
                mv4.setVisibility(VISIBLE);
                break;
        }
        return this;
    }

    public TabBar setMarkShapeAtPosition(int position, MarkView.MarkShape bgShape) {
        switch (position) {
            case 0:
                mv0.setBgShape(bgShape);
                break;
            case 1:
                mv1.setBgShape(bgShape);
                break;
            case 2:
                mv2.setBgShape(bgShape);
                break;
            case 3:
                mv3.setBgShape(bgShape);
                break;
            case 4:
                mv4.setBgShape(bgShape);
                break;
        }
        return this;
    }

    public TabBar setMarkNumberAtPosition(int position, int markNumber) {
        switch (position) {
            case 0:
                mv0.setMarkNumber(markNumber);
                break;
            case 1:
                mv1.setMarkNumber(markNumber);
                break;
            case 2:
                mv2.setMarkNumber(markNumber);
                break;
            case 3:
                mv3.setMarkNumber(markNumber);
                break;
            case 4:
                mv4.setMarkNumber(markNumber);
                break;
        }
        return this;
    }

    public TabBar setMaxMarkNumberAtPosition(int position, int maxMarkNumber) {
        switch (position) {
            case 0:
                mv0.setMaxMarkNumber(maxMarkNumber);
                break;
            case 1:
                mv1.setMaxMarkNumber(maxMarkNumber);
                break;
            case 2:
                mv2.setMaxMarkNumber(maxMarkNumber);
                break;
            case 3:
                mv3.setMaxMarkNumber(maxMarkNumber);
                break;
            case 4:
                mv4.setMaxMarkNumber(maxMarkNumber);
                break;
        }
        return this;
    }

    public TabBar hideMarkViewAt(int position) {
        switch (position) {
            case 0:
                mv0.setVisibility(GONE);
                break;
            case 1:
                mv1.setVisibility(GONE);
                break;
            case 2:
                mv2.setVisibility(GONE);
                break;
            case 3:
                mv3.setVisibility(GONE);
                break;
            case 4:
                mv4.setVisibility(GONE);
                break;
        }
        return this;
    }

    public TabBar setPressIconRes(int... icons) {
        this.pressIcons = icons;
        return this;
    }

    public TabBar setTextSize(int textSizePx) {
        this.textSize = AutoUtils.getPercentWidthSize(textSizePx);
        return this;
    }

    public TabBar setNormalIconRes(int... icons) {
        this.normalIcons = icons;
        return this;
    }

    public void update() {
        mv0.update();
        mv1.update();
        mv2.update();
        mv3.update();
        mv4.update();
        tv0.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        tv1.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        tv2.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        tv3.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        tv4.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        if (showImg) {
            if (pressIcons != null && pressIcons.length > 0) {
                for (int i = 0; i < pressIcons.length; i++) {
                    if (position == i) {
                        switch (i) {
                            case 0:
                                iv0.setImageResource(pressIcons[0]);
                                tv0.setTextColor(textColorPress);
                                if (showUnderLine) {
                                    line0.setVisibility(VISIBLE);
                                    line0.setBackgroundColor(textColorPress);

                                    setVisible2(line1, false);
                                    setVisible2(line2, false);
                                    setVisible2(line3, false);
                                    setVisible2(line4, false);
                                }
                                break;
                            case 1:
                                iv1.setImageResource(pressIcons[1]);
                                tv1.setTextColor(textColorPress);
                                if (showUnderLine) {
                                    line1.setVisibility(VISIBLE);
                                    line1.setBackgroundColor(textColorPress);

                                    setVisible2(line0, false);
                                    setVisible2(line2, false);
                                    setVisible2(line3, false);
                                    setVisible2(line4, false);
                                }
                                break;
                            case 2:
                                iv2.setImageResource(pressIcons[2]);
                                tv2.setTextColor(textColorPress);
                                if (showUnderLine) {
                                    line2.setVisibility(VISIBLE);
                                    line2.setBackgroundColor(textColorPress);

                                    setVisible2(line0, false);
                                    setVisible2(line1, false);
                                    setVisible2(line3, false);
                                    setVisible2(line4, false);
                                }
                                break;
                            case 3:
                                iv3.setImageResource(pressIcons[3]);
                                tv3.setTextColor(textColorPress);
                                if (showUnderLine) {
                                    line3.setVisibility(VISIBLE);
                                    line3.setBackgroundColor(textColorPress);

                                    setVisible2(line0, false);
                                    setVisible2(line1, false);
                                    setVisible2(line2, false);
                                    setVisible2(line4, false);
                                }
                                break;
                            case 4:
                                iv4.setImageResource(pressIcons[4]);
                                tv4.setTextColor(textColorPress);
                                if (showUnderLine) {
                                    line4.setVisibility(VISIBLE);
                                    line4.setBackgroundColor(textColorPress);

                                    setVisible2(line0, false);
                                    setVisible2(line1, false);
                                    setVisible2(line2, false);
                                    setVisible2(line3, false);
                                }
                                break;
                        }
                        break;
                    }
                }
            }
            if (normalIcons != null && normalIcons.length > 0) {
                for (int i = 0; i < normalIcons.length; i++) {
                    if (position != i) {
                        switch (i) {
                            case 0:
                                iv0.setImageResource(normalIcons[0]);
                                tv0.setTextColor(textColorNormal);
                                break;
                            case 1:
                                iv1.setImageResource(normalIcons[1]);
                                tv1.setTextColor(textColorNormal);
                                break;
                            case 2:
                                iv2.setImageResource(normalIcons[2]);
                                tv2.setTextColor(textColorNormal);
                                break;
                            case 3:
                                iv3.setImageResource(normalIcons[3]);
                                tv3.setTextColor(textColorNormal);
                                break;
                            case 4:
                                iv4.setImageResource(normalIcons[4]);
                                tv4.setTextColor(textColorNormal);
                                break;
                        }
                    }
                }
            }
        } else if (showText) {
            tv0.setTextColor(position == 0 ? textColorPress : textColorNormal);
            tv1.setTextColor(position == 1 ? textColorPress : textColorNormal);
            tv2.setTextColor(position == 2 ? textColorPress : textColorNormal);
            tv3.setTextColor(position == 3 ? textColorPress : textColorNormal);
            tv4.setTextColor(position == 4 ? textColorPress : textColorNormal);

            if (showUnderLine) {
                line0.setBackgroundColor(position == 0 ? textColorPress : textColorNormal);
                line1.setBackgroundColor(position == 1 ? textColorPress : textColorNormal);
                line2.setBackgroundColor(position == 2 ? textColorPress : textColorNormal);
                line3.setBackgroundColor(position == 3 ? textColorPress : textColorNormal);
                line4.setBackgroundColor(position == 4 ? textColorPress : textColorNormal);

                setVisible2(line0, position == 0);
                setVisible2(line1, position == 1);
                setVisible2(line2, position == 2);
                setVisible2(line3, position == 3);
                setVisible2(line4, position == 4);
            }
        }

    }

    private void setVisible(View view, boolean visible) {
        if (view != null) {
            view.setVisibility(visible ? VISIBLE : GONE);
        }
    }

    private void setVisible2(View view, boolean visible) {
        if (view != null) {
            view.setVisibility(visible ? VISIBLE : INVISIBLE);
        }
    }

    private void initEvent() {
        ll0.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelection(0);
            }
        });
        ll1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelection(1);
            }
        });
        ll2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelection(2);
            }
        });
        ll3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelection(3);
            }
        });
        ll4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setSelection(4);
            }
        });
    }

    public void setSelection(int i) {
        if (onTabBarClick != null) {
            switch (i) {
                case 0:
                    onTabBarClick.onTabClick(iv0, tv0, i, position != i);
                    break;
                case 1:
                    onTabBarClick.onTabClick(iv1, tv1, i, position != i);
                    break;
                case 2:
                    onTabBarClick.onTabClick(iv2, tv2, i, position != i);
                    break;
                case 3:
                    onTabBarClick.onTabClick(iv3, tv3, i, position != i);
                    break;
                case 4:
                    onTabBarClick.onTabClick(iv4, tv4, i, position != i);
                    break;
            }
        }
        position = i;
        update();
    }

    public void setOnTabBarClickListener(OnTabBarClick onTabBarClick) {
        this.onTabBarClick = onTabBarClick;
    }
}
