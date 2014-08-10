package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-4.
 */
public class OperatorResp extends Response{
    private ArrayList<Operator> ResultData;

    public ArrayList<Operator> getResultData() {
        return ResultData;
    }

    public void setResultData(ArrayList<Operator> resultData) {
        ResultData = resultData;
    }
}
