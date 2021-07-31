package com.neusoft.springboottest.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.SysData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Reem
 * @since 2021-02-15
 */
public interface ISysDataService extends IService<SysData> {
    public IPage<SysData> getMenuListByConditions(PageCondition pc);
}
