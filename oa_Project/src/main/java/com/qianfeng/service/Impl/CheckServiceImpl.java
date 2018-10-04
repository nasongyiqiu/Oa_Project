package com.qianfeng.service.Impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianfeng.dao.ICheckDao;
import com.qianfeng.dao.IUserDao;
import com.qianfeng.entity.Check;
import com.qianfeng.entity.Role;
import com.qianfeng.entity.User;
import com.qianfeng.service.ICheckService;
import com.qianfeng.vo.PageBean;
@Service
public class CheckServiceImpl implements ICheckService{
	@Autowired
	private ICheckDao checkDao;
	@Autowired
	private IUserDao userDao;

	@Override
	public PageBean<Check> findAllCheck(Integer page,Integer limit,String startno) {
		// TODO Auto-generated method stub
		PageBean<Check> pageInfo = new PageBean<>();
		
		pageInfo.setPageSize(limit);
		
		Map<String,Object> map = new HashMap<>();
		map.put("startno", startno);
		// 获取表中的记录总数
		int count = checkDao.count(map);
		// 设置总记录数
		pageInfo.setCount(count);
		int index = (page - 1) * pageInfo.getPageSize();
		map.put("index", index);
		map.put("size", pageInfo.getPageSize());
		List<Check> list = checkDao.findAllCheck(map);
		pageInfo.setPageInfos(list);
		
		return pageInfo;
	}

	@Override
	public int calcelCheck(Integer id, Integer pid) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("pid", pid);
		Check check = null;
		
		try {
			check = checkDao.findCheckOne(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (check.getFlag() != 1 && check.getFlag() != 2) {
			return check.getFlag();
		}else if (check.getFlag() == 2) {
			Date today = new Date();
			long current = today.getTime();
			long starttime = check.getStartdate().getTime();
			if (current-starttime<0) {
				try {
					checkDao.calcelCheck(map);
					return 1;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return 0;
				}
			}else {
				return 2;
			}
			
		}else {
			try {
				checkDao.calcelCheck(map);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 1;
		}
	}

	@Override
	public void addCheck(Check check,HttpServletRequest request) {
		// TODO Auto-generated method stub
		String startno = (String) request.getSession().getAttribute("no");
		check.setStartno(startno);
		User user = userDao.findByNo(startno);
		String startname = user.getName();
		check.setStartname(startname);
		String pid = 10000 + (int)(9999 * Math.random()) + "";
		check.setPid(pid);
		check.setFlag(1);
		check.setRname(userDao.findById(Integer.parseInt(check.getRname())).getName());
		try {
			checkDao.addCheck(check);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public PageBean<Check> findAllCheckByRname(Integer page, Integer limit,HttpServletRequest request) {
		// TODO Auto-generated method stub
		PageBean<Check> pageInfo = new PageBean<>();
		
		pageInfo.setPageSize(limit);
		
		Map<String,Object> map = new HashMap<>();
		
		String rname = userDao.findByNo((String)request.getSession().getAttribute("no")).getName();
		map.put("rname", rname);
		map.put("flag", 1);
		// 获取表中的记录总数
		int count = checkDao.count(map);
		// 设置总记录数
		pageInfo.setCount(count);
		int index = (page - 1) * pageInfo.getPageSize();
		
		map.put("index", index);
		map.put("size", pageInfo.getPageSize());
		List<Check> list = checkDao.findAllCheck(map);
		
		pageInfo.setPageInfos(list);
		
		return pageInfo;
	}

	@Override
	public void updateCheckFlag(Integer id, Integer flag) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("flag", flag);
		try {
			checkDao.updateCheck(map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
