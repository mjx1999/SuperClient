package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-21.
 */
public class SalesBillResp extends Response {
    private SalesBillMasterData MasterData;
    private ArrayList<SalesBillDetail1Data> Detail1Data;

    public SalesBillMasterData getMasterData() {
        return MasterData;
    }

    public void setMasterData(SalesBillMasterData masterData) {
        MasterData = masterData;
    }

    public ArrayList<SalesBillDetail1Data> getDetail1Data() {
        return Detail1Data;
    }

    public void setDetail1Data(ArrayList<SalesBillDetail1Data> detail1Data) {
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
