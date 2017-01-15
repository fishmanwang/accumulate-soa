package com.accumulate.service.password.check;


import com.accumulate.service.password.PolicyTip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * 黑名单检查
 * <pre>
 *     目前解决方案是直接在 bannedUrl 中保存单词内容
 * </pre>
 * Created by tjwang on 2017/1/4.
 */
public class BannedConstraintCheck implements ConstraintCheck {

    private Set<String> bannedSet;

    private static final Logger logger = LoggerFactory.getLogger(BannedConstraintCheck.class);

    public BannedConstraintCheck(Set<String> bannedSet) {
        this.bannedSet = bannedSet;
    }

    public PolicyTip check(String password) {
        if (null == bannedSet || bannedSet.size() == 0) {
            return PolicyTip.pass();
        }
        for (String line : bannedSet) {
            // 不区分大小写
            if (line.toLowerCase().contains(password.toLowerCase())) {
                return new PolicyTip("密码已列入黑名单", false);
            }
        }

        for (String word : bannedSet) {
            if (password.toLowerCase().contains(word.toLowerCase())) {
                return new PolicyTip(String.format("密码中【%s】已列入黑名单", word),
                        false);
            }
        }

        return PolicyTip.pass();
    }
}
