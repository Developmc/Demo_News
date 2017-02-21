package com.clement.example.demo_news.http.customrx;

import android.annotation.TargetApi;
import android.util.ArrayMap;

import java.util.Set;

import rx.Subscription;

/**处理真实的取消请求作用，维护RxJava的订阅池
 * Created by clement on 17/2/21.
 */

public class RxApiManager implements RxActionManager<String>{
    private static RxApiManager instance = null;
    private ArrayMap<String,Subscription> maps;

    /**单例模式
     * @return
     */
    public static RxApiManager getInstance(){
        if(instance==null){
            synchronized (RxApiManager.class){
                if(instance==null){
                    instance = new RxApiManager();
                }
            }
        }
        return instance;
    }

    /**
     * 私有构造函数
     */
    @TargetApi(19)
    private RxApiManager(){
        maps = new ArrayMap<>();
    }

    @TargetApi(19)
    @Override
    public void add(String tag, Subscription subscription) {
        maps.put(tag,subscription);
    }

    @TargetApi(19)
    @Override
    public void remove(String tag) {
        if(!maps.isEmpty()){
            maps.remove(tag);
        }
    }

    @TargetApi(19)
    @Override
    public void cancel(String tag) {
        if(maps.isEmpty()){
            return;
        }
        if(maps.get(tag)==null){
            return;
        }
        //如果还没有解绑,则取消网络请求
        if(!maps.get(tag).isUnsubscribed()){
            maps.get(tag).unsubscribe();
            maps.remove(tag);
        }
    }

    @TargetApi(19)
    @Override
    public void cancelAll() {
        if(!maps.isEmpty()){
            Set<String> keys = maps.keySet();
            for(String key :keys){
                cancel(key);
            }
        }
    }

}

