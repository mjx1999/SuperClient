package com.twisty.superclient.bean;

/**
 * Created by twisty on 14-8-26.
 */
public class StockCheck {
//            "BillID": 3,                               /*单据ID*/
//            "Amount": -7.87,                           /*金额*/
//            "BillCode": "BA-2014-01-00001",            /*单号*/
//            "BillDate": "2014-01-16 00:00:00",         /*单据日期*/
//            "BillState": 2,                            /*单据状态*/
//            "BillStateName": "已审核",                 /*单据状态名称*/
//            "CheckorName": "系统管理员",               /*审核人*/
//            "DepartmentName": null,                    /*部门名称*/
//            "EmpName": null,                           /*业务员*/
//            "IOTypeName": "报废",                      /*出库类型名称*/
//            "OpName": "系统管理员",                    /*制单人*/
//            "StoreName": "半成品仓"                    /*仓库名称*/

    private Long BillID;
    private Double Amount;
    private String BillCode;
    private String BillDate;
    private String BillStateName;
    private String CheckorName;
    private String DepartmentName;
    private String EmpName;
    private String IOTypeName;
    private String OpName;
    private String StoreName;
    private Integer BillState;

    public Long getBillID() {
        return BillID;
    }

    public void setBillID(Long billID) {
        BillID = billID;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    public String getBillCode() {
        return BillCode;
    }

    public void setBillCode(String billCode) {
        BillCode = billCode;
    }

    public String getBillDate() {
        return BillDate;
    }

    public void setBillDate(String billDate) {
        BillDate = billDate;
    }

    public String getBillStateName() {
        return BillStateName;
    }

    public void setBillStateName(String billStateName) {
        BillStateName = billStateName;
    }

    public String getCheckorName() {
        return CheckorName;
    }

    public void setCheckorName(String checkorName) {
        CheckorName = checkorName;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }

    public String getIOTypeName() {
        return IOTypeName;
    }

    public void setIOTypeName(String IOTypeName) {
        this.IOTypeName = IOTypeName;
    }

    public String getOpName() {
        return OpName;
    }

    public void setOpName(String opName) {
        OpName = opName;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public Integer getBillState() {
        return BillState;
    }

    public void setBillState(Integer billState) {
        BillState = billState;
    }
}
