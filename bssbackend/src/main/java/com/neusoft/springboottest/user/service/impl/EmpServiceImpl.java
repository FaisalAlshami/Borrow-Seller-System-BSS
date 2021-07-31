package com.neusoft.springboottest.user.service.impl;

import com.neusoft.springboottest.user.dto.EmpDept;
import com.neusoft.springboottest.user.entity.Emp;
import com.neusoft.springboottest.user.mapper.EmpMapper;
import com.neusoft.springboottest.user.service.IEmpService;
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
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements IEmpService {

    @Autowired
    EmpMapper empMapper;

    public Emp getEmpByid(Integer id){

      return  empMapper.selectById(id);
    }
    public EmpDept getEmpByid2(Integer id){

        return  empMapper.selectById2(id);
    }

}
