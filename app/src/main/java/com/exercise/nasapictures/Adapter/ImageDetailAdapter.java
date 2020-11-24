package com.exercise.nasapictures.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.exercise.nasapictures.Data.PictureData;
import com.exercise.nasapictures.R;
import com.exercise.nasapictures.databinding.ImageDetailLayoutBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageDetailAdapter extends RecyclerView.Adapter<ImageDetailAdapter.ViewHolder> {
    List<PictureData> nasa_pictures_list;
    Context context;
    public ImageDetailAdapter(Context context,List<PictureData> nasa_pictures_list){
        this.context=context;
        this.nasa_pictures_list=nasa_pictures_list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageDetailLayoutBinding v= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.image_detail_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PictureData entry=nasa_pictures_list.get(position);
        holder.bind(entry);
    }

    @Override
    public int getItemCount() {
        return nasa_pictures_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageDetailLayoutBinding imageDetailLayoutBinding;
        TextView title, date, copyright, description;
        ImageView image_source;
        public ViewHolder(@NonNull ImageDetailLayoutBinding  itemView) {
            super(itemView.getRoot());
            this.imageDetailLayoutBinding=itemView;
        }

        public void bind(PictureData entry) {
            imageDetailLayoutBinding.imageTitle.setText(entry.getTitle());
            imageDetailLayoutBinding.imageDate.setText(entry.getDate());
            imageDetailLayoutBinding.imageCopyright.setText(entry.getCopyright());
            imageDetailLayoutBinding.imageExplanation.setText(entry.getExplanation());
            Picasso.get().load(entry.getUrl()).placeholder(context.getResources().getDrawable(R.drawable.loading)).fit().centerCrop().into(imageDetailLayoutBinding.imageSource);
        }
    }
}
