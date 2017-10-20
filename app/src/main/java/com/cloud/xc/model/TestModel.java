package com.cloud.xc.model;

/**
 * Created by cloud on 2017/10/16.
 */

public class TestModel {
    private String mString;
    private int mInt;

    public String getString() {
        return mString;
    }

    public void setString(String string) {
        mString = string;
    }

    public int getInt() {
        return mInt;
    }

    public void setInt(int anInt) {
        mInt = anInt;
    }

    public int addMethod(int s, int b) {
        return s + b;
    }
}
