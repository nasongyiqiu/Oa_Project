package com.qianfeng.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianfeng.dao.IAuthorityDao;
import com.qianfeng.dao.IRoleauthorityDao;
import com.qianfeng.entity.Authority;
import com.qianfeng.entity.RoleauthorityKey;
import com.qianfeng.service.IAuthorityService;
import com.qianfeng.vo.PageBean;
@Service
public class AuthorityServiceImpl implements IAuthorityService{
	@Autowired
	private IAuthorityDao authorityDao;
	@Autowired
	private IRoleauthorityDao roleAuthorityDao;
	@Override
	public List<Authority> findAuthorityByPid(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Authority> list = null;
		try {
			list = authorityDao.findAuthorityByPid(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Authority> findAllAuth() {
		// TODO Auto-generated method stub
		List<Authority> list = null;
		try {
			list = authorityDao.findAllAuth();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public void UpdateRoleAuthority(int id, String[] rids) {
		// TODO Auto-generated method stub
		try {
			List<RoleauthorityKey> list = roleAuthorityDao.findRoleauthorityKeyByRid(id);
			if (list.size()!=0) {
				authorityDao.deleteRoleAuthorityByRid(id);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rids != null) {
			for (String string : rids) {
				int aid = Integer.parseInt(string);
				Map<String, Object> map = new HashMap<>();
				map.put("rid", id);
				map.put("aid", aid);
				try {
					authorityDao.addByRAid(map);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public PageBean<Authority> findAllAuth2(Integer page, Integer limit) {
		// TODO Auto-generated method stub
		PageBean<Authority> pageInfo = new PageBean<>();
		pageInfo.setCount(authorityDao.count());
		pageInfo.setPageSize(limit);
		
		Map<String,Object> map = new HashMap<>();
		
		// 获取表中的记录总数
		// 设置总记录数
		int index = (page - 1) * pageInfo.getPageSize();
		
		map.put("index", index);
		map.put("size", pageInfo.getPageSize());
		List<Authority> list = null;
		try {
			list = authorityDao.findAllAuth2(map);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pageInfo.setPageInfos(list);
		
		return pageInfo;
		
	}

	@Override
	public void authorityadd(Authority authority, Integer pid) {
		// TODO Auto-generated method stub
		if (pid == 0) {
			authority.setParentId(pid);
		}
		try {
			authority.setType(1);
			authorityDao.authorityadd(authority);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Authority> findFirstLevel(int parentId) {
		// TODO Auto-generated method stub
		List<Authority> firstLevel = null;
		try {
			firstLevel = authorityDao.findFirstLevel(parentId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return firstLevel;
	}

	@Override
	public int deleteResourse(int id) {
		// TODO Auto-generated method stub
		List<Authority> list = null;
		try {
			//判断是否要删除的id下面还有权限
			list = authorityDao.findFirstLevel(id);
			if (list.size() == 0) {
				//可以删除
				authorityDao.deleteAuthorityByid(id);
				roleAuthorityDao.deleteRoleAuthorityByAid(id);
				return 1;
			}
			return 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public void courseupdate(Authority authority) {
		// TODO Auto-generated method stub
		try {
			authorityDao.courseupdate(authority);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	
	

}
