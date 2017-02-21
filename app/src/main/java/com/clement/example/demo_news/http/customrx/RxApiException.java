package com.clement.example.demo_news.http.customrx;

/**抛出自定义异常
 * Created by clement on 17/2/21.
 */

public class RxApiException extends RuntimeException {
    private int code;
    private String message;

    public RxApiException(int code,String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
