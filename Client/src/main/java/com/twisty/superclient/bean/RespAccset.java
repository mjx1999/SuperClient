package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-3.
 */
public class RespAccset extends Response {
    private ArrayList<Accset> ResultData;

    public ArrayList<Accset> getResultData() {
        return ResultData;
    }

    public void setResultData(ArrayList<Accset> resultData) {
        ResultData = resultData;
    }

    @Override
    public String toString() {
        return "RespAccset{" +
                "ErrNo="+super.getErrNo()+
                ",ErrMessage="+super.getErrMessage()+
                ",ResultData=" + ResultData +
                '}';
    }
}
