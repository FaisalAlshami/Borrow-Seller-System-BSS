package com.neusoft.springboottest.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.ManManufacturer;
import com.neusoft.springboottest.user.entity.ProBrand;
import com.neusoft.springboottest.user.mapper.ManManufacturerMapper;
import com.neusoft.springboottest.user.mapper.ProBrandMapper;
import com.neusoft.springboottest.user.service.IManManufacturerService;
import com.neusoft.springboottest.user.service.IProBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProBrandServiceImp extends ServiceImpl<ProBrandMapper, ProBrand> implements IProBrandService {

    @Autowired
    ProBrandMapper proBrandMapper;

    @Override
    public int insert(ProBrand proBrand) {
        return proBrandMapper.insert(proBrand);
    }
    @Override
    public int delete(int brd_id) {
        return proBrandMapper.delete(brd_id);
    }
    @Override
    public int update(ProBrand proBrand) {
        return proBrandMapper.update(proBrand);
    }
    @Override
    public List<ProBrand> getByManId(int man_id) {
        return proBrandMapper.getByManId(man_id);
    }
    @Override
    public ProBrand getByBrdId(int brd_id) {
        return proBrandMapper.getByBrdId(brd_id);
    }
}
