package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-10.
 */
public class DepartmentResp extends Response {
    private ArrayList<Department> ResultData;

    public ArrayList<Department> getResultData() {
        return ResultData;
    }

    public void setResultData(ArrayList<Department> resultData) {
        ResultData = resultData;
    }

    @Override
    public String toString() {
        return "DepartmentResp{" +
                "ResultData=" + ResultData +
                '}';
    }
}
