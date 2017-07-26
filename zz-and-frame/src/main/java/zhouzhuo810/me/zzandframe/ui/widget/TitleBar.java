package zhouzhuo810.me.zzandframe.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.media.Image;
import android.util.AttributeSet;
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

    public interface OnTitleClick {
        void onLeftClick(ImageView ivLeft, TextView tvLeft);
        void onTitleClick(TextView tvTitle);
        void onRightClick(ImageView ivRight, TextView tvRight);
    }

    /**
     * 设置标题、左边、右边按钮点击事件
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
        View root = LayoutInflater.from(context).inflate(R.layout.title_layout, null);
        ivLeft = (ImageView) root.findViewById(R.id.iv_back);
        tvLeft = (TextView) root.findViewById(R.id.tv_back);
        llLeft = (LinearLayout) root.findViewById(R.id.ll_back);
        tvTitle = (TextView) root.findViewById(R.id.tv_title);
        ivRight = (ImageView) root.findViewById(R.id.iv_right);
        tvRight = (TextView) root.findViewById(R.id.tv_right);
        llRight = (LinearLayout) root.findViewById(R.id.ll_right);
        initAttrs(context, attrs);
        initEvent();
        addView(root);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
            /*visibility*/
            boolean showLeftLayout = t.getBoolean(R.styleable.TitleBar_showLeftLayout, true);
            boolean showRightLayout = t.getBoolean(R.styleable.TitleBar_showRightLayout, false);
            boolean showLeftImg = t.getBoolean(R.styleable.TitleBar_showLeftImg, true);
            boolean showLeftText = t.getBoolean(R.styleable.TitleBar_showLeftText, true);
            boolean showTitle = t.getBoolean(R.styleable.TitleBar_showTitle, true);
            boolean showRightImg = t.getBoolean(R.styleable.TitleBar_showRightImg, false);
            boolean showRightText = t.getBoolean(R.styleable.TitleBar_showRightText, true);
            setVisible(llLeft, showLeftLayout);
            setVisible(llRight, showRightLayout);
            setVisible(ivLeft, showLeftImg);
            setVisible(tvLeft, showLeftText);
            setVisible(tvTitle, showTitle);
            setVisible(ivRight, showRightImg);
            setVisible(tvRight, showRightText);
            /*img*/
            int leftDrawableId = t.getResourceId(R.styleable.TitleBar_leftImg, -1);
            int rightDrawableId = t.getResourceId(R.styleable.TitleBar_showRightImg, -1);
            if (leftDrawableId != -1) {
                ivLeft.setImageResource(leftDrawableId);
            }
            if (rightDrawableId != -1) {
                ivRight.setImageResource(rightDrawableId);
            }
            /*textSize*/
            int textSizeTitle = t.getDimensionPixelSize(R.styleable.TitleBar_textSizeTitle, 50);
            int textSizeTwoSide = t.getDimensionPixelSize(R.styleable.TitleBar_textSizeTwoSide, 40);
            tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeTitle);
            tvLeft.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeTwoSide);
            tvRight.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizeTwoSide);
            AutoUtils.autoTextSize(tvTitle);
            AutoUtils.autoTextSize(tvLeft);
            AutoUtils.autoTextSize(tvRight);
            /*textColor*/
            int color = t.getColor(R.styleable.TitleBar_textColorAll, Color.WHITE);
            tvTitle.setTextColor(color);
            tvLeft.setTextColor(color);
            tvRight.setTextColor(color);
            /*text*/
            String leftText = t.getString(R.styleable.TitleBar_leftText);
            String rightText = t.getString(R.styleable.TitleBar_rightText);
            String titleText = t.getString(R.styleable.TitleBar_titleText);
            tvLeft.setText(leftText);
            tvRight.setText(rightText);
            tvTitle.setText(titleText);
            t.recycle();
        }
    }

    private void setVisible(View view, boolean visible) {
        if (view != null) {
            view.setVisibility(visible ? VISIBLE : GONE);
        }
    }

    private void initEvent() {
        llLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (titleClick != null) {
                    titleClick.onLeftClick(ivLeft, tvLeft);
                }
            }
        });

        tvTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (titleClick != null) {
                    titleClick.onTitleClick(tvTitle);
                }
            }
        });

        llRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (titleClick != null) {
                    titleClick.onRightClick(ivRight, tvRight);
                }
            }
        });
    }

    public ImageView getIvLeft() {
        return ivLeft;
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


}
