package com.qianfeng.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianfeng.entity.Course;
import com.qianfeng.service.ICourseService;
import com.qianfeng.vo.PageBean;

@RequestMapping("/Ticktack_Office")
@Controller
public class CourseController {
	@Autowired
	private ICourseService courseService;
	
	@RequestMapping("/courseall.do")
	@ResponseBody
	public Map<String, Object> courseall(){
		Map<String, Object> map	= new HashMap<>();
		
		try {
			List<Course> list = courseService.findAllCourse();
			map.put("code", 0);
			map.put("data", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
	
	
	@RequestMapping("/coursepage.do")
	@ResponseBody
	public Map<String, Object> coursepage(Integer page,Integer limit){
		Map<String, Object> map	= new HashMap<>();
		try {
			PageBean<Course> pageBean = courseService.findAllCourseByPage(page, limit);
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
	
	@RequestMapping("/deletecourse.do")
	@ResponseBody
	public Map<String, Object> deletecourse(Integer id){
		Map<String, Object> map	= new HashMap<>();
		try {
			int ret = courseService.deleteCourse(id);
			if (ret == 0) {
				map.put("code", 1);
				map.put("msg", "该课程下面存在修这门课程的班级，无法删除");
			}else {
				map.put("code", 0);
				map.put("msg", "删除成功");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
	
	@RequestMapping("/courseadd.do")
	@ResponseBody
	public Map<String, Object> courseadd(Course course){
		Map<String, Object> map	= new HashMap<>();
		try {
			course.setFlag(1);
			int ret = courseService.addCourse(course);
			if (ret == 0) {
				map.put("code", 1);
				map.put("msg", "已经存在同名的课程，不能添加");
			}else {
				map.put("code", 0);
				map.put("msg", "添加成功");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
	
	@RequestMapping("/updatecourse.do")
	@ResponseBody
	public Map<String, Object> courseupdate(Course course){
		Map<String, Object> map	= new HashMap<>();
		try {
			courseService.updateCourse(course);
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
	

}
