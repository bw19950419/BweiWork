package com.feicui.wnews.myapplication;

import android.graphics.Bitmap;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * Created by baiwei on 2016/11/17.
 */

public class ImageManage {
    private ImageLruCache cache;
    private HashMap<String, SoftReference<Bitmap>> map = new HashMap<String, SoftReference<Bitmap>>();
    static ImageManage manage = null;

    private ImageManage() {

    }

    public static ImageManage getInstance() {
        if (manage == null) {
            manage = new ImageManage();
        }
        return manage;

    }

    public ImageLruCache getCache() {
        //获取程序占用最大内存
        int size = (int) Runtime.getRuntime().maxMemory();
        int maxSize = size / 8;

        if (cache == null) {
            cache = new ImageLruCache(maxSize, map);
        }
        return cache;
    }

    /**
     * 从缓存中取图片
     */
    public Bitmap getImage(String key) {
        //从强引用中取图片
        Bitmap bitm = getCache().get(key);
        if (bitm != null) {
            return bitm;
        } else {
            //从软引用中取图片
            SoftReference<Bitmap> softbitmap = map.get(key);
            if (softbitmap != null) {
                Bitmap bitmap = softbitmap.get();


                cache.put(key, bitmap);
                return bitmap;
            } else {
                map.remove(key);
            }

        }
        return null;
    }
}
