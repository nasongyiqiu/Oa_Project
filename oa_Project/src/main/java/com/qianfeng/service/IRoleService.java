package com.qianfeng.service;

import java.util.List;

import com.qianfeng.entity.Role;
import com.qianfeng.vo.PageBean;

public interface IRoleService {
//	public List<Role> findAllRole();
	
	public int deleteUserRole(int id);
	
	public List<Role> findRoles();
	
	public PageBean<Role> findRoles2(Integer page,Integer limit,String name,Integer flag);
	
//	public void updateUserRole(Map<String, Object> map);
	
	public void updateUserRole(int uid,String rid);

}
