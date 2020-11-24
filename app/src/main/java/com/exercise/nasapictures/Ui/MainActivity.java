package com.exercise.nasapictures.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import com.exercise.nasapictures.R;
import com.exercise.nasapictures.Ui.ImagesList;
import com.exercise.nasapictures.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;
    FragmentManager fm;
    FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        activityMainBinding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
        ft.replace(R.id.main_activity_frame,new ImagesList());
        ft.addToBackStack("ImagesList");
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fManager=MainActivity.this.getSupportFragmentManager();
        if(fManager.getBackStackEntryCount()>0) {
            //   fManager.popBackStack();
            Fragment f = fManager.findFragmentById(R.id.main_activity_frame);
            if(f instanceof ImagesList){
                exitAppFlow();
            }else {
                super.onBackPressed();
            }
        }
    }

    private void exitAppFlow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do you want to exit & close the app ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}