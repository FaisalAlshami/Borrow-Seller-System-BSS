package com.neusoft.springboottest.user.service;

import com.neusoft.springboottest.user.dto.UserRole;
import com.neusoft.springboottest.user.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author phil
 * @since 2021-01-28
 */
public interface ISysRoleService extends IService<SysRole> {

    public Integer verifyUserRoleWithUserIdAndTargetUrl(UserRole userRole);
}
