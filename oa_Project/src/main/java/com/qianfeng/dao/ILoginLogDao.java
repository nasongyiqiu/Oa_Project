package com.qianfeng.dao;

import java.util.List;
import java.util.Map;

import com.qianfeng.entity.LoginLog;

public interface ILoginLogDao {
	
//	public List<LoginLog> findAllLogLogin();
	
	public void add(LoginLog loginLog);
	
	public int count();
	
	public List<LoginLog> findByIndexAndSize(Map<String, Object> map);
}