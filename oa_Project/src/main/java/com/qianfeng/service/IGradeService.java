package com.qianfeng.service;

import java.util.List;

import com.qianfeng.entity.Grade;
import com.qianfeng.vo.PageBean;

public interface IGradeService {
	
	public List<Grade> findAllGrade();
	
	public PageBean<Grade> findAllGradeAndCourse(Integer page,Integer limit);

	public int deleteGradeById(int id);
	
	public void updateGradeById(Grade grade);
	
	//添加班级
	public int addGrade(Grade grade);
	
	public List<Grade> findAllGradeByFlag();
}
