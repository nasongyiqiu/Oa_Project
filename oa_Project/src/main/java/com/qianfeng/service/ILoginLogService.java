package com.qianfeng.service;

import java.util.List;

import com.qianfeng.entity.LoginLog;
import com.qianfeng.vo.PageBean;

public interface ILoginLogService {
//	public List<LoginLog> findAllLogLogin();
	
	public void add(String ip, String no);
	
	public PageBean<LoginLog> findLoginLogByPage(int page, int limit);
	
}
