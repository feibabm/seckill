package com.fndsoft.pojo;

import java.io.Serializable;

/**
 * Created by feibabm on 2017/9/9 0009.
 */
public class SecKillResult implements Serializable{
    private boolean success;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SecKillResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public SecKillResult() {
    }
}
