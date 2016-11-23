package com.feicui.wnews.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baiwei on 2016/11/17.
 */

public class FragmentNews extends Fragment {

    private String url;
    private String data;
    private PullToRefreshListView listviewnews;
    private NewsAdapter newsAdapter;
    private ArrayList<NewsInfo> newlist;



    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            senfmessage(msg);
        }
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);


        listviewnews = (PullToRefreshListView) view.findViewById(R.id.listview_news);
        listviewnews.setMode(PullToRefreshBase.Mode.BOTH);
        listviewnews.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                // 显示加载的最后时间
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

                // Update the LastUpdatedLabel
                listviewnews.getLoadingLayoutProxy().setLastUpdatedLabel(label);


                loadData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                loadData();
            }
        });
        newsAdapter = new NewsAdapter(getActivity());
        listviewnews.setAdapter(newsAdapter);

        listviewnews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                NewsInfo newsInfo = newlist.get(position-1);
                String link = newsInfo.getLink();
                intent.putExtra("link", link);
                startActivity(intent);


            }
        });




        //判断数据库是否为空
        if (DBManger.getInstance(getActivity()).selectCount()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    List list = DBManger.getInstance(getActivity()).selectData();

                    Message msg = handler.obtainMessage();
                    msg.what = 1;
                    msg.obj = list;
                    handler.sendMessage(msg);
                }
            }).start();
        } else {
            loadData();
        }
        return view;
    }


    //网络获取，数据解析
    private void loadData() {
        url = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20";
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<NewsInfo> list = null;
                try {
                    data = HttpUtils.getHttp(url);

                    JsonNews jsonNews = new JsonNews(getActivity());
                    list = jsonNews.parser(data);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                Message msg = handler.obtainMessage();
                msg.what = 2;
                msg.obj = list;
                handler.sendMessage(msg);
            }

        }).start();

    }

    private void senfmessage(Message msg) {
        if (msg.what == 1) {
            newlist = (ArrayList<NewsInfo>) msg.obj;
            newsAdapter.setList(newlist);
            newsAdapter.notifyDataSetChanged();
            listviewnews.onRefreshComplete();
        }
        if (msg.what == 2) {
            newlist = (ArrayList<NewsInfo>) msg.obj;
            newsAdapter.setList(newlist);
            newsAdapter.notifyDataSetChanged();
            listviewnews.onRefreshComplete();

        }

    }

}
