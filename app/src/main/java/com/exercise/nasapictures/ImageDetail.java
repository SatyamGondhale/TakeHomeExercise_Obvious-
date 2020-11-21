package com.exercise.nasapictures;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercise.nasapictures.databinding.ImageDetailBinding;


public class ImageDetail extends Fragment {

    ImageDetailBinding imageDetailBinding;
    public ImageDetail() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        imageDetailBinding= DataBindingUtil.inflate(inflater,R.layout.image_detail,container,false);
        View v=imageDetailBinding.getRoot();
        return v;
    }
}