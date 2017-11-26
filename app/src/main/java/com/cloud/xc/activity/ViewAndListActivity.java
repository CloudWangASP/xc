package com.cloud.xc.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.OrientationHelper;

import com.cloud.xc.spec.GridItem;
import com.cloud.xc.spec.ListItem;
import com.cloud.xc.spec.MyCell;
import com.cloud.xc.spec.ViewAndList;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentInfo;
import com.facebook.litho.LithoView;
import com.facebook.litho.widget.GridLayoutInfo;
import com.facebook.litho.widget.LinearLayoutInfo;
import com.facebook.litho.widget.Recycler;
import com.facebook.litho.widget.RecyclerBinder;

/**
 * Created by cloud on 2017/11/26.
 */

public class ViewAndListActivity extends BaseActivity {

    RecyclerBinder listBinder;
    Component recyclerComponent;

    RecyclerBinder girdBinder;
    Component gridComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        girdBinder = new RecyclerBinder(context, new GridLayoutInfo(context, 3));
        gridComponent = Recycler.create(context).binder(girdBinder).build();
        addGridContent(girdBinder, context);

        listBinder = new RecyclerBinder(context, new LinearLayoutInfo(this, OrientationHelper.VERTICAL, false));
        recyclerComponent = Recycler.create(context).binder(listBinder).build();
        addListContent(listBinder, context);

        Component component = ViewAndList.create(context).girdBinder(girdBinder).listBinder(listBinder).build();
        LithoView lithoView = LithoView.create(context, component);
        setContentView(lithoView);
    }

    private void addGridContent(RecyclerBinder girdBinder, ComponentContext context) {
        for (int i = 0; i < 6; i++) {
            ComponentInfo.Builder componentInfoBuilder = ComponentInfo.create();
            componentInfoBuilder.component(
                    GridItem.create(context).build()
            );
            girdBinder.insertItemAt(i, componentInfoBuilder.build());
        }
    }

    private static void addListContent(RecyclerBinder recyclerBinder, ComponentContext context) {
        for (int i = 0; i < 12; i++) {
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
