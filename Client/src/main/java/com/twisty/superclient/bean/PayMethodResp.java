package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-16.
 */
public class PayMethodResp extends Response {
    private ArrayList<PayMethod> ResultData;

    public ArrayList<PayMethod> getResultData() {
        return ResultData;
    }

    public void setResultData(ArrayList<PayMethod> resultData) {
        ResultData = resultData;
    }

    @Override
    public String toString() {
        return "PayMethodResp{" +
                "ResultData=" + ResultData +
                '}';
    }
}
