package com.neusoft.springboottest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neusoft.springboottest.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component("UserMapper")
public interface UserMapper extends BaseMapper<User> {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}