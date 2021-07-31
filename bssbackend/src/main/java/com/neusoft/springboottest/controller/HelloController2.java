package com.neusoft.springboottest.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController2 {


    @RequestMapping("getHello3")
    public @ResponseBody String  method3(){

        return "HelloController getHello1 ok HelloController2";

    }




}
