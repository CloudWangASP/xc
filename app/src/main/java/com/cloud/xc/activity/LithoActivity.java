package com.cloud.xc.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;

import com.cloud.xc.spec.ListItem;
import com.cloud.xc.spec.MyCell;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentInfo;
import com.facebook.litho.LithoView;
import com.facebook.litho.widget.LinearLayoutInfo;
import com.facebook.litho.widget.Recycler;
import com.facebook.litho.widget.RecyclerBinder;

public class LithoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final RecyclerBinder recyclerBinder = new RecyclerBinder(
                context, new LinearLayoutInfo(this, OrientationHelper.VERTICAL, false)
        );
        //定义控件
        final Component component = Recycler.create(context)
                .binder(recyclerBinder)
                .build();
        addContent(recyclerBinder, context);
        setContentView(LithoView.create(context, component));
    }

    private static void addContent(RecyclerBinder recyclerBinder, ComponentContext context) {
        for (int i = 0; i < 32; i++) {
            ComponentInfo.Builder componentInfoBuilder = ComponentInfo.create();
            if (0 == i) {
                componentInfoBuilder.component(
                        MyCell.create(context).build()
                );
            } else {
                componentInfoBuilder.component(
                        ListItem.create(context)
                                .color(Color.GRAY)
                                .title("Cloud!")
                                .build()
                );
            }
            recyclerBinder.insertItemAt(i, componentInfoBuilder.build());
        }
    }
}
