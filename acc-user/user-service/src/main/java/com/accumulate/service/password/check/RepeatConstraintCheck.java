package com.accumulate.service.password.check;

import com.accumulate.service.password.PolicyTip;

import java.util.HashMap;
import java.util.Map;

/**
 * 重复次数检查
 * Created by tjwang on 2017/1/4.
 */
public class RepeatConstraintCheck implements ConstraintCheck {

    private int maxRepeatCharacter;

    public RepeatConstraintCheck(int maxRepeatCharacter) {
        this.maxRepeatCharacter = maxRepeatCharacter;
    }

    public PolicyTip check(String password) {
        char[] chars = password.toCharArray();
        Map<Character, Integer> distinctChars = new HashMap<Character, Integer>();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int num = 0;
            if (distinctChars.containsKey(c)) {
                num = distinctChars.get(c);
                distinctChars.remove(c);
            }
            num++;
            distinctChars.put(c, num);
        }

        for (Character c : distinctChars.keySet()) {
            int num = distinctChars.get(c);
            if (num > maxRepeatCharacter) {
                return new PolicyTip(String.format("字符 %c 重复出现%d次，超过%d次",
                        c, num, maxRepeatCharacter), false);
            }
        }

        return PolicyTip.pass();
    }

}
