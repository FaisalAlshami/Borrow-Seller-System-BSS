package com.neusoft.springboottest.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.ManManufacturer;
import com.neusoft.springboottest.user.entity.ProBrand;

import java.util.List;


public interface IProBrandService extends IService<ProBrand> {

    int insert(ProBrand proBrand);
    int update(ProBrand proBrand);
    int delete(int brd_id);
    ProBrand getByBrdId(int brd_id);
    List<ProBrand> getByManId(int man_id);
}
