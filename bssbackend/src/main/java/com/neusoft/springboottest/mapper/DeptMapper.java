package com.neusoft.springboottest.mapper;

import com.neusoft.springboottest.entity.Dept;

public interface DeptMapper {

    int deleteByPrimaryKey(Integer deptno);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(Integer deptno);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);


}