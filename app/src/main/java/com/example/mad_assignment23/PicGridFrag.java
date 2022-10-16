package com.example.mad_assignment23;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;


public class PicGridFrag extends Fragment {

    List<Bitmap> bitmapList;
    public PicGridFrag() {
        // Required empty public constructor
    }


    public static PicGridFrag newInstance(String param1, String param2) {
        PicGridFrag fragment = new PicGridFrag();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bitmapList = getArguments().getParcelableArrayList("Image list");
        int i = 0;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_image_view, container, false);
        Button change = v.findViewById(R.id.change_view);
        RecyclerView rv = v.findViewById(R.id.imageRecyc);
        rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        PicGridAdapt imageAdapt = new PicGridAdapt(bitmapList);
        rv.setAdapter(imageAdapt);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();

            }
        });
        return v;
    }
}