package com.feicui.wnews.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by baiwei on 2016/11/17.
 */
public class NewsAsyncTask extends AsyncTask<String, Void, Bitmap> {
    private BitmapListener bitmapListener;
    private String imagelru;

    public NewsAsyncTask(NewsAdapter newsAdapter) {
        this.bitmapListener = (BitmapListener) newsAdapter;

    }


    @Override
    protected Bitmap doInBackground(String... params) {
        imagelru = params[0];


        Bitmap bitmap = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);

        if (result != null) {
            bitmapListener.getBitmapData(result);
            ImageManage.getInstance().getCache().put(imagelru, result);

        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
