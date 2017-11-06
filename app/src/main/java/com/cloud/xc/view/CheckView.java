package com.cloud.xc.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;

/**
 * Created by cloud on 2017/10/31.
 */

public class CheckView extends View implements Checkable{

    public CheckView(Context context) {
        super(context);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CheckView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void setChecked(boolean checked) {

    }

    @Override
    public boolean isChecked() {
        return false;
    }

    @Override
    public void toggle() {

    }
}
