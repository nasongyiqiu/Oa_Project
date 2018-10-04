package com.qianfeng.dao;

import java.util.List;
import java.util.Map;

import com.qianfeng.entity.Course;


public interface ICourseDao {
    public List<Course> findAllCourse();
    
    public List<Course> findAllCourseByPage(Map<String, Object> map);
    
    public int count();
    
    public Course findCourseById(int id);
    
    public void updateCourseFlag(int id);
    
    //删除科目
    public void deleteCourseById(int id);
    
    //添加的时候查找是否已经存在同名的课程
    public Course findCourseByName(String name);
    
    public void addCourse(Course course);
    
    public void updateCourse(Course course);
}