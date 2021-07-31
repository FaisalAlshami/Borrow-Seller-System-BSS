package com.neusoft.springboottest.user.service.impl;

import com.neusoft.springboottest.user.entity.Dept;
import com.neusoft.springboottest.user.mapper.DeptMapper;
import com.neusoft.springboottest.user.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author phil
 * @since 2021-01-13
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Autowired
    DeptMapper deptMapper;
    public Dept getDeptByid(Integer id){
       return deptMapper.getDeptById(id);
    }
}
