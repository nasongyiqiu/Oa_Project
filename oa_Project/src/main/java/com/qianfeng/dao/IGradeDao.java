package com.qianfeng.dao;

import java.util.List;
import java.util.Map;

import com.qianfeng.entity.Grade;

public interface IGradeDao {
	
	public List<Grade> findAllGrade();
	
	public Grade findGradeById(int id);
	
	public List<Grade> findAllGradeAndCourse(Map<String, Object> map);
	
	//查询特定班的人数
	public int findCountOfGrade(int gid);
	
	//查找总班数
	public int count();
	
	public void gradeAdd(Grade grade);
	
	//根据班级名或者地址查找是否存在班级，添加班级的时候使用
	public Grade findGradeByName(String gradeName);
	public Grade findGradeByLocation(String lication);
	
	//根据id删除班级
	public void deleteGradeById(int id);
	
	
	//编辑班级
	public void gradeUpdate(Grade grade);
	
	//查看该课程下面有多少个班级 
	public int findCountOfGradeInCourse(int id);
	
	public List<Grade> findAllGradeByFlag();
}