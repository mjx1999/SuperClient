package com.twisty.superclient.bean;

import java.io.Serializable;

/**
 * Created by twisty on 14-8-3.
 */
public class Params implements Serializable {
    private String ClientVer;


    private String AccsetCode;


    private long OpID;               /*操作员ID*/
    private String OpPassword;       /*操作员密码*/
    private String DefaultStoreCode;


    //-------------销售单-------------
    private String BillName;
    private String Operate;
    private long BillID;
    private String BillCode;

    //销售单列表
    private int PageSize;
    private int PageNo;
    private String BegDate;
    private String EndDate;
    private long TraderID;
    private long BillKind;
    private long BillState;
    //销售单列表

    //新增销售单
    private boolean IsAddnew;
    private Long StoreID;

    public Long getStoreID() {
        return StoreID;
    }

    public void setStoreID(Long storeID) {
        StoreID = storeID;
    }

    public String getBillName() {
        return BillName;
    }

    public void setBillName(String billName) {
        BillName = billName;
    }

    public String getOperate() {
        return Operate;
    }

    public void setOperate(String operate) {
        Operate = operate;
    }

    public long getBillID() {
        return BillID;
    }

    public void setBillID(long billID) {
        BillID = billID;
    }

    public String getBillCode() {
        return BillCode;
    }

    public void setBillCode(String billCode) {
        BillCode = billCode;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public int getPageNo() {
        return PageNo;
    }

    public void setPageNo(int pageNo) {
        PageNo = pageNo;
    }

    public String getBegDate() {
        return BegDate;
    }

    public void setBegDate(String begDate) {
        BegDate = begDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public long getTraderID() {
        return TraderID;
    }

    public void setTraderID(long traderID) {
        TraderID = traderID;
    }

    public long getBillKind() {
        return BillKind;
    }

    public void setBillKind(long billKind) {
        BillKind = billKind;
    }

    public long getBillState() {
        return BillState;
    }

    public void setBillState(long billState) {
        BillState = billState;
    }

    public boolean isAddnew() {
        return IsAddnew;
    }

    public void setAddnew(boolean isAddnew) {
        IsAddnew = isAddnew;
    }


    public String getClientVer() {
        return ClientVer;
    }

    public void setClientVer(String clientVer) {
        ClientVer = clientVer;
    }

    public String getAccsetCode() {
        return AccsetCode;
    }

    public void setAccsetCode(String accsetCode) {
        AccsetCode = accsetCode;
    }

    public long getOpID() {
        return OpID;
    }

    public void setOpID(long opID) {
        OpID = opID;
    }

    public String getOpPassword() {
        return OpPassword;
    }

    public void setOpPassword(String opPassword) {
        OpPassword = opPassword;
    }

    public String getDefaultStoreCode() {
        return DefaultStoreCode;
    }

    public void setDefaultStoreCode(String defaultStoreCode) {
        DefaultStoreCode = defaultStoreCode;
    }

    @Override
    public String toString() {
        return "Params{" +
                "ClientVer='" + ClientVer + '\'' +
                ", AccsetCode='" + AccsetCode + '\'' +
                ", OpID=" + OpID +
                ", OpPassword='" + OpPassword + '\'' +
                ", DefaultStoreCode='" + DefaultStoreCode + '\'' +
                '}';
    }
}
