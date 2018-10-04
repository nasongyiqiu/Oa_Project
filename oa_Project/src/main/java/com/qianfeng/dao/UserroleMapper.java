package com.qianfeng.dao;

import com.qianfeng.entity.UserroleKey;

public interface UserroleMapper {
    int deleteByPrimaryKey(UserroleKey key);

    int insert(UserroleKey record);

    int insertSelective(UserroleKey record);
}