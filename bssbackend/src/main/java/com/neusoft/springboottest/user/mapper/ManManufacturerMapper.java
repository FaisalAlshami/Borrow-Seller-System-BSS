package com.neusoft.springboottest.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neusoft.springboottest.user.entity.ManManufacturer;
import com.neusoft.springboottest.user.entity.SysCompany;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("ManManufacturerMapper")
public interface ManManufacturerMapper extends BaseMapper<ManManufacturer> {

    List<ManManufacturer> getAllManManufacturers();
    ManManufacturer getByManId(int man_id);

    int delete(int man_id);
    int update(ManManufacturer manufacturer);

}
