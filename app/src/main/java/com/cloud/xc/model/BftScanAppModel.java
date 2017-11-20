package com.cloud.xc.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by cloud on 2017/11/14.
 */

public class BftScanAppModel implements Serializable {
    public ArrayList<String> applist;

    public ArrayList<String> getApplist() {
        return applist;
    }

    public void setApplist(ArrayList<String> applist) {
        this.applist = applist;
    }
}
