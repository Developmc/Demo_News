package com.clement.example.demo_news.mvp.new_fragment.view;

import android.content.Context;

import com.clement.example.demo_news.entity.WxNew;

import java.util.List;

/**NewFragment 应该有哪些功能
 * Created by clement on 16/11/10.
 */

public interface INewView {
    void getData(int page, boolean isLoadMore);
    void doRefresh(List<WxNew> wxNews);
    void doLoadMore(List<WxNew> wxNews);
    void setRefreshing(boolean refreshing);
    Context getViewContext();
}
