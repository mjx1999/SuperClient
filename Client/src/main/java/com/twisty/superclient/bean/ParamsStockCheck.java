package com.twisty.superclient.bean;

/**
 * Created by twisty on 14-8-26.
 */
public class ParamsStockCheck extends Params {
    private StockCheckMasterData MasterData;
    private StockCheckDetail1Data Detail1Data;

    public StockCheckMasterData getMasterData() {
        return MasterData;
    }

    public void setMasterData(StockCheckMasterData masterData) {
        MasterData = masterData;
    }

    public StockCheckDetail1Data getDetail1Data() {
        return Detail1Data;
    }

    public void setDetail1Data(StockCheckDetail1Data detail1Data) {
        Detail1Data = detail1Data;
    }
}
