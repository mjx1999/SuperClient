package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-25.
 */
public class SalesOrderListResp extends Response{
    private int TotalCount;
    private int PageNo;
    private int PageSize;
    private ArrayList<SalesOrder> ListData;

    @Override
    public String toString() {
        return "SalesOrderListResp{" +
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

    public ArrayList<SalesOrder> getListData() {
        return ListData;
    }

    public void setListData(ArrayList<SalesOrder> listData) {
        ListData = listData;
    }
}
