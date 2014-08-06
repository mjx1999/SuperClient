package com.twisty.superclient.global;

import android.app.Application;
import android.content.Context;

import com.twisty.superclient.bean.DaoMaster;
import com.twisty.superclient.bean.DaoSession;

/**
 * Created by twisty on 14-8-6.
 */
public class SuperClient extends Application {
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    private static boolean isOnline;

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
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context,GlobalConstant.DB_NAME, null);
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
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}
