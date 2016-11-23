package com.feicui.wnews.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baiwei on 2016/11/23.
 */
public class SxAdapter extends BaseAdapter {
    private List<String> list = new ArrayList<String>();

    private Context context;

    public SxAdapter(MainActivity mainActivity) {

        this.context = mainActivity;

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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder hodler = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_item, null);
            hodler = new ViewHolder();
            hodler.tv = (TextView) convertView.findViewById(R.id.textView);

            convertView.setTag(hodler);
        } else {

            hodler = (ViewHolder) convertView.getTag();

        }

        hodler.tv.setText(list.get(position));
        return convertView;
    }

    class ViewHolder {

        TextView tv;


    }

    public void setListData(List<String> list) {
        this.list = list;
    }
}
