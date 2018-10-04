package com.qianfeng.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qianfeng.entity.Role;
import com.qianfeng.service.IAuthorityService;
import com.qianfeng.service.IRoleService;
import com.qianfeng.vo.PageBean;

@Controller
@RequestMapping("/Ticktack_Office")
public class RoleController {
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IAuthorityService authorityService;
	
	/*@RequestMapping("/rolepage.do")
	@ResponseBody
	public Map<String, Object> findAllRoles(){
		Map<String, Object> map = new HashMap<>();
		try {
			List<Role> list = roleService.findAllRole();
			map.put("code", 0);
			map.put("msg", null);
			map.put("data", list);
			map.put("count", list.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
			map.put("msg", "查找失败");
		}
		return map;
	}*/
	
	@RequestMapping("/rolepage.do")
	@ResponseBody
	public Map<String, Object> findAllRoles(Integer page,Integer limit,String name,Integer flag){
		Map<String, Object> map = new HashMap<>();
		try {
			PageBean<Role> pageBean = roleService.findRoles2(page, limit, name, flag);
			map.put("code", 0);
			map.put("data", pageBean.getPageInfos());
			map.put("count", pageBean.getCount());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
	
	@RequestMapping("/userroledel.do")
	@ResponseBody
	public Map<String, Object> userroledel(int id){
		Map<String, Object> map = new HashMap<>();
		try {
			int ret = roleService.deleteUserRole(id);
			if (ret == 1) {
				map.put("code", 0);
				map.put("msg", null);
			}else {
				map.put("code", 1);
				map.put("msg", "管理员无法删除");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
	
	@RequestMapping("/roleall.do")
	@ResponseBody
	public Map<String, Object> roleAll(){
		Map<String, Object> map = new HashMap<>();
		try {
			List<Role> roles = roleService.findRoles();
			map.put("code", 0);
			map.put("data", roles);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
		}
		return map;
	}
	
	@RequestMapping("/userroleedit.do")
	@ResponseBody
	public Map<String, Object> userroleedit(int id,String rid){
		Map<String, Object> map = new HashMap<>();
		try {
			roleService.updateUserRole(id, rid);
			map.put("code", 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
		}
		
		return map;
	}
	
	
	@RequestMapping("/roleauthorityedit.do")
	@ResponseBody
	public Map<String, Object> roleauthorityedit(int id,String[] rids){
		Map<String, Object> map = new HashMap<>();
		if (id == 1) {
			map.put("code", 1);
			map.put("msg", "管理员不能进行编辑");
			return map;
		}
		try {
			authorityService.UpdateRoleAuthority(id, rids);
			map.put("code", 0);
			map.put("msg", "编辑权限成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("code", 1);
			map.put("msg", "编辑权限失败");
		}
		return map;
	}
	
	

}
