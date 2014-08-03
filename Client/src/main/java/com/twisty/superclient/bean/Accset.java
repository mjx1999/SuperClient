package com.twisty.superclient.bean;

/**
 * Created by twisty on 14-8-3.
 */
public class Accset {
    private String AccsetID;
    private String AccsetCode;
    private String AccsetName;

    public String getAccsetID() {
        return AccsetID;
    }

    public void setAccsetID(String accsetID) {
        AccsetID = accsetID;
    }

    public String getAccsetCode() {
        return AccsetCode;
    }

    public void setAccsetCode(String accsetCode) {
        AccsetCode = accsetCode;
    }

    public String getAccsetName() {
        return AccsetName;
    }

    public void setAccsetName(String accsetName) {
        AccsetName = accsetName;
    }

    @Override
    public String toString() {
        return "Accset{" +
                "AccsetID='" + AccsetID + '\'' +
                ", AccsetCode='" + AccsetCode + '\'' +
                ", AccsetName='" + AccsetName + '\'' +
                '}';
    }
}
