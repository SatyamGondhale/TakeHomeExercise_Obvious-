package com.exercise.nasapictures.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by SatyaBhai on 23-Apr-18.
 */

public class AlertClass {

        public void noInternetAlert(Activity activity)
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(activity);
            builder.setTitle("No Internet Connection");
            builder.setMessage("You need to have Mobile data or Wifi to access this.");
            builder.setCancelable(false);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    activity.finish();
                }
            });
            builder.show();
        }
}
