package com.qianfeng.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qianfeng.entity.Student;
import com.qianfeng.service.IStudentService;
import com.qianfeng.vo.PageBean;

@Controller
@RequestMapping("/Ticktack_Office")
public class StudentController {
	@Autowired
	private IStudentService studentService;
	
	@RequestMapping("/studentadd.do")
	@ResponseBody
//	public Map<String, Object> studentadd(String no,String name,String sex, String birthday, String cardno,String school,String education,Integer gid,String email,String qq, String phone,String introno,String createdate,String photo){
	public Map<String, Object> studentadd(Student student){
		Map<String, Object> map = new HashMap<>();
		
		try {
			student.setFlag(1);
			studentService.studentadd(student);
			map.put("code", 0);
			map.put("msg", "学员添加成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
			map.put("msg", "学员添加失败");
		}
		return map;
	}

	@RequestMapping("/studentpage.do")
	@ResponseBody
	public Map<String, Object> studentpage(Integer page,Integer limit){
		Map<String, Object> map = new HashMap<>();
		try {
			PageBean<Student> pageBean = studentService.findAllStudent(page, limit);
			map.put("code", 0);
			map.put("count", pageBean.getCount());
			map.put("data", pageBean.getPageInfos());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
	
	@RequestMapping("/studentdelete.do")
	@ResponseBody
	public Map<String, Object> studentdelete(String no){
		Map<String, Object> map = new HashMap<>();
		
		try {
			studentService.studentdelete(no);
			map.put("code", 0);
			map.put("msg", "删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
	
	@RequestMapping("/studentbatch.do")
	@ResponseBody
	public Map<String, Object> studentbatch(@RequestParam MultipartFile mFile){
		Map<String, Object> map = new HashMap<>();
		// 获取上传的文件的文件名
		String filename = mFile.getOriginalFilename();
		try {
			// 获取文件的输入流
			InputStream inputStream = mFile.getInputStream();
			
			// 解析exel文件，进行导入操作
			studentService.importExcel(filename, inputStream);
			map.put("code", 0);
			map.put("msg", "导入成功");
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
			map.put("msg", "导入失败");
		}
		return map;
	}
	
	@RequestMapping("/studentCount")
	@ResponseBody
	public Map<String, Object> studentCount(){
		Map<String, Object> map = new HashMap<>();
		try {
			int count = studentService.findStudentCount();
			map.put("code", 0);
			map.put("count", count);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
	
	
}
