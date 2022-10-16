package com.example.mad_assignment23;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PicSingleFrag extends Fragment {

    List<Bitmap> bitmapList;
    public PicSingleFrag() {
        // Required empty public constructor
    }


    public static PicSingleFrag newInstance(String param1, String param2) {
        PicSingleFrag fragment = new PicSingleFrag();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //bitmapList =  from view model;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_image_view, container, false);
        Button change = v.findViewById(R.id.change_view);
        RecyclerView rv = v.findViewById(R.id.imageRecyc);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));


        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();

            }
        });
        return v;
    }
}
