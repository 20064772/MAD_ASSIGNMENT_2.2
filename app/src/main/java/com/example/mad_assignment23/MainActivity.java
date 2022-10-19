/**************************
 * Author Ryan Mckenney
 * Main Activity for Assignment 2.2
 */





package com.example.mad_assignment23;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    Button loadImage;
    ImageView picture;
    ProgressBar progressBar;
    EditText searchKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadImage = findViewById(R.id.loadImage);
        progressBar = findViewById(R.id.progressBarId);
        searchKey = findViewById(R.id.inputSearch);
        progressBar.setVisibility(View.INVISIBLE);
        loadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchImage();
            }
        });


    }

    public void searchImage() {
        Toast.makeText(MainActivity.this, "Searching starts", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.VISIBLE);
        SearchTask searchTask = new SearchTask(MainActivity.this);
        searchTask.setSearchkey(searchKey.getText().toString());
        Single<String> searchObservable = Single.fromCallable(searchTask);
        searchObservable = searchObservable.subscribeOn(Schedulers.io());
        searchObservable = searchObservable.observeOn(AndroidSchedulers.mainThread());
        searchObservable.subscribe(new SingleObserver<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onSuccess(@NonNull String s) {
                Toast.makeText(MainActivity.this, "Searching Ends", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                loadImage(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Toast.makeText(MainActivity.this, "Searching Error" + e , Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);

            }
        });
    }

    public void loadImage(String response) {
        ImageRetrievalTask imageRetrievalTask = new ImageRetrievalTask(MainActivity.this);
        imageRetrievalTask.setData(response);

        Toast.makeText(MainActivity.this, "Image loading starts", Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.VISIBLE);
        Single<List<Bitmap>> searchObservable = Single.fromCallable(imageRetrievalTask);

        searchObservable = searchObservable.subscribeOn(Schedulers.io());
        searchObservable = searchObservable.observeOn(AndroidSchedulers.mainThread());
        searchObservable.subscribe(new SingleObserver<List<Bitmap>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull List<Bitmap> bitmap) {
                Toast.makeText(MainActivity.this, "Image loading Ends", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                //picture.setVisibility(View.VISIBLE);
                //picture.setImageBitmap(bitmap);
                FragmentManager fm = getSupportFragmentManager();
                Fragment picFrag = (PicGridFrag) fm.findFragmentById(R.id.imageFrag);

                if (picFrag == null){
                    Bundle b = new Bundle();
                    b.putParcelableArrayList("Image list", (ArrayList<? extends Parcelable>) bitmap);
                    picFrag = new PicGridFrag();
                    picFrag.setArguments(b);
                    fm.beginTransaction().add(R.id.imageFrag, picFrag).commit();
                }else{
                    getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    SharedViewModel  viewModel = new ViewModelProvider(picFrag.getActivity(), (ViewModelProvider.Factory) new ViewModelProvider.NewInstanceFactory()).get(SharedViewModel.class);
                    viewModel.changeSet();
                    Bundle b = new Bundle();
                    b.putParcelableArrayList("Image list", (ArrayList<? extends Parcelable>) bitmap);
                    picFrag = new PicGridFrag();
                    picFrag.setArguments(b);
                    fm.beginTransaction().add(R.id.imageFrag, picFrag).commit();
                }

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Toast.makeText(MainActivity.this, "Image loading error, search again", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });


    }
}