package com.qianfeng.dao;

import java.util.List;
import java.util.Map;

import com.qianfeng.entity.Student;

public interface IStudentDao {
	public void studentadd(Student student);
	
	public int count();
	
	public Student findStudentByNo(String no);
	
	public void deleteStudentByNo(String no);
	
	public List<Student> findAllStudent(Map<String, Object> map);
	
	//班级管理中删除班级使用
	public List<Student> findStudentByGid(int id);
	
	public void addStudent(Student student);
}