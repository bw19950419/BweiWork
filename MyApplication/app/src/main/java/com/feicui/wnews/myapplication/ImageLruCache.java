package com.feicui.wnews.myapplication;

import android.graphics.Bitmap;
import android.util.LruCache;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * Created by baiwei on 2016/11/17.
 */

public class ImageLruCache extends LruCache<String , Bitmap>{
    HashMap<String, SoftReference<Bitmap>> map = new HashMap<String, SoftReference<Bitmap>>();
    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     * @param map
     */
    public ImageLruCache(int maxSize, HashMap<String, SoftReference<Bitmap>> map) {
        super(maxSize);
        this.map = this.map;
    }
    //强引用添加图片大小

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getByteCount();
    }
    //移除图片，放入引用

    @Override
    protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
        if (evicted){
            SoftReference<Bitmap> softReference = new SoftReference<Bitmap>(oldValue);
            map.put(key , softReference);
        }
    }
}
