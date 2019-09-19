package com.yingxiaotian.pojo;

import java.io.Serializable;

public class Result implements Serializable {

    private Boolean  success;//添加是成功
    private String message;//返回消息

    public Result() {
    }

    public Result(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
