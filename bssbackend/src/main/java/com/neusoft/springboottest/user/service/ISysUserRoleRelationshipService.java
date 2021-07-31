package com.neusoft.springboottest.user.service;

import com.neusoft.springboottest.user.entity.SysUserRoleRelationship;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author phil
 * @since 2021-03-19
 */
public interface ISysUserRoleRelationshipService extends IService<SysUserRoleRelationship> {
    int deleteList(int userId);
}
