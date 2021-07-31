package com.neusoft.springboottest.user.mapper;

import com.neusoft.springboottest.user.entity.SysUserRoleRelationship;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author phil
 * @since 2021-03-19
 */
@Mapper
@Component("SysUserRoleRelationshipMapper")
public interface SysUserRoleRelationshipMapper extends BaseMapper<SysUserRoleRelationship> {
    int deleteList(int userId);
}
