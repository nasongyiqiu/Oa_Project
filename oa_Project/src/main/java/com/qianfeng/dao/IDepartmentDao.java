package com.qianfeng.dao;

import java.util.List;
import java.util.Map;

import com.qianfeng.entity.Department;

public interface IDepartmentDao {
	
	public List<Department> findAllDepartment(Map<String, Object> map);
	
	public List<Department> findAllDepart();
	
	//根据部门id查找该部门下面的员工个数
	public int findStaffCountOfDepart(int id);
	//查找部门个数
	public int count();
	
	public Department findDepartmentById(int id);
	//删除时，将有效改为无效假删
	public void updateDepartmentById(int id);
	
	public void deleteDepartmentById(int id);
	
	//修改部门
	public void updateDepartment(Department department);
	
	public void addDepartment(Department department);
	
	public Department findDepartByName(String name);
}