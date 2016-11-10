package com.clement.example.demo_news.navigation.wx_new;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.clement.example.demo_news.R;
import com.clement.example.demo_news.base.BaseActivity;

import butterknife.BindView;

public class DetailActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar ;
    @BindView(R.id.webView)
    WebView webView ;
    @BindView(R.id.iv_preview)
    ImageView ivPreview ;
    private String picUrl,url;
    @Override
    protected int getLayoutViewId() {
        return R.layout.activity_wx_new_detail;
    }

    @Override
    protected void initVariables() {
        Bundle bundle = getIntent().getExtras();
        picUrl = bundle.getString("picUrl");
        url = bundle.getString("url");
    }

    @Override
    protected void initViews() {
        toolbar.setTitle(R.string.new_detail);
        setSupportActionBar(toolbar);
        //设置返回箭头
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void initBehavior() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(url);
        Glide.with(this)
                .load(picUrl).error(R.mipmap.ic_launcher)
                .fitCenter().into(ivPreview);
    }

}
