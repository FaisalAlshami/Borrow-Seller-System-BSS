package com.neusoft.springboottest.user.service.impl;

import com.neusoft.springboottest.user.dto.UserRole;
import com.neusoft.springboottest.user.entity.SysRole;
import com.neusoft.springboottest.user.mapper.SysRoleMapper;
import com.neusoft.springboottest.user.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author phil
 * @since 2021-01-28
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    public Integer verifyUserRoleWithUserIdAndTargetUrl(UserRole userRole){

        return sysRoleMapper.verifyUserRoleWithUserIdAndTargetUrl(userRole);
    }
}
