package com.twisty.superclient.bean;

/**
 * Created by twisty on 14-8-28.
 */
public class Transfer {
//            "BillID": 3,                       /*单据ID*/
//            "BillKind": 1,                     /*单据类型ID  取值am_kind表 kind=8*/
//            "BillKindName": "同价调拨",        /*单据类型名称*/
//            "BillStateName": "已审核",         /*审核状态名称*/
//            "Amount": 140.07,                  /*金额*/
//            "BillCode": "AL-2014-01-00001",    /*单号*/
//            "BillDate": "2014-01-16 00:00:00", /*单据日期*/
//            "CheckorName": "系统管理员",       /*审核人姓名*/
//            "DepartmentName": null,            /*部门名称*/
//            "EmpName": null,                   /*业务员姓名*/
//            "InStoreName": "分厂成品仓",       /*调入仓名称*/
//            "OpName": "系统管理员",            /*制单人姓名*/
//            "SFlag": 0,                        /*冲红标志*/
//            "ShopID": 0,                       /*分支机构ID*/
//            "StoreName": "成品仓"              /*调出仓库名称*/

    private Long BillID;
    private Long ShopID;
    private Integer BillKind;
    private Integer SFlag;
    private String BillKindName;
    private String BillStateName;
    private String BillCode;
    private String BillDate;
    private String CheckorName;
    private String DepartmentName;
    private String EmpName;
    private String InStoreName;
    private String StoreName;
    private String OpName;
    private Double Amount;

    public Long getBillID() {
        return BillID;
    }

    public void setBillID(Long billID) {
        BillID = billID;
    }

    public Long getShopID() {
        return ShopID;
    }

    public void setShopID(Long shopID) {
        ShopID = shopID;
    }

    public Integer getBillKind() {
        return BillKind;
    }

    public void setBillKind(Integer billKind) {
        BillKind = billKind;
    }

    public Integer getSFlag() {
        return SFlag;
    }

    public void setSFlag(Integer SFlag) {
        this.SFlag = SFlag;
    }

    public String getBillKindName() {
        return BillKindName;
    }

    public void setBillKindName(String billKindName) {
        BillKindName = billKindName;
    }

    public String getBillStateName() {
        return BillStateName;
    }

    public void setBillStateName(String billStateName) {
        BillStateName = billStateName;
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

    public String getInStoreName() {
        return InStoreName;
    }

    public void setInStoreName(String inStoreName) {
        InStoreName = inStoreName;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public String getOpName() {
        return OpName;
    }

    public void setOpName(String opName) {
        OpName = opName;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }
}
