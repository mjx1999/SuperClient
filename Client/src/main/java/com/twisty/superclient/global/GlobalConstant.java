package com.twisty.superclient.global;

/**
 * Created by twisty on 14-8-4.
 */
public interface GlobalConstant {
    public static final String DB_NAME = "SuperClientDB";

    public static final int BILL_TYPE_SALES_BILL = 1;
    public static final int BILL_TYPE_SALES_ORDER = 2;
    public static final int BILL_TYPE_TRANSFER = 3;
    public static final int BILL_TYPE_STOCK_CHECK = 4;


    public static final String METHOD_GET_ACC_LIST = "GetAccList";
    public static final String METHOD_LOGIN = "Login";
    public static final String METHOD_DO_BILL = "DoBill";
    public static final String METHOD_GET_ACC_ONHAND = "GetAccOnhand";
    public static final String MEHOTD_DOWNLOAD_LINKMAN = "DownloadLinkman";

    public static final String METHOD_DOWNLOADSYSPARAM = "downloadsysparam";

    public static final String METHOD_DOWNLOAD_OPERATOR = "DownloadOperator";
    public static final String METHOD_DOWNLOAD_TRADER_TYPE = "DownloadTraderType";
    public static final String METHOD_DOWNLOAD_AREA = "DownloadArea";
    public static final String METHOD_DOWNLOAD_DEPARTMENT = "DownloadDepartment";
    public static final String METHOD_DOWNLOAD_EMPLOY = "DownloadEmploy";
    public static final String METHOD_DOWNLOAD_TRADER = "DownloadTrader";
    public static final String METHOD_DOWNLOAD_IO_TYPE = "DownloadIoType";

    public static final String METHOD_DOWNLOAD_STORE = "DownloadStore";

    public static final String METHOD_DOWNLOAD_GDTYPE = "DownloadGDType";

    public static final String METHOD_DOWNLOAD_GOODS = "DownloadGoods";
    public static final String METHOD_DOWNLOAD_GOODS_UNIT = "DownloadGoodsUnit";
    public static final String METHOD_DOWNLOAD_TRADER_PRICE = "DownloadTraderPrice";
    public static final String METHOD_DOWNLOAD_GOODS_PICTURE = "DownloadGoodsPicture";
    public static final String METHOD_DOWNLOAD_ONHAND = "DownloadOnhand";
    public static final String METHOD_DOWNLOAD_ACCOUNT = "DownloadAccount";

    public static final String METHOD_DOWNLOAD_PAYMETHOD = "DownloadPaymethod";

    public static final String MEHTOD_DOWNLOAD_AM_KIND = "DownloadAmKind";

}
