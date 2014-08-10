package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-10.
 */
public class UnitResp extends Response {
    private ArrayList<Unit> ResultData;

    public ArrayList<Unit> getResultData() {
        return ResultData;
    }

    public void setResultData(ArrayList<Unit> resultData) {
        ResultData = resultData;
    }

    @Override
    public String toString() {
        return "UnitResp{" +
                "ResultData=" + ResultData +
                '}';
    }
}
