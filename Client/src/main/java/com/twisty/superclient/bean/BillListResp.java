package com.twisty.superclient.bean;

import android.content.Context;
import android.os.Looper;

import com.twisty.superclient.util.CommonUtil;

import java.util.ArrayList;

/**
 * Created by twisty on 14-8-16.
 */
public class BillListResp extends Response {
    private int TotalCount;
    private int PageNo;
    private int PageSize;
    private ArrayList<Bill> ListData;

    @Override
    public void catchException(Context context) {
        super.catchException(context);
            if(getListData()==null||getListData().size()<=0){
                log.i("未检索到数据.....");
                Looper.prepare();
                CommonUtil.showToastError(context, "未检索到数据");
                Looper.loop();
        }
    }

    public void catchEmptyData(Context context){
        if(getListData()==null||getListData().size()<=0){
            log.i("未检索到数据.....");
            Looper.prepare();
            CommonUtil.showToastError(context, "未检索到数据");
            Looper.loop();
        }
    }

    @Override
    public String toString() {
        return "BillListResp{" +
                "TotalCount=" + TotalCount +
                ", PageNo=" + PageNo +
                ", PageSize=" + PageSize +
                '}';
    }

    public ArrayList<Bill> getListData() {
        return ListData;
    }

    public void setListData(ArrayList<Bill> listData) {
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
