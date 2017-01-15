package com.accumulate.resp;

import com.accumulate.exception.ErrorCode;

import java.io.Serializable;

/**
 * Dubbo服务返回对象
 * @param <T>
 */
public class Response<T> implements Serializable {

	private int code;
	private String msg;
	private boolean success;
	private T data;

	public static <T> Response ok(T data) {
		Response resp = new Response();
		resp.setSuccess(true);
		resp.setData(data);
		return resp;
	}

	public static Response fail(ErrorCode code) {
		Response resp = new Response();
		resp.setCode(code.getCode());
		resp.setMsg(code.getMessage());
		return resp;
	}

	public static Response fail(int code, String msg) {
		Response resp = new Response();
		resp.setCode(code);
		resp.setMsg(msg);
		return resp;
	}

	public static <T> Response fail(int code, String msg, T data) {
		Response resp = new Response();
		resp.setCode(code);
		resp.setMsg(msg);
		resp.setData(data);
		return resp;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
