package com.cloud.xc.event;

/**
 * Created by cloud on 2017/8/7.
 */

public class TestCallBack {
    private CallBack mCallBack;

    public void onChange(){
        mCallBack.callBack();
    }

    public void setCallBack(CallBack callBack) {
        mCallBack = callBack;
    }
}