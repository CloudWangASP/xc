package com.cloud.xc.spec;

import com.cloud.xc.R;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.Row;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Image;
import com.facebook.litho.widget.Text;
import com.facebook.litho.widget.VerticalGravity;

import static android.widget.ImageView.ScaleType.FIT_XY;
import static com.facebook.yoga.YogaEdge.ALL;
import static com.facebook.yoga.YogaEdge.LEFT;
import static com.facebook.yoga.YogaEdge.TOP;

/**
 * Created by cloud on 2017/11/20.
 */
@LayoutSpec
public class ListItemSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(ComponentContext c,
                                          @Prop int color,
                                          @Prop String title) {
        return Row.create(c)
                .paddingDip(ALL, 16)
                .marginDip(TOP, 1)
                .backgroundColor(color)
                .child(
                        Text.create(c)
                                .text("hi")
                                .textSizeSp(40))
                .child(
                        Text.create(c)
                                .text(title)
                                .verticalGravity(VerticalGravity.CENTER)
                                .textSizeSp(20)
                                .withLayout()
                                .marginDip(LEFT, 20)
                )
                .child(
                        Image.create(c)
                                .drawableRes(R.mipmap.icon1)
                                .scaleType(FIT_XY)
                                .withLayout()
                                .widthDip(40)
                                .heightDip(40)
                                .marginDip(TOP, 5)
                                .marginDip(LEFT, 20)
                )
                .build();
    }

}
