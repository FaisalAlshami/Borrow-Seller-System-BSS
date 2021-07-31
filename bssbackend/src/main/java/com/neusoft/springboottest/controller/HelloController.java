package com.neusoft.springboottest.controller;


//import com.neusoft.springboottest.dto.Message;
import com.neusoft.springboottest.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin
public class HelloController {
//    @RequestMapping(value="getHello1")
//    public String  method1(){
//        return "HelloController getHello1 ok";
//    }
    @PostMapping(value="getHello1")
    public String  method1(){
        return "HelloController getHello1 ok post";

    }
    @GetMapping(value="getHello1")
    public String  method2(){
        return "HelloController getHello1 ok get";

    }


    @GetMapping(value="getPic")
    public String  method3(){
        return "2.png";

    }


//    @PostMapping("/login")
//    public User  method4(@RequestBody User user){
//        System.out.println(user);
//        return user;
//    }
//
//    @PostMapping("/login2")
//    public Message  method5(@RequestBody User user){
//        System.out.println(user);
//        Message msg=    new Message<User>();
//        if("tom".equals(user.getName()) && "123".equals(user.getPassword())){
//
//            msg.setObj(user);
//            msg.setCode(1001);
//            msg.setMsg("verify user password is ok");
//        }else{
//            msg.setCode(2001);
//            msg.setMsg("username or password is wrong");
//        }
//        return msg;
//    }
}
