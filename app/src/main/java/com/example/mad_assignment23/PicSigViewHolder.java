package com.example.mad_assignment23;


import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PicSigViewHolder extends RecyclerView.ViewHolder {

    ImageView pic;
    private List<Bitmap> bitmapList;
    public PicSigViewHolder(@NonNull View itemView, List<Bitmap> bitmapList) {
        super(itemView);
        pic = itemView.findViewById(R.id.picture);
        this.bitmapList = bitmapList;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap image = bitmapList.get(getAdapterPosition());
                Toast.makeText(itemView.getContext(),"Adding Image to fireBase",Toast.LENGTH_SHORT).show();
                Upload.uploadPic(itemView.getContext(), image);

            }
        });
    }


}
