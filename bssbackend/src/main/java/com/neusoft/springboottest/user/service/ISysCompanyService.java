package com.neusoft.springboottest.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.SysCompany;
import com.neusoft.springboottest.user.entity.SysUser;

import java.util.List;


public interface ISysCompanyService extends IService<SysCompany> {

    public IPage<SysCompany> getCompanyConditions(PageCondition pc);
    public SysCompany getCompanyInfoByUserId(String userId);
    public List<SysCompany> getAllCompany();
}
