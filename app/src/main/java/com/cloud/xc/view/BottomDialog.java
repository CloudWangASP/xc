package com.cloud.xc.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.WindowManager;

import com.cloud.xc.R;

/**
 * Created by cloud on 2017/11/7.
 */

public class BottomDialog extends Dialog {

    private Context mContext;

    public BottomDialog(@NonNull Context context) {
        super(context, R.style.Dialog_FullScreen);
        mContext = context;
        setContentView(R.layout.alert_dialog);
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(lp);
        getWindow().setWindowAnimations(R.style.AnimBottom);
        setCanceledOnTouchOutside(true);
    }
}
