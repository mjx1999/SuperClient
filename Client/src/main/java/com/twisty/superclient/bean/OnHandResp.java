package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-10.
 */
public class OnHandResp extends Response {
    private ArrayList<OnHand> ResultData;

    public ArrayList<OnHand> getResultData() {
        return ResultData;
    }

    public void setResultData(ArrayList<OnHand> resultData) {
        ResultData = resultData;
    }

    @Override
    public String toString() {
        return "OnHandResp{" +
                "ResultData=" + ResultData +
                '}';
    }
}
