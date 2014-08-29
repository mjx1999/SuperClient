package com.twisty.superclient.global;

import android.app.Application;
import android.content.Context;

import com.twisty.superclient.bean.Accset;
import com.twisty.superclient.bean.DaoMaster;
import com.twisty.superclient.bean.DaoSession;
import com.twisty.superclient.bean.Operator;
import com.twisty.superclient.bean.Request;

import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by twisty on 14-8-6.
 */
public class SuperClient extends Application {
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private static boolean isOnline;
    private static Accset currentAccset;
    private static Operator currentOperator;
    private static Long defaultStoreID;
    private static String defaultStoreCode;
    private static String currentIP;
    private static int currentPort;
    private static Request currentLoginRequest;
    private static String companyName;

    public static String getCompanyName() {
        return companyName;
    }

    public static void setCompanyName(String companyName) {
        SuperClient.companyName = companyName;
    }

    public static Request getCurrentLoginRequest() {
        return currentLoginRequest;
    }

    public static void setCurrentLoginRequest(Request currentLoginRequest) {
        SuperClient.currentLoginRequest = currentLoginRequest;
    }

    public static String getCurrentIP() {
        return currentIP;
    }

    public static void setCurrentIP(String currentIP) {
        SuperClient.currentIP = currentIP;
    }

    public static int getCurrentPort() {
        return currentPort;
    }

    public static void setCurrentPort(int currentPort) {
        SuperClient.currentPort = currentPort;
    }

    public static String getDefaultStoreCode() {
        return defaultStoreCode;
    }

    public static void setDefaultStoreCode(String defaultStoreCode) {
        SuperClient.defaultStoreCode = defaultStoreCode;
    }

    public static Accset getCurrentAccset() {
        return currentAccset;
    }

    public static void setCurrentAccset(Accset currentAccset) {
        SuperClient.currentAccset = currentAccset;
    }

    public static Operator getCurrentOperator() {
        return currentOperator;
    }

    public static void setCurrentOperator(Operator currentOperator) {
        SuperClient.currentOperator = currentOperator;
    }

    public static Long getDefaultStoreID() {
        return defaultStoreID;
    }

    public static void setDefaultStoreID(Long defaultStoreID) {
        SuperClient.defaultStoreID = defaultStoreID;
    }

    public static boolean getIsOnline() {
        return isOnline;
    }

    public static void setIsOnline(boolean isOnline) {
        SuperClient.isOnline = isOnline;
    }

    /**
     * 取得DaoMaster
     *
     * @param context
     * @return
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context, GlobalConstant.DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @param context
     * @return
     */
    public static DaoSession getDaoSession(Context context) {
        QueryBuilder.LOG_SQL = true;
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}
