package com.example.mad_assignment23;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import androidx.lifecycle.ViewModelStoreOwner;
public class PicSingleFrag extends Fragment {

    List<Bitmap> bitmapList;
    SharedViewModel viewModel;
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
        viewModel = new ViewModelProvider((ViewModelStoreOwner) getActivity(), (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(SharedViewModel.class);
        //bitmapList =  from view model;
        bitmapList = viewModel.getBitmapList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_image_view, container, false);
        Button change = v.findViewById(R.id.change_view);
        RecyclerView rv = v.findViewById(R.id.imageRecyc);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        PicSingleAdapt picSingleAdapt = new PicSingleAdapt(bitmapList);
        rv.setAdapter(picSingleAdapt);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                PicGridFrag picFrag = new PicGridFrag();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.imageFrag, picFrag).addToBackStack(null).commit();
            }
        });
        return v;
    }
}
