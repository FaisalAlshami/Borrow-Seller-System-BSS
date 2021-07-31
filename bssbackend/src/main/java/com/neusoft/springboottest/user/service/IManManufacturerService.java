package com.neusoft.springboottest.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.ManManufacturer;
import com.neusoft.springboottest.user.entity.SysCompany;

import java.util.List;


public interface IManManufacturerService extends IService<ManManufacturer> {

    int insert(ManManufacturer manManufacturer);
    int update(ManManufacturer manManufacturer);
    public ManManufacturer getByManId(int man_id);
    int delete(int man_id);

    public IPage<ManManufacturer> getManManufacturerConditions(PageCondition pc);
    public ManManufacturer getManManufacturerByManBuyerId(Integer man_buyer_id);
    public List<ManManufacturer> getAllManManufacturer();
}
