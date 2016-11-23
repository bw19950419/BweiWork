package com.feicui.wnews.myapplication;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baiwei on 2016/11/1.
 */

public class HttpUtils {

    //网络连接的设置方法
    public void httpUtils() {
        //创建对象
        HttpParams params = new BasicHttpParams();
        //设置网络超时链接
        ConnManagerParams.setTimeout(params, 3000);
        //设置网络最大链接数
        ConnManagerParams.setMaxTotalConnections(params, 8);
        HttpConnectionParams.setConnectionTimeout(params, 3000);
        HttpClient mhttpclient = new DefaultHttpClient(params);

    }

    //Get方法
    public static String getHttp(String url) {
        //返回值初始化
        String str = null;

        try {

            //得到HttpGet对象
            HttpGet httpGet = new HttpGet(url);
            //得到HttpClient对象
            HttpClient httpClient = new DefaultHttpClient();
            //发送Get请求
            HttpResponse httpResponse = httpClient.execute(httpGet);
            //获取返回值
            HttpEntity httpEntity = httpResponse.getEntity();
            //转化返回值成String类型
            str = EntityUtils.toString(httpEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getPost(String url, String key, String value) {
        String str = null;

        try {
            //设置超时链接和最大数

            //通过list传值
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            list.add(new BasicNameValuePair(key, value));
            //新建HttpPost对象
            HttpPost httpPost = new HttpPost(url);
            //通过list传值给HttpPost对象
            httpPost.setEntity(new UrlEncodedFormEntity(list, url));
            //新建HttpClient对象
            HttpClient httpClient = new DefaultHttpClient();
            //发送请求POST
            HttpResponse httpResponse = httpClient.execute(httpPost);
            //得到返回值并且转化为String类型
            String result = EntityUtils.toString(httpResponse.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;


    }
}
