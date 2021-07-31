package com.neusoft.springboottest.user.mapper;

import com.neusoft.springboottest.user.entity.Salgrade;
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
@Component("SalgradeMapper")
public interface SalgradeMapper extends BaseMapper<Salgrade> {

}
