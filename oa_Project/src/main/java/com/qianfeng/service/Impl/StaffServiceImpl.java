package com.qianfeng.service.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianfeng.dao.IDepartmentDao;
import com.qianfeng.dao.IStaffDao;
import com.qianfeng.entity.Department;
import com.qianfeng.entity.Grade;
import com.qianfeng.entity.Staff;
import com.qianfeng.entity.Student;
import com.qianfeng.service.IStaffService;
import com.qianfeng.util.IsXls;
import com.qianfeng.vo.PageBean;
@Service
public class StaffServiceImpl implements IStaffService{
	@Autowired
	private IStaffDao staffDao;
	@Autowired
	private IDepartmentDao departmentDao;

	@Override
	public List<Staff> findAllStaff() {
		// TODO Auto-generated method stub
		List<Staff> list = null;
		try {
			list = staffDao.findAllStaff();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public PageBean<Staff> findAllStaffByPage(Integer page, Integer limit) {
		// TODO Auto-generated method stub
		PageBean<Staff> pageInfo = new PageBean<>();
		
		pageInfo.setPageSize(limit);
		
		Map<String,Object> map = new HashMap<>();
		
		// 获取表中的记录总数
		int count = staffDao.count();
		// 设置总记录数
		pageInfo.setCount(count);
		int index = (page - 1) * pageInfo.getPageSize();
		
		map.put("index", index);
		map.put("size", pageInfo.getPageSize());
		List<Staff> list = staffDao.findAllStaffByPage(map);
		
		pageInfo.setPageInfos(list);
		
		return pageInfo;
	}

	@Override
	public void deleteStaff(String no) {
		// TODO Auto-generated method stub
		try {
			Staff staff = staffDao.findStaffByNo(no);
			if (staff.getFlag() == 1) {
				staffDao.updateStaffFlagByNo(no);
			} else {
				staffDao.deleteStaffByNo(no);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void importExcel(String fileName, InputStream inputStream) throws IOException {
		// TODO Auto-generated method stub
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
		List<Staff> list = new ArrayList<>();
		for(int i = 1; i <= num; i++){
			Row row = sheet.getRow(i);
			Staff staff = new Staff();
			//工号
			Cell cell = row.getCell(0);
			if(cell != null){
				staff.setNo(cell.getStringCellValue());
			}
			//姓名
			cell = row.getCell(1);
			if(cell != null){
				staff.setName(cell.getStringCellValue());
			}
			//部门
			cell = row.getCell(2);
			if(cell != null){
				String departName = cell.getStringCellValue();
				Department department = departmentDao.findDepartByName(departName);
				if (department != null) {
					staff.setDid(department.getId());;
				}
			}
			//性别
			cell = row.getCell(3);
			if(cell != null){
				staff.setSex(cell.getStringCellValue());
			}
			//手机号
			cell = row.getCell(4);
			if(cell != null){
				staff.setPhone(cell.getStringCellValue());
			}
			//邮箱
			cell = row.getCell(5);
			if(cell != null){
				staff.setEmail(cell.getStringCellValue());
			}
			//qq
			cell = row.getCell(6);
			if(cell != null){
				staff.setQq(cell.getStringCellValue());
			}
			//入职日期
			cell = row.getCell(7);
			if(cell != null){
				staff.setCreatedate(cell.getDateCellValue());
			}
			staff.setFlag(1);
			list.add(staff);
			if (list.size() == 5) {
				staffDao.addStaff(list);
				list.clear();
			}
		}
		if (list.size() != 0) {
			staffDao.addStaff(list);
		}
		workBook.close();
	}

	@Override
	public void updateStaff(Staff staff) {
		// TODO Auto-generated method stub
		try {
			staffDao.updateStaff(staff);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void addStaffForOne(Staff staff) {
		// TODO Auto-generated method stub
		List<Staff> list = new ArrayList<>();
		if (staff != null) {
			staff.setFlag(1);
			list.add(staff);
			try {
				staffDao.addStaff(list);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	

}
