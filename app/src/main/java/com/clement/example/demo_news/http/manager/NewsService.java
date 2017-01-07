package com.clement.example.demo_news.http.manager;

import com.clement.example.demo_news.entity.WxNew;
import com.clement.example.demo_news.http.entity.HttpResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**封装所有的API请求
 * Created by clement on 16/11/9.
 */

public interface NewsService {
    @GET("wxnew/")
    Observable<HttpResult<List<WxNew>>> getWxNews(@Query("key") String key,@Query("num") int num,@Query("page") int page);
    @GET("https://192.168.10.94:8443/")
    Observable<HttpResult<Object>> getObject();
}
