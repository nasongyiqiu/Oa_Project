package com.qianfeng.service;


import javax.servlet.http.HttpServletRequest;

import com.qianfeng.entity.Check;
import com.qianfeng.vo.PageBean;

public interface ICheckService {
	
	public PageBean<Check> findAllCheck(Integer page,Integer limit,String startno);
	
	public int calcelCheck(Integer id,Integer pid);
	
	public void addCheck(Check check,HttpServletRequest request);
	
	//查找代办事项
	public PageBean<Check> findAllCheckByRname(Integer page,Integer limit,HttpServletRequest request);

	public void updateCheckFlag(Integer id,Integer flag);
}
