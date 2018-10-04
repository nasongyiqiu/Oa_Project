package com.qianfeng.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianfeng.dao.IGradeDao;
import com.qianfeng.dao.IStudentDao;
import com.qianfeng.entity.Grade;
import com.qianfeng.entity.Student;
import com.qianfeng.service.IGradeService;
import com.qianfeng.vo.PageBean;
@Service
public class GradeServiceImpl implements IGradeService{
	@Autowired
	private IGradeDao gradeDao;
	@Autowired
	private IStudentDao studentDao;

	@Override
	public List<Grade> findAllGrade() {
		// TODO Auto-generated method stub
		List<Grade> list = null;
		try {
			list = gradeDao.findAllGrade();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public PageBean<Grade> findAllGradeAndCourse(Integer page, Integer limit) {
		// TODO Auto-generated method stub
		PageBean<Grade> pageInfo = new PageBean<>();
		
		pageInfo.setPageSize(limit);
		
		Map<String,Object> map = new HashMap<>();
		
		// 获取表中的记录总数
		int count = gradeDao.count();
		// 设置总记录数
		pageInfo.setCount(count);
		int index = (page - 1) * pageInfo.getPageSize();
		
		map.put("index", index);
		map.put("size", pageInfo.getPageSize());
		List<Grade> list = gradeDao.findAllGradeAndCourse(map);
		for (Grade grade : list) {
			int gid = grade.getId();
			grade.setCount(gradeDao.findCountOfGrade(gid));
		}
		pageInfo.setPageInfos(list);
		
		return pageInfo;
		
	}

	@Override
	public int deleteGradeById(int id) {
		// TODO Auto-generated method stub
		try {
			List<Student> list = studentDao.findStudentByGid(id);
			if (list.size() != 0) {
				return 0;
			}else {
				gradeDao.deleteGradeById(id);
				return 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public void updateGradeById(Grade grade) {
		// TODO Auto-generated method stub
		try {
			gradeDao.gradeUpdate(grade);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int addGrade(Grade grade) {
		// TODO Auto-generated method stub
		try {
			Grade grade2 = gradeDao.findGradeByName(grade.getName());
			Grade grade3 = gradeDao.findGradeByLocation(grade.getLocation());
			
			if (grade2 == null && grade3 == null) {
				gradeDao.gradeAdd(grade);
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
	public List<Grade> findAllGradeByFlag() {
		// TODO Auto-generated method stub
		List<Grade> list = null;
		try {
			list = gradeDao.findAllGradeByFlag();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	

}
