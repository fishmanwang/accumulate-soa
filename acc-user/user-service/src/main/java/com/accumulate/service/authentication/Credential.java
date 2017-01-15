package com.accumulate.service.authentication;

/**
 * 凭证
 * Created by tjwang on 2017/1/12.
 */
public interface Credential {

    /**
     * 获取用户信息，一般为用户名。
     * @return
     */
    String getSubject();

    /**
     *  支持CAS (可选）
     * @return
     */
    String getLt();

}
