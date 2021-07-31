package com.neusoft.springboottest;

import com.neusoft.springboottest.user.entity.SysUser;
import com.neusoft.springboottest.entity.User;
import com.neusoft.springboottest.user.mapper.SysUserMapper;
import com.neusoft.springboottest.mapper.UserMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringboottestApplicationTests {

    @Test
    void contextLoads() {

        System.out.println("aaaa");
    }
//    @Test
//    public void  test1()  {
////        try {
////            GeneratorSqlmap generatorSqlmap = new GeneratorSqlmap();
////            generatorSqlmap.generator();
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }

    @Autowired
    SysUserMapper sysUserMapper;

    @Test
    public void test2(){
        System.out.println(("----- selectAll method test ------"));
        List<SysUser> userList = sysUserMapper.selectList(null);
        Assert.assertEquals(3, userList.size());
        userList.forEach(System.out::println);
    }

    @Autowired
    UserMapper userMapper;
    @Test
    public void test3(){
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(3, userList.size());
        userList.forEach(System.out::println);
    }





}
