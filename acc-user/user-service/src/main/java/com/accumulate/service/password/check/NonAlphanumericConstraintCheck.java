package com.accumulate.service.password.check;

import com.accumulate.service.password.PolicyTip;

/**
 * 特殊字符检查
 * Created by tjwang on 2017/1/4.
 */
public class NonAlphanumericConstraintCheck implements ConstraintCheck {

    private int minNonAlphanumeric;

    public NonAlphanumericConstraintCheck(int minNonAlphanumeric) {
        this.minNonAlphanumeric = minNonAlphanumeric;
    }

    public PolicyTip check(String password) {
        char[] chars = password.toCharArray();
        int num = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (!((c >= '0' && c <= '9')
                    || (c >= 'a' && c <= 'z')
                    || (c >= 'A' && c <= 'Z'))) {
                num++;
            }
        }
//        long num = password.chars().mapToObj(f -> (char) f)
//                .filter(c -> !((c >= '0' && c <= '9')
//                        || (c >= 'a' && c <= 'z')
//                        || (c >= 'A' && c <= 'Z'))).count();

        if (num < minNonAlphanumeric) {
            return new PolicyTip(String.format("至少包含%d位特殊字符", minNonAlphanumeric), false);
        }
        return PolicyTip.pass();
    }
}
