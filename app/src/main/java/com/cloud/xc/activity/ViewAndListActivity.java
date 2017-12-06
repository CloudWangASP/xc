package com.cloud.xc.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.OrientationHelper;

import com.cloud.xc.spec.ListItem;
import com.cloud.xc.spec.MyCell;
import com.cloud.xc.spec.TextItem;
import com.cloud.xc.spec.ViewAndList;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.ComponentInfo;
import com.facebook.litho.LithoView;
import com.facebook.litho.widget.LinearLayoutInfo;
import com.facebook.litho.widget.Recycler;
import com.facebook.litho.widget.RecyclerBinder;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cloud on 2017/11/26.
 */

public class ViewAndListActivity extends BaseActivity {
    static Drawable mDrawable;
    RecyclerBinder listBinder;
    Component recyclerComponent;
    DelayThread mDelayThread = new DelayThread();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Thread(mDelayThread).start();
        listBinder = new RecyclerBinder(context, new LinearLayoutInfo(this, OrientationHelper.VERTICAL, false));
        recyclerComponent = Recycler.create(context).binder(listBinder).build();
        addListContent(listBinder, context);
        Component component = ViewAndList.create(context).listBinder(listBinder).build();
        LithoView lithoView = LithoView.create(context, component);
        setContentView(lithoView);
    }

    private static void addListContent(RecyclerBinder recyclerBinder, ComponentContext context) {
        for (int i = 0; i < 20; i++) {
            ComponentInfo.Builder componentInfoBuilder = ComponentInfo.create();
            if (i % 2 == 0) {
                componentInfoBuilder.component(
                        MyCell.create(context).drawable(mDrawable).build()
                );
            } else if (i % 3 == 0) {
                componentInfoBuilder.component(
                        TextItem.create(context).build()
                );
            } else {
                componentInfoBuilder.component(
                        ListItem.create(context)
                                .color(Color.WHITE)
                                .title("Cloud!")
                                .build()
                );
            }
            recyclerBinder.insertItemAt(i, componentInfoBuilder.build());
        }
    }

    public static Drawable drawableFromUrl(String url) {
        Bitmap b = null;
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.connect();
            InputStream input = connection.getInputStream();
            b = BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BitmapDrawable(b);
    }

    class DelayThread implements Runnable {
        @Override
        public void run() {
            mDrawable = drawableFromUrl("http://h.hiphotos.baidu.com/image/pic/item/dc54564e9258d10944fb3972d858ccbf6d814dd2.jpg");
        }
    }


}
