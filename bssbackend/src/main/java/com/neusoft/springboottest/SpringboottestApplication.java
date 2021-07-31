package com.neusoft.springboottest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

//@MapperScan("com.neusoft.springboottest")
public class SpringboottestApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringboottestApplication.class, args);
    }
}
