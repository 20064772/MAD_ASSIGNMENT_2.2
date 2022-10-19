package com.example.mad_assignment23;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;

public class Upload /*implements Runnable*/ {

    private StorageReference mStorageRef;

    public void uploadPic(Context inContext, Bitmap image) {

        mStorageRef = FirebaseStorage.getInstance().getReference();
        Uri uri = getImageUri(inContext, image);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}
