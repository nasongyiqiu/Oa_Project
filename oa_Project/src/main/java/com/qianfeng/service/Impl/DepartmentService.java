package com.qianfeng.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianfeng.dao.IDepartmentDao;
import com.qianfeng.entity.Department;
import com.qianfeng.service.IDepartmentService;
import com.qianfeng.vo.PageBean;
@Service
public class DepartmentService implements IDepartmentService{
	@Autowired
	private IDepartmentDao departmentDao;

	@Override
	public PageBean<Department> findAllDepartment(Integer page, Integer limit) {
		// TODO Auto-generated method stub
		PageBean<Department> pageInfo = new PageBean<>();
		
		pageInfo.setPageSize(limit);
		
		Map<String,Object> map = new HashMap<>();
		
		// 获取表中的记录总数
		int count = departmentDao.count();
		// 设置总记录数
		pageInfo.setCount(count);
		int index = (page - 1) * pageInfo.getPageSize();
		
		map.put("index", index);
		map.put("size", pageInfo.getPageSize());
		List<Department> list = departmentDao.findAllDepartment(map);
		for (Department department : list) {
			department.setDcount(departmentDao.findStaffCountOfDepart(department.getId()));
		}
		pageInfo.setPageInfos(list);
		
		return pageInfo;
	}

	@Override
	public int deleteDepartmentById(int id) {
		// TODO Auto-generated method stub
		try {
			int count = departmentDao.findStaffCountOfDepart(id);
			//表示部门下面没有员工，可以执行删除操作
			if (count == 0) {
				Department department = departmentDao.findDepartmentById(id);
				//假删
				if (department.getFlag() == 1) {
					departmentDao.updateDepartmentById(id);
				}else {
					departmentDao.deleteDepartmentById(id);
				}
				return 1;
			}else {
				return 0;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public void updateDepartment(Department department) {
		// TODO Auto-generated method stub
		try {
			departmentDao.updateDepartment(department);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int addDepartment(Department department) {
		// TODO Auto-generated method stub
		try {
			Department department2 = departmentDao.findDepartByName(department.getName());
			if (department2 == null) {
				departmentDao.addDepartment(department);
				return 1;
			}else {
				return 0;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Department> findAllDepart() {
		// TODO Auto-generated method stub
		List<Department> list = null;
		try {
			list = departmentDao.findAllDepart();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	

}
