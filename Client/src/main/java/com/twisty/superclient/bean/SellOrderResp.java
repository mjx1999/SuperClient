package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-21.
 */
public class SellOrderResp extends Response {
    private MasterData MasterData;
    private ArrayList<Detail1Data> Detail1Data;

    public MasterData getMasterData() {
        return MasterData;
    }

    public void setMasterData(MasterData masterData) {
        MasterData = masterData;
    }

    public ArrayList<Detail1Data> getDetail1Data() {
        return Detail1Data;
    }

    public void setDetail1Data(ArrayList<Detail1Data> detail1Data) {
        Detail1Data = detail1Data;
    }

    @Override
    public String toString() {
        return "SellOrderResp{" +
                "MasterData=" + MasterData +
                ", Detail1Data=" + Detail1Data +
                '}';
    }
}
