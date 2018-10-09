package neo.tao.entity;

/**
 * Created by taolei on 2017/12/9.
 */
public class ResultDTO<T> {
    private T data;
    private String msg;
    private int code;

    public T getData() {
        return data;
    }

    public void setData(T result) {
        this.data = result;
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
}
