package com.accumulate.service.password;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 密码过期提示
 * Created by tjwang on 2017/1/4.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpirationTip {

    private int d = 30;

    private int lastDay = 0;

    private Status status = Status.normal;

    private ExpirationTip() {
        this(30, 0, Status.normal);
    }

    public ExpirationTip(int d, int lastDay, Status status) {
        this.d = d;
        this.lastDay = lastDay;
        this.status = status;
    }

    public int getD() {
        return d;
    }

    public Status getStatus() {
        return status;
    }

    public static ExpirationTip buildDefault() {
        return new ExpirationTip(30, 0, Status.normal);
    }

    public int getLastDay() {
        return lastDay;
    }

    public static enum Status {
        // 正常
        normal("正常"),
        // 警告
        waring("警告"),
        // 过期
        overdue("过期");

        private String name;

        private Status(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}
