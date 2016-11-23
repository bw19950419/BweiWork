package com.feicui.wnews.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baiwei on 2016/11/21.
 */

public class TitleAdapter extends BaseAdapter{
    private Context context;
    private LayoutInflater layoutInflater;
    private int anInt;
    List list = new ArrayList();



    public TitleAdapter(Context context , List anInt){

        this.list  = anInt;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.listview_tou, null);
            viewHolder.item_boby = (ImageView) view.findViewById(R.id.image_tou);
            viewHolder.tv = (TextView) view.findViewById(R.id.text2);
        }else {
           viewHolder = (ViewHolder) view.getTag();

        }

        viewHolder.tv.setText( list.get(position)+"");
        return view;
    }
    class ViewHolder {
        ImageView item_boby;
        TextView tv ;
    }
}
