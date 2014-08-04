package com.twisty.superclient.bean;

/**
 * Created by twisty on 14-8-4.
 */
public class TradeType {
    private long TradeTypeID;/*主键ID*/
    private String TradeTypeCode;/*往来单位类型编码*/
    private String TradeTypeName; /*往来单位类型名称*/
    private String LCode;/*分层码*/
    private String ParentID;/*父级ID*/

    public long getTradeTypeID() {
        return TradeTypeID;
    }

    public void setTradeTypeID(long tradeTypeID) {
        TradeTypeID = tradeTypeID;
    }

    public String getTradeTypeCode() {
        return TradeTypeCode;
    }

    public void setTradeTypeCode(String tradeTypeCode) {
        TradeTypeCode = tradeTypeCode;
    }

    public String getTradeTypeName() {
        return TradeTypeName;
    }

    public void setTradeTypeName(String tradeTypeName) {
        TradeTypeName = tradeTypeName;
    }

    public String getLCode() {
        return LCode;
    }

    public void setLCode(String LCode) {
        this.LCode = LCode;
    }

    public String getParentID() {
        return ParentID;
    }

    public void setParentID(String parentID) {
        ParentID = parentID;
    }
}
