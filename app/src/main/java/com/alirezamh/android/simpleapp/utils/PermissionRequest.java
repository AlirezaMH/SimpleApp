package com.alirezamh.android.simpleapp.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.PermissionChecker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:      Alireza Mahmoodi
 * Created:     5/24/2017
 * Email:       mahmoodi.dev@gmail.com
 * Website:     alirezamh.com
 */

public class PermissionRequest {

    private static Map<Integer, Request> requests = new HashMap<>();
    private final Request request = new Request();

    public PermissionRequest (Fragment fragment, String... permissions){
        request.permissions = permissions;
        request.fragment = fragment;
        request.code = (int) Math.round(Math.random() * 10000);
    }
    public PermissionRequest (Activity activity, String... permissions){
        request.permissions = permissions;
        request.activity = activity;
        request.code = (int) Math.round(Math.random() * 10000);
    }


    public static void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requests.containsKey(requestCode)){
            if(isGrantedRequests(grantResults)){
                requests.get(requestCode).granted();
            }else{
                if(requests.get(requestCode).repeat > 0){
                    requests.get(requestCode).resend();
                }else{
                    requests.get(requestCode).notGranted();
                }
            }
        }
    }

    public boolean isGranted(){
        for (String p : request.permissions){
            if(ActivityCompat.checkSelfPermission(request.getContext(), p) != PackageManager.PERMISSION_GRANTED) return false;
        }
        return true;
    }

    public void send() {
        send(1);
    }

    public void send(int repeatCount) {
        request.send(repeatCount-1);
    }

    public void setOnGrantListener(Runnable onGrant){
        request.onGrant = onGrant;
    }

    public void setOnNotGrantListener(Runnable onNotGrant){
        request.onNotGrant = onNotGrant;
    }


    private static boolean isGrantedRequests(@NonNull int[] grantResults){
        for(int g: grantResults){
            if(g != PermissionChecker.PERMISSION_GRANTED) return false;
        }
        return true;
    }

    private static class Request {
        private Activity activity;
        private Fragment fragment;
        private String[] permissions;
        private Runnable onGrant;
        private Runnable onNotGrant;
        private int repeat;
        public int code;

        public void send(int repeatCount) {
            List<String> notGranted = null;
            for (String p : permissions){
                if(ActivityCompat.checkSelfPermission(getContext(), p) != PackageManager.PERMISSION_GRANTED){
                    if(notGranted == null) notGranted = new ArrayList<>();
                    notGranted.add(p);
                }
            }
            if(notGranted == null) return;

            repeat = repeatCount;
            if(!requests.containsKey(code)) requests.put(code, this);
            if(fragment == null){
                ActivityCompat.requestPermissions(activity, notGranted.toArray(new String[0]), code);
            }else{
                fragment.requestPermissions(notGranted.toArray(new String[0]), code);
            }
        }

        public void resend(){
            send(repeat-1);
        }

        public void granted(){
            if(!requests.containsKey(code)) requests.remove(code);
            if(onGrant != null){
                onGrant.run();
            }
        }

        public void notGranted(){
            if(!requests.containsKey(code)) requests.remove(code);
            if(onNotGrant != null){
                onNotGrant.run();
            }
        }

        public Context getContext(){
            if(activity != null) return activity;
            return fragment.getContext();
        }
    }

}