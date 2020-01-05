package com.example.smartclass.util;

import android.content.Context;
import android.preference.PreferenceManager;

/**
 * Created by YangFan
 * On 2019/2/26
 * GitHub: https://github.com/TIYangFan
 * Email: yangfan_98@163.com
 */
public class SharedPreferencesUtil {

    private static final String JOB_NUMBER = "jobNumber";
    private static final String USER_NAME = "userName";

    public static String getStoreJobNumber(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(JOB_NUMBER, null);
    }

    public static void setStoreJobNumber(Context context, String jobNumber){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(JOB_NUMBER, jobNumber)
                .apply();
    }

    public static void clearStoreJobNumber(Context context){

        setStoreJobNumber(context, null);
    }


    public static String getStoreUserName(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(USER_NAME, null);
    }

    public static void setStoreUserName(Context context, String jobNumber){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(USER_NAME, jobNumber)
                .apply();
    }

    public static void clearStoreUserName(Context context){

        setStoreJobNumber(context, null);
    }
}
