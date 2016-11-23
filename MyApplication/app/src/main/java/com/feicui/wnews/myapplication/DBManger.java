package com.feicui.wnews.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baiwei on 2016/11/17.
 */

public class DBManger {
    private static DBHelper dbHelper;
    private static SQLiteDatabase sqLiteDatabase;
    public DBManger(Context context) {
        dbHelper = new DBHelper(context);

    }

    //单例获取DBhelper
    public static DBManger getInstance(Context context) {
        DBManger dbManger = null;
        if (dbManger == null) {
            dbManger = new DBManger(context);
        }
        return dbManger;


    }

   public static boolean selectCount() {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        String sql = "select*from news";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        int count = cursor.getCount();
        if (count > 0) {
            return true;
        }
        return false;

    }


    public static void insertData(NewsInfo news) {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("summary", news.getSummary());
        values.put("nid", news.getNid());
        values.put("icon", news.getIcon());
        values.put("link", news.getLink());
       values.put("type",news.getType());
        values.put("title", news.getTitle());

        sqLiteDatabase.insert("news", null, values);


    }
    public static List<NewsInfo> selectData() {
        sqLiteDatabase = dbHelper.getReadableDatabase();
        String sql = "select*from news";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        List<NewsInfo> list = new ArrayList<NewsInfo>();
        if (cursor.moveToFirst()) {
            do {
                String summary = cursor.getString(cursor.getColumnIndex("summary"));
                int nid = cursor.getInt(cursor.getColumnIndex("nid"));
                String icon = cursor.getString(cursor.getColumnIndex("icon"));
                String link = cursor.getString(cursor.getColumnIndex("link"));
                int type = cursor.getInt(cursor.getColumnIndex("type"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                NewsInfo newsInfo = new NewsInfo(nid, title, icon, link, summary, type);
                list.add(newsInfo);

            } while (cursor.moveToNext());
        }
        return list;
    }


}
