package com.exercise.nasapictures.Ui;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.exercise.nasapictures.Data.PictureData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class ImageDetailViewModel extends AndroidViewModel {

    List<PictureData> getNasaPictureList;
    Context context;
    public ImageDetailViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
        Gson gson=new Gson();
        Type listType = new TypeToken<List<PictureData>>(){}.getType();
        getNasaPictureList=gson.fromJson(loadJSONFromAsset(),listType);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("data.json");
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
