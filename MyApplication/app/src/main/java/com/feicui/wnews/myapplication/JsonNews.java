package com.feicui.wnews.myapplication;


import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by baiwei on 2016/11/2.
 * 解析数据
 */

public class JsonNews {
    private Context context;

    public JsonNews(Context context) {
        this.context = context;
    }



    //解析新闻数据
    public ArrayList<NewsInfo> parser (String dataStr){
        ArrayList<NewsInfo> newslist = new ArrayList<NewsInfo>();
        try {
            JSONObject jsonObject = new JSONObject(dataStr);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0 ; i<jsonArray.length(); i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                int nid = object.getInt("nid");
                String title = object.getString("title");
                String icon = object.getString("icon");
                String summary = object.getString("summary");

                String link = object.getString("link");
                int type = object.getInt("type");
                NewsInfo newsinfo = new NewsInfo(nid,title,icon,summary,link,type);

                DBManger.getInstance(context).insertData(newsinfo);
                newslist.add(newsinfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newslist;
    }
}
