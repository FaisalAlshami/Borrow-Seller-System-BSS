package com.neusoft.springboottest.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neusoft.springboottest.user.entity.SysData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Reem
 * @since 2021-02-15
 */
@Mapper
@Component("SysDataMapper")
public interface SysDataMapper extends BaseMapper<SysData> {

}
