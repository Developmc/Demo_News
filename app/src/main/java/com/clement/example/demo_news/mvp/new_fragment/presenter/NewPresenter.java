package com.clement.example.demo_news.mvp.new_fragment.presenter;

import com.clement.example.demo_news.entity.WxNew;
import com.clement.example.demo_news.http.subscriber.ProgressSubscriber;
import com.clement.example.demo_news.mvp.new_fragment.model.NewModelImp;
import com.clement.example.demo_news.mvp.new_fragment.view.INewView;

import java.util.List;

/**
 * Created by clement on 16/11/10.
 */

public class NewPresenter {
    //持有view对象
    private INewView newView;
    //持有model对象
    private NewModelImp modelImp;
    public NewPresenter(INewView newView){
        this.newView = newView ;
        modelImp = new NewModelImp();
    }

    /**
     *@param page 指定请求的位置
     *@param isLoadMore false表示下拉刷新:,true:表示加载更多
     */
    public void getData(int page,final boolean isLoadMore){
        //如果是刷新数据
        if(!isLoadMore){
            //显示下拉刷新的动画
            newView.setRefreshing(true);
        }
        //网络请求
//        BaseSubscriber<List<WxNew>> subscriber = new BaseSubscriber<List<WxNew>>(){
//            @Override
//            public void onCompleted() {
//                super.onCompleted();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//            }
//
//            @Override
//            public void onNext(List<WxNew> wxNews) {
//                super.onNext(wxNews);
//                if(isLoadMore){
//                    newView.doLoadMore(wxNews);
//                }
//                else{
//                    newView.doRefresh(wxNews);
//                }
//            }
//        };
        ProgressSubscriber<List<WxNew>> subscriber = new ProgressSubscriber<List<WxNew>>(newView.getViewContext()){
            @Override
            public void onProgressNext(List<WxNew> wxNews) {
                if(isLoadMore){
                    newView.doLoadMore(wxNews);
                }
                else{
                    newView.doRefresh(wxNews);
                }
            }
        };
        //开始进行网络请求
        modelImp.getWxNew(subscriber,10,page);

    }
}
