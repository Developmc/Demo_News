package com.clement.example.demo_news.mvp.new_fragment.model;

import com.clement.example.demo_news.entity.WxNew;
import com.clement.example.demo_news.http.entity.HttpResult;
import com.clement.example.demo_news.http.manager.RetrofitHttpFactory;
import com.clement.example.demo_news.http.subscriber.BaseSubscriber;

import java.util.List;

/**
 * Created by clement on 16/11/10.
 */

public class NewModelImp {
    /**开始进行网络请求
     * @param subscriber
     * @param num
     * @param page
     */
    public void getWxNew(BaseSubscriber<List<WxNew>> subscriber, int num, int page){
        RetrofitHttpFactory.getInstance().getWxNew(subscriber,num,page);
    }

    public void getObject(BaseSubscriber<HttpResult<Object>> subscriber){
        RetrofitHttpFactory.getInstance().getObject(subscriber);
    }
}
