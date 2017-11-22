package com.cloud.xc.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.facebook.litho.ComponentContext;

/**
 * Created by cloud on 2017/11/20.
 */

public class BaseActivity extends AppCompatActivity {
    ComponentContext context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //声明控件上下文
        context = new ComponentContext(this);
    }
}
