package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-10.
 */
public class AccountResp extends Response {
    private ArrayList<Account> ResultData;

    @Override
    public String toString() {
        return "AccountResp{" +
                "ResultData=" + ResultData +
                '}';
    }

    public ArrayList<Account> getResultData() {
        return ResultData;
    }

    public void setResultData(ArrayList<Account> resultData) {
        ResultData = resultData;
    }
}
