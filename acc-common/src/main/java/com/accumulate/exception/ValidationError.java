package com.accumulate.exception;

/**
 * 校验错误信息
 *
 * Created by tjwang on 2016/8/18.
 */
public class ValidationError {

    private String key;

    private String message;

    private ValidationError() {}

    public static ValidationError build(String key, String message) {
        ValidationError ve = new ValidationError();
        ve.setKey(key);
        ve.setMessage(message);
        return ve;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
