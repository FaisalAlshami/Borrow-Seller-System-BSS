package com.neusoft.springboottest.user.service.impl;

import com.neusoft.springboottest.user.entity.SysUserRoleRelationship;
import com.neusoft.springboottest.user.mapper.SysUserRoleRelationshipMapper;
import com.neusoft.springboottest.user.service.ISysUserRoleRelationshipService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author phil
 * @since 2021-03-19
 */
@Service
public class SysUserRoleRelationshipServiceImpl extends ServiceImpl<SysUserRoleRelationshipMapper, SysUserRoleRelationship> implements ISysUserRoleRelationshipService {
    @Autowired
    SysUserRoleRelationshipMapper sysUserRoleRelationshipMapper;



    @Override
    public int deleteList(int userId){
        return sysUserRoleRelationshipMapper.deleteList(userId);
    }
}
