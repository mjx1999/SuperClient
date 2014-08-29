package com.twisty.superclient.bean;

import com.twisty.superclient.util.CommonLog;
import com.twisty.superclient.util.LogFactory;

import java.io.Serializable;

/**
 * Created by twisty on 14-8-3.
 */
public class Response implements Serializable {
    protected CommonLog log = LogFactory.createLog();
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

    public boolean isCorrect() {
        return ErrNo == 0;
    }


}
