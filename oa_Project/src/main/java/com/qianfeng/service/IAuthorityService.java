package com.qianfeng.service;

import java.util.List;
import java.util.Map;


import com.qianfeng.entity.Authority;
import com.qianfeng.vo.PageBean;

public interface IAuthorityService {
	public List<Authority> findAuthorityByPid(Map<String, Object> map);

	public List<Authority> findAllAuth();
	
	public PageBean<Authority> findAllAuth2(Integer page,Integer limit);
	
	//角色权限中使用
	public void UpdateRoleAuthority(int id,String[] rids);
	
	//资源管理使用
	public void authorityadd(Authority authority,Integer pid);
	
	public List<Authority> findFirstLevel(int parentId);
	
	//资源管理使用删除，如果为0表示该权限下面还存在子权限，不能删除
	public int deleteResourse(int id);
	
	public void courseupdate(Authority authority);
}
