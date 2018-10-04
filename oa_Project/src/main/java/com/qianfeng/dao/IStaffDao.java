package com.qianfeng.dao;

import java.util.List;
import java.util.Map;

import com.qianfeng.entity.Staff;

public interface IStaffDao {
	public List<Staff> findAllStaff();
	
	//查找员工个数
	public int count();
	
	public List<Staff> findAllStaffByPage(Map<String, Object> map);
	
	//删除操作，修改flag值
	public void deleteStaffByNo(String no);
	public Staff findStaffByNo(String no);
	public void updateStaffFlagByNo(String no);
	
	//导入员工
	public Staff findStaffByName(String name);
	
	public void addStaff(List<Staff> list);
	
	public void updateStaff(Staff staff);
	
}