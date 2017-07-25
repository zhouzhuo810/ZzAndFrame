package zhouzhuo810.me.zzandframe.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.media.Image;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import zhouzhuo810.me.zzandframe.R;

/**
 * TitleBar
 * Created by admin on 2017/7/25.
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

            t.recycle();
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
