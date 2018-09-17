package com.alirezamh.android.simpleapp.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Author:      Alireza Mahmoodi
 * Created:     8/18/2018
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */
public final class Utility {
    private static Activity activity;

    public static void init(Activity activity){
        Utility.activity = activity;
    }


    public static boolean isNetworkConnected(Context context) {
        try{
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }catch (Exception ignored){

        }
        return false;
    }

    public static String readStringFromAssets(Context context, String path)
    {
        BufferedReader reader = null;
        String json = "";
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(path)));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                json += mLine;

            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        return json;
    }

    public static float getDisplayRatio(){
        DisplayMetrics outMetrics = getScreenMetric();
        return (float) outMetrics.heightPixels / (float) outMetrics.widthPixels;
    }

    public static float getHeightByRatio(float ratio){
        return getHeightByRatioInPixel(ratio) / getScreenDensity();
    }

    public static float getHeightByRatioInPixel(float ratio){
        return getScreenWidthInPixel() / ratio;
    }

    public static float getWidthByRatio(float ratio){
        return getWidthByRatioInPixel(ratio) / getScreenDensity();
    }

    public static float getWidthByRatioInPixel(float ratio){
        return getScreenHeightInPixel() / ratio;
    }

    public static float getScreenDensity(){
        return activity.getResources().getDisplayMetrics().density;
    }

    public static int dp(int pixel){
        return (int) (getScreenDensity() * pixel);
    }

    public static DisplayMetrics getScreenMetric(){
        DisplayMetrics outMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics;
    }

    public static int getScreenHeightInPixel(){
        return getScreenMetric().heightPixels;
    }
    public static float getScreenHeight(){
        return (float) getScreenHeightInPixel() / getScreenDensity();
    }

    public static int getScreenWidthInPixel(){
        return getScreenMetric().widthPixels;
    }
    public static float getScreenWidth(){
        return (float) getScreenWidthInPixel() / getScreenDensity();
    }
}
