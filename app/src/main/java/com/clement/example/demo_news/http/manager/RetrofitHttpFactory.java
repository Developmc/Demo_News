package com.clement.example.demo_news.http.manager;

import com.clement.example.demo_news.entity.WxNew;
import com.clement.example.demo_news.http.entity.HttpResult;
import com.clement.example.demo_news.http.subscriber.BaseSubscriber;

import java.util.List;

import rx.Observable;

/**用于在activity调用网络请求接口
 * Created by clement on 16/11/9.
 */

public class RetrofitHttpFactory extends RetrofitHttp {

    /**
     * 单例模式
     */
    private static class SingletonHolder{
        public static final RetrofitHttpFactory INSTANCE = new RetrofitHttpFactory();
    }
    public static RetrofitHttpFactory getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**获取WxNew下的数据
     * @param subscriber
     * @param num
     */
    public void getWxNew(BaseSubscriber<List<WxNew>> subscriber, int num,int page){
        //创建观察者
        Observable<List<WxNew>> observable = getService().getWxNews(KEY,num,page)
                .map(new HttpResultFunc<List<WxNew>>());
        //订阅
        toSubscribe(observable,subscriber);
    }

    public void getObject(BaseSubscriber<HttpResult<Object>> subscriber){
        //创建观察者
        Observable<HttpResult<Object>> observable = getService().getObject();
        //订阅
        toSubscribe(observable,subscriber);
    }
}
