package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-16.
 */
public class SalesBillListResp extends Response {
    private int TotalCount;
    private int PageNo;
    private int PageSize;
    private ArrayList<SalesBill> ListData;



    @Override
    public String toString() {
        return "BillListResp{" +
                "TotalCount=" + TotalCount +
                ", PageNo=" + PageNo +
                ", PageSize=" + PageSize +
                '}';
    }

    public ArrayList<SalesBill> getListData() {
        return ListData;
    }

    public void setListData(ArrayList<SalesBill> listData) {
        ListData = listData;
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

}
