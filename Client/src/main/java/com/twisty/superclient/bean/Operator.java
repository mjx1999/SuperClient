package com.twisty.superclient.bean;

/**
 * Created by twisty on 14-8-4.
 */
public class Operator {
    private long OpID;
    private String OpCode;
    private String OpName;
    private String OpPassword;
    private String SysManager;
    private String ShopID;

    @Override
    public String toString() {
        return "Operator{" +
                "OpID='" + OpID + '\'' +
                ", OpCode='" + OpCode + '\'' +
                ", OpName='" + OpName + '\'' +
                ", OpPassword='" + OpPassword + '\'' +
                ", SysManager='" + SysManager + '\'' +
                ", ShopID='" + ShopID + '\'' +
                '}';
    }

    public long getOpID() {
        return OpID;
    }

    public void setOpID(long opID) {
        OpID = opID;
    }

    public String getOpCode() {
        return OpCode;
    }

    public void setOpCode(String opCode) {
        OpCode = opCode;
    }

    public String getOpName() {
        return OpName;
    }

    public void setOpName(String opName) {
        OpName = opName;
    }

    public String getOpPassword() {
        return OpPassword;
    }

    public void setOpPassword(String opPassword) {
        OpPassword = opPassword;
    }

    public String getSysManager() {
        return SysManager;
    }

    public void setSysManager(String sysManager) {
        SysManager = sysManager;
    }

    public String getShopID() {
        return ShopID;
    }

    public void setShopID(String shopID) {
        ShopID = shopID;
    }
}
