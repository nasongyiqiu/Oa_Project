package com.qianfeng.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianfeng.dao.IAuthorityDao;
import com.qianfeng.dao.IRoleDao;
import com.qianfeng.entity.Authority;
import com.qianfeng.entity.Role;
import com.qianfeng.service.IRoleService;
import com.qianfeng.vo.PageBean;
@Service
public class RoleServiceImpl implements IRoleService{
	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IAuthorityDao authorityDao;

	/*@Override
	public List<Role> findAllRole() {
		// TODO Auto-generated method stub
		List<Role> list = null;
		try {
			list = roleDao.findAllRole();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}*/

	@Override
	public int deleteUserRole(int id) {
		// TODO Auto-generated method stub
		try {
			if (id == 1) {
				return 0;
			}else {
				roleDao.deleteUserRole(id);
				roleDao.deleteUser(id);
				authorityDao.deleteRoleAuthorityByRid(id);
				
				return 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	//角色权限中使用
	@Override
	public PageBean<Role> findRoles2(Integer page,Integer limit,String name,Integer flag) {
		// TODO Auto-generated method stub
		PageBean<Role> pageInfo = new PageBean<>();
		
		pageInfo.setPageSize(limit);
		
		Map<String,Object> map = new HashMap<>();
		if (name == null || name.equals("")) {
			map.put("name", null);
		}else {
			map.put("name", "%" + name + "%");
		}
		map.put("flag", flag);
		// 获取表中的记录总数
		int count = roleDao.count();
		// 设置总记录数
		pageInfo.setCount(count);
		int index = (page - 1) * pageInfo.getPageSize();
		
		map.put("index", index);
		map.put("size", pageInfo.getPageSize());
		List<Role> list = null;
		try {
			list = roleDao.findRoles2(map);
			for (Role role : list) {
				int id = role.getId();
				List<Authority> list2 = authorityDao.findAuthByRid(id);
				String aid = "";
				String aname = "";
				for (Authority authority : list2) {
					aname = aname + "/" + authority.getTitle();
					aid = aid + "," + authority.getId();
				}
				role.setAname(aname);
				role.setAid(aid);
				role.setAuthorities(list2);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pageInfo.setPageInfos(list);
		
		return pageInfo;
	}

	@Override
	public List<Role> findRoles() {
		// TODO Auto-generated method stub
		List<Role> roles = null;
		try {
			roles = roleDao.findRoles();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roles;
	}

	@Override
	public void updateUserRole(int uid, String rid) {
		// TODO Auto-generated method stub
		try {
			roleDao.deleteUserRoleByUid(uid);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] str = rid.split(",");
		try {
			for (String string : str) {
				int rid1 = Integer.parseInt(string);
				Map<String, Object> map = new HashMap<>();
				map.put("uid", uid);
				map.put("rid", rid1);
				roleDao.addRoles(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
