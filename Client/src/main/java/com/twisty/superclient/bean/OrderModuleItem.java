package com.twisty.superclient.bean;

/**
 * Created by twisty on 14-8-16.
 */
public class OrderModuleItem {
    private String BillName;
    private int BillIcon;

    public String getBillName() {
        return BillName;
    }

    public void setBillName(String billName) {
        BillName = billName;
    }

    public int getBillIcon() {
        return BillIcon;
    }

    public void setBillIcon(int billIcon) {
        BillIcon = billIcon;
    }

    @Override
    public String toString() {
        return "OrderModuleItem{" +
                "BillName='" + BillName + '\'' +
                ", BillIcon=" + BillIcon +
                '}';
    }
}
