package com.example.mad_assignment23;

import android.graphics.Bitmap;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {

    List<Bitmap> bitmapList;
    Boolean set;

    public SharedViewModel(){
        bitmapList = null;
        set = false;
    }

    public void setData(List<Bitmap> bitmapList){
        this.bitmapList = bitmapList;
        set = true;
    }

    public List<Bitmap> getBitmapList() {
        return bitmapList;
    }

    public Boolean isSet() {
        return set;
    }
}
