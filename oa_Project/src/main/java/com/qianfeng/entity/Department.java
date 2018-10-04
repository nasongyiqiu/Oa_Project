package com.qianfeng.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Department {
    private Integer id;

    private String name;

    private Date createtime;

    private Integer flag;
    
    private Integer dcount;

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
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getCreatetime() {
        return createtime;
    }
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

	/**
	 * @return the dcount
	 */
	public Integer getDcount() {
		return dcount;
	}

	/**
	 * @param dcount the dcount to set
	 */
	public void setDcount(Integer dcount) {
		this.dcount = dcount;
	}

    
}