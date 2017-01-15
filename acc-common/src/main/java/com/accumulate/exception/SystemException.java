package com.accumulate.exception;

import java.text.MessageFormat;

/**
 * <p>
 * 系统异常通常是程序无法继续运行下去的异常，如数据库异常等。此类异常一般会显示“系统异常，请联系管理员...”等提示信息。
 * </p>
 * <p>
 * 此类异常除抛出去外没有办法处理，所以继承<code>RuntimeException</code>,省去调用时捕获后因无法处理再次抛出的麻烦。
 * </p>
 * 
 * @author tjwang
 * 
 */
public class SystemException extends RuntimeException {
	private static final long serialVersionUID = -8649925931214974641L;

	private ErrorCode errorCode;

	public SystemException(ErrorCode errorCode, Object... params) {
		super(MessageFormat.format(errorCode.getMessage(), params));
		this.errorCode = errorCode;
	}
	
	public SystemException(ErrorCode errorCode, Throwable e, Object... params) {
		super(MessageFormat.format(errorCode.getMessage(), params), e);
		this.errorCode = errorCode;
	}
	
	@Deprecated
	public SystemException(String msg, Throwable e) {
		super(msg, e);
	}
	
	public ErrorCode getErrorCode() {
		return this.errorCode;
	}
}