package com.neusoft.springboottest.user.service;

import com.neusoft.springboottest.user.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.springboottest.user.entity.Emp;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author phil
 * @since 2021-01-13
 */
public interface IDeptService extends IService<Dept> {
    public Dept getDeptByid(Integer id);
}
