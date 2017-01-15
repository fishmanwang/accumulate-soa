package com.accumulate.service.password.check;

import com.accumulate.service.password.PolicyTip;

/**
 * Created by 军 on 2015/9/17.
 */
public class UppercaseConstraintCheck implements ConstraintCheck {
    private int minUppercase;

    public UppercaseConstraintCheck(int minUppercase) {
        this.minUppercase = minUppercase;
    }

    public PolicyTip check(String password) {

        char[] chars = password.toCharArray();
        int num = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if ( c >= 'A' && c <= 'Z') {
                num++;
            }
        }

//        long num = password.chars().mapToObj(f -> (char) f)
//                .filter(c -> c >= 'A' && c <= 'Z').count();

        if (num < minUppercase) {
            return new PolicyTip(String.format("至少包含%d位大写字母", minUppercase), false);
        }
        return PolicyTip.pass();
    }
}
