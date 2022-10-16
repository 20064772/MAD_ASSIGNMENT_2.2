package com.example.mad_assignment23;


import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PicViewHolder extends RecyclerView.ViewHolder {

    ImageView pic;
    public PicViewHolder(@NonNull View itemView) {
        super(itemView);

        pic = itemView.findViewById(R.id.picture);
    }
}
