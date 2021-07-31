package com.neusoft.springboottest.user.mapper;

import com.neusoft.springboottest.user.entity.SysUser;
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
@Component("SysUserMapper")
public interface SysUserMapper extends BaseMapper<SysUser> {

    int updateManBuyerId(SysUser sysUser);

}
