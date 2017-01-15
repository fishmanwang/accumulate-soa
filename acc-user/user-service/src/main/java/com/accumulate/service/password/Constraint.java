package com.accumulate.service.password;

import com.accumulate.service.password.check.*;

/**
 * 密码约束配置
 * Created by tjwang on 2017/1/10.
 */
public class Constraint {

    private Boolean enable;

    /**
     * 最小长度
     */
    private Integer min;

    /**
     * 最大长度
     */
    private Integer max;

    /**
     * 单个字符最大重复数
     */
    private Integer maxRepeatCharacter;

    /**
     * 最少特殊字符个数
     */
    private Integer minNonAlphanumeric;

    /**
     * 最少数字个数
     */
    private Integer minDigits;

    /**
     * 最少小写字母个数
     */
    private Integer minLowercase;

    /**
     * 最少大写字母次数
     */
    private Integer minUppercase;

    /**
     * 是否允许空格
     */
    private Boolean noBlank;

    public PolicyTip check(String password) {
        PolicyTip.Items items = new PolicyTip.Items();

        ConstraintCheck lengthConstraintCheck = new LengthConstraintCheck(getMin(), getMax());
        items = items.add(lengthConstraintCheck.check(password))
                .add(new RepeatConstraintCheck(getMaxRepeatCharacter()).check(password));

        if (getMinNonAlphanumeric() != 0) {
            items = items.add(new NonAlphanumericConstraintCheck(getMinNonAlphanumeric()).check(password));
        }

        if (getMinDigits() != 0) {
            items = items.add(new DigitsConstraintCheck(getMinDigits()).check(password));
        }

        if (getMinLowercase() != 0) {
            items = items.add(new LowercaseConstraintCheck(getMinLowercase()).check(password));
        }
        if (getMinUppercase() != 0) {
            items = items.add(new UppercaseConstraintCheck(getMinUppercase()).check(password));
        }
        if (getNoBlank()) {
            items = items.add(new BlankConstraintCheck().check(password));
        }
        return items.stop();
    }

    /**
     * 获取密码限制提示信息
     * @return
     */
    public String getTip() {
        StringBuilder sb = new StringBuilder();
        sb.append("为保证安全，密码有以下限制：");
        sb.append("最小长度");
        sb.append(this.getMin());
        sb.append("，最大长度");
        sb.append(this.getMax());
        sb.append("，最多包含");
        sb.append(this.getMaxRepeatCharacter());
        sb.append("个重复字符");
        if (this.getMinNonAlphanumeric() != 0) {
            sb.append("，至少包含");
            sb.append(this.getMinNonAlphanumeric());
            sb.append("位特殊字符(如:!@&*,.)");
        }

        if (this.getMinDigits() != 0) {
            sb.append("，至少包含");
            sb.append(this.getMinDigits());
            sb.append("位数字");
        }

        if (this.getMinLowercase() != 0) {
            sb.append("，至少包含");
            sb.append(this.getMinLowercase());
            sb.append("位小写字母");
        }

        if (this.getMinUppercase() != 0) {
            sb.append("，至少包含");
            sb.append(this.getMinUppercase());
            sb.append("位大写字母");
        }

        sb.append("。");

        return sb.toString();
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getMaxRepeatCharacter() {
        return maxRepeatCharacter;
    }

    public void setMaxRepeatCharacter(Integer maxRepeatCharacter) {
        this.maxRepeatCharacter = maxRepeatCharacter;
    }

    public Integer getMinNonAlphanumeric() {
        return minNonAlphanumeric;
    }

    public void setMinNonAlphanumeric(Integer minNonAlphanumeric) {
        this.minNonAlphanumeric = minNonAlphanumeric;
    }

    public Integer getMinDigits() {
        return minDigits;
    }

    public void setMinDigits(Integer minDigits) {
        this.minDigits = minDigits;
    }

    public Integer getMinLowercase() {
        return minLowercase;
    }

    public void setMinLowercase(Integer minLowercase) {
        this.minLowercase = minLowercase;
    }

    public Integer getMinUppercase() {
        return minUppercase;
    }

    public void setMinUppercase(Integer minUppercase) {
        this.minUppercase = minUppercase;
    }

    public Boolean getNoBlank() {
        return noBlank;
    }

    public void setNoBlank(Boolean noBlank) {
        this.noBlank = noBlank;
    }
}
