package com.qianfeng.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianfeng.entity.LoginLog;
import com.qianfeng.service.ILoginLogService;
import com.qianfeng.vo.PageBean;
@RequestMapping("/Ticktack_Office")
@Controller
public class LoginLogController {
	@Autowired
	private ILoginLogService loginLogService;
	
	/*@RequestMapping("/loginloglist.do")
	@ResponseBody
	public Map<String, Object> loginloglist(){
		Map<String, Object> map = new HashMap<>();
		
		try {
			List<LoginLog> list = loginLogService.findAllLogLogin();
			map.put("count", list.size());
			map.put("data", list);
			map.put("code", 0);
			map.put("msg", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
			map.put("msg", e.getMessage());
		}
		return map;
	}*/
	
	
	@RequestMapping("/loginloglist.do")
	@ResponseBody
	public Map<String, Object> loginloglist(int page, int limit){
		Map<String, Object> map = new HashMap<>();
		PageBean<LoginLog> pageInfo = loginLogService.findLoginLogByPage(page, limit);
		
		map.put("code", 0);// 针对layui的表格，0表示成功
		map.put("msg", "");
		map.put("count", pageInfo.getCount());
		map.put("data", pageInfo.getPageInfos());
		
		return map;
	}
	
	@RequestMapping("/userloginout.do")
	public String userloginout(HttpServletRequest request){
		/*HttpSession session = request.getSession(true);
		if (session.isNew()) {
			return "Ticktack_Office/login.html";
		}else {
			session.invalidate();
			return "Ticktack_Office/login.html";
		}*/
		request.getSession().removeAttribute("no");
		return "redirect:/Ticktack_Office/login.html";
	}
}
