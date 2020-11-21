package com.exercise.nasapictures;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercise.nasapictures.Adapter.ImageListAdapter;
import com.exercise.nasapictures.Data.ListData;
import com.exercise.nasapictures.Data.PictureData;
import com.exercise.nasapictures.databinding.ImagesListBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class ImagesList extends Fragment {

    ImagesListBinding imagesListBinding;
    public ImagesList() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        imagesListBinding= DataBindingUtil.inflate(inflater,R.layout.images_list,container,false);
        View v=imagesListBinding.getRoot();
        Gson gson=new Gson();
        Type listType = new TypeToken<List<PictureData>>(){}.getType();
        List<PictureData> list=gson.fromJson(loadJSONFromAsset(),listType);
        imagesListBinding.imagesList.setHasFixedSize(true);
        imagesListBinding.imagesList.setLayoutManager(new GridLayoutManager(getActivity(),2));
        ImageListAdapter adapter=new ImageListAdapter(getActivity(),list);
        imagesListBinding.imagesList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return v;
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}