package com.cloud.xc.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cloud.xc.R;
import com.cloud.xc.annotation.CloudFindView;

/**
 * Created by cloud on 2017/6/22.
 */

public class Fragment3 extends Fragment {

    private TextView mTextView;
    private MyHandler handler;
    private DelayThread mDelayThread;
    private long displayTime;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.xc_fragment3, container, false);
        mTextView = (TextView) v.findViewById(R.id.textView);
        displayTime = 5000;
        mTextView.setText("倒计时" + displayTime / 1000 + "秒");
        handler = new MyHandler();
        mDelayThread = new DelayThread();
        startCount();
        return v;
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            long time = msg.getData().getLong("time");
            if (time == 0) {
                mTextView.setVisibility(View.GONE);
                handler.removeCallbacks(mDelayThread);
            } else {
                mTextView.setText("倒计时" + time / 1000 + "秒");
                startCount();
            }
        }
    }

    class DelayThread implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (displayTime >= 1000) {
                displayTime = displayTime - 1000;
            }
            Message msg = new Message();
            Bundle bundle = new Bundle();
            bundle.putLong("time", displayTime);
            msg.setData(bundle);
            handler.sendMessage(msg);
        }
    }

    private void startCount() {
        new Thread(mDelayThread).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
