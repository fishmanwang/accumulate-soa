package com.accumulate.service.factory;

import com.accumulate.service.password.Constraint;
import com.accumulate.service.password.Expiration;
import com.accumulate.service.password.PasswordPolicy;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 工厂类，创建密码策略
 * Created by tjwang on 2017/1/10.
 */
public class PasswordPolicyFactory {

    /**
     * 需排除的密码
     */
    private static final Set<String> WEAK_PASSWORD_25 = Sets.newHashSet("000000", "111111", "11111111",
            "112233", "123123", "123321", "123456", "12345678", "654321", "666666", "888888",
            "abcdef", "abcabc", "abc123", "a1b2c3", "aaa111", "123qwe", "qwerty", "qweasd", "admin",
            "password", "p@ssword", "passwd", "iloveyou", "5201314");

    /**
     * 创建默认密码策略
     * @return
     */
    public static PasswordPolicy createDefaut() {
        PasswordPolicy pp = new PasswordPolicy();

        pp.setEnable(true);
        pp.setName("default");
        pp.setDescription("默认密码策略");
        pp.setBanned(true);
        pp.setBannedSet(WEAK_PASSWORD_25);

        Constraint constraint = createDefaultConstraint();
        pp.setConstraint(constraint);

        Expiration expiration = createDefaultExpiration();
        pp.setExpiration(expiration);

        return pp;
    }

    private static final PasswordPolicy pp = createDefaut();

    /**
     * 获取默认密码策略
     * @return
     */
    public static PasswordPolicy getDefault() {
        return pp;
    }

    private static Constraint createDefaultConstraint() {
        Constraint constraint = new Constraint();
        constraint.setEnable(true);
        constraint.setMin(6);
        constraint.setMax(20);
        constraint.setMaxRepeatCharacter(3);
        constraint.setMinNonAlphanumeric(1);
        constraint.setMinDigits(1);
        constraint.setMinLowercase(1);
        constraint.setMinUppercase(1);
        constraint.setNoBlank(false);
        return constraint;
    }

    private static Expiration createDefaultExpiration() {
        Expiration expiration = new Expiration();
        expiration.setEnable(true);
        expiration.setExpiredDay(30);
        expiration.setWaringBeforeDay(10);
        expiration.setWaringAction(1);
        expiration.setSubject("密码过期通知，请勿回复");
        expiration.setEmailExpiringMessage("尊敬的用户{{u.displayName}}你好,你的账户【{{u.username}}】密码将在{{d}}天后过期，请在【个人中心】及时修改密码。请勿回复本邮件。");
        expiration.setEmailExpiredMessage("尊敬的用户{{u.displayName}}你好,你账户【{{u.username}}】密码已过期，通过【忘记密码】流程重置密码。请勿回复本邮件。");
        expiration.setSmsExpiringMessage("尊敬的用户【{{u.username}}】你好,你的账户密码将在{{d}}天后过期，请及时修改密码。请勿回复本短信。");
        expiration.setSmsExpiredMessage("尊敬的用户【{{u.username}}】你好,你的账户密码已过期，通过【忘记密码】流程重置密码。请勿回复本短信。");
        return expiration;
    }

}
