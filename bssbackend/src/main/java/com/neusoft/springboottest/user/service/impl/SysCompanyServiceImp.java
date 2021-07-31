package com.neusoft.springboottest.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.SysCompany;
import com.neusoft.springboottest.user.entity.SysUser;
import com.neusoft.springboottest.user.mapper.SysCompanyMapper;
import com.neusoft.springboottest.user.service.ISysCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class SysCompanyServiceImp extends ServiceImpl<SysCompanyMapper, SysCompany> implements  ISysCompanyService {

    @Autowired
    SysCompanyMapper sysCompanyMapper;
    public IPage<SysCompany> getCompanyConditions(PageCondition pc){

        //  Conditional constructor
        QueryWrapper<SysCompany> userQuery = Wrappers.query();

        IPage<SysCompany> page = new Page<SysCompany>(pc.getCurrentPage(), pc.getPageSize());

        IPage<SysCompany> companyPage = sysCompanyMapper.selectPage(page,userQuery);
        System.out.println("total pages num：" + companyPage.getPages());
        System.out.println("total：" + companyPage.getTotal());
        return companyPage;
//        List<SysUser> userList = userPage.getRecords();
//        userList.forEach(System.out::println);
    }
    public SysCompany getCompanyInfoByUserId(String userId){

        QueryWrapper<SysCompany> companyQuery = new QueryWrapper<>();
        companyQuery.eq("user_id",userId);
        SysCompany  company = sysCompanyMapper.selectOne(companyQuery);
        return company;
    }

    public List<SysCompany> getAllCompany(){
        return sysCompanyMapper.getAllCompanyMVO();
    }


}
