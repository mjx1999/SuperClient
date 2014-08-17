package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-16.
 */
public class AMKindResp extends Response {
    private ArrayList<AMKind> ResultData;

    public ArrayList<AMKind> getResultData() {
        return ResultData;
    }

    public void setResultData(ArrayList<AMKind> resultData) {
        ResultData = resultData;
    }

    @Override
    public String toString() {
        return "AMKindResp{" +
                "ResultData=" + ResultData +
                '}';
    }
}
