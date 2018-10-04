package com.qianfeng.dao;

import com.qianfeng.entity.Interviewquest;

public interface InterviewquestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Interviewquest record);

    int insertSelective(Interviewquest record);

    Interviewquest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Interviewquest record);

    int updateByPrimaryKeyWithBLOBs(Interviewquest record);

    int updateByPrimaryKey(Interviewquest record);
}