package com.qianfeng.dao;

import java.util.List;
import java.util.Map;

import com.qianfeng.entity.Check;


public interface ICheckDao {
    public List<Check> findAllCheck(Map<String, Object> map);
    
    public int count(Map<String, Object> map);
    
    public Check findCheckOne(Map<String, Object> map);
    
    public void calcelCheck(Map<String, Object> map);
    
    public void addCheck(Check check);
    
    public void updateCheck(Map<String, Object> map);
    
    
}