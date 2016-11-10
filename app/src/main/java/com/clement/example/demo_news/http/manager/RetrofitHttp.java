package com.clement.example.demo_news.http.manager;

import android.content.Context;

import com.clement.example.demo_news.http.entity.HttpResult;
import com.clement.example.demo_news.http.subscriber.BaseSubscriber;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**工具类,用来配置Retrofit和OKHttp
 * Created by clement on 16/11/9.
 */

public class RetrofitHttp {
    //数据来源:天性数据 http://www.tianapi.com/
    private static final String BASE_URL = "http://api.tianapi.com/";
    static final String KEY = "6e29b23558394a440e908dc10183c563" ;
    //请求的接口对象
    public NewsService service;
    //retrofit对象
    private static Retrofit retrofit = null;
    //OKHttpClient对象
    private static OkHttpClient okHttpClient = null;
    private Context mContext ;
    //是否缓存
    private boolean isUseCache ;
    private int maxCacheTime = 60;
    public void setMaxCacheTime(int maxCacheTime){
        this.maxCacheTime = maxCacheTime ;
    }
    public void setUseCache(boolean useCache){
        this.isUseCache = useCache ;
    }

    /**获取请求服务的对象
     * @return
     */
    public NewsService getService(){
        if(service ==null && retrofit!=null){
            service = retrofit.create(NewsService.class);
        }
        return service;
    }

    /**在application中初始化
     * @param context
     */
    public void init(Context context){
        this.mContext = context ;
        initOkHttp();
        initRetrofit();
        //初始化APIService
        if(service ==null && retrofit!=null){
            service = retrofit.create(NewsService.class);
        }
    }

    /**
     * 初始化配置OkHttpClient
     */
    private void initOkHttp(){
        //获取build
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //打印日志
//        if(Debug.isDebuggerConnected()){
//            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            builder.addInterceptor(loggingInterceptor);
//        }
        //设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20,TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        okHttpClient = builder.build() ;
    }

    /**
     * 初始化retrofit
     */
    private void initRetrofit(){
        //配置retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    /**用于绑定观察者和被观察者
     * 网络请求在Schedulers.io线程,UI操作在主线程
     * @param observable
     * @param subscriber
     * @param <T>
     */
    public <T> void toSubscribe(Observable<T> observable, BaseSubscriber<T> subscriber){
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**统一处理http的resultCode,并返回HttpResult中的data数据
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    public class HttpResultFunc<T> implements Func1<HttpResult<T>,T> {
        @Override
        public T call(HttpResult<T> tHttpResult) {
            //如果请求失败
            if(!tHttpResult.isSuccess()){
                //TODO
            }
            return tHttpResult.getData();
        }
    }
}
