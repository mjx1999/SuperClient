package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-10.
 */
public class TraderPriceResp extends Response {
    private ArrayList<TraderPrice> ResultData;

    public ArrayList<TraderPrice> getResultData() {
        return ResultData;
    }

    public void setResultData(ArrayList<TraderPrice> resultData) {
        ResultData = resultData;
    }

    @Override
    public String toString() {
        return "TraderPriceResp{" +
                "ResultData=" + ResultData +
                '}';
    }
}
