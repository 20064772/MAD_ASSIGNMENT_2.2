package com.example.mad_assignment23;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class PicGridAdapt extends RecyclerView.Adapter<PicViewHolder> {

    List<Bitmap> bitmapList;
    public PicGridAdapt(List<Bitmap> bitmapList){
        this.bitmapList = bitmapList;
    }


    @NonNull
    @Override
    public PicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.pic_item, parent,  false);
        PicViewHolder imageViewHolder = new PicViewHolder(v);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PicViewHolder holder, int position) {
       holder.pic.setImageBitmap(bitmapList.get(position));

    }

    @Override
    public int getItemCount() {
        return bitmapList.size();
    }
}
