package com.cloud.xc.spec;

import com.facebook.litho.Column;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Recycler;
import com.facebook.litho.widget.RecyclerBinder;

/**
 * Created by cloud on 2017/11/26.
 */
@LayoutSpec
public class ViewAndListSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c,
                                          @Prop final RecyclerBinder listBinder) {

        return Column.create(c)
                .child(
                        Recycler.create(c).binder(listBinder)
                )
                .build();
    }
}
