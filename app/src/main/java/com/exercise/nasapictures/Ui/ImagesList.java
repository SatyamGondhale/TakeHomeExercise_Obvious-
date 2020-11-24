package com.exercise.nasapictures.Ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import com.exercise.nasapictures.utils.AlertClass;
import com.exercise.nasapictures.utils.NetworkStatus;

import java.util.List;

public class ImagesList extends Fragment {

    private FragmentManager fm;
    private FragmentTransaction ft;
    private ImagesListBinding imagesListBinding;
    private ImageListViewModel imageListViewModel;
    private AlertClass alertClass;
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
        alertClass=new AlertClass();
        imagesListBinding.imagesList.setHasFixedSize(true);
        imagesListBinding.imagesList.setLayoutManager(new GridLayoutManager(getActivity(),2));
        if(NetworkStatus.checkConnection(getActivity())){
            List<PictureData> getList=imageListViewModel.getNasaPictureList;
            ImageListAdapter adapter=new ImageListAdapter(getActivity(),getList);
            imagesListBinding.imagesList.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            adapter.onImageClickListener(new ImageListAdapter.onClickImage() {
                @Override
                public void onImageClick(int pos) {
                    ImageDetail imageDetail=new ImageDetail();
                    Bundle bundle=new Bundle();
                    bundle.putInt("position",pos);
                    imageDetail.setArguments(bundle);
                    fm=getActivity().getSupportFragmentManager();
                    ft=fm.beginTransaction();
                    ft.replace(R.id.main_activity_frame,imageDetail);
                    ft.addToBackStack("ImagesList");
                    ft.commit();
                }
            });
        }else{
            if(getActivity()!=null){
                alertClass.noInternetAlert(getActivity());
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