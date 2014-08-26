package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-26.
 */
public class StockCheckListResp extends Response {
    private int TotalCount;
    private int PageNo;
    private int PageSize;
    private ArrayList<StockCheck> ListData;

    @Override
    public String toString() {
        return "StockCheckListResp{" +
                "TotalCount=" + TotalCount +
                ", PageNo=" + PageNo +
                ", PageSize=" + PageSize +
                ", ListData=" + ListData +
                '}';
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int totalCount) {
        TotalCount = totalCount;
    }

    public int getPageNo() {
        return PageNo;
    }

    public void setPageNo(int pageNo) {
        PageNo = pageNo;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public ArrayList<StockCheck> getListData() {
        return ListData;
    }

    public void setListData(ArrayList<StockCheck> listData) {
        ListData = listData;
    }
}
