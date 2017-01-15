package com.accumulate.service.authentication;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * 用户名密码登录凭证
 * Created by tjwang on 2017/1/12.
 */
public class UserLoginPwdCredential implements Credential, Serializable {
	private static final long serialVersionUID = -2627733840004987755L;

	@NotBlank(message = "用户名不能为空")
	private String username;

	@NotBlank(message = "密码不能为空")
	private String password;

	public UserLoginPwdCredential(String username, String password) {
		this.username = username;
		this.password = password;
	}

	// CAS 登录 LT
	public String lt;

	@Override
	public String getSubject() {
		return username;
	}

	@Override
	public String getLt() {
		return this.lt;
	}
}