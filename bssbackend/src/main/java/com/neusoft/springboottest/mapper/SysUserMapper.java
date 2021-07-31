package com.neusoft.springboottest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neusoft.springboottest.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

//@Mapper
//@Component("SysUserMapper")
public interface SysUserMapper extends BaseMapper<SysUser> {



}
