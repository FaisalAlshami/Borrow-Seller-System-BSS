package com.neusoft.springboottest.user.mapper;

import com.neusoft.springboottest.user.entity.ParParameter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author phil
 * @since 2021-03-12
 */
@Mapper
@Component("ParParameterMapper")
public interface ParParameterMapper extends BaseMapper<ParParameter> {
}