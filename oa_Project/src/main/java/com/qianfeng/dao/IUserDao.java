package com.qianfeng.dao;

import java.util.List;
import java.util.Map;

import com.qianfeng.entity.User;

public interface IUserDao {
	public User findByNo(String no);
	
//	public List<User> findAllUserRole();
	
	public int count(Map<String, Object> map);
	
	public List<User> findUserRole(Map<String, Object> map);
	
	public User findById(int id);
	
	public void updateFlag(int id);
	
	public void userdel(int id);
	
	public void userroledel(int id);
	
	public List<User> findUser(String no);
	
	
	
}