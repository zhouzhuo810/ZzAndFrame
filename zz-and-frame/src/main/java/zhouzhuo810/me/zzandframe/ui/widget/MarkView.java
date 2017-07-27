package zhouzhuo810.me.zzandframe.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * 角标
 * Created by admin on 2017/7/27.
 */
public class MarkView extends View{

    public MarkView(Context context) {
        super(context);
    }

    public MarkView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarkView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @RequiresApi(value = 21)
    public MarkView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    private void drawBg(Canvas canvas) {

    }

    private void drawText(Canvas canvas) {

    }

    private void setMaxNumber(int maxNumber) {

    }

    private void setMarkNumber(int number) {

    }

    private void setTextColor(int color) {

    }

    private void setTextSizeInPx(int pxSize) {

    }

    private void setBgColor(int color) {

    }

    private void setTextColorRes(int colorRes) {

    }

    private void setBgColorRes(int colorRes) {

    }
}
