package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-26.
 */
public class StockCheckResp extends Response {
    private StockCheckMasterData MasterData;
    private ArrayList<StockCheckDetail1Data> Detail1Data;

    public StockCheckMasterData getMasterData() {
        return MasterData;
    }

    public void setMasterData(StockCheckMasterData masterData) {
        MasterData = masterData;
    }

    public ArrayList<StockCheckDetail1Data> getDetail1Data() {
        return Detail1Data;
    }

    public void setDetail1Data(ArrayList<StockCheckDetail1Data> detail1Data) {
        Detail1Data = detail1Data;
    }
}
