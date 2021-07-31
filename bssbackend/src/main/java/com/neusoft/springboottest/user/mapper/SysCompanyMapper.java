package com.neusoft.springboottest.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neusoft.springboottest.user.entity.SysCompany;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("SysCompanyMapper")
public interface SysCompanyMapper extends BaseMapper<SysCompany> {

    List<SysCompany> getAllCompanyMVO();
}
