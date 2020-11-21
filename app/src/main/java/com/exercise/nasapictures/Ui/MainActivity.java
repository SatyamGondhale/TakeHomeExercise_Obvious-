package com.exercise.nasapictures.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
        ft.commit();
    }
}