package com.tao.common;

/**
 * @Author neotao
 * @Date 2018/9/3
 * @Version V0.0.1
 * @Desc
 */
public enum ResultEnum {
    SUCCESS("0", "成功"),
    ERROR("99999", "系统错误"),
    FAILED("-1", "请求失败");

    private String msg;
    private String code;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
