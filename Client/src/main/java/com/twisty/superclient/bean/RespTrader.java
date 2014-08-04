package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-5.
 */
public class RespTrader {
    private ArrayList<Trader> ResultData;

    public ArrayList<Trader> getResultData() {
        return ResultData;
    }

    public void setResultData(ArrayList<Trader> resultData) {
        ResultData = resultData;
    }

    @Override
    public String toString() {
        return "RespTrader{" +
                "ResultData=" + ResultData +
                '}';
    }
}
