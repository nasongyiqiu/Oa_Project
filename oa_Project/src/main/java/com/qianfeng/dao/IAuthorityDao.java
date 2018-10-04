package com.qianfeng.dao;

import java.util.List;
import java.util.Map;

import com.qianfeng.entity.Authority;

public interface IAuthorityDao {
    public List<Authority> findAuthorityByPid(Map<String, Object> map);
    
    public List<Authority> findAuthByRid(Integer id);
    
    public  List<Authority> findAllAuth();
    
    public int count();
    
    public  List<Authority> findAllAuth2(Map<String, Object> map);
    
    //角色权限
    public void deleteRoleAuthorityByRid(int id);
    //角色权限添加
    public void addByRAid(Map<String, Object> map);
    
    
    public void authorityadd(Authority authority);
    
    public List<Authority> findFirstLevel(int parentId);
    
    public void deleteAuthorityByid(int id);
    
    public void courseupdate(Authority authority);
    
    
}