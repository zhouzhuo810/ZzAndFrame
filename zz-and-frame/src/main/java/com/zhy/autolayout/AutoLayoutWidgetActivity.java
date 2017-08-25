package com.zhy.autolayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by hupei on 2016/3/7 16:44.
 */
public class AutoLayoutWidgetActivity extends AutoLayoutActivity {

    private static final String RADIO_GROUP = "android.widget.RadioGroup";

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = null;

        if (name.equals(RADIO_GROUP)) {
            view = new AutoRadioGroup(context, attrs);
        }


        if (view != null) return view;
        return super.onCreateView(name, context, attrs);
    }
}