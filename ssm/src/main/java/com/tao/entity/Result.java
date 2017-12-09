package com.tao.entity;

/**
 * Created by taolei on 2017/12/9.
 */
public class Result<T> {
    private T data;
    private String message;
    private String code;

    public Result() {
    }

    public Result(T data, String code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }
    public void setResult(T data, String code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T result) {
        this.data = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
