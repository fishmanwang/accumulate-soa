package com.accumulate.service.authentication;

public enum AuthResultType {
	/**
	 * 用户可用
	 */
	ACTIVE,
	/**
	 * 密码过期
	 */
	PASSWORD_EXPIRED,
	/**
	 * 不存在
	 */
	NOT_EXIST,
	/**
	 * 被移除了
	 */
	REMOVED,
	/**
	 * 用户被禁用了
	 */
	DISABLED,
	/**
	 * 用户已过期
	 */
	EXPIRED,
	/**
	 * 用户还未生效
	 */
	NOT_ACTIVE,

	/**
	 * 需要强制修改密码
	 */
	NEED_FORCE_PASSWORD,

	/**
	 * 密码失效需要重置
	 */
	NEED_FORCE_PASSWORD_EXPIRED,

	/**
	 * 密码不符合安全策略
	 */
	NEED_FORCE_PASSWORD_POLICY

}
