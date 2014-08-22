package com.twisty.superclient.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by twisty on 14-8-22.
 */
public class ParamsSalesBill extends Params {
    private SalesBillMasterData MasterData;
    private List<SalesBillDetail1Data> Detail1Data = new ArrayList<SalesBillDetail1Data>();

    public SalesBillMasterData getMasterData() {
        return MasterData;
    }

    public void setMasterData(SalesBillMasterData masterData) {
        MasterData = masterData;
    }

    public List<SalesBillDetail1Data> getDetail1Data() {
        return Detail1Data;
    }

    public void setDetail1Data(List<SalesBillDetail1Data> detail1Data) {
        Detail1Data = detail1Data;
    }
}
