package com.qianfeng.service;

import java.util.List;

import com.qianfeng.entity.Department;
import com.qianfeng.vo.PageBean;

public interface IDepartmentService {
	
	public List<Department> findAllDepart();
	//查找所有部门表
	public PageBean<Department> findAllDepartment(Integer page,Integer limit);

	public int deleteDepartmentById(int id);
	
	public void updateDepartment(Department department);
	
	public int addDepartment(Department department);
}
