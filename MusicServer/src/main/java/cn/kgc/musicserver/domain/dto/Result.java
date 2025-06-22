package cn.kgc.musicserver.domain.dto;

import java.io.Serializable;

/**
 * 返回信息包装类
 * @param <T>
 */
public class Result<T> implements Serializable {
    //信息编码
    public String code;
    //内容
    public String msg;
    //携带数据
    private T data;

    // 新增静态成功方法
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    // 原有构造方法保持不变
    public Result() {}
    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    // 原有getter/setter保持不变
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }
    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}