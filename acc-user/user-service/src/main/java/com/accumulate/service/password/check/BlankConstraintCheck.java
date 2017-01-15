package com.accumulate.service.password.check;


import com.accumulate.service.password.PolicyTip;

/**
 * 空字符检查
 * Created by tjwang on 2017/1/4.
 */
public class BlankConstraintCheck implements ConstraintCheck {
    @Override
    public PolicyTip check(String password) {
        if (password.contains(" ")) {
            return new PolicyTip("密码中包含有空字符",
                    false);
        }
        return PolicyTip.pass();
    }
}
