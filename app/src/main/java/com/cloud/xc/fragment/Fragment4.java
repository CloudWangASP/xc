package com.cloud.xc.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cloud.xc.R;
import com.cloud.xc.event.CallBack;
import com.cloud.xc.event.TestCallBack;
import com.cloud.xc.view.SegmentView;

import java.util.ArrayList;

/**
 * Created by cloud on 2017/6/22.
 */

public class Fragment4 extends Fragment {

    private Button callback;
    private TestCallBack mTestCallBack = new TestCallBack();
    private OnTitleChangeListener mOnTitleChangeListener;
    private SegmentView segment;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.xc_fragment4, container, false);
        callback = (Button) v.findViewById(R.id.callback);
        segment = (SegmentView) v.findViewById(R.id.segment);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("一天");
        arrayList.add("一周");
        arrayList.add("一月");
        segment.init(arrayList.size(), arrayList);

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
                mOnTitleChangeListener.changeTitle("标题回调");
            }
        });
        return v;
    }

    public interface OnTitleChangeListener {
        void changeTitle(String title);
    }

    public void setOnTitleChangerListener(OnTitleChangeListener mOnTitleChangeListener) {
        this.mOnTitleChangeListener = mOnTitleChangeListener;
    }
}
