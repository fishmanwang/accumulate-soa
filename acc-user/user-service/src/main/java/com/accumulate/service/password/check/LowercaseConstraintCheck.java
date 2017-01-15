package com.accumulate.service.password.check;


import com.accumulate.service.password.PolicyTip;

/**
 * 小写字母检查
 * Created by tjwang on 2017/1/4.
 */
public class LowercaseConstraintCheck implements ConstraintCheck {

    private int minLowercase;

    public LowercaseConstraintCheck(int minLowercase) {
        this.minLowercase = minLowercase;
    }

    public PolicyTip check(String password) {
        char[] chars = password.toCharArray();
        int num = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c >= 'a' && c <= 'z') {
                num++;
            }
        }
//        long num = password.chars().mapToObj(f -> (char) f)
//                .filter(c -> c >= 'a' && c <= 'z').count();
        if (num < minLowercase) {
            return new PolicyTip(String.format("至少包含%d位小写字母", minLowercase), false);
        }
        return PolicyTip.pass();
    }
}
