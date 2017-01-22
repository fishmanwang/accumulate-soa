package com.accumulate.resp;

import com.accumulate.utils.StringUtils;

import java.io.Serializable;

/**
 * 用户模型
 */
public class UserModel implements Serializable {
    private Integer id;
    /**
     * 用户名
     */
    private String username;

    /**
     * 显示名
     */
    private String displayName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getDisplayName() {
        return StringUtils.isNotBlank(displayName) ? displayName : username;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName == null ? null : displayName.trim();
    }
}