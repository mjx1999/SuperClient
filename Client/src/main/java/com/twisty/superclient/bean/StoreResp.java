package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-10.
 */
public class StoreResp extends Response {
    private ArrayList<Store> ResultData;

    public ArrayList<Store> getResultData() {
        return ResultData;
    }

    public void setResultData(ArrayList<Store> resultData) {
        ResultData = resultData;
    }

    @Override
    public String toString() {
        return "StoreResp{" +
                "ResultData=" + ResultData +
                '}';
    }
}
