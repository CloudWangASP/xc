package com.cloud.xc.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cloud.xc.R;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

/**
 * Created by cloud on 2017/9/28.
 */

public class SegmentView extends LinearLayout {

    private Onsegmentlistenerclicker listener;

    public SegmentView(Context context) {
        super(context);
    }

    public SegmentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("NewApi")
    public SegmentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface Onsegmentlistenerclicker {
        void setOnsegment(View v, int position);
    }

    //初始化
    public void init(int tabNum, ArrayList<String> tabName) {
        //设置文字的颜色
        XmlPullParser xrp = getResources().getXml(R.drawable.text_selected);
        ColorStateList csl = null;
        try {
            csl = ColorStateList.createFromXml(getResources(), xrp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.removeAllViews();
        for (int i = 0; i < tabNum; i++) {
            final TextView textView = new TextView(getContext());
            View line = new TextView(getContext());
            textView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1));
            line.setLayoutParams(new LayoutParams(1, LayoutParams.MATCH_PARENT));
            textView.setTextSize(14);
            textView.setTextColor(csl);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(5, 15, 5, 15);
            textView.setText(tabName.get(i));
            line.setBackgroundColor(getResources().getColor(R.color.xc_red));
            if (0 == i) {
                textView.setBackgroundResource(R.drawable.left);
            } else if (tabNum - 1 == i) {
                textView.setBackgroundResource(R.drawable.right);
            } else {
                textView.setBackgroundResource(R.drawable.middle);
            }
            this.addView(textView);
            if (tabNum - 1 != i) {
                this.addView(line);
            }
            if (0 == i) {
                textView.setSelected(true);
            }
        }
        this.invalidate();
        for (int i = 0; i < getChildCount(); i++) {
            final int finalI = i;
            getChildAt(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (finalI == 0 || finalI % 2 == 0) {
                        if (getChildAt(finalI).isSelected()) {
                            return;
                        }
                        getChildAt(finalI).setSelected(true);
                        for (int j = 0; j < getChildCount(); j++) {
                            if (j != finalI && finalI % 2 == 0) {
                                getChildAt(j).setSelected(false);
                            }
                        }
                        if (listener != null) {
                            listener.setOnsegment(getChildAt(finalI), finalI);
                        }
                    }
                }
            });
        }
    }

    public void setOnsegmentlistenerclicker(Onsegmentlistenerclicker listener) {
        if (listener != null)
            this.listener = listener;
    }
}