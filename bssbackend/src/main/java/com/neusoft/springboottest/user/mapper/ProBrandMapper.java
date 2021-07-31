package com.neusoft.springboottest.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neusoft.springboottest.user.entity.ManManufacturer;
import com.neusoft.springboottest.user.entity.ProBrand;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("ProBrandMapper")
public interface ProBrandMapper extends BaseMapper<ProBrand> {

    List<ProBrand> getByManId(int man_id);
    int insert(ProBrand proBrand);
    int update(ProBrand proBrand);
    int delete(int brd_id);
    ProBrand getByBrdId(int brd_id);

}
