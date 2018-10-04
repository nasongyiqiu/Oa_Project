package com.qianfeng.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianfeng.entity.Authority;
import com.qianfeng.service.IAuthorityService;
import com.qianfeng.vo.PageBean;

@Controller
@RequestMapping("/Ticktack_Office")
public class AuthorityController {
	@Autowired
	private IAuthorityService authorityService;
	
	@RequestMapping("/listAuth")
	@ResponseBody
	public Map<String, Object> listAuth(HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		
		map.put("no", request.getSession().getAttribute("no"));
		map.put("parentId", 0);
		List<Authority> firstLevel = authorityService.findAuthorityByPid(map);
		
		Map<String, Object> map2 = new HashMap<>();
		map2.put("code", 0);
		map2.put("msg", null);
		List<Map<String, Object>> all = new ArrayList<>(); 
		for (Authority authority : firstLevel) {
			Integer id = authority.getId();
			Map<String, Object> map3 = new HashMap<>();
			map3.put("firstLevel", authority);
			Map<String, Object> map4 = new HashMap<>();
			map4.put("no", request.getSession().getAttribute("no"));
			map4.put("parentId", id);
			
			List<Authority> secondLevel = authorityService.findAuthorityByPid(map4);
			map3.put("secondLevel", secondLevel);
			all.add(map3);
		}
		map2.put("level", all);
		return map2;
	}
	
	//权限管理角色权限使用
	@RequestMapping("/authorityall.do")
	@ResponseBody
	public Map<String, Object> authorityall(){
		Map<String, Object> map = new HashMap<>();
		
		try {
			List<Authority> list  = authorityService.findAllAuth();
			map.put("code", 0);
			map.put("data", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
	
	@RequestMapping("/authoritylist.do")
	@ResponseBody
	public Map<String, Object> authoritylist(Integer page,Integer limit){
		Map<String, Object> map = new HashMap<>();
		
		try {
			PageBean<Authority> pageBean = authorityService.findAllAuth2(page, limit);
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
	
	//查找一级的所有权限
	@RequestMapping("/authorityroot.do")
	@ResponseBody
	public Map<String, Object> authorityroot(){
		Map<String, Object> map = new HashMap<>();
		try {
			List<Authority> list = authorityService.findFirstLevel(0);
			map.put("code", 0);
			map.put("data", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
	
	@RequestMapping("/authorityadd.do")
	@ResponseBody
	public Map<String, Object> authorityadd(Authority authority,Integer pid){
		Map<String, Object> map = new HashMap<>();
		
		try {
			authorityService.authorityadd(authority, pid);
			map.put("code", 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
	
	@RequestMapping("/coursedelete.do")
	@ResponseBody
	public Map<String, Object> coursedelete(int id){
		Map<String, Object> map = new HashMap<>();
		try {
			int ret = authorityService.deleteResourse(id);
			if (ret == 1) {
				map.put("msg", "删除成功");
			}else {
				map.put("msg", "存在该权限的子级，删除失败");
			}
			map.put("code", 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
	
	@RequestMapping("/courseupdate.do")
	@ResponseBody
	public Map<String, Object> courseupdate(Authority authority,HttpServletResponse response,HttpServletRequest request){
		Map<String, Object> map = new HashMap<>();
		try {
			authorityService.courseupdate(authority);
			map.put("code", 0);
			map.put("msg", "编辑成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
			map.put("msg", "编辑失败");
		}
		return map;
	}

}
