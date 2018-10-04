package com.qianfeng.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianfeng.entity.Department;
import com.qianfeng.service.IDepartmentService;
import com.qianfeng.vo.PageBean;
@RequestMapping("/Ticktack_Office")
@Controller
public class DepartmentController {
	@Autowired
	private IDepartmentService departmentService;
	
	@RequestMapping("/departpage.do")
	@ResponseBody
	public Map<String, Object> departpage(Integer page,Integer limit){
		Map<String, Object> map = new HashMap<>();
		
		try {
			PageBean<Department> pageBean = departmentService.findAllDepartment(page, limit);
			map.put("code", 0);
			map.put("count", pageBean.getCount());
			map.put("data", pageBean.getPageInfos());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
		}
		
		return map;
	}
	
	@RequestMapping("/departdelete.do")
	@ResponseBody
	public Map<String, Object> departdelete(int id){
		Map<String, Object> map = new HashMap<>();
		
		try {
			int ret = departmentService.deleteDepartmentById(id);
			if (ret == 1) {
				map.put("code", 0);
				map.put("msg", "删除成功");
			}else {
				map.put("code", 1);
				map.put("msg", "部门下面还有员工，无法删除");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
			map.put("msg", "删除失败");
		}
		return map;
	}
	
	@RequestMapping("/departupdate.do")
	@ResponseBody
	public Map<String, Object> departupdate(Department department){
		Map<String, Object> map = new HashMap<>();
		
		try {
			departmentService.updateDepartment(department);
			map.put("code", 0);
			map.put("msg", "编辑成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
			map.put("msg", "编辑失败");
		}
		return map;
	}
	
	@RequestMapping("/departadd.do")
	@ResponseBody
	public Map<String, Object> departadd(Department department){
		Map<String, Object> map = new HashMap<>();
		
		try {
			department.setFlag(1);
			int ret = departmentService.addDepartment(department);;
			if (ret == 1) {
				map.put("code", 0);
				map.put("msg", "添加成功");
			}else {
				map.put("code", 1);
				map.put("msg", "该名字的部门已经存在，不能添加");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
			map.put("msg", "添加失败");
		}
		return map;
	}
	
	@RequestMapping("/departall.do")
	@ResponseBody
	public Map<String, Object> departall(){
		Map<String, Object> map = new HashMap<>();
		try {
			List<Department> list = departmentService.findAllDepart();
			map.put("code", 0);
			map.put("data", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
}
