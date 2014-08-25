package com.twisty.superclient.bean;

/**
 * Created by twisty on 14-8-25.
 */
public class BillSaveResp extends Response {
    private Long BillID;

    public Long getBillID() {
        return BillID;
    }

    public void setBillID(Long billID) {
        BillID = billID;
    }
}
