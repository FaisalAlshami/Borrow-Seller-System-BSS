package com.neusoft.springboottest.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.ManManufacturer;
import com.neusoft.springboottest.user.entity.SysCompany;
import com.neusoft.springboottest.user.mapper.ManManufacturerMapper;
import com.neusoft.springboottest.user.mapper.SysCompanyMapper;
import com.neusoft.springboottest.user.service.IManManufacturerService;
import com.neusoft.springboottest.user.service.ISysCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManManufacturerServiceImp extends ServiceImpl<ManManufacturerMapper, ManManufacturer> implements IManManufacturerService {

    @Autowired
    ManManufacturerMapper manManufacturerMapper;

    @Override
    public int insert(ManManufacturer manufacturer) {
        return manManufacturerMapper.insert(manufacturer);
    }

    @Override
    public int update(ManManufacturer manufacturer) {
        return manManufacturerMapper.update(manufacturer);
    }

    @Override
    public int delete(int man_id) {
        return manManufacturerMapper.delete(man_id);
    }

    @Override
    public ManManufacturer getByManId(int man_id) {
        return manManufacturerMapper.getByManId(man_id);
    }

    public IPage<ManManufacturer> getManManufacturerConditions(PageCondition pc){

        //  Conditional constructor
        QueryWrapper<ManManufacturer> manQuery = Wrappers.query();

        IPage<ManManufacturer> page = new Page<ManManufacturer>(pc.getCurrentPage(), pc.getPageSize());

        IPage<ManManufacturer> manPage = manManufacturerMapper.selectPage(page,manQuery);
        System.out.println("total pages num：" + manPage.getPages());
        System.out.println("total：" + manPage.getTotal());
        return manPage;

    }
    public ManManufacturer getManManufacturerByManBuyerId(Integer man_buyer_id){

        QueryWrapper<ManManufacturer> manQuery = new QueryWrapper<>();
        manQuery.eq("MAN_ID",man_buyer_id);
        ManManufacturer  man = manManufacturerMapper.selectOne(manQuery);
        return man;
    }

    public List<ManManufacturer> getAllManManufacturer(){
        return manManufacturerMapper.getAllManManufacturers();
    }




}
