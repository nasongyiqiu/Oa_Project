package com.qianfeng.service;

import java.util.List;

import com.qianfeng.entity.User;
import com.qianfeng.vo.PageBean;

public interface IUserService {
	public User findUserByNo(String no);
	
	public PageBean<User> findUserByPage(Integer page,Integer limit,String no,Integer flag);
	
	public int deleteUserRole(int id);
	
	public List<User> findLeader(String no);
	

}
