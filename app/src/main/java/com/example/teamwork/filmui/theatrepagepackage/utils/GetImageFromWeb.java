package com.example.teamwork.filmui.theatrepagepackage.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetImageFromWeb {

    public static void setImageView(final String path, final ImageView imageView, final Activity activity){
        new Thread(){
            @Override
            public void run() {
                try {
                    URL url=new URL(path);
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(5000);
                    int code=httpURLConnection.getResponseCode();
                    if (code==200){
                        int len=-1;
                        InputStream inputStream=httpURLConnection.getInputStream();
                        final Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                            }
                        });
                    }
                    else {
                        return;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
