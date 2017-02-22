package com.clement.example.demo_news.http.customrx;

/**监听Dialog取消时间，从而在Dialog取消的时候，取消网络请求
 * Created by clement on 17/2/21.
 */

public interface ProgressCancelListener {
    void onCancelProgress();
}
