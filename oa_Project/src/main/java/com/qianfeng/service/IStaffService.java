package com.qianfeng.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.qianfeng.entity.Staff;
import com.qianfeng.vo.PageBean;

public interface IStaffService {
	
	public List<Staff> findAllStaff();
	
	public PageBean<Staff> findAllStaffByPage(Integer page,Integer limit);

	public void deleteStaff(String no);
	
	public void importExcel(String fileName, InputStream inputStream) throws IOException;
	
	public void updateStaff(Staff staff);
	
	public void addStaffForOne(Staff staff);
	
}
