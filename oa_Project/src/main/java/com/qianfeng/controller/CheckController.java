package com.qianfeng.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianfeng.entity.Check;
import com.qianfeng.service.ICheckService;
import com.qianfeng.vo.PageBean;

@Controller
@RequestMapping("/Ticktack_Office")
public class CheckController {
	@Autowired
	private ICheckService checkService;
	
	@RequestMapping("/processlist.do")
	@ResponseBody
	public Map<String, Object> processlist(Integer page,Integer limit,HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		String startno  = (String) request.getSession().getAttribute("no");
		try {
			PageBean<Check> pageBean = checkService.findAllCheck(page, limit, startno);
			map.put("count", pageBean.getCount());
			map.put("data", pageBean.getPageInfos());
			map.put("code", 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 map.put("code", 1);
		}
		return map;
	}
	
	@RequestMapping("/processdel.do")
	@ResponseBody
	public Map<String, Object> processdel(Integer id,Integer pid){
		Map<String, Object> map = new HashMap<>();
		try {
			int ret = checkService.calcelCheck(id, pid);
			if (ret == 1) {
				map.put("msg", "已取消");
			}else if (ret == 2) {
				map.put("msg", "请假已生效，无法取消");
			}else if (ret == 3) {
				map.put("msg", "请假已拒绝，不用取消");
			}else if (ret == 4) {
				map.put("msg", "该请假已取消");
			}
			map.put("code", 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 map.put("code", 1);
		}
		return map;
	}
	
	@RequestMapping("/processadd.do")
	@ResponseBody
	public Map<String, Object> processadd(Check check,HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		try {
			checkService.addCheck(check,request);
			map.put("code", 0);
			map.put("msg", "申请成功，请耐心等候");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 map.put("code", 1);
			 map.put("msg", "提交失败");
		}
		return map;
	}
	
	@RequestMapping("/processnolist.do")
	@ResponseBody
	public Map<String, Object> processnolist(HttpServletRequest request,Integer page,Integer limit){
		Map<String, Object> map = new HashMap<>();
		try {
			PageBean<Check> pageBean = checkService.findAllCheckByRname(page, limit,request);
			map.put("code", 0);
			map.put("count", pageBean.getCount());
			map.put("data", pageBean.getPageInfos());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 map.put("code", 1);
			 map.put("msg", "查找失败");
		}
		return map;
	}
	
	@RequestMapping("/processupdate.do")
	@ResponseBody
	public Map<String, Object> processupdate(Integer id,Integer flag){
		Map<String, Object> map = new HashMap<>();
		try {
			checkService.updateCheckFlag(id, flag);
			map.put("code", 0);
			if (flag == 2) {
				map.put("msg", "已同意");
			}else {
				map.put("msg", "已拒绝");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 map.put("code", 1);
			 map.put("msg", "编辑失败");
		}
		return map;
	}
}
