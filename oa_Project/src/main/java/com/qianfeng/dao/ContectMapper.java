package com.qianfeng.dao;

import com.qianfeng.entity.Contect;

public interface ContectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Contect record);

    int insertSelective(Contect record);

    Contect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Contect record);

    int updateByPrimaryKey(Contect record);
}