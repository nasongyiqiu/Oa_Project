package com.qianfeng.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianfeng.entity.Grade;
import com.qianfeng.service.IGradeService;
import com.qianfeng.vo.PageBean;

@Controller
@RequestMapping("/Ticktack_Office")
public class GradeController {
	@Autowired
	private IGradeService gradeService;
	
	@RequestMapping("/gradeall.do")
	@ResponseBody
	public Map<String, Object> findAllGrade() {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		try {
			List<Grade> list = gradeService.findAllGrade();
			map.put("code", 0);
			map.put("msg", null);
			map.put("data", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
	
	@RequestMapping("/gradepage.do")
	@ResponseBody
	public Map<String, Object> gradepage(Integer page,Integer limit){
		Map<String, Object> map = new HashMap<>();
		
		try {
			PageBean<Grade> pageBean = gradeService.findAllGradeAndCourse(page, limit);
			map.put("code", 0);
			map.put("data", pageBean.getPageInfos());
			map.put("count", pageBean.getCount());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 0);
		}
		
		return map;
	}
	
	@RequestMapping("/gradedelete.do")
	@ResponseBody
	public Map<String, Object> gradedelete(int id){
		Map<String, Object> map = new HashMap<>();
		try {
			int ret = gradeService.deleteGradeById(id);
			if (ret == 0) {
				map.put("code", 1);
				map.put("msg", "班级下面还有对应的学生，不能删除");
			}else {
				map.put("code", 0);
				map.put("msg", "删除成功");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
			map.put("msg", "删除失败");
		}
		return map;
	}
	
	@RequestMapping("/gradeupdate.do")
	@ResponseBody
	public Map<String, Object> gradeupdate(Grade grade){
		Map<String, Object> map = new HashMap<>();
		try {
			gradeService.updateGradeById(grade);
			map.put("code", 0);
			map.put("msg", "编辑成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}

	@RequestMapping("/gradeadd.do")
	@ResponseBody
	public Map<String, Object> gradeadd(Grade grade){
		Map<String, Object> map = new HashMap<>();
		try {
			int ret = gradeService.addGrade(grade);
			if (ret == 1) {
				map.put("code", 0);
				map.put("msg", "添加成功");
			}else {
				map.put("code", 1);
				map.put("msg", "添加失败，该班级可能已经存在或者班级位置已经被占用");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
			map.put("msg", "添加失败");
		}
		return map;
	}
	
	@RequestMapping("/gradeCount.do")
	@ResponseBody
	public Map<String, Object> gradeCount(){
		Map<String, Object> map = new HashMap<>();
		try {
			List<Grade> list = gradeService.findAllGradeByFlag();
			map.put("code", 0);
			map.put("count", list.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
	
	@RequestMapping("/personOfCount")
	@ResponseBody
	public Map<String, Object> personOfCount(){
		Map<String, Object> map = new HashMap<>();
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		int size = principals.asList().size();
		map.put("code", 0);
		map.put("count", size);
		return map;
	}
	
}