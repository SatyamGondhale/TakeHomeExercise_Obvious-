package com.exercise.nasapictures.Ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercise.nasapictures.Adapter.ImageDetailAdapter;
import com.exercise.nasapictures.Adapter.ImageListAdapter;
import com.exercise.nasapictures.Data.PictureData;
import com.exercise.nasapictures.R;
import com.exercise.nasapictures.databinding.ImageDetailBinding;
import com.exercise.nasapictures.utils.NetworkStatus;
import com.exercise.nasapictures.utils.RecyclerViewItemSnapHelper;

import java.util.List;


public class ImageDetail extends Fragment {

    ImageDetailBinding imageDetailBinding;
    ImageDetailViewModel imageDetailViewModel;
    int position;
    public ImageDetail() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageDetailViewModel= new ViewModelProvider.AndroidViewModelFactory(this.getActivity().getApplication()).create(ImageDetailViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        imageDetailBinding= DataBindingUtil.inflate(inflater, R.layout.image_detail,container,false);
        View v=imageDetailBinding.getRoot();
        if(getArguments()!=null){
            position=getArguments().getInt("position",0);
        }
        imageDetailBinding.imageDetailLayout.setHasFixedSize(true);
        imageDetailBinding.imageDetailLayout.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        if(NetworkStatus.checkConnection(getActivity())){
            List<PictureData> getList=imageDetailViewModel.getNasaPictureList;
            ImageDetailAdapter adapter=new ImageDetailAdapter(getActivity(),getList);
            imageDetailBinding.imageDetailLayout.scrollToPosition(position);
            LinearSnapHelper linearSnapHelper = new RecyclerViewItemSnapHelper();
            linearSnapHelper.attachToRecyclerView(imageDetailBinding.imageDetailLayout);
            imageDetailBinding.imageDetailLayout.setAdapter(adapter);
        }else{
           if(getActivity()!=null){
               noInternetAlert();
           }
        }

        return v;
    }

    private void noInternetAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Please check your Internet settings & try again.");
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(getActivity()!=null){
                    getActivity().finish();
                }
            }
        });
        builder.show();
    }
}