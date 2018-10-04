package com.qianfeng.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Check {
    private Integer id;

    private String info;

    private String type;

    private String startname;

    private String startno;

    private Date startdate;

    private Date enddate;

    private Integer days;

    private String pid;

    private Integer flag;
    
    private String rname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getStartname() {
        return startname;
    }

    public void setStartname(String startname) {
        this.startname = startname == null ? null : startname.trim();
    }

    public String getStartno() {
        return startno;
    }

    public void setStartno(String startno) {
        this.startno = startno == null ? null : startno.trim();
    }
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getStartdate() {
        return startdate;
    }
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getEnddate() {
        return enddate;
    }
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

	/**
	 * @return the rname
	 */
	public String getRname() {
		return rname;
	}

	/**
	 * @param rname the rname to set
	 */
	public void setRname(String rname) {
		this.rname = rname;
	}
    
}