package com.qianfeng.dao;

import java.util.List;
import java.util.Map;


import com.qianfeng.entity.Role;

public interface IRoleDao {
	
	public List<Role> findAllRole();
	
	public List<Role> findRoles();
	
	public List<Role> findRoles2(Map<String, Object> map);
	
	public int count();
	
	public void addRoles(Map<String, Object> map);
	
//	public void deleteUserRoleByUR(Map<String, Object> map);
	
	public void deleteUserRole(int id);
	
	public void deleteUser(int id);
	
	public void deleteUserRoleByUid(int uid);
	
	
}