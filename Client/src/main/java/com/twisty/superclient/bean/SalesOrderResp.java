package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-25.
 */
public class SalesOrderResp extends Response {
    private SalesOrderMasterData MasterData;
    private ArrayList<SalesOrderDetail1Data> Detail1Data;

    public ArrayList<SalesOrderDetail1Data> getDetail1Data() {
        return Detail1Data;
    }

    public void setDetail1Data(ArrayList<SalesOrderDetail1Data> detail1Data) {
        Detail1Data = detail1Data;
    }

    public SalesOrderMasterData getMasterData() {

        return MasterData;
    }

    public void setMasterData(SalesOrderMasterData masterData) {
        MasterData = masterData;
    }
}
