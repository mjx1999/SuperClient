package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-28.
 */
public class SysParamResp extends Response {
    private ArrayList<SysParam> ResultData;

    public ArrayList<SysParam> getResultData() {
        return ResultData;
    }

    public void setResultData(ArrayList<SysParam> resultData) {
        ResultData = resultData;
    }
}
