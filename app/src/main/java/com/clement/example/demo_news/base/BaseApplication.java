package com.clement.example.demo_news.base;

import android.app.Application;

import com.clement.example.demo_news.http.manager.RetrofitHttpFactory;

/**Base Application
 * Created by clement on 16/11/9.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化retrofit
        RetrofitHttpFactory.getInstance().init();
    }
}
