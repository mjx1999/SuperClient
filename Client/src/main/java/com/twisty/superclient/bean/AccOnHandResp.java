package com.twisty.superclient.bean;

/**
 * Created by twisty on 14-9-9.
 */
public class AccOnHandResp extends Response {
    private Double EndPrice;
    private Double EndQty;

    public Double getEndPrice() {
        return EndPrice;
    }

    public void setEndPrice(Double endPrice) {
        EndPrice = endPrice;
    }

    public Double getEndQty() {
        return EndQty;
    }

    public void setEndQty(Double endQty) {
        EndQty = endQty;
    }
}
