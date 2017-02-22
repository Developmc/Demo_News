package com.clement.example.demo_news.http.subscriber;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.clement.example.demo_news.http.customrx.ProgressCancelListener;
import com.clement.example.demo_news.http.customrx.SubscriberOnNextListener;

import java.lang.ref.WeakReference;

/**自定义Subscriber,在请求开始前，弹框；请求结束后，自动关闭弹框
 * Created by clement on 17/2/21.
 */

public abstract class ProgressSubscriber<T> extends BaseSubscriber<T> implements
        SubscriberOnNextListener<T>,ProgressCancelListener{
    private WeakReference<Context> weakReference;
    private ProgressDialog dialog;
    public ProgressSubscriber(Context context){
        weakReference = new WeakReference<Context>(context);
    }

    @Override
    public void onStart() {
        super.onStart();
        //在请求开始打开弹框
        showDialog("打开弹框");
    }

    @Override
    public void onCompleted() {
        //在请求完成关闭弹框
        dismissDialog();
    }

    @Override
    public void onError(Throwable e) {
        //在请求异常的情况下关闭弹框
        dismissDialog();
    }

    @Override
    public void onNext(T t) {
        //执行回调，暴露给外部activity/fragment使用
        onProgressNext(t);
    }

    @Override
    public void onCancelProgress() {
        //取消订阅网络请求
        if(!this.isUnsubscribed()){
            this.unsubscribe();
        }
    }

    /**从虚引用中获取Context
     * @return
     */
    private Context getContext(){
        if(weakReference!=null){
            return weakReference.get();
        }
        return null;
    }

    /**模拟打开弹框
     * @param content
     */
    private void showDialog(String content){
        if(getContext()!=null){
            if(dialog==null){
                dialog = ProgressDialog.show(getContext(),null,content);
                dialog.setCancelable(true);
                dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        //通知取消网络订阅
                        onCancelProgress();
                    }
                });
            }
        }
    }

    /**
     * 模拟关闭弹框
     */
    private void dismissDialog(){
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
