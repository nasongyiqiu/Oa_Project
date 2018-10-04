package com.qianfeng.service;


import java.io.IOException;
import java.io.InputStream;

import com.qianfeng.entity.Student;
import com.qianfeng.vo.PageBean;

public interface IStudentService {
	public void studentadd(Student student);
	
	public PageBean<Student> findAllStudent(Integer page,Integer limit);
	
	public void studentdelete(String no);
	
	public int findStudentCount();
	
	public void importExcel(String fileName, InputStream inputStream) throws IOException;
}
