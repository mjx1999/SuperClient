package com.twisty.superclient.bean;

/**
 * Created by twisty on 14-8-25.
 */
public class SalesOrder {
//            "BillID": 6,                              /*单据ID*/
//            "BillState": 0,                           /*单据状态*/
//            "BillStateName": "待审核",                /*单据状态名称*/
//            "Amount": 1872.0,                         /*总金额*/
//            "ForwardAmt":0.0,                         /*订金*/
//            "BillCode": "SO-2014-08-00001",           /*单号*/
//            "BillDate": "2014-08-17 00:00:00",        /*单据日期*/
//            "CheckorName": null,                      /*审批人姓名*/
//            "Closed": 0,                              /*是否中止*/
//            "DepartmentName": "销售部",               /*部门名称*/
//            "EmpName": "李修",                        /*业务员姓名*/
//            "LinkMan": "袁萍",                        /*收货人*/
//            "OpName": "系统管理员",                   /*开单人*/
//            "PaymethodName": "银行汇票",              /*结算方式名称*/
//            "RevDate": "2014-08-21 00:00:00",         /*交货时间*/
//            "ShipTypeName": null,                     /*运输方式名称*/
//            "Shopid": 0,                              /*分支机构ID*/
//            "TraderName": "中通股份",                 /*往来单位名称*/
//            "ValidDate": "2050-12-31 00:00:00"        /*有效日期*/
    private long BillID;
    private long Shopid;
    private String BillCode;
    private String BillDate;
    private String ShipTypeName;
    private String LinkMan;
    private String RevDate;
    private double Amount;
    private String Linkman;
    private double ForwardAmt;
    private String TraderName;
    private String OpName;
    private String CheckorName;
    private String DepartmentName;
    private String PaymethodName;
    private String EmpName;
    private String BillStateName;
    private String ValidDate;
    private Integer Closed;

    public long getBillID() {
        return BillID;
    }

    public void setBillID(long billID) {
        BillID = billID;
    }

    public long getShopid() {
        return Shopid;
    }

    public void setShopid(long shopid) {
        Shopid = shopid;
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

    public String getShipTypeName() {
        return ShipTypeName;
    }

    public void setShipTypeName(String shipTypeName) {
        ShipTypeName = shipTypeName;
    }

    public String getLinkMan() {
        return LinkMan;
    }

    public void setLinkMan(String linkMan) {
        LinkMan = linkMan;
    }

    public String getRevDate() {
        return RevDate;
    }

    public void setRevDate(String revDate) {
        RevDate = revDate;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public String getLinkman() {
        return Linkman;
    }

    public void setLinkman(String linkman) {
        Linkman = linkman;
    }

    public double getForwardAmt() {
        return ForwardAmt;
    }

    public void setForwardAmt(double forwardAmt) {
        ForwardAmt = forwardAmt;
    }

    public String getTraderName() {
        return TraderName;
    }

    public void setTraderName(String traderName) {
        TraderName = traderName;
    }

    public String getOpName() {
        return OpName;
    }

    public void setOpName(String opName) {
        OpName = opName;
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

    public String getPaymethodName() {
        return PaymethodName;
    }

    public void setPaymethodName(String paymethodName) {
        PaymethodName = paymethodName;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }

    public String getBillStateName() {
        return BillStateName;
    }

    public void setBillStateName(String billStateName) {
        BillStateName = billStateName;
    }

    public String getValidDate() {
        return ValidDate;
    }

    public void setValidDate(String validDate) {
        ValidDate = validDate;
    }

    public Integer getClosed() {
        return Closed;
    }

    public void setClosed(Integer closed) {
        Closed = closed;
    }
}
