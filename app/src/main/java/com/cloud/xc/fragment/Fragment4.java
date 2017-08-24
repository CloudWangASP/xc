package com.cloud.xc.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cloud.xc.R;
import com.cloud.xc.event.CallBack;
import com.cloud.xc.event.TestCallBack;

/**
 * Created by cloud on 2017/6/22.
 */

public class Fragment4 extends Fragment {

    private Button callback;
    private TestCallBack mTestCallBack = new TestCallBack();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.xc_fragment4, container, false);
        callback = (Button) v.findViewById(R.id.callback);
        mTestCallBack.setCallBack(new CallBack() {
            @Override
            public void callBack() {
                callback.setTextColor(getResources().getColor(R.color.xc_blue));
            }
        });

        callback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTestCallBack.onChange();
            }
        });
        return v;
    }

}
