package com.neusoft.springboottest.user.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.springboottest.user.dto.EmpDept;
import com.neusoft.springboottest.user.entity.Emp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author phil
 * @since 2021-01-13
 */
@Mapper
@Component("EmpMapper")
public interface EmpMapper extends BaseMapper<Emp> {


    Emp selectById(Integer id);

    EmpDept selectById2(Integer id);

    @Select("select * from emp  ${ew.customSqlSegment}")
    List<Emp> selectAll(@Param(Constants.WRAPPER) Wrapper wrapper);


    List<Emp> selectAll2(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select * from emp  ${ew.customSqlSegment}")
    IPage<Emp> selectUserPage(Page<Emp> page, @Param(Constants.WRAPPER) Wrapper wrapper);



}
