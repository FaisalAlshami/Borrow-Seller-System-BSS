package com.neusoft.springboottest.user.mapper;

import com.neusoft.springboottest.user.entity.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author phil
 * @since 2021-01-13
 */
@Mapper
@Component("DeptMapper")
public interface DeptMapper extends BaseMapper<Dept> {

    public Dept getDeptById(Integer id);


}
