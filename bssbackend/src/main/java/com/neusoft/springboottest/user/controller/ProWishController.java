package com.neusoft.springboottest.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.springboottest.dto.Message;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.ProCart;
import com.neusoft.springboottest.user.entity.ProProduct;
import com.neusoft.springboottest.user.entity.ProWish;
import com.neusoft.springboottest.user.entity.SysUser;
import com.neusoft.springboottest.user.service.IProWishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user/pro-wish")
public class ProWishController {

    @Autowired
    IProWishService iProWishService;


    @PostMapping("getAllWishByBorrowUserId")
    public Message getAllWishByBorrowUserId(@RequestBody PageCondition pc){
        System.out.println(pc);
        Message msg = new Message();
        List<ProWish> list=iProWishService.getListWishByBorrowUserId(pc);
        System.out.println("Wish List: "+list);
        if(list!=null && list.size()>0){
            msg.setCode(200);
            msg.setMsg("getAllborrow Wishs successful");
            msg.setObj(list);
        }else{
            msg.setCode(504);
            msg.setMsg("getAllborrow Wishs failure can't find data ");
        }
        return msg;

    }

    @PostMapping("addWishByUser")
    public Message addWishByUser(@RequestBody ProWish proWish){
        System.out.println("Delete Cart :  ===>  ProCart.cartId ==>:"+proWish.getWishId()+
                ", ProCart.userId ==>:"+proWish.getUserId()
                +", proId==>:"+proWish.getProId());
        Message msg= new Message();
        proWish.setCreateDate(LocalDateTime.now().toString());
        if(iProWishService.verfiyWishByProIdUserId(proWish.getProId(),proWish.getUserId()).size()>0)
        {
            msg.setCode(501);
            msg.setMsg("this product already in wish list");
        }
        else {
            boolean ok=iProWishService.save(proWish);
            if(ok){
                msg.setCode(200);
                msg.setMsg("add to wish list successful");
            }else{
                msg.setCode(504);
                msg.setMsg("failure add to wish list");
            }
        }
        return msg;
    }

    @PostMapping("deleteWish")
    public Message deleteWish(@RequestBody ProWish proWish){

        Message msg= new Message();
        boolean ok=iProWishService.removeById(proWish.getWishId());
        if(ok){
            msg.setCode(200);
            msg.setMsg("delete wish successful");
        }else{
            msg.setCode(504);
            msg.setMsg("delete wish failure");
        }
        return msg;
    }


}
