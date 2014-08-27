package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-26.
 */
public class TransferResp extends Response {
    private TransferMasterData MasterData;
    private ArrayList<TransferDetail1Data> Detail1Data;

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
