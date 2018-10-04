package com.qianfeng.service;

import java.util.List;

import com.qianfeng.entity.Course;
import com.qianfeng.vo.PageBean;

public interface ICourseService {
	public List<Course> findAllCourse();
	
	public PageBean<Course> findAllCourseByPage(Integer page,Integer limit);

	public int deleteCourse(int id);
	
	public int addCourse(Course course);
	
	public void updateCourse(Course course);
}
