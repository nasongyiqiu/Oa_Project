package com.qianfeng.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianfeng.dao.IUserDao;
import com.qianfeng.entity.Role;
import com.qianfeng.entity.User;
import com.qianfeng.service.IUserService;
import com.qianfeng.vo.PageBean;
@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	private IUserDao userDao;

	@Override
	public User findUserByNo(String no) {
		// TODO Auto-generated method stub
		User user = null;
		
		try {
			user = userDao.findByNo(no);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public PageBean<User> findUserByPage(Integer page,Integer limit,String no,Integer flag) {
		// TODO Auto-generated method stub
		PageBean<User> pageInfo = new PageBean<>();
		
		pageInfo.setPageSize(limit);
		
		Map<String,Object> map = new HashMap<>();
		if (no == null || no.equals("")) {
			map.put("no", null);
		}else {
			map.put("no", "%" + no + "%");
		}
		map.put("flag", flag);
		// 获取表中的记录总数
		int count = userDao.count(map);
		// 设置总记录数
		pageInfo.setCount(count);
		int index = (page - 1) * pageInfo.getPageSize();
		
		map.put("index", index);
		map.put("size", pageInfo.getPageSize());
		List<User> list = userDao.findUserRole(map);
		for (User user : list) {
			List<Role> roles = user.getRoles();
			String rolea = "";
			String rid = "";
			for (Role role : roles) {
				rolea = rolea + role.getInfo();
				rid = rid + role.getRid();
			}
			user.setRid(rid);
			user.setRole(rolea);
		}
		pageInfo.setPageInfos(list);
		
		return pageInfo;
	}

	@Override
	public int deleteUserRole(int id) {
		// TODO Auto-generated method stub
		try {
			User user = userDao.findById(id);
			if (user.getId() == 1) {
				return 0;
			}
			if (user.getFlag() == 1) {
				userDao.updateFlag(id);
				return 1;
			}else {
				userDao.userdel(id);
				userDao.userroledel(id);
				return 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<User> findLeader(String no) {
		// TODO Auto-generated method stub
		List<User> list = null;
		try {
			list = userDao.findUser(no);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
