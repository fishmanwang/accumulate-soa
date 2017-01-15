package com.accumulate.service.password.check;


import com.accumulate.service.password.PolicyTip;

/**
 * 密码长度检查
 * Created by tjwang on 2017/1/4.
 */
public class LengthConstraintCheck implements ConstraintCheck {

    private int min;
    private int max;

    public LengthConstraintCheck(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public PolicyTip check(String password) {
        if (password.length() < min) {
            return new PolicyTip(String.format("密码长度不足%d位", min), false);
        }
        if (password.length() > max) {
            return new PolicyTip(String.format("密码长度超过%d位", max), false);
        }
        return PolicyTip.pass();
    }
}
