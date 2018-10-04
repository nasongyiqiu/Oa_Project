package com.qianfeng.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianfeng.dao.ICourseDao;
import com.qianfeng.dao.IGradeDao;
import com.qianfeng.entity.Course;
import com.qianfeng.service.ICourseService;
import com.qianfeng.vo.PageBean;
@Service
public class CourseServiceImpl implements ICourseService{
	@Autowired
	private ICourseDao courseDao;
	@Autowired
	private IGradeDao gradeDao;

	@Override
	public List<Course> findAllCourse() {
		// TODO Auto-generated method stub
		List<Course> courses = null;
		try {
			courses = courseDao.findAllCourse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courses;
	}

	@Override
	public PageBean<Course> findAllCourseByPage(Integer page, Integer limit) {
		// TODO Auto-generated method stub
		PageBean<Course> pageInfo = new PageBean<>();
		
		pageInfo.setPageSize(limit);
		
		Map<String,Object> map = new HashMap<>();
		
		// 获取表中的记录总数
		int count = courseDao.count();
		// 设置总记录数
		pageInfo.setCount(count);
		int index = (page - 1) * pageInfo.getPageSize();
		
		map.put("index", index);
		map.put("size", pageInfo.getPageSize());
		List<Course> list = courseDao.findAllCourseByPage(map);
		pageInfo.setPageInfos(list);
		
		return pageInfo;
	}

	@Override
	public int deleteCourse(int id) {
		// TODO Auto-generated method stub
		try {
			Course course = courseDao.findCourseById(id);
			if (course != null) {
				//查看该课程下面是否有班级
				int size = gradeDao.findCountOfGradeInCourse(id);
				if (size == 0) {
					//假删
					if (course.getFlag() == 1) {
						courseDao.updateCourseFlag(id);
					}
					//可以删除
//					courseDao.deleteCourseById(id);
					return 1;
				}
				return 0;
			}
			return 0;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int addCourse(Course course) {
		// TODO Auto-generated method stub
		try {
			Course course2 = courseDao.findCourseByName(course.getName());
			if (course2 != null) {
				return 0;
			}else {
				courseDao.addCourse(course);
				return 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public void updateCourse(Course course) {
		// TODO Auto-generated method stub
		try {
			courseDao.updateCourse(course);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
