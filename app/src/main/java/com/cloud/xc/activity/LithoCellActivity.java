package com.cloud.xc.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.cloud.xc.R;
import com.cloud.xc.spec.MyCell;
import com.facebook.litho.Component;
import com.facebook.litho.LithoView;

/**
 * Created by cloud on 2017/11/20.
 */

public class LithoCellActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(20, 0, 20, 0);

        ScrollView scrollView = new ScrollView(this);
        LinearLayout linearLayout = new LinearLayout(this);

        scrollView.setLayoutParams(layoutParams);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        Component component = MyCell.create(context).build();
        LithoView lithoView = LithoView.create(context, component);
        linearLayout.addView(lithoView);


        LayoutInflater inflater = LayoutInflater.from(this);
        View centerView = inflater.inflate(R.layout.alert_dialog, null);
        linearLayout.addView(centerView);

        scrollView.addView(linearLayout);
        setContentView(scrollView);
    }

}
