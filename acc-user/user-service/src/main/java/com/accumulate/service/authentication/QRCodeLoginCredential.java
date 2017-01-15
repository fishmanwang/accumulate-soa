package com.accumulate.service.authentication;

/**
 * 二维码登录凭证
 * Created by tjwang on 2017/1/12.
 */
public class QRCodeLoginCredential implements Credential {

    public String requestId;

    // CAS 登录 LT
    public String lt;

    @Override
    public String getSubject() {
        return requestId;
    }

    @Override
    public String getLt() {
        return this.lt;
    }
}
