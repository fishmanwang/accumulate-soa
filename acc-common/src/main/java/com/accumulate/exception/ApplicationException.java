package com.accumulate.exception;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tjwang on 2016/8/18.
 */
public class ApplicationException extends RuntimeException {

    /**
     * 系统错误代码
     */
    private ErrorCode errorCode;

    /**
     * 当验证产生问题时，记录字段和错误原因的键值对
     */
    private List<ValidationError> validationErrors = new ArrayList<ValidationError>();

    @Override
    public String toString() {
        if (errorCode.equals(CommonErrorCode.VALIDATION_ERROR)) {
            String additionMsg = "";
            for (ValidationError v : getValidationErrors()) {
                additionMsg = additionMsg + "\n" + v;
            }
            return super.toString() + additionMsg;
        }

        return errorCode.getMessage();
    }

    private ApplicationException(String message) {
        super(message);
    }

    private ApplicationException(String message, Throwable ex) {
        super(message, ex);
    }

    public static ApplicationException build(ErrorCode errorCode) {
        ApplicationException e = new ApplicationException(errorCode.getMessage());
        e.errorCode = errorCode;
        return e;
    }

    public static ApplicationException build(ErrorCode errorCode, String message) {
        ApplicationException e = new ApplicationException(message);
        e.errorCode = errorCode;
        return e;
    }

    public static ApplicationException build(ErrorCode errorCode, String message, Throwable ex) {
        ApplicationException e = new ApplicationException(message, ex);
        e.errorCode = errorCode;
        return e;
    }

    /**
     * 使用ErrorCode的message，使用参数填充。
     * @param errorCode
     * @param params
     * @return
     */
    public static ApplicationException buildWithPlaceholder(ErrorCode errorCode, Object... params) {
        ApplicationException e = new ApplicationException(MessageFormat.format(errorCode.getMessage(), params));
        e.errorCode = errorCode;
        return e;
    }

    /**
     * 使用ErrorCode的message，使用参数填充。
     * @param errorCode
     * @param ex
     * @param params
     * @return
     */
    public static ApplicationException buildWithPlaceholder(ErrorCode errorCode, Throwable ex, Object... params) {
        ApplicationException e = new ApplicationException(MessageFormat.format(errorCode.getMessage(), params), ex);
        e.errorCode = errorCode;
        return e;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<ValidationError> errors) {
        this.validationErrors = errors;
    }

}
