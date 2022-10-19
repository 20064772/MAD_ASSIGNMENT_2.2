/**************************
 * Author Ryan Mckenney
 * this class hold the code for uploading to fireBase data base
 * contains code as recommended by Google and firebase
 */



package com.example.mad_assignment23;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class Upload /*implements Runnable*/ {

    private static StorageReference mStorageRef;

    public static void uploadPic(Context inContext, Bitmap image) {

        mStorageRef = FirebaseStorage.getInstance().getReference();
        Uri uri = getImageUri(inContext, image);
        Context c = inContext;
        addToFireBase(uri, c);

    }

    private static void addToFireBase(Uri uri, Context c) {

        StorageReference riversRef = mStorageRef.child("images/demoAdd.jpg");

        riversRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content

                        String downloadUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                        Toast.makeText(c,"Image added to " + downloadUrl,Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {

                        Toast.makeText(c,"Unable to upload Image",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}
