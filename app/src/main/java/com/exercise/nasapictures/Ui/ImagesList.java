package com.exercise.nasapictures.Ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercise.nasapictures.Adapter.ImageListAdapter;
import com.exercise.nasapictures.Data.PictureData;
import com.exercise.nasapictures.R;
import com.exercise.nasapictures.databinding.ImagesListBinding;

import java.util.List;

public class ImagesList extends Fragment {

    ImagesListBinding imagesListBinding;
    ImageListViewModel imageListViewModel;
    public ImagesList() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageListViewModel= new ViewModelProvider.AndroidViewModelFactory(this.getActivity().getApplication()).create(ImageListViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        imagesListBinding= DataBindingUtil.inflate(inflater, R.layout.images_list,container,false);
        View v=imagesListBinding.getRoot();
        imagesListBinding.imagesList.setHasFixedSize(true);
        imagesListBinding.imagesList.setLayoutManager(new GridLayoutManager(getActivity(),2));
        List<PictureData> getList=imageListViewModel.getNasaPictureList;
        ImageListAdapter adapter=new ImageListAdapter(getActivity(),getList);
        imagesListBinding.imagesList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return v;
    }
}