package indi.study.system.common.bean;

import java.io.Serializable;

public class JsonResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    // 状态编码
    private Integer status;

    // 提示信息
    private String message;

    // 返回数据
    private T data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}
