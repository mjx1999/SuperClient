package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-10.
 */
public class IoTypeResp extends Response {
    private ArrayList<IoType> ResultData;

    public ArrayList<IoType> getResultData() {
        return ResultData;
    }

    public void setResultData(ArrayList<IoType> resultData) {
        ResultData = resultData;
    }

    @Override
    public String toString() {
        return "IoTypeResp{" +
                "ResultData=" + ResultData +
                '}';
    }
}
