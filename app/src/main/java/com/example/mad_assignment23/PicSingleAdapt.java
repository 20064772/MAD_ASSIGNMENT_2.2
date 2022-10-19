package com.example.mad_assignment23;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PicSingleAdapt extends RecyclerView.Adapter<PicSigViewHolder> {

    private List<Bitmap> bitmapList;
    public PicSingleAdapt(List<Bitmap> bitmapList){
        this.bitmapList = bitmapList;
    }

    @NonNull
    @Override
    public PicSigViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.pic_item_large, parent,  false);
        PicSigViewHolder picSigViewHolder = new PicSigViewHolder(v, bitmapList);
        return picSigViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PicSigViewHolder holder, int position) {
        holder.pic.setImageBitmap(bitmapList.get(position));
    }

    @Override
    public int getItemCount() {
        return bitmapList.size()  ;
    }
}
