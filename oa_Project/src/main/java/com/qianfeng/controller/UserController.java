package com.qianfeng.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianfeng.entity.Check;
import com.qianfeng.entity.User;
import com.qianfeng.service.ILoginLogService;
import com.qianfeng.service.IUserService;
import com.qianfeng.util.IpGet;
import com.qianfeng.vo.PageBean;

@Controller
@RequestMapping("/Ticktack_Office")
public class UserController {
	@Autowired
	private IUserService userService;
	@Autowired
	private ILoginLogService loginLogService;
	
	
	@RequestMapping("/loginName")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request, String no,String password){
		UsernamePasswordToken token = new UsernamePasswordToken(no, new SimpleHash("md5", password,null,1).toString());
		Subject subject = SecurityUtils.getSubject();
		Map<String, Object> map = new HashMap<>();
		try {
			subject.login(token);
			request.getSession().setAttribute("no", no);
			map.put("code", 0);
			String ip = IpGet.getIpAddr(request);
			loginLogService.add(ip, no);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
			map.put("msg", "用户名或密码错误，登录失败");
		}
		return map;
	}
	
	@RequestMapping("/userall.do")
	@ResponseBody
	public Map<String, Object> listUserRole(Integer page ,Integer limit ,String no,Integer flag){
		Map<String, Object> map = new HashMap<>();
		try {
			PageBean<User> pageBean = userService.findUserByPage(page, limit, no, flag);
			map.put("code", 0);
			map.put("count", pageBean.getCount());
			map.put("data", pageBean.getPageInfos());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
			map.put("msg", null);
		}
		return map;
	}
	
	@RequestMapping("/userdel.do")
	@ResponseBody
	public Map<String, Object> userdel(int id){
		Map<String, Object> map = new HashMap<>();
		try {
			int ret = userService.deleteUserRole(id);
			if (ret == 0) {
				map.put("code", 1);
				map.put("msg", "管理员无法删除");
			}else {
				map.put("msg", "删除成功");
				map.put("code", 0);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
			map.put("msg", "删除失败");
		}
		return map;
	}
	
	@RequestMapping("/leaderall.do")
	@ResponseBody
	public Map<String, Object> leaderall(HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		String no = (String) request.getSession().getAttribute("no");
		try {
			List<User> list = userService.findLeader(no);
			map.put("code", 0);
			map.put("data", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
}
