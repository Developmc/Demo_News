package com.clement.example.demo_news.http.entity;

import com.clement.example.demo_news.http.util.Constant;
import com.google.gson.annotations.SerializedName;

/**解析请求回来的数据
 * Created by clement on 16/11/9.
 */

public class HttpResult<T> {
    private int code ;
    @SerializedName("msg")
    private String message ;
    @SerializedName("newslist")
    private T data ;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**判断返回是否成功,只有当code=200,而且msg="success"才算成功
     * @return
     */
    public boolean isSuccess(){
        return code== Constant.CODE && Constant.SUCCESS.equals(message) ;
    }
}
