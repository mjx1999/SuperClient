package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-10.
 */
public class GoodsPictureResp extends Response {
    private ArrayList<GoodsPicture> ResultData;

    public ArrayList<GoodsPicture> getResultData() {
        return ResultData;
    }

    public void setResultData(ArrayList<GoodsPicture> resultData) {
        ResultData = resultData;
    }

    @Override
    public String toString() {
        return "GoodsPictureResp{" +
                "ResultData=" + ResultData +
                '}';
    }
}
