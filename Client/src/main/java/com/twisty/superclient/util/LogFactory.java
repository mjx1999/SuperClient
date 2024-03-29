package com.twisty.superclient.util;


/**
 * 日志工程,确保Log单例
 *
 * @author twisty
 */
public class LogFactory {
    private static final String TAG = "SuperClient";
    private static CommonLog log = null;

    public static CommonLog createLog() {
        if (log == null) {
            log = new CommonLog();
        }

        log.setTag(TAG);
        return log;
    }

    public static CommonLog createLog(String tag) {
        if (log == null) {
            log = new CommonLog();
        }

        if (tag == null || tag.length() < 1) {
            log.setTag(TAG);
        } else {
            log.setTag(tag);
        }
        return log;
    }
}