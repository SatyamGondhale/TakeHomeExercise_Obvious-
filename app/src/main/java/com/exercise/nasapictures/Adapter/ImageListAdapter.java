package com.exercise.nasapictures.Adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exercise.nasapictures.Data.PictureData;
import com.exercise.nasapictures.R;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> {
    List<PictureData> nasa_pictures_list;
    Context context;
    onClickImage onClickImage;

    public interface  onClickImage{
        void onImageClick(int pos);
    }

    public void onImageClickListener(onClickImage onClickImage){
        this.onClickImage=onClickImage;
    }
    public ImageListAdapter(Context context,List<PictureData> nasa_pictures_list){
        this.context=context;
        this.nasa_pictures_list=nasa_pictures_list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_list_layout,parent,false);
        return new ViewHolder(v,onClickImage);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PictureData entry=nasa_pictures_list.get(position);
        Picasso.get().load(entry.getUrl()).placeholder(context.getResources().getDrawable(R.drawable.loading)).fit().centerCrop().into(holder.nasa_image);
    }

    @Override
    public int getItemCount() {
        return nasa_pictures_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView nasa_image;
        public ViewHolder(@NonNull View itemView,final onClickImage onClickImage) {
            super(itemView);
            nasa_image=itemView.findViewById(R.id.nasa_image);
            nasa_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onClickImage!=null){
                        onClickImage.onImageClick(getAdapterPosition());
                    }
                }
            });

        }
    }
}
