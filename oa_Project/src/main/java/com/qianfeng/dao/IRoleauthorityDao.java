package com.qianfeng.dao;

import java.util.List;

import com.qianfeng.entity.RoleauthorityKey;

public interface IRoleauthorityDao {
	
	public List<RoleauthorityKey> findRoleauthorityKeyByRid(int rid);
	
	public void deleteRoleAuthorityByAid(int aid);
}