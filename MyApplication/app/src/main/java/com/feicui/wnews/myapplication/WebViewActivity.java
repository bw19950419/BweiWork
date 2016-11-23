package com.feicui.wnews.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * Created by baiwei on 2016/11/17.
 */

public class WebViewActivity extends Activity{
    private ProgressBar pb;
    private WebView wbview;
    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //除去Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web_view);
        //获取网址
        link = getIntent().getStringExtra("link");
        //找到ProgressBar控件和WebView控件
        pb = (ProgressBar) findViewById(R.id.progressBar_news);
        wbview = (WebView) findViewById(R.id.web_View_news);
        wbview.loadUrl(link);
        wbview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        wbview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                pb.setProgress(newProgress);
                pb.setBackgroundColor(Color.GRAY);
                if (pb.getProgress() == 100) {
                    pb.setVisibility(view.GONE);
                }
            }
        });


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && wbview.canGoBack()) {
            wbview.canGoBack();
        } else {
            this.finish();
            return true;
        }
        return false;
    }
}
