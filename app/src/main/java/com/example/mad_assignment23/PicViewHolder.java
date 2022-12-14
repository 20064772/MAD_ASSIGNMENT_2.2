package com.example.mad_assignment23;


import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PicViewHolder extends RecyclerView.ViewHolder {

    ImageView pic;
    private List<Bitmap> bitmapList;
    public PicViewHolder(@NonNull View itemView, List<Bitmap> bitmapList) {
        super(itemView);
        pic = itemView.findViewById(R.id.picture);
        this.bitmapList = bitmapList;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap image = bitmapList.get(getAdapterPosition());
                Upload.uploadPic(itemView.getContext(), image);
            }
        });
    }


}
