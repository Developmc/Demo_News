package com.clement.example.demo_news.http.customrx;

/**和ProgressSubscriber使用，暴露onNext给activity/fragment
 * Created by clement on 17/2/21.
 */

public interface SubscriberOnNextListener<T> {
    void onProgressNext(T t);
}
