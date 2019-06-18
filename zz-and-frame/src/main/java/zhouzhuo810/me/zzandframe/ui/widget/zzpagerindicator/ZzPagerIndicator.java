package zhouzhuo810.me.zzandframe.ui.widget.zzpagerindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.autolayout.utils.AutoUtils;

import zhouzhuo810.me.zzandframe.R;
import zhouzhuo810.me.zzandframe.ui.widget.zzpagerindicator.en.IndicatorType;
import zhouzhuo810.me.zzandframe.ui.widget.zzpagerindicator.intef.IIndicatorResProvider;
import zhouzhuo810.me.zzandframe.ui.widget.zzpagerindicator.intef.IPagerIndicator;


/**
 * Created by zz on 2016/8/22.
 */
public class ZzPagerIndicator extends HorizontalScrollView implements IPagerIndicator {
    
    private IndicatorType indicatorType = IndicatorType.RoundPoint;
    
    private ViewPager mViewPager;
    
    private LinearLayout mIndicatorContainer;
    private boolean shouldExpand = false;
    private Paint selectPaint;
    private Paint unSelectPaint;
    private Paint underlinePaint;
    private int colorSelectPoint = 0xff438cff;
    private int colorUnSelectPoint = 0xff000000;
    private int selectPointSize = 100;
    private int unSelectPointSize = 90;
    private int spacing = 8;
    
    private boolean showUnderline = true;
    private int tabTextColorSelect = 0xff438cff;
    private int tabTextColorUnSelect = 0xff000000;
    private int tabTextSizeUnSelect = 30;
    private int tabIconTextMargin = 10;
    private int tabTextSizeSelect = 40;
    private int underlineHeight = 10;
    private int underlinePadding = 0;
    private int underlineColor = 0xff438cff;
    private int tabBgNormalId;
    private int tabBgSelectId;
    private TabOrientation tabTextIconOrientation = TabOrientation.VERTICAL;
    
    private int tabPadding = 24;
    private int zz_tab_icon_size = 80;
    
    private LinearLayout.LayoutParams defaultTabLayoutParams;
    private LinearLayout.LayoutParams expandedTabLayoutParams;
    
    private int currentPosition = 0;
    private float currentPositionOffset = 0f;
    
    private int tabCount;
    private int lastScrollX = 0;
    private boolean isNeedScaleInPx = false;
    
    private boolean horizontalHideIconMode = false;
    
    public static enum TabOrientation {
        VERTICAL, HORIZONTAL
    }
    
    public ZzPagerIndicator(Context context) {
        super(context);
        init(context, null);
    }
    
    public ZzPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        
    }
    
    public ZzPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
        
    }
    
    private void init(Context context, AttributeSet attrs) {
        
        setHorizontalScrollBarEnabled(false);
        
        setFillViewport(true);
        setWillNotDraw(false);
        
        //init attrs
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ZzPagerIndicator);
            isNeedScaleInPx = a.getBoolean(R.styleable.ZzPagerIndicator_zz_is_need_scale_in_px, false);
            
            shouldExpand = a.getBoolean(R.styleable.ZzPagerIndicator_zz_should_tab_expand, false);
            int indicatorInt = a.getInt(R.styleable.ZzPagerIndicator_zz_indicator_type, 0);
            int tabOriInt = a.getInt(R.styleable.ZzPagerIndicator_zz_tab_orientation, 0);
            colorSelectPoint = a.getColor(R.styleable.ZzPagerIndicator_zz_select_point_color, 0xff438cff);
            colorUnSelectPoint = a.getColor(R.styleable.ZzPagerIndicator_zz_unselect_point_color, 0xff000000);
            selectPointSize = a.getDimensionPixelSize(R.styleable.ZzPagerIndicator_zz_select_point_size, 30);
            unSelectPointSize = a.getDimensionPixelSize(R.styleable.ZzPagerIndicator_zz_unselect_point_size, 30);
            spacing = a.getDimensionPixelSize(R.styleable.ZzPagerIndicator_zz_point_spacing, 8);
            tabBgNormalId = a.getResourceId(R.styleable.ZzPagerIndicator_zz_normal_tab_bg, -1);
            tabBgSelectId = a.getResourceId(R.styleable.ZzPagerIndicator_zz_select_tab_bg, -1);
            
            tabTextColorSelect = a.getColor(R.styleable.ZzPagerIndicator_zz_select_tab_text_color, 0xff438cff);
            tabTextColorUnSelect = a.getColor(R.styleable.ZzPagerIndicator_zz_unselect_tab_text_color, 0xff000000);
            tabTextSizeSelect = a.getDimensionPixelSize(R.styleable.ZzPagerIndicator_zz_select_tab_text_size, 40);
            tabTextSizeUnSelect = a.getDimensionPixelSize(R.styleable.ZzPagerIndicator_zz_unselect_tab_text_size, 40);
            tabIconTextMargin = a.getDimensionPixelSize(R.styleable.ZzPagerIndicator_zz_tab_icon_text_margin, 10);
            showUnderline = a.getBoolean(R.styleable.ZzPagerIndicator_zz_show_underline, true);
            underlineHeight = a.getDimensionPixelSize(R.styleable.ZzPagerIndicator_zz_underline_height, 10);
            underlinePadding = a.getDimensionPixelSize(R.styleable.ZzPagerIndicator_zz_underline_padding, 20);
            tabPadding = a.getDimensionPixelSize(R.styleable.ZzPagerIndicator_zz_tab_padding, 24);
            zz_tab_icon_size = a.getDimensionPixelSize(R.styleable.ZzPagerIndicator_zz_tab_icon_size, 80);
            underlineColor = a.getColor(R.styleable.ZzPagerIndicator_zz_underline_color, 0xff438cff);
            horizontalHideIconMode = a.getBoolean(R.styleable.ZzPagerIndicator_zz_tab_is_horizontal_hide_icon, false);
            
            if (isNeedScaleInPx) {
                selectPointSize = AutoUtils.getPercentWidthSize(selectPointSize);
                unSelectPointSize = AutoUtils.getPercentWidthSize(unSelectPointSize);
                spacing = AutoUtils.getPercentWidthSize(spacing);
                tabTextSizeSelect = AutoUtils.getPercentWidthSize(tabTextSizeSelect);
                tabIconTextMargin = AutoUtils.getPercentWidthSize(tabIconTextMargin);
                tabTextSizeUnSelect = AutoUtils.getPercentWidthSize(tabTextSizeUnSelect);
                underlineHeight = AutoUtils.getPercentWidthSize(underlineHeight);
                underlinePadding = AutoUtils.getPercentWidthSize(underlinePadding);
                tabPadding = AutoUtils.getPercentWidthSize(tabPadding);
                zz_tab_icon_size = AutoUtils.getPercentWidthSize(zz_tab_icon_size);
            }
            
            switch (indicatorInt) {
                case 0:
                    indicatorType = IndicatorType.RoundPoint;
                    break;
                case 1:
                    indicatorType = IndicatorType.TabWithText;
                    break;
                case 2:
                    indicatorType = IndicatorType.TabWithIcon;
                    break;
                case 3:
                    indicatorType = IndicatorType.TabWithIconAndText;
                    break;
            }
            switch (tabOriInt) {
                case 0:
                    tabTextIconOrientation = TabOrientation.VERTICAL;
                    break;
                case 1:
                    tabTextIconOrientation = TabOrientation.HORIZONTAL;
                    break;
            }
            a.recycle();
        }
        if (indicatorType == IndicatorType.RoundPoint) {
            setMinimumHeight(selectPointSize > unSelectPointSize ? selectPointSize * 2 : unSelectPointSize * 2);
        }
        //initial paints
        initPaints();
        //initial linearLayout
        initContainer(context);
        //initial child layout params
        initParams();
        
    }
    
    private void initPaints() {
        unSelectPaint = new Paint();
        unSelectPaint.setAntiAlias(true);
        unSelectPaint.setColor(colorUnSelectPoint);
        selectPaint = new Paint();
        selectPaint.setAntiAlias(true);
        selectPaint.setStyle(Paint.Style.FILL);
        selectPaint.setColor(colorSelectPoint);
        underlinePaint = new Paint();
        underlinePaint.setColor(underlineColor);
        underlinePaint.setStyle(Paint.Style.FILL);
        underlinePaint.setAntiAlias(true);
    }
    
    private void initContainer(Context context) {
        mIndicatorContainer = new LinearLayout(context);
        mIndicatorContainer.setOrientation(LinearLayout.HORIZONTAL);
        mIndicatorContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mIndicatorContainer.setGravity(Gravity.CENTER_VERTICAL);
        addView(mIndicatorContainer);
    }
    
    private void initParams() {
        defaultTabLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        expandedTabLayoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (indicatorType) {
            case RoundPoint:
                drawPoints(canvas);
                break;
            case TabWithIcon:
            case TabWithText:
            case TabWithIconAndText:
                if (showUnderline)
                    drawUnderline(canvas);
                break;
        }
    }
    
    private void drawUnderline(Canvas canvas) {
        if (mViewPager != null) {
            View currentTab = getItem(currentPosition);
            if (currentTab != null) {
                float lineLeft = currentTab.getLeft() + underlinePadding;
                float lineRight = currentTab.getRight() - underlinePadding;
                
                // if there is an offset, start interpolating left and right coordinates between current and next tab
                if (currentPositionOffset > 0f && currentPosition < tabCount - 1) {
                    
                    View nextTab = getItem(currentPosition + 1);
                    final float nextTabLeft = nextTab.getLeft() + underlinePadding;
                    final float nextTabRight = nextTab.getRight() - underlinePadding;
                    
                    lineLeft = (currentPositionOffset * nextTabLeft + (1f - currentPositionOffset) * lineLeft);
                    lineRight = (currentPositionOffset * nextTabRight + (1f - currentPositionOffset) * lineRight);
                }
                canvas.drawRect(lineLeft, getHeight() - underlineHeight, lineRight, getHeight(), underlinePaint);
            }
        }
    }
    
    private void drawPoints(Canvas canvas) {
        if (mViewPager != null && mViewPager.getAdapter() != null) {
            int count = mViewPager.getAdapter().getCount();
            int index = mViewPager.getCurrentItem();
            int uLeft = (getWidth() - unSelectPointSize * count - spacing * (count - 1)) / 2;
            int y = getHeight() / 2;
            int sR = selectPointSize / 2;
            int uR = unSelectPointSize / 2;
            for (int i = 0; i < count; i++) {
                int uX = uLeft + 2 * i * uR + uR + i * spacing;
                canvas.drawCircle(uX, y, uR, unSelectPaint);
            }
            float uX = uLeft + (2 * index + 1) * uR + index * spacing;
            canvas.drawCircle(uX, y, sR, selectPaint);
        }
    }
    
    @Override
    public void setCurrentItem(int position, boolean animate) {
        if (mViewPager != null) {
            mViewPager.setCurrentItem(position, animate);
        }
    }
    
    /**
     * bind indicator to your viewpager.
     *
     * @param viewPager your viewpager
     */
    @Override
    public void setViewPager(ViewPager viewPager) {
        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        tabCount = viewPager.getAdapter().getCount();
        this.mViewPager = viewPager;
        addPageChangeListener();
        switch (indicatorType) {
            case RoundPoint:
                setMinimumWidth(selectPointSize > unSelectPointSize ? selectPointSize * tabCount + spacing * (tabCount - 1) : unSelectPointSize * tabCount + spacing * (tabCount - 1));
                break;
            case TabWithText:
                setUpText();
                selectText(0);
                break;
            case TabWithIcon:
                setUpIcons();
                selectIcon(0);
                break;
            case TabWithIconAndText:
                setUpIconsAndText();
                selectIconAndText(0);
                break;
        }
    }
    
    
    private void setUpIconsAndText() {
        mIndicatorContainer.removeAllViews();
        LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(zz_tab_icon_size, zz_tab_icon_size);
        LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (tabTextIconOrientation == TabOrientation.VERTICAL) {
            ivParams.topMargin = 0;
            tvParams.leftMargin = 0;
            tvParams.topMargin = tabIconTextMargin;
        } else {
            ivParams.topMargin = 0;
            tvParams.topMargin = 0;
            tvParams.leftMargin = tabIconTextMargin;
        }
        
        if (mViewPager == null) {
            return;
        }
        if (mViewPager.getAdapter() != null) {
            if (!(mViewPager.getAdapter() instanceof IIndicatorResProvider)) {
                throw new UnsupportedOperationException("Please implements IIndicatorResProvider<T> for your adapter");
            }
            for (int i = 0; i < mViewPager.getAdapter().getCount(); i++) {
                ImageView iv = new ImageView(getContext());
                int icon = ((IIndicatorResProvider) mViewPager.getAdapter()).getUnselectedIcon(i);
                iv.setImageResource(icon);
                
                TextView tv = new TextView(getContext());
                tv.setGravity(Gravity.CENTER);
                tv.setSingleLine();
                tv.setTextColor(tabTextColorUnSelect);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSizeUnSelect);
                tv.setText(mViewPager.getAdapter().getPageTitle(i));
                
                LinearLayout ll = new LinearLayout(getContext());
                ll.setGravity(Gravity.CENTER);
                if (tabTextIconOrientation == TabOrientation.VERTICAL) {
                    ll.setOrientation(LinearLayout.VERTICAL);
                } else {
                    ll.setOrientation(LinearLayout.HORIZONTAL);
                }
                ll.addView(iv, 0, ivParams);
                ll.addView(tv, 1, tvParams);
                ll.setPadding(tabPadding, 0, tabPadding, 0);
                final int finalI = i;
                ll.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setCurrentItem(finalI, true);
                    }
                });
                mIndicatorContainer.addView(ll, i, shouldExpand ? expandedTabLayoutParams : defaultTabLayoutParams);
            }
        }
    }
    
    private void setUpIcons() {
        mIndicatorContainer.removeAllViews();
        if (mViewPager == null) {
            return;
        }
        if (mViewPager.getAdapter() != null) {
            if (!(mViewPager.getAdapter() instanceof IIndicatorResProvider)) {
                throw new UnsupportedOperationException("Please implements IIndicatorResProvider<T> for your adapter");
            }
            LinearLayout.LayoutParams ivParams = new LinearLayout.LayoutParams(zz_tab_icon_size, zz_tab_icon_size);
            for (int i = 0; i < mViewPager.getAdapter().getCount(); i++) {
                
                ImageView iv = new ImageView(getContext());
                int icon = ((IIndicatorResProvider) mViewPager.getAdapter()).getUnselectedIcon(i);
                iv.setImageResource(icon);
                
                LinearLayout ll = new LinearLayout(getContext());
                ll.setGravity(Gravity.CENTER);
                ll.addView(iv, 0, ivParams);
                ll.setPadding(tabPadding, 0, tabPadding, 0);
                final int finalI = i;
                ll.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setCurrentItem(finalI, true);
                    }
                });
                mIndicatorContainer.addView(ll, i, shouldExpand ? expandedTabLayoutParams : defaultTabLayoutParams);
            }
        }
        
    }
    
    private void setUpText() {
        mIndicatorContainer.removeAllViews();
        if (mViewPager == null) {
            return;
        }
        if (mViewPager.getAdapter() != null) {
            for (int i = 0; i < mViewPager.getAdapter().getCount(); i++) {
                TextView tv = new TextView(getContext());
                tv.setFocusable(true);
                tv.setClickable(true);
                tv.setSingleLine();
                tv.setGravity(Gravity.CENTER);
                tv.setText(mViewPager.getAdapter().getPageTitle(i));
                final int finalI = i;
                tv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setCurrentItem(finalI, true);
                    }
                });
                tv.setPadding(tabPadding, 0, tabPadding, 0);
                
                mIndicatorContainer.addView(tv, i, shouldExpand ? expandedTabLayoutParams : defaultTabLayoutParams);
            }
        }
    }
    
    private void addPageChangeListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currentPosition = position;
                currentPositionOffset = positionOffset;
                
                switch (indicatorType) {
                    case TabWithIcon:
                    case TabWithText:
                    case TabWithIconAndText:
                        scrollToChild(currentPosition, positionOffset);
                        break;
                }
                
                invalidate();
            }
            
            @Override
            public void onPageSelected(int position) {
                switch (indicatorType) {
                    case TabWithIcon:
                        selectIcon(position);
                        break;
                    case TabWithText:
                        selectText(position);
                        break;
                    case TabWithIconAndText:
                        selectIconAndText(position);
                        break;
                }
            }
            
            @Override
            public void onPageScrollStateChanged(int state) {
                switch (indicatorType) {
                    case TabWithIcon:
                    case TabWithText:
                    case TabWithIconAndText:
                        if (state == ViewPager.SCROLL_STATE_IDLE) {
                            //                            scrollToChild(mViewPager.getCurrentItem(), 0);
                        }
                        break;
                }
            }
        });
    }
    
    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SaveState saveState = new SaveState(superState);
        saveState.position = currentPosition;
        return saveState;
    }
    
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SaveState savedState = (SaveState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        currentPosition = savedState.position;
        switch (indicatorType) {
            case TabWithIcon:
                selectIcon(currentPosition);
                break;
            case TabWithText:
                selectText(currentPosition);
                break;
            case TabWithIconAndText:
                selectIconAndText(currentPosition);
                break;
        }
    }
    
    static class SaveState extends BaseSavedState {
        int position;
        
        @Override
        public int describeContents() {
            return 0;
        }
        
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(this.position);
        }
        
        public SaveState(Parcelable superState) {
            super(superState);
        }
        
        protected SaveState(Parcel in) {
            super(in);
            this.position = in.readInt();
        }
        
        public static final Creator<SaveState> CREATOR = new Creator<SaveState>() {
            @Override
            public SaveState createFromParcel(Parcel source) {
                return new SaveState(source);
            }
            
            @Override
            public SaveState[] newArray(int size) {
                return new SaveState[size];
            }
        };
    }
    
    private void scrollToChild(int position, float offset) {
        
        if (tabCount == 0) {
            return;
        }
        
        View selectedChild = getItem(position);
        if (selectedChild != null) {
            int selectedWidth = selectedChild.getWidth();
            View nextChild = position + 1 < mIndicatorContainer.getChildCount() ? mIndicatorContainer.getChildAt(position + 1) : null;
            int nextWidth = nextChild != null ? nextChild.getWidth() : 0;
            int scrollOffset = (int) ((float) (selectedWidth + nextWidth) * 0.5F * offset);
            int newScrollX = selectedChild.getLeft() + selectedWidth / 2 - this.getWidth() / 2 + scrollOffset;
            
            if (newScrollX != lastScrollX) {
                lastScrollX = newScrollX;
                scrollTo(newScrollX, 0);
            }
        }
        
    }
    
    
    private void selectIcon(int position) {
        if (mViewPager == null) {
            return;
        }
        if (mViewPager.getAdapter() != null) {
            for (int i = 0; i < mViewPager.getAdapter().getCount(); i++) {
                LinearLayout ll = (LinearLayout) getItem(i);
                ImageView iv = (ImageView) ll.getChildAt(0);
                if (i == position) {
                    if (tabBgSelectId != -1) {
                        ll.setBackgroundResource(tabBgSelectId);
                    }
                    int icon = ((IIndicatorResProvider) mViewPager.getAdapter()).getSelectedIcon(i);
                    iv.setImageResource(icon);
                } else {
                    if (tabBgNormalId != -1) {
                        ll.setBackgroundResource(tabBgNormalId);
                    }
                    int icon = ((IIndicatorResProvider) mViewPager.getAdapter()).getUnselectedIcon(i);
                    iv.setImageResource(icon);
                }
            }
        }
    }
    
    
    private void selectText(int position) {
        if (mViewPager == null) {
            return;
        }
        if (mViewPager.getAdapter() != null) {
            for (int i = 0; i < mViewPager.getAdapter().getCount(); i++) {
                TextView tv = (TextView) getItem(i);
                if (i == position) {
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSizeSelect);
                    tv.setTextColor(tabTextColorSelect);
                    if (tabBgSelectId != -1) {
                        tv.setBackgroundResource(tabBgSelectId);
                    }
                } else {
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSizeUnSelect);
                    tv.setTextColor(tabTextColorUnSelect);
                    if (tabBgNormalId != -1) {
                        tv.setBackgroundResource(tabBgNormalId);
                    }
                }
            }
        }
    }
    
    public void setTabTextIconOrientation(TabOrientation orientation) {
        if (indicatorType != IndicatorType.TabWithIconAndText) {
            return;
        }
        this.tabTextIconOrientation = orientation;
        switch (orientation) {
            case VERTICAL:
                if (mViewPager.getAdapter() != null) {
                    for (int i = 0; i < mViewPager.getAdapter().getCount(); i++) {
                        LinearLayout ll = (LinearLayout) getItem(i);
                        ll.setOrientation(LinearLayout.VERTICAL);
                        ImageView iv = (ImageView) ll.getChildAt(0);
                        iv.setVisibility(VISIBLE);
                        TextView tv = (TextView) ll.getChildAt(1);
                        LinearLayout.LayoutParams ivP = (LinearLayout.LayoutParams) iv.getLayoutParams();
                        LinearLayout.LayoutParams tvP = (LinearLayout.LayoutParams) tv.getLayoutParams();
                        ivP.topMargin = 0;
                        tvP.leftMargin = 0;
                        tvP.topMargin = tabIconTextMargin;
                        iv.setLayoutParams(ivP);
                        tv.setLayoutParams(tvP);
                    }
                }
                break;
            case HORIZONTAL:
                if (mViewPager.getAdapter() != null) {
                    for (int i = 0; i < mViewPager.getAdapter().getCount(); i++) {
                        LinearLayout ll = (LinearLayout) getItem(i);
                        ll.setOrientation(LinearLayout.HORIZONTAL);
                        ImageView iv = (ImageView) ll.getChildAt(0);
                        TextView tv = (TextView) ll.getChildAt(1);
                        LinearLayout.LayoutParams ivP = (LinearLayout.LayoutParams) iv.getLayoutParams();
                        LinearLayout.LayoutParams tvP = (LinearLayout.LayoutParams) tv.getLayoutParams();
                        ivP.topMargin = 0;
                        tvP.topMargin = 0;
                        tvP.bottomMargin = 0;
                        tvP.leftMargin = tabIconTextMargin;
                        iv.setLayoutParams(ivP);
                        tv.setLayoutParams(tvP);
                    }
                }
                break;
            
        }
    }
    
    public TabOrientation getTabTextIconOrientation() {
        return tabTextIconOrientation;
    }
    
    private void selectIconAndText(int position) {
        if (mViewPager == null) {
            return;
        }
        if (mViewPager.getAdapter() != null) {
            for (int i = 0; i < mViewPager.getAdapter().getCount(); i++) {
                LinearLayout ll = (LinearLayout) getItem(i);
                ImageView iv = (ImageView) ll.getChildAt(0);
                TextView tv = (TextView) ll.getChildAt(1);
                if (i == position) {
                    iv.setVisibility(VISIBLE);
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSizeSelect);
                    tv.setTextColor(tabTextColorSelect);
                    if (tabBgSelectId != -1) {
                        ll.setBackgroundResource(tabBgSelectId);
                    }
                    int icon = ((IIndicatorResProvider) mViewPager.getAdapter()).getSelectedIcon(i);
                    iv.setImageResource(icon);
                } else {
                    if (horizontalHideIconMode && tabTextIconOrientation == TabOrientation.HORIZONTAL) {
                        iv.setVisibility(GONE);
                    } else {
                        iv.setVisibility(VISIBLE);
                    }
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabTextSizeUnSelect);
                    tv.setTextColor(tabTextColorUnSelect);
                    if (tabBgNormalId != -1) {
                        ll.setBackgroundResource(tabBgNormalId);
                    }
                    int icon = ((IIndicatorResProvider) mViewPager.getAdapter()).getUnselectedIcon(i);
                    iv.setImageResource(icon);
                }
            }
        }
    }
    
    private View getItem(int position) {
        return mIndicatorContainer.getChildAt(position);
    }
}
