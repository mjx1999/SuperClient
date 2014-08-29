package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-28.
 */
public class ParamsTransfer extends Params {
    private Long InStoreID;
    private Long StoreID;

    private TransferMasterData MasterData;
    private ArrayList<TransferDetail1Data> Detail1Data;

    public Long getInStoreID() {
        return InStoreID;
    }

    public void setInStoreID(Long inStoreID) {
        InStoreID = inStoreID;
    }

    public Long getStoreID() {
        return StoreID;
    }

    public void setStoreID(Long storeID) {
        StoreID = storeID;
    }

    public TransferMasterData getMasterData() {
        return MasterData;
    }

    public void setMasterData(TransferMasterData masterData) {
        MasterData = masterData;
    }

    public ArrayList<TransferDetail1Data> getDetail1Data() {
        return Detail1Data;
    }

    public void setDetail1Data(ArrayList<TransferDetail1Data> detail1Data) {
        Detail1Data = detail1Data;
    }
}
