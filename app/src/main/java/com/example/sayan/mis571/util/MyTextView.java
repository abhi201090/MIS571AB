package com.example.sayan.mis571.util;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by lilinshuo on 12/10/16.
 */

public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),"font.ttf");
        setTypeface(tf);
    }
}