package com.cloud.xc.spec;

import android.graphics.Color;
import android.text.TextUtils;

import com.cloud.xc.R;
import com.facebook.litho.ClickEvent;
import com.facebook.litho.Column;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentLayout;
import com.facebook.litho.Row;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.widget.Image;
import com.facebook.litho.widget.Text;

import static android.widget.ImageView.ScaleType.FIT_XY;
import static com.facebook.yoga.YogaEdge.ALL;
import static com.facebook.yoga.YogaEdge.LEFT;
import static com.facebook.yoga.YogaEdge.TOP;

/**
 * Created by cloud on 2017/11/20.
 */
@LayoutSpec
public class MyCellSpec {
    @OnCreateLayout
    static ComponentLayout onCreateLayout(
            ComponentContext c) {
        return Row.create(c)
                .paddingDip(ALL, 20)
                .backgroundColor(Color.WHITE)
                .child(
                        Image.create(c)
                                .drawableRes(R.mipmap.icon2)
                                .scaleType(FIT_XY)
                                .withLayout()
                                .widthDip(60)
                                .heightDip(60)
                )
                .child(
                        Column.create(c)
                                .child(
                                        Text.create(c)
                                                .text("Hello, world!")
                                                .ellipsize(TextUtils.TruncateAt.END)
                                                .textSizeDip(16)
                                                .withLayout()
                                                .marginDip(LEFT, 15)
                                                .clickHandler(MyCell.onMyClick(c))
                                                .touchExpansionDip(ALL, 10)
                                )
                                .child(
                                        Row.create(c)
                                                .marginDip(TOP, 5)
                                                .marginDip(LEFT, 15)
                                                .child(
                                                        Column.create(c)
                                                                .child(
                                                                        Text.create(c)
                                                                                .text("浏览354")
                                                                                .textSizeDip(10)
                                                                                .withLayout()
                                                                )
                                                                .child(
                                                                        Text.create(c)
                                                                                .text("客户24")
                                                                                .textSizeDip(10)
                                                                                .withLayout()
                                                                                .marginDip(TOP, 5))
                                                )
                                                .child(
                                                        Row.create(c)
                                                                .marginDip(LEFT, 10)
                                                                .child(
                                                                        Column.create(c)
                                                                                .child(
                                                                                        Text.create(c)
                                                                                                .text("点赞86")
                                                                                                .textSizeDip(10)
                                                                                                .withLayout()
                                                                                )
                                                                                .child(
                                                                                        Text.create(c)
                                                                                                .text("好评率98.7%")
                                                                                                .textSizeDip(10)
                                                                                                .withLayout()
                                                                                                .marginDip(TOP, 5))
                                                                )
                                                )
                                )
                )
                .build();

    }

    @OnEvent(ClickEvent.class)
    public static void onMyClick(ComponentContext c) {
    }


}
