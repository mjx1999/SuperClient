package com.twisty.superclient.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by twisty on 14-8-22.
 */
public class ParamsSalesOrder extends Params {

    private Long InStoreID;
    private Long StoreID;

    private SalesOrderMasterData MasterData;
    private List<SalesOrderDetail1Data> Detail1Data = new ArrayList<SalesOrderDetail1Data>();

    public SalesOrderMasterData getMasterData() {
        return MasterData;
    }

    public void setMasterData(SalesOrderMasterData masterData) {
        MasterData = masterData;
    }

    public List<SalesOrderDetail1Data> getDetail1Data() {
        return Detail1Data;
    }

    public void setDetail1Data(List<SalesOrderDetail1Data> detail1Data) {
        Detail1Data = detail1Data;
    }
}
