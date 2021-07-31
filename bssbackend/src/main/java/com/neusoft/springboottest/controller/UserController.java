package com.neusoft.springboottest.controller;


import com.neusoft.springboottest.dto.Message;
import com.neusoft.springboottest.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController  // Controller +@Responsebody
public class UserController {



    @PostMapping("login")
    public  User   method1(@RequestBody User user){

        System.out.println(user);
        // tom 123
//        if("tom".equals(user.getUsername())&&"123".equals(user.getPassword())){
            return user;
//        }
//        else{
//            return null;
//        }
    }

    @PostMapping("login2")
    public  Message   method2(@RequestBody User user){


        System.out.println(user);
        Message msg=new Message<User>();
        if(!"tom".equals(user.getUsername())){
            msg.setCode(500);
            msg.setMsg("not exit username "+user.getUsername());
        }else if(!"123".equals(user.getPassword())){
            msg.setCode(500);
            msg.setMsg("wrong password check it again");
        }else{
            msg.setCode(200);
            msg.setMsg("login successful !");
            msg.setObj(user);
        }
        return msg;
    }






}
