package com.accumulate.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity基类
 * Created by tjwang on 2017/1/3.
 */
public class BaseEntity implements Serializable {

    protected Integer id;

    protected Date createTime;

    protected Integer createBy;

    protected Date updateTime;

    protected Integer updateBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }
}
