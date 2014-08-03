package com.twisty.superclient.bean;

/**
 * Created by twisty on 14-8-3.
 */
public class Response {
    private String ErrNo;
    private String ErrMessage;

    public String getErrNo() {
        return ErrNo;
    }

    public void setErrNo(String errNo) {
        ErrNo = errNo;
    }

    public String getErrMessage() {
        return ErrMessage;
    }

    public void setErrMessage(String errMessage) {
        ErrMessage = errMessage;
    }

    @Override
    public String toString() {
        return "Response{" +
                "ErrNo='" + ErrNo + '\'' +
                ", ErrMessage='" + ErrMessage + '\'' +
                '}';
    }
}
