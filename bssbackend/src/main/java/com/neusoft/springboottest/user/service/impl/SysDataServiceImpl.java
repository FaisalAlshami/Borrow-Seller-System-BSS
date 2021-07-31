package com.neusoft.springboottest.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.SysData;
import com.neusoft.springboottest.user.mapper.SysDataMapper;
import com.neusoft.springboottest.user.service.ISysDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Reem
 * @since 2021-02-15
 */
@Service
public class SysDataServiceImpl extends ServiceImpl<SysDataMapper, SysData> implements ISysDataService {

    @Autowired
    SysDataMapper sysDataMapperr;
    public IPage<SysData> getMenuListByConditions(PageCondition pc) {

        //  Conditional constructor
        QueryWrapper<SysData> dataQuery = Wrappers.query();

        IPage<SysData> page = new Page<SysData>(pc.getCurrentPage(), pc.getPageSize());

        IPage<SysData> dataPage = sysDataMapperr.selectPage(page,dataQuery);
        System.out.println("total pages num：" + dataPage.getPages());
        System.out.println("total：" + dataPage.getTotal());


        return dataPage;
//        List<SysUser> userList = userPage.getRecords();
//        userList.forEach(System.out::println);

    }



}
