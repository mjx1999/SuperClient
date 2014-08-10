package com.twisty.superclient.bean;

/**
 * Created by twisty on 14-8-3.
 */
public class Params {
    private String ClientVer;


    private String AccsetCode;


    private long OpID;               /*操作员ID*/
    private String OpPassword;       /*操作员密码*/
    private String DefaultStoreCode;



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
