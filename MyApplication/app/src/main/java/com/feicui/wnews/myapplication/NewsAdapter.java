package com.feicui.wnews.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baiwei on 2016/11/17.
 */

public class NewsAdapter extends BaseAdapter implements BitmapListener{
    private Context context;
    List<NewsInfo> list = new ArrayList<NewsInfo>();
    private Bitmap bitmap;

    public NewsAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.layout_news_listview_item, null);
            viewHolder.btlayout = (TextView) view.findViewById(R.id.bt_layout);
            viewHolder.zylayout = (TextView) view.findViewById(R.id.zy_layout);
            viewHolder.tblayout = (ImageView) view.findViewById(R.id.tb_layout);
            view.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Bitmap imagebit = ImageManage.getInstance().getImage(list.get(i).getIcon());
        if (imagebit != null) {
            viewHolder.tblayout.setImageBitmap(imagebit);
        } else {
            new NewsAsyncTask(this).execute(list.get(i).getIcon());
            viewHolder.tblayout.setImageBitmap(bitmap);
        }


        viewHolder.btlayout.setText(list.get(i).getTitle());
        Log.e("---------------------",  list.get(i).getSummary()+"" );
        viewHolder.zylayout.setText(list.get(i).getSummary());


        return view;
    }

    @Override
    public void getBitmapData(Bitmap result) {
        this.bitmap = result;
    }


    public class ViewHolder {
        TextView btlayout;
        TextView zylayout;
        ImageView tblayout;
    }

    public void setList(List list) {
        this.list = list;
    }
}
