package com.cloud.xc.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.cloud.xc.R;
import com.cloud.xc.view.BottomDialog;

import java.util.List;

public class BottomViewActivity extends AppCompatActivity {
    //自定义的弹出框类
    BottomDialog mDialog;
    List<String> appList;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_view);
        TextView tv = (TextView) this.findViewById(R.id.text);
        //把文字控件添加监听，点击弹出自定义窗口
        tv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDialog = new BottomDialog(BottomViewActivity.this);
                mDialog.show();
            }
        });
    }
}
