package com.neusoft.springboottest.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.ProWish;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author phil
 * @since 2021-01-26
 */
public interface IProWishService extends IService<ProWish> {

    public IPage<ProWish> getWishByBorrowUserId(PageCondition pc);
    public List<ProWish> getListWishByBorrowUserId(PageCondition pc);
    List<ProWish> verfiyWishByProIdUserId(Integer pid, Integer uid);
}
