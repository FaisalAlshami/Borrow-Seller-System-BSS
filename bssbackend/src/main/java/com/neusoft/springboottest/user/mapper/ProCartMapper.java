package com.neusoft.springboottest.user.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.neusoft.springboottest.user.entity.Emp;
import com.neusoft.springboottest.user.entity.ProCart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author phil
 * @since 2021-01-26
 */
@Mapper
@Component("ProCartMapper")
public interface ProCartMapper extends BaseMapper<ProCart> {

    List<ProCart> selectByBorrowUserId(@Param("id") Integer id);
    List<ProCart> selectByBrandUserId(@Param("brandusername") Integer brandusername);

}
