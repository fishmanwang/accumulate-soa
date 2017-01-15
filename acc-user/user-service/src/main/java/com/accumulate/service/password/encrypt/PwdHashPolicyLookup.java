package com.accumulate.service.password.encrypt;

import java.util.*;

/**
 * 密码策略查找类
 * Created by tjwang on 2017/1/11.
 */
public class PwdHashPolicyLookup {

    public static Map<String, PwdHashPolicy> config = new LinkedHashMap<String, PwdHashPolicy>();

    static {
        // 添加明文
        config.put(PwdHashPolicy.PLAINTEXT, new PlaintextPwdHashPolicy());
        // 添加MD5
        config.put(PwdHashPolicy.MD5, new MD5PwdHashPolicy());

        // 其他算法
        ServiceLoader<PwdHashPolicy> serviceLoader = ServiceLoader.load(PwdHashPolicy.class);
        Iterator<PwdHashPolicy> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            PwdHashPolicy pwdHashPolicy = iterator.next();
            config.put(pwdHashPolicy.name(), pwdHashPolicy);
        }
        Collections.unmodifiableMap(config);
    }

    public static PwdHashPolicy getMD5() {
        return config.get(PwdHashPolicy.MD5);
    }

    public static PwdHashPolicy getPlainText() {
        return config.get(PwdHashPolicy.PLAINTEXT);
    }

}
