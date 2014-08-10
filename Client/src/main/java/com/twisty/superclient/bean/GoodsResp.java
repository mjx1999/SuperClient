package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-10.
 */
public class GoodsResp extends Response {
    private ArrayList<Goods> ResultData;

    public ArrayList<Goods> getResultData() {
        return ResultData;
    }

    public void setResultData(ArrayList<Goods> resultData) {
        ResultData = resultData;
    }

    @Override
    public String toString() {
        return "GoodsResp{" +
                "ResultData=" + ResultData +
                '}';
    }
}
