package com.qianfeng.service.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qianfeng.dao.IGradeDao;
import com.qianfeng.dao.IStaffDao;
import com.qianfeng.dao.IStudentDao;
import com.qianfeng.entity.Grade;
import com.qianfeng.entity.Staff;
import com.qianfeng.entity.Student;
import com.qianfeng.service.IStudentService;
import com.qianfeng.util.IsXls;
import com.qianfeng.vo.PageBean;
@Service
public class StudentServiceImpl implements IStudentService{
	@Autowired
	private IStudentDao studentDao;
	@Autowired
	private IStaffDao staffDao;
	@Autowired
	private IGradeDao gradeDao;

	@Override
	public void studentadd(Student student) {
		// TODO Auto-generated method stub
		studentDao.studentadd(student);
	}

	@Override
	public PageBean<Student> findAllStudent(Integer page,Integer limit) {
		// TODO Auto-generated method stub
		PageBean<Student> pageInfo = new PageBean<>();
		
		pageInfo.setPageSize(limit);
		
		Map<String,Object> map = new HashMap<>();
		
		// 获取表中的记录总数
		int count = studentDao.count();
		// 设置总记录数
		pageInfo.setCount(count);
		int index = (page - 1) * pageInfo.getPageSize();
		
		map.put("index", index);
		map.put("size", pageInfo.getPageSize());
		List<Student> list = studentDao.findAllStudent(map);
		pageInfo.setPageInfos(list);
		
		return pageInfo;
	}

	@Override
	public void studentdelete(String no) {
		// TODO Auto-generated method stub
		Student student = null;
		try {
			student = studentDao.findStudentByNo(no);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (student != null) {
			try {
				studentDao.deleteStudentByNo(no);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void importExcel(String fileName, InputStream inputStream) throws IOException {
		// TODO Auto-generated method stub
		boolean ret = IsXls.isXls(fileName);
		// 根据不同的后缀，创建不同的对象
		Workbook workBook = null;
		if(ret){
			workBook = new HSSFWorkbook(inputStream);// xls
		}else{
			workBook = new XSSFWorkbook(inputStream);// xlsx
		}
		
		Sheet sheet = workBook.getSheetAt(0);
		int num = sheet.getLastRowNum();
		
		for(int i = 1; i <= num; i++){
			Row row = sheet.getRow(i);
			Student student = new Student();
			
			Cell cell = row.getCell(0);
			if(cell != null){
				student.setNo(cell.getStringCellValue());
			}
			
			cell = row.getCell(1);
			if(cell != null){
				student.setName(cell.getStringCellValue());
			}
			//班级
			cell = row.getCell(2);
			if(cell != null){
				String gradeName = cell.getStringCellValue();
				Grade grade = gradeDao.findGradeByName(gradeName);
				if (grade != null) {
					student.setGid(grade.getId());
				}
			}
			//性别
			cell = row.getCell(3);
			if(cell != null){
				student.setSex(cell.getStringCellValue());
			}
			//手机号
			cell = row.getCell(4);
			if(cell != null){
				student.setPhone(cell.getStringCellValue());
			}
			//qq
			cell = row.getCell(5);
			if(cell != null){
				student.setQq(cell.getStringCellValue());
			}
			//邮箱
			cell = row.getCell(6);
			if(cell != null){
				student.setEmail(cell.getStringCellValue());
			}
			//身份证号
			cell = row.getCell(7);
			if(cell != null){
				student.setCardno(cell.getStringCellValue());
			}
			//毕业学校
			cell = row.getCell(8);
			if(cell != null){
				student.setSchool(cell.getStringCellValue());
			}
			//学历
			cell = row.getCell(9);
			if(cell != null){
				student.setEducation(cell.getStringCellValue());
			}
			//招生老师
			cell = row.getCell(10);
			if(cell != null){
				String teacher = cell.getStringCellValue();
				Staff staff = staffDao.findStaffByName(teacher);
				if (staff!=null) {
					student.setIntrono(staff.getNo());
				}
			}
			//出生日期
			cell = row.getCell(11);
			if(cell != null){
				student.setBirthday(cell.getDateCellValue());
			}
			//入学日期
			cell = row.getCell(12);
			if(cell != null){
				student.setCreatedate(cell.getDateCellValue());;
			}
			student.setFlag(1);
			studentDao.addStudent(student);
		}
		workBook.close();
	}

	@Override
	public int findStudentCount() {
		// TODO Auto-generated method stub
		int count = 0;
		try {
			count = studentDao.count();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	

}
