package com.twisty.superclient.util;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Calendar;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * 通用工具类
 *
 * @author twisty
 */
public class CommonUtil {
    static Gson gson;
    private static CommonLog log = LogFactory.createLog();

    public static boolean hasSDCard() {
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

    public static Gson getGson() {
        if (gson == null) gson = new Gson();
        return gson;
    }

    /**
     * 获取时间间隔天数
     *
     * @param timeInMillis
     * @return
     */
    public static int getInterval(long timeInMillis, long currentTimeInMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeInMillis);
        Long timeInMillisInterval = timeInMillis - calendar.getTimeInMillis();
        Float x = Float.valueOf(timeInMillisInterval) / 1000 / 3600 / 24;
        return (int) Math.ceil(x);
    }




    public static String getYMDTime(Long timeStr) {
        if (timeStr == null || timeStr == 0) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStr);
        return calendar.get(Calendar.YEAR) + "年"
                + (calendar.get(Calendar.MONTH) + 1) + "月"
                + calendar.get(Calendar.DAY_OF_MONTH) + "日 ";

    }

    /**
     * 获取人性化的时间格式
     *
     * @param timeStr
     * @return
     */
    public static String getTimeStr(Long timeStr, boolean isOnlyTime) {
        if (timeStr == null || timeStr == 0) {
            return null;
        }
        String retVal = null;
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timeStr);

        Calendar cal2 = Calendar.getInstance();
        long timeMillisSpace = cal2.getTimeInMillis() - cal1.getTimeInMillis();
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        int daySpace = day2 - day1;
        if (isOnlyTime) {
            return cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE);
        }
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        int yearSpace = year2 - year1;
        if (yearSpace >= 1) {
            retVal = cal1.get(Calendar.YEAR) + "年" + (cal1.get(Calendar.MONTH) + 1) + "月" + cal1.get(Calendar.DAY_OF_MONTH) + "日 " + cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE);
        } else if (daySpace > 1) {
            retVal = (cal1.get(Calendar.MONTH) + 1) + "月" + cal1.get(Calendar.DAY_OF_MONTH) + "日 " + cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE);
        } else if (daySpace == 1) {
            retVal = "昨天 " + cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE);
        } else if (daySpace < 1 && timeMillisSpace > 60 * 60 * 1000) {
            retVal = timeMillisSpace / 1000 / 60 / 60 + "小时前";
        } else if (daySpace < 1 && timeMillisSpace < 60 * 60 * 1000 && timeMillisSpace >= 60 * 1000) {
            retVal = timeMillisSpace / 1000 / 60 + "分钟前";
        } else if (daySpace < 1 && timeMillisSpace < 60 * 1000) {
            retVal = "刚才";
        }
        return retVal;
    }

    /**
     * 获取项目路径
     *
     * @return
     */
    public static String getRootFilePath() {
        if (hasSDCard()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath() + "/";// filePath:/sdcard/
        } else {
            return Environment.getDataDirectory().getAbsolutePath() + "/data/"; // filePath: /data/data/
        }
    }

    /**
     * 检查网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean checkNetState(Context context) {
        boolean netstate = false;
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        netstate = true;
                        break;
                    }
                }
            }
        }
        return netstate;
    }


    /**
     * 封装Toast操作
     *
     * @param context
     * @param tip
     */
    public static void showToastInfo(Context context, String tip,ViewGroup viewGroup) {
        if(viewGroup==null){
            Crouton.makeText((android.app.Activity) context, tip, Style.INFO).show();
        }else{
            Crouton.makeText((android.app.Activity) context, tip, Style.INFO,viewGroup).show();
        }
    }


    public static void showToastError(Context context, String tip,ViewGroup viewGroup) {
        if(viewGroup==null){
            Crouton.makeText((android.app.Activity) context, tip, Style.ALERT).show();
        }else{
            Crouton.makeText((android.app.Activity) context, tip, Style.ALERT,viewGroup).show();
        }
    }

    public static void cancelAllToast() {
    }


    public static void showToastNormal(Context context, String tip) {
        Toast.makeText(context, tip, Toast.LENGTH_SHORT).show();
//        if (errToast == null) {
//            errToast = new Toast(context);
//            View view = LayoutInflater.from(context).inflate(R.layout.toast, null);
//            view.setLayoutParams(new LinearLayout.LayoutParams(getScreenWidth(context), getScreenHeight(context)));
//            view.setBackgroundColor(Color.BLUE);
//            TextView tipView = (TextView) view.findViewById(R.id.tip);
//            tipView.setText(tip);
//            errToast.setView(view);
//        } else {
//            TextView tipView = (TextView) errToast.getView().findViewById(R.id.tip);
//            tipView.setText(tip);
//        }
//        errToast.setMargin(0, 0);
//        errToast.setDuration(Toast.LENGTH_LONG);
//        errToast.setGravity(Gravity.TOP, 0, 0);
//        errToast.show();
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

}
