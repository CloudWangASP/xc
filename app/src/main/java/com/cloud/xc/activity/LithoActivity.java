package com.cloud.xc.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.cloud.xc.spec.GridItem;
import com.cloud.xc.spec.ListItem;
import com.cloud.xc.spec.MyCell;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentInfo;
import com.facebook.litho.LithoView;
import com.facebook.litho.widget.GridLayoutInfo;
import com.facebook.litho.widget.Recycler;
import com.facebook.litho.widget.RecyclerBinder;

public class LithoActivity extends BaseActivity {

    RecyclerBinder recyclerBinder;
    static RecyclerBinder girdBinder;

    Component recyclerComponent;
    static Component gridComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //表格视图
        girdBinder = new RecyclerBinder(context, new GridLayoutInfo(context, 3));
        gridComponent = Recycler.create(context).binder(girdBinder).build();
        addGridContent(girdBinder, context);

//        //列表视图
//        recyclerBinder = new RecyclerBinder(context, new LinearLayoutInfo(this, OrientationHelper.VERTICAL, false));
//        recyclerComponent = Recycler.create(context).binder(recyclerBinder).build();
//        addListContent(recyclerBinder, context);

        //设置UI
        setContentView(LithoView.create(context, recyclerComponent));
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
