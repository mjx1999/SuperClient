package com.twisty.superclient.bean;

/**
 * Created by twisty on 14-8-27.
 */
public class ParamsAccOnHand extends Params {
    private Long GoodsID;
    private Long StoreID;
    private String Date;

    public Long getGoodsID() {
        return GoodsID;
    }

    public void setGoodsID(Long goodsID) {
        GoodsID = goodsID;
    }

    public Long getStoreID() {
        return StoreID;
    }

    public void setStoreID(Long storeID) {
        StoreID = storeID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
