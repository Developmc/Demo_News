package com.clement.example.demo_news.navigation.wx_new;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.clement.example.demo_news.R;
import com.clement.example.demo_news.base.BaseFragment;
import com.clement.example.demo_news.entity.WxNew;
import com.clement.example.demo_news.http.manager.RetrofitHttpFactory;
import com.clement.example.demo_news.http.subscriber.BaseSubscriber;
import com.clement.example.demo_news.recycleview.OnItemClickListener;

import java.util.List;

import butterknife.BindView;

/**展示WxNews
 * Created by clement on 16/11/9.
 */

public class WxNewFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private WxNewAdapter adapter;
    private int page = 1 ;
    //是否正在加载更多
    private boolean loading = false ;
    @Override
    protected int getFragmentLayoutResId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initVariables() {
        adapter = new WxNewAdapter(getActivity(),null);
    }

    @Override
    protected void initViews() {
        //设置布局
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //设置分割线
        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
        //设置间距
        //recyclerView.addItemDecoration(new SpaceItemDecoration(16));
        //设置item动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //设置adapter
        recyclerView.setAdapter(adapter);
        //设置下拉刷新动画的颜色
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE);
    }

    @Override
    protected void initBehavior() {
        //开始网络请求
        getData(page,false);
        //点击事件
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                //用Bundle携带数据
                Bundle bundle = new Bundle();
                bundle.putString("picUrl",adapter.getList().get(position).getPicUrl());
                bundle.putString("url",adapter.getList().get(position).getUrl());
                intent.putExtras(bundle);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    getActivity(),view.findViewById(R.id.iv_preview),getString(R.string.transition_preview_name));
                ActivityCompat.startActivity(getActivity(),intent,options.toBundle());
            }
        });
        //下拉刷新事件
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新,默认请求第一页数据
                page = 1;
                //获取数据
                getData(page,false);
            }
        });

        //添加滑动事件,用于处理加载更多
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //当前item的总数
                int totalItemCount = layoutManager.getItemCount();
                //最后一个可见item位置的position
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if (!loading && totalItemCount < (lastVisibleItem + 2)) {
                    page++;
                    //请求数据
                    getData(page,true);
                    loading = true;
                }
            }
        });
    }


    /**
     * 开始网络请求获取数据
     * @param page 指定请求的位置
     * @param isLoadMore false表示下拉刷新:,true:表示加载更多
     */
    private void getData(int page, final boolean isLoadMore){
        //如果是刷新数据
        if(!isLoadMore){
            //显示下拉刷新的动画
            swipeRefreshLayout.setRefreshing(true);
        }
        //网络请求
        BaseSubscriber<List<WxNew>> subscriber = new BaseSubscriber<List<WxNew>>(){
            @Override
            public void onCompleted() {
                super.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

            @Override
            public void onNext(List<WxNew> wxNews) {
                super.onNext(wxNews);
                if(isLoadMore){
                    doLoadMore(wxNews);
                }
                else{
                    doRefresh(wxNews);
                }
            }
        };
        //开始进行网络请求
        RetrofitHttpFactory.getInstance().getWxNew(subscriber,10,page);
    }

    /**
     * 刷新
     */
    private void doRefresh(List<WxNew> wxNews){
        //清除原来的数据
        adapter.getList().clear();
        //网络请求成功
        adapter.getList().addAll(wxNews);
        //刷新
        adapter.notifyDataSetChanged();
        //取消下拉刷新的动画
        swipeRefreshLayout.setRefreshing(false);
    }
    /**加载更多
     */
    private void doLoadMore(List<WxNew> wxNews){
        if(!wxNews.isEmpty()){
            //删除footer
            adapter.getList().remove(adapter.getList().size()-1);
            //添加数据
            adapter.getList().addAll(wxNews);
            //刷新
            adapter.notifyDataSetChanged();
            loading = false ;
        }
    }
}
