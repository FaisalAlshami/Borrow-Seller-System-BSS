package com.neusoft.springboottest.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.springboottest.dto.Message;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.ProCart;
import com.neusoft.springboottest.user.entity.ProProduct;
import com.neusoft.springboottest.user.entity.SysUser;
import com.neusoft.springboottest.user.service.IProCartService;
import com.neusoft.springboottest.user.service.IProProductService;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user/pro-cart")
public class ProCartController {

    @Autowired
    IProCartService iProCartService;

    @PostMapping("getAllCartByBrandUserId")
    public Message getAllCartByBrandUserId(@RequestBody SysUser sysUser){

        System.out.println("userId"+sysUser.getUserId()+"  username="+sysUser.getUsername());
        System.out.println("Get Cart for user :"+iProCartService.getCartByBrandUserId(sysUser.getUserId()).toString());


        Message msg= new Message();
        List<ProCart> list=iProCartService.getCartByBrandUserId(sysUser.getUserId());

        msg.setCode(200);
        msg.setObj(list);
       // msg.setMsg("get all cart from brand "+sysUser.getUsername()+ " successful");
        return msg;

    }

    @PostMapping("getAllCartByBorrowUserId")
    public Message getAllCartByBorrowUserId(@RequestBody SysUser sysUser){

        System.out.println("userId"+sysUser.getUserId()+"  username="+sysUser.getUsername());
        System.out.println("Get Cart for user :"+iProCartService.getCartByBorrowUserId(sysUser.getUserId()).toString());


        Message msg= new Message();
        List<ProCart> list=iProCartService.getCartByBorrowUserId(sysUser.getUserId());
       /* IPage<ProCart> page = new Page<>(1, 10);
        IPage<ProCart> cartIPage=page.setRecords(list);*/
        msg.setCode(200);
        msg.setObj(list);

        return msg;

    }

    @PostMapping("addCartByUser")
    public Message addCartByUser(@RequestBody ProCart proCart){
        System.out.println("Delete Cart :  ===>  ProCart.cartId ==>:"+proCart.getCartId()+
                           ", ProCart.userId ==>:"+proCart.getBorrowUserId()
                           +", proId==>:"+proCart.getProId()+", Quantity==>:"+proCart.getQuantity());
        Message msg= new Message();
        proCart.setCreateDate(LocalDateTime.now().toString());
        boolean ok=iProCartService.save(proCart);
        if(ok){
            msg.setCode(200);
            msg.setMsg("successfully added to cart");
        }else{
            msg.setCode(504);
            msg.setMsg("delete cart failure");
        }
        return msg;

    }

    @PostMapping("deleteCartByUser")
    public Message deleteCartByUser(@RequestBody ProCart proCart){
        System.out.println("Delete Cart :  ===>  ProCart.cartId ==>:"+proCart.getCartId()+
                            ", ProCart.userId ==>:"+proCart.getBorrowUserId()
                            +", proId==>:"+proCart.getProId()+", Quantity==>:"+proCart.getQuantity());
        Message msg= new Message();
        boolean ok=iProCartService.removeById(proCart.getCartId());
        if(ok){
            msg.setCode(200);
            msg.setMsg("successfully deleted cart ");
        }else{
            msg.setCode(504);
            msg.setMsg("failure deleted cart ");
        }
        return msg;

    }

}
