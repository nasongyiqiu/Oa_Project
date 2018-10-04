package com.qianfeng.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qianfeng.entity.Staff;
import com.qianfeng.service.IStaffService;
import com.qianfeng.vo.PageBean;

@Controller
@RequestMapping("/Ticktack_Office")
public class StaffController {
	@Autowired
	private IStaffService staffService;
	
	@RequestMapping("/staffall.do")
	@ResponseBody
	public Map<String, Object> staffall(){
		Map<String, Object> map = new HashMap<>();
		try {
			List<Staff> staffs = staffService.findAllStaff();
			map.put("code", 0);
			map.put("data", staffs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
	
	@RequestMapping("/staffpage.do")
	@ResponseBody
	public Map<String, Object> staffpage(Integer page,Integer limit){
		Map<String, Object> map = new HashMap<>();
		try {
			PageBean<Staff> pageBean = staffService.findAllStaffByPage(page, limit);
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
	
	@RequestMapping("/staffdelete.do")
	@ResponseBody
	public Map<String, Object> staffdelete(String no){
		Map<String, Object> map = new HashMap<>();
		try {
			staffService.deleteStaff(no);
			map.put("code", 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
	
	@RequestMapping("/staffbatch.do")
	@ResponseBody
	public Map<String, Object> studentbatch(@RequestParam MultipartFile mFile){
		Map<String, Object> map = new HashMap<>();
		// 获取上传的文件的文件名
		String filename = mFile.getOriginalFilename();
		try {
			// 获取文件的输入流
			InputStream inputStream = mFile.getInputStream();
			
			// 解析exel文件，进行导入操作
			staffService.importExcel(filename, inputStream);
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
	
	@RequestMapping("/photoupload.do")
	@ResponseBody
	public Map<String, Object> photoupload(@RequestParam MultipartFile file){
		Map<String, Object> map = new HashMap<>();
		//System.out.println(filename);
		// 获取上传的文件的文件名
		String filename = file.getOriginalFilename();
		
		String path = "D:\\git_repository\\oa_Project\\src\\main\\webapp\\Ticktack_Office\\media\\images";
		map.put("msg", filename);
		File pathFile = new File(path);
		// 如果文件夹不存在创建
		if(!pathFile.exists()){
			pathFile.mkdir();
		}
		File file1 = new File(path, filename);
		// 将上传的文件保存到服务器指定位置
		
		try {
			file.transferTo(file1);
			map.put("code", 0);
		} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
	
	@RequestMapping("/staffupdate.do")
	@ResponseBody
	public Map<String, Object> staffupdate(Staff staff){
		Map<String, Object> map = new HashMap<>();
		
		try {
			staffService.updateStaff(staff);
			map.put("code", 0);
			map.put("msg", "编辑成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 0);
			map.put("msg", "编辑失败");
		}
		return map;
	}
	
	@RequestMapping("/staffadd.do")
	@ResponseBody
	public Map<String, Object> staffadd(Staff staff){
		Map<String, Object> map = new HashMap<>();
		
		try {
			staffService.addStaffForOne(staff);
			map.put("code", 0);
			map.put("msg", "添加成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 0);
			map.put("msg", "添加失败");
		}
		return map;
	}
	
	@RequestMapping("/staffCount")
	@ResponseBody
	public Map<String, Object> staffCount(){
		Map<String, Object> map = new HashMap<>();
		
		try {
			List<Staff> list = staffService.findAllStaff();
			map.put("code", 0);
			map.put("count", list.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
		}
		
		return map;
	}
	
}
