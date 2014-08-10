package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-4.
 */
public class EmployeeResp {
    private ArrayList<Employee> ResultData;

    public ArrayList<Employee> getResultData() {
        return ResultData;
    }

    public void setResultData(ArrayList<Employee> resultData) {
        ResultData = resultData;
    }
}
