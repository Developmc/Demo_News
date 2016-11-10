package com.clement.example.demo_news.entity;

import com.google.gson.annotations.SerializedName;

/**微信公众平台 热门精选实体类
 * Created by clement on 16/11/9.
 */

public class WxNew {

    /**
     * ctime : 2016-11-03
     * title : 妹子杯广州华丽的终结 等着看成都上海
     * description : 电竞Girl
     * picUrl : http://mmbiz.qpic.cn/mmbiz_jpg/2GnTuBBKnvyVV1NA5BZY2aic5NR91iaCSuOQDIVpqoBfTsCYwaBZWcEfEbqn8lIMHHmgwTcP1LGOJA65w8mFaDBA/0?wx_fmt=jpeg
     * url : http://mp.weixin.qq.com/s?__biz=MzIyMTM2MzE5OQ==&mid=2247484226&idx=2&sn=dd5595d6c0450f6331f5a3cef3b888fc&chksm=e83cadd2df4b24c489613388371017a3c3e16d
     */
    @SerializedName("ctime")
    private String time;
    private String title;
    private String description;
    private String picUrl;
    private String url;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
