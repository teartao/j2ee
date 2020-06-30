package com.neotao.testing.controller;

public class Res<T> {
    private String msg;
    private int code;
    private T data;

    public Res() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Res resErr() {
        Res<Object> res = new Res<>();
        res.setCode(-1);
        res.setMsg("请求失败");
        return res;
    }

    public static Res<Object> res(Object data) {
        Res<Object> res = new Res<>();
        res.setCode(0);
        res.setMsg("请求成功");
        res.setData(data);
        return res;
    }
}

