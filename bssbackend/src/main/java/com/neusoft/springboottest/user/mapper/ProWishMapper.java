package com.neusoft.springboottest.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neusoft.springboottest.user.entity.ProWish;
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
@Component("ProWishMapper")
public interface ProWishMapper extends BaseMapper<ProWish> {

    List<ProWish> selectByBorrowUserId(@Param("id") Integer id);

    List<ProWish> verfiyWishByProIdUserId(@Param("pid") Integer pid,@Param("uid") Integer uid);

}
