package com.exercise.nasapictures.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkStatus {
    public static boolean checkConnection(Context context) {
        if(context!=null){
            ConnectivityManager connectivitymanager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(connectivitymanager!=null ){
                if (connectivitymanager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED || connectivitymanager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                } else {
                    return false;
                }
            }else{
                return false;
            }

        }
        return false;
    }
}
