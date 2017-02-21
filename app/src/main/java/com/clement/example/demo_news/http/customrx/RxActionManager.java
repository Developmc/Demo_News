package com.clement.example.demo_news.http.customrx;

import rx.Subscription;

/**用于取消网络请求的接口，定义操作的类型
 * Created by clement on 17/2/21.
 */

public interface RxActionManager<T> {

    void add(T tag, Subscription subscription);
    void remove(T tag);

    void cancel(T tag);

    void cancelAll();
}
