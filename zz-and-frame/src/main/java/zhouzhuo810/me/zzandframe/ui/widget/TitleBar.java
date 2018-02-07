package zhouzhuo810.me.zzandframe.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import zhouzhuo810.me.zzandframe.R;

/**
 * TitleBar
 * Created by zhouzhuo810 on 2017/7/25.
 */
public class TitleBar extends RelativeLayout {

    private ImageView ivLeft;
    private TextView tvLeft;
    private LinearLayout llLeft;
    private TextView tvTitle;
    private ImageView ivRight;
    private TextView tvRight;
    private LinearLayout llRight;

    private OnTitleClick titleClick;
    private MarkView mvLeft;
    private RelativeLayout rlRight;
    private MarkView mvRight;
    private RelativeLayout rlLeft;

    public interface OnTitleClick {
        void onLeftClick(ImageView ivLeft, MarkView mv, TextView tvLeft);

        void onTitleClick(TextView tvTitle);

        void onRightClick(ImageView ivRight, MarkView mv, TextView tvRight);
    }

    /**
     * 设置标题、左边、右边按钮点击事件
     *
     * @param titleClick OnTitleClick
     */
    public void setOnTitleClickListener(OnTitleClick titleClick) {
        this.titleClick = titleClick;
    }

    public TitleBar(Context context) {
        super(context);
        init(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View root = LayoutInflater.from(context).inflate(R.layout.title_layout, this, false);
        ivLeft = (ImageView) root.findViewById(R.id.iv_back);
        tvLeft = (TextView) root.findViewById(R.id.tv_back);
        llLeft = (LinearLayout) root.findViewById(R.id.ll_back);
        tvTitle = (TextView) root.findViewById(R.id.tv_title);
        ivRight = (ImageView) root.findViewById(R.id.iv_right);
        rlRight = (RelativeLayout) root.findViewById(R.id.rl_right);
        rlLeft = (RelativeLayout) root.findViewById(R.id.rl_left);
        tvRight = (TextView) root.findViewById(R.id.tv_right);
        mvLeft = (MarkView) root.findViewById(R.id.mv_left);
        mvRight = (MarkView) root.findViewById(R.id.mv_right);
        llRight = (LinearLayout) root.findViewById(R.id.ll_right);
        initEvent();
        addView(root);
        initAttrs(context, attrs);
        setGravity(Gravity.CENTER_VERTICAL);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
            /*visibility*/
            boolean showLeftLayout = t.getBoolean(R.styleable.TitleBar_ttb_showLeftLayout, true);
            boolean showRightLayout = t.getBoolean(R.styleable.TitleBar_ttb_showRightLayout, false);
            boolean showLeftImg = t.getBoolean(R.styleable.TitleBar_ttb_showLeftImg, true);
            boolean showLeftText = t.getBoolean(R.styleable.TitleBar_ttb_showLeftText, false);
            boolean showLeftMarkView = t.getBoolean(R.styleable.TitleBar_ttb_showLeftMarkView, false);
            boolean showRightMarkView = t.getBoolean(R.styleable.TitleBar_ttb_showRightMarkView, false);
            boolean showTitle = t.getBoolean(R.styleable.TitleBar_ttb_showTitle, true);
            boolean showRightImg = t.getBoolean(R.styleable.TitleBar_ttb_showRightImg, false);
            boolean showRightText = t.getBoolean(R.styleable.TitleBar_ttb_showRightText, true);
            setVisible(llLeft, showLeftLayout);
            setVisible(llRight, showRightLayout);
            setVisible(ivLeft, showLeftImg);
            setVisible(rlLeft, showLeftImg);
            setVisible(tvLeft, showLeftText);
            setVisible(tvTitle, showTitle);
            setVisible(ivRight, showRightImg);
            setVisible(rlRight, showRightImg);
            setVisible(tvRight, showRightText);
            setVisible(mvLeft, showLeftMarkView);
            setVisible(mvRight, showRightMarkView);
            /*img*/
            int leftDrawableId = t.getResourceId(R.styleable.TitleBar_ttb_leftImg, -1);
            int rightDrawableId = t.getResourceId(R.styleable.TitleBar_ttb_rightImg, -1);
            if (leftDrawableId != -1) {
                ivLeft.setImageResource(leftDrawableId);
            }
            if (rightDrawableId != -1) {
                ivRight.setImageResource(rightDrawableId);
            }
            /*textSize*/
            int textSizeTitle = t.getDimensionPixelSize(R.styleable.TitleBar_ttb_textSizeTitle, 50);
            textSizeTitle = AutoUtils.getPercentWidthSize(textSizeTitle);
            int textSizeTwoSide = t.getDimensionPixelSize(R.styleable.TitleBar_ttb_textSizeTwoSide, 40);
            textSizeTwoSide = AutoUtils.getPercentWidthSize(textSizeTwoSide);
            tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeTitle);
            tvLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeTwoSide);
            tvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeTwoSide);
            /*textColor*/
            int color = t.getColor(R.styleable.TitleBar_ttb_textColorAll, Color.WHITE);
            tvTitle.setTextColor(color);
            tvLeft.setTextColor(color);
            tvRight.setTextColor(color);
            /*text*/
            String leftText = t.getString(R.styleable.TitleBar_ttb_leftText);
            String rightText = t.getString(R.styleable.TitleBar_ttb_rightText);
            String titleText = t.getString(R.styleable.TitleBar_ttb_titleText);
            tvLeft.setText(leftText);
            tvRight.setText(rightText);
            tvTitle.setText(titleText);
            t.recycle();
        } else {
            setVisible(llLeft, true);
            setVisible(llRight, false);
            setVisible(ivLeft, false);
            setVisible(rlLeft, false);
            setVisible(tvLeft, false);
            setVisible(tvTitle, true);
            setVisible(ivRight, false);
            setVisible(rlRight, false);
            setVisible(tvRight, false);
            setVisible(mvLeft, false);
            setVisible(mvRight, false);

            /*textSize*/
            int textSizeTitle = AutoUtils.getPercentWidthSize(50);
            int textSizeTwoSide = AutoUtils.getPercentWidthSize(40);
            tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeTitle);
            tvLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeTwoSide);
            tvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeTwoSide);
            /*textColor*/
            tvTitle.setTextColor(Color.WHITE);
            tvLeft.setTextColor(Color.WHITE);
            tvRight.setTextColor(Color.WHITE);
            /*text*/
            tvLeft.setText("返回");
            tvTitle.setText("标题");
        }
    }

    private void setVisible(View view, boolean visible) {
        if (view != null) {
            view.setVisibility(visible ? VISIBLE : GONE);
        }
    }

    private void initEvent() {

        tvTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (titleClick != null) {
                    titleClick.onTitleClick(tvTitle);
                }
            }
        });

        llLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (titleClick != null) {
                    titleClick.onLeftClick(ivLeft, mvLeft,  tvLeft);
                }
            }
        });

        llRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (titleClick != null) {
                    titleClick.onRightClick(ivRight, mvLeft, tvRight);
                }
            }
        });
    }

    public ImageView getIvLeft() {
        return ivLeft;
    }

    public RelativeLayout getRlLeft() {
        return rlLeft;
    }

    public MarkView getMvLeft() {
        return mvLeft;
    }

    public MarkView getMvRight() {
        return mvRight;
    }

    public TextView getTvLeft() {
        return tvLeft;
    }

    public LinearLayout getLlLeft() {
        return llLeft;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public ImageView getIvRight() {
        return ivRight;
    }

    public TextView getTvRight() {
        return tvRight;
    }

    public LinearLayout getLlRight() {
        return llRight;
    }

    public RelativeLayout getRlRight() {
        return rlRight;
    }
}
