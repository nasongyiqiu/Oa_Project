package com.qianfeng.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Course {
    private Integer id;

    private String name;

    private Integer flag;

    private Integer week;

    private Date createdate;

    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getCreatedate() {
        return createdate;
    }
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}