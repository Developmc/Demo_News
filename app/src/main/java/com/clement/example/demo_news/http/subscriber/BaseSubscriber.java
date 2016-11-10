package com.clement.example.demo_news.http.subscriber;

import rx.Subscriber;

/**BaseSubscriber
 * Created by clement on 16/11/9.
 */

public class BaseSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }
}
