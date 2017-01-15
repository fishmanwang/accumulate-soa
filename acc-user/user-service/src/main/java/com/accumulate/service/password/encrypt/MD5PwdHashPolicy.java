package com.accumulate.service.password.encrypt;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * MD5加密
 * Created by tjwang on 2017/1/11.
 */
public class MD5PwdHashPolicy implements PwdHashPolicy {

    @Override
    public String encryptPassword(String password, String salt) {
        Md5PasswordEncoder md5PasswordEncoder = buildMd5PasswordEncoder(true);
        String md5Password = md5PasswordEncoder.encodePassword(password, "");
        return md5Password;
    }

    @Override
    public String name() {
        return "MD5";
    }

    @Override
    public String description() {
        return "演示用MD5算法";
    }

    @Override
    public String displayName() {
        return "演示用MD5算法";
    }


    /**
     * 构建一个MD5编码器
     *
     * @return Md5PasswordEncoder
     */
    private static Md5PasswordEncoder buildMd5PasswordEncoder(boolean base64) {
        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        md5PasswordEncoder.setEncodeHashAsBase64(base64);
        md5PasswordEncoder.setIterations(1024);
        return md5PasswordEncoder;
    }
}
