package com.neusoft.springboottest.user.controller;

import com.neusoft.springboottest.dto.Message;
import com.neusoft.springboottest.user.dto.MainInfoItem;
import com.neusoft.springboottest.user.entity.ManManufacturer;
import com.neusoft.springboottest.user.entity.SysUser;
import com.neusoft.springboottest.user.entity.WalletAccountFund;
import com.neusoft.springboottest.user.mapper.ProOrderMapper;
import com.neusoft.springboottest.user.mapper.ProProductMapper;
import com.neusoft.springboottest.user.mapper.WalletAccountFundMapper;
import com.neusoft.springboottest.user.service.ISysUserService;
import com.neusoft.springboottest.user.service.impl.SysUserServiceImpl;
import com.neusoft.springboottest.user.service.impl.WalletAccountFundServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/main")
public class MainController {

    @Autowired
    SysUserServiceImpl sysUserService;

    @Autowired
    WalletAccountFundServiceImp walletAccountFundServiceImp;

    @Autowired
    ProProductMapper proProductMapper;
    @Autowired
    WalletAccountFundMapper walletAccountFundMapper;

    @Autowired
    ProOrderMapper proOrderMapper;

    @PostMapping("getUserInfo")
    public Message getUserInfo(@RequestBody SysUser user){

        MainInfoItem info=new MainInfoItem();


        info.setTotalOrder(12);

        SysUser acuser=sysUserService.getUserInfoByUserId(user.getUserId());
        System.out.println("User acuser. ==>"+acuser);
        System.out.println("User acuser.acuser.getRoleId() ==>"+acuser.getRoleId());
        WalletAccountFund fund=walletAccountFundServiceImp.getById(user.getUserId());
        if(fund!=null) {
            float balance = fund.getDEPOSITING_MONEY() +  fund.getWITHDRAWING_MONEY();
            info.setTotalWallet(String.valueOf(balance));
        }else {
            info.setTotalWallet("-");
        }
        if(acuser.getRoleId().equals("1")){
            info.setRoleName("Administrator");
            info.setTotalWallet(String.valueOf(walletAccountFundMapper.getTotalCommission()));
        }else if(acuser.getRoleId().equals("2")){
            info.setRoleName("Brand-Seller");
        }else if(acuser.getRoleId().equals("3")){
            info.setRoleName("Borrow-Seller");
        }
        info.setTotalGoods(proProductMapper.getTotalGoods());
        info.setTotalOrder(proOrderMapper.getTotalOrders());
       System.out.println("User Info. ==>"+info);
        Message msg=new Message();
        msg.setObj(info);
        msg.setCode(200);
        msg.setMsg("Successfully get user info.");
        return msg;
    }
}
