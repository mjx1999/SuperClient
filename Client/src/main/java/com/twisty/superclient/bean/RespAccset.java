package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-3.
 */
public class RespAccset extends Response {
    private ArrayList<Accset> Data;

    public ArrayList<Accset> getData() {
        return Data;
    }

    public void setData(ArrayList<Accset> data) {
        Data = data;
    }

    @Override
    public String toString() {
        return "RespAccset{" +
                "ErrNo="+super.getErrNo()+
                ",ErrMessage="+super.getErrMessage()+
                ",Data=" + Data +
                '}';
    }
}
