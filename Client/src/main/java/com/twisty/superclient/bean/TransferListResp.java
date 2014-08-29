package com.twisty.superclient.bean;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-28.
 */
public class TransferListResp extends Response {
    private Integer PageSize;
    private Integer PageNo;
    private Integer TotalCount;
    private ArrayList<Transfer> ListData;

    public Integer getPageSize() {
        return PageSize;
    }

    public void setPageSize(Integer pageSize) {
        PageSize = pageSize;
    }

    public Integer getPageNo() {
        return PageNo;
    }

    public void setPageNo(Integer pageNo) {
        PageNo = pageNo;
    }

    public Integer getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(Integer totalCount) {
        TotalCount = totalCount;
    }

    public ArrayList<Transfer> getListData() {
        return ListData;
    }

    public void setListData(ArrayList<Transfer> listData) {
        ListData = listData;
    }
}
