package com.neusoft.springboottest.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.ParParameter;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author phil
 * @since 2021-03-12
 */
public interface IParParameterService extends IService<ParParameter> {

    /*** Get list of parameters and handling pagination ***/
    public IPage<ParParameter> getParameterList(PageCondition pageCondition);


}
