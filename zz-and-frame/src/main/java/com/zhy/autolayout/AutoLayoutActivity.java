package com.zhy.autolayout;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

;

/**
 * Created by zhy on 15/11/19.
 */
public class AutoLayoutActivity extends AppCompatActivity {
    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;
        switch (name) {
            case LAYOUT_FRAMELAYOUT:
                view = new AutoFrameLayout(context, attrs);
                break;
            case LAYOUT_LINEARLAYOUT:
                view = new AutoLinearLayout(context, attrs);
                break;
            case LAYOUT_RELATIVELAYOUT:
                view = new AutoRelativeLayout(context, attrs);
                break;
        }

        if (view != null) return view;

        return super.onCreateView(name, context, attrs);
    }


}
