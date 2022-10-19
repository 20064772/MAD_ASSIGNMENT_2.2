/**************************
 * Author Ryan Mckenney
 * Image Rerevial class used by rxJava
 * Code Taken from lecture 9, writen by Sajib
 */


package com.example.mad_assignment23;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ImageRetrievalTask implements Callable<List<Bitmap>> {
    private Activity uiActivity;
    private String data;
    private RemoteUtilities remoteUtilities;
    public ImageRetrievalTask(Activity uiActivity) {
        remoteUtilities = RemoteUtilities.getInstance(uiActivity);
        this.uiActivity=uiActivity;
        this.data = null;
    }
    @Override
    public List<Bitmap> call() throws Exception {
        List<Bitmap> image = null;
        List<String> endpoint = getEndpoint(this.data);
        if(endpoint==null){
            uiActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(uiActivity,"No image found",Toast.LENGTH_LONG).show();
                }
            });
        }
        else {
            image = getImageFromUrl(endpoint);
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
            }

        }
        return image;
    }

    private List<String> getEndpoint(String data){
        List<String> imageUrl = new ArrayList<>();
        try {
            JSONObject jBase = new JSONObject(data);
            JSONArray jHits = jBase.getJSONArray("hits");
            if(jHits.length()>0){
                int x = 0;
                int y;
                if (jHits.length() > 15){
                    y = 15;
                }
                else{
                    y = jHits.length();
                }

                while(x < y){
                    JSONObject jHitsItem = jHits.getJSONObject(x);
                    imageUrl.add(jHitsItem.getString("largeImageURL"));
                    x++;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return imageUrl;
    }

    private List<Bitmap> getImageFromUrl(List<String> imageUrl){
        List<Bitmap> image = new ArrayList<>();
        for (int x = 0; x < imageUrl.size(); x++) {
            Uri.Builder url = Uri.parse(imageUrl.get(x)).buildUpon();
            String urlString = url.build().toString();
            HttpURLConnection connection = remoteUtilities.openConnection(urlString);
            if (connection != null) {
                if (remoteUtilities.isConnectionOkay(connection) == true) {
                    image.add(getBitmapFromConnection(connection));
                    connection.disconnect();
                }
            }
        }
        return image;
    }


    //shouldnt need to change this
    public Bitmap getBitmapFromConnection(HttpURLConnection conn){
        Bitmap data = null;
        try {
            InputStream inputStream = conn.getInputStream();
            byte[] byteData = getByteArrayFromInputStream(inputStream);
            data = BitmapFactory.decodeByteArray(byteData,0,byteData.length);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return data;
    }

    private byte[] getByteArrayFromInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[4096];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        return buffer.toByteArray();
    }

    public void setData(String data) {
        this.data = data;
    }
}
