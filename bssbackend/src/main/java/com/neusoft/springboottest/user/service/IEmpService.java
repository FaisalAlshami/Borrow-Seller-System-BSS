package com.neusoft.springboottest.user.service;

import com.neusoft.springboottest.user.dto.EmpDept;
import com.neusoft.springboottest.user.entity.Emp;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author phil
 * @since 2021-01-13
 */
public interface IEmpService extends IService<Emp> {

    public Emp getEmpByid(Integer id);
    public EmpDept getEmpByid2(Integer id);
}
