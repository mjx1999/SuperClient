package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-10.
 */
public class GDTypeResp extends Response {
    private ArrayList<GDType> ResultData;

    public ArrayList<GDType> getResultData() {
        return ResultData;
    }

    public void setResultData(ArrayList<GDType> resultData) {
        ResultData = resultData;
    }

    @Override
    public String toString() {
        return "GDTypeResp{" +
                "ResultData=" + ResultData +
                '}';
    }
}
