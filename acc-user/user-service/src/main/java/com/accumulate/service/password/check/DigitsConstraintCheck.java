package com.accumulate.service.password.check;


import com.accumulate.service.password.PolicyTip;

/**
 * 数字检查
 * Created by tjwang on 2017/1/4.
 */
public class DigitsConstraintCheck implements ConstraintCheck {

    private int minDigits;

    public DigitsConstraintCheck(int minDigits) {
        this.minDigits = minDigits;
    }

    public PolicyTip check(String password) {
        char[] chars = password.toCharArray();
        int num = 0;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c >= '0' && c <= '9') {
                num++;
            }
        }
//        long num = password.chars().mapToObj(f -> (char) f)
//                .filter(c -> c >= '0' && c <= '9').count();
        if (num < minDigits) {
            return new PolicyTip(String.format("至少包含%d位数字", minDigits), false);
        }
        return PolicyTip.pass();
    }
}
