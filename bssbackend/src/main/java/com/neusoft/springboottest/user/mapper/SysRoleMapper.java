package com.neusoft.springboottest.user.mapper;

import com.neusoft.springboottest.user.dto.UserRole;
import com.neusoft.springboottest.user.entity.SysRole;
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
 * @since 2021-01-28
 */
@Mapper
@Component("SysRoleMapper")
public interface SysRoleMapper extends BaseMapper<SysRole> {

    public Integer verifyUserRoleWithUserIdAndTargetUrl(UserRole userRole);

    public List<SysRole> findRolesByUsername(String username);

}
