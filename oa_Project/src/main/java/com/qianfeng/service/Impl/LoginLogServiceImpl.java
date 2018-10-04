package com.qianfeng.service.Impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianfeng.dao.ILoginLogDao;
import com.qianfeng.entity.LoginLog;
import com.qianfeng.service.ILoginLogService;
import com.qianfeng.util.AddressUtils;
import com.qianfeng.vo.PageBean;
@Service
public class LoginLogServiceImpl implements ILoginLogService{

	@Autowired
	private ILoginLogDao loginDao;
	
	/*@Override
	public List<LoginLog> findAllLogLogin() {
		// TODO Auto-generated method stub
		List<LoginLog> list = null;
		try {
			list = loginDao.findAllLogLogin();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}*/
	
	@Override
	public PageBean<LoginLog> findLoginLogByPage(int page, int limit) {
		// TODO Auto-generated method stub
		PageBean<LoginLog> pageInfo = new PageBean<>();
		
//		pageInfo.setCurrentPage(page);
		
		// 设置每页显示的记录数
		pageInfo.setPageSize(limit);
		
		// 获取表中的记录总数
		int count = loginDao.count();
		// 设置总记录数
		pageInfo.setCount(count);
		// 计算总页数
		/*if(count % pageInfo.getPageSize() == 0){
			pageInfo.setTotalPage(count / pageInfo.getPageSize());
		}else{
			pageInfo.setTotalPage(count / pageInfo.getPageSize()+ 1);
		}*/
		// 根据页码计算分页查询时的开始索引
		int index = (page - 1) * pageInfo.getPageSize();
		
		Map<String,Object> map = new HashMap<>();
		map.put("index", index);
		map.put("size", pageInfo.getPageSize());
		
		List<LoginLog> list = loginDao.findByIndexAndSize(map);
		pageInfo.setPageInfos(list);
		
		return pageInfo;
	}
	
	@Override
	public void add(String ip, String no) {
		// TODO Auto-generated method stub
		LoginLog loginLog = new LoginLog();
		
		AddressUtils addressUtils = new AddressUtils();
		try {
			String address = addressUtils.getAddresses("ip=" + ip, "utf-8");
			System.out.println(address);
			Date createtime = new Date();
			loginLog.setCreatetime(createtime);
			loginLog.setIp(ip);
			loginLog.setLocation(address);
			loginLog.setNo(no);
			loginDao.add(loginLog);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}


}
