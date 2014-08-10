package com.twisty.superclient.bean;

/**
 * Created by twisty on 14-8-3.
 */
public class Response {
    private int ErrNo;
    private String ErrMessage;

    public int getErrNo() {
        return ErrNo;
    }

    public void setErrNo(int errNo) {
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

    public boolean isCorrect(){
        return ErrNo ==0;
    }

}
