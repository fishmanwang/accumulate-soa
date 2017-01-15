package com.accumulate.service.password;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tjwang on 2017/1/4.
 */
public class PolicyTip {

    private String tip;

    private boolean pass = true;

    private PolicyTip() {
    }

    public PolicyTip(String tip) {
        this(tip, true);
    }

    public PolicyTip(String tip, boolean pass) {
        this.tip = tip;
        this.pass = pass;
    }

    /**
     * 通过
     * @return
     */
    public static PolicyTip pass() {
        return new PolicyTip("");
    }

    /**
     * 决绝
     * @param tip
     * @return
     */
    public static PolicyTip reject(String tip) {
        return new PolicyTip(tip, false);
    }

    public String getTip() {
        return tip;
    }

    public boolean isPass() {
        return pass;
    }

    public String toString() {
        if (isPass()) {
            return "通过";
        }
        return getTip();
    }

    public static class Items {

        private List<PolicyTip> items = new ArrayList<PolicyTip>();

        public Items() {
        }

        public Items add(PolicyTip policyTip) {
            items.add(policyTip);
            return this;
        }

        public PolicyTip stop() {
            StringBuilder sb = new StringBuilder();
            boolean pass = true;
            for (PolicyTip f : items) {
                if (!f.isPass()) {
                    sb.append(f.getTip());
                    sb.append("，");
                    pass = false;
                }
            }
            if (!pass) {
                String v = sb.toString();
                int index = v.lastIndexOf("，");
                if (index > 0) {
                    v =  v.substring(0, v.length()-1);
                }
                return new PolicyTip(v, false);
            }

            return PolicyTip.pass();
        }


    }

}
