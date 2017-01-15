package com.accumulate.service.password.encrypt;

/**
 * 密码策略
 * Created by tjwang on 2017/1/11.
 */
public interface PwdHashPolicy {

    String MD5 = "MD5";
    String PLAINTEXT = "plaintext";

    /**
     * 加密
     * @param password
     * @param salt
     * @return
     */
    public String encryptPassword(String password, String salt);


    /**
     * 名称
     *
     * @return
     */
    public String name();


    /**
     * 描述
     *
     * @return
     */
    public String description();


	/**
	 * 显示名
	 * 
	 * @return
	 */
	String displayName();
}
