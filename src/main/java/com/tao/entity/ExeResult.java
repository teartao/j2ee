package com.tao.entity;

/**
 * Created by taolei on 2017/8/24.
 */
public class ExeResult<T> {
    private T data;
    private String msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
