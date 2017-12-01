package com.cloud.xc.spec;

import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.Row;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.widget.Text;
import com.facebook.litho.widget.VerticalGravity;

import static com.facebook.yoga.YogaEdge.LEFT;

/**
 * Created by cloud on 2017/11/27.
 */
@LayoutSpec
public class TextItemSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c) {
        return Row.create(c)
                .child(
                        Text.create(c)
                                .text("访客")
                                .textSizeDip(16)
                                .verticalGravity(VerticalGravity.CENTER)
                                .withLayout()
                                .heightDip(50)
                                .marginDip(LEFT, 20)
                                .flexGrow(1)
                )
                .child(
                        Text.create(c)
                                .text("成交客户")
                                .textSizeDip(16)
                                .verticalGravity(VerticalGravity.CENTER)
                                .withLayout()
                                .heightDip(50)
                                .marginDip(LEFT, 20)
                                .flexGrow(1)
                )
                .child(
                        Text.create(c)
                                .text("回头率")
                                .textSizeDip(16)
                                .verticalGravity(VerticalGravity.CENTER)
                                .withLayout()
                                .heightDip(50)
                                .marginDip(LEFT, 20)
                                .flexGrow(1)
                )
                .build();
    }
}
