package com.accumulate.service.password.check;

import com.accumulate.service.password.PolicyTip;

/**
 * 校验类，针对密码的一项约束进行校验
 * Created by tjwang on 2017/1/4.
 */
public interface ConstraintCheck {

    PolicyTip check(String password);
}
