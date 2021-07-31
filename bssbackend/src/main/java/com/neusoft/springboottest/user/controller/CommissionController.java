package com.neusoft.springboottest.user.controller;

import com.neusoft.springboottest.config.security.EncryptionAlgorithm;
import com.neusoft.springboottest.dto.Message;
import com.neusoft.springboottest.user.dto.CommissionItem;
import com.neusoft.springboottest.user.entity.SysUser;
import com.neusoft.springboottest.user.entity.WalletCommission;
import com.neusoft.springboottest.user.service.ISysUserService;
import com.neusoft.springboottest.user.service.IWalletCommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/wa-comm")
public class CommissionController {

    @Autowired
    ISysUserService iSysUserService;

    @Autowired
    IWalletCommissionService iWalletCommissionService;
    
    @PostMapping("getAll")
    public Message getAll(@RequestBody SysUser sysUser){
        Message msg = new Message();
        System.out.println("user==> :"+sysUser);
       SysUser user=  iSysUserService.getUserInfoByUserId(sysUser.getUserId());
        if(user!=null){
            msg.setCode(200);
            msg.setObj(iWalletCommissionService.getAll());
            msg.setMsg("successfully get all commissions");
        }else{
            msg.setCode(504);
            msg.setMsg("failure got commissions");
        }
        return msg;
    }

    @PostMapping("saveComm")
    public Message saveComm(@RequestBody CommissionItem commissionItem){
        Message msg = new Message();
        System.out.println("commissionItem==> :"+commissionItem);
        SysUser user=  iSysUserService.getUserInfoByUserId(commissionItem.getUser_id());
        if(user!=null){

            WalletCommission wcom=iWalletCommissionService.getByCommId(commissionItem.getCommission_id());
            if(wcom==null){
                WalletCommission c1=iWalletCommissionService.getByCommTypeId(commissionItem.getCommission_type());
                if(c1!=null){
                    msg.setCode(504);
                    msg.setMsg("failure saved commission, the commission with type ("+
                            commissionItem.getCommission_type()+") available");
                    return msg;
                }
            }
            if(wcom!=null){
                wcom.setTransaction_type_id(commissionItem.getCommission_type());
                wcom.setPercent(commissionItem.getAmount());
                iWalletCommissionService.updateWalletCommission(wcom);
            }else {
                wcom = new WalletCommission();
                wcom.setTransaction_type_id(commissionItem.getCommission_type());
                wcom.setPercent(commissionItem.getAmount());
                iWalletCommissionService.insertWalletCommission(wcom);
            }
            msg.setCode(200);
            msg.setMsg("successfully saved commission");
        }else{
            msg.setCode(504);
            msg.setMsg("failure save commission");
        }
        return msg;
    }

    @PostMapping("deleteComm")
    public Message deleteComm(@RequestBody CommissionItem commissionItem){
        Message msg = new Message();
        System.out.println("commissionItem==> :"+commissionItem);
        SysUser user=  iSysUserService.getUserInfoByUserId(commissionItem.getUser_id());
        if(user!=null){
            WalletCommission wcom=iWalletCommissionService.getByCommId(commissionItem.getCommission_id());
            if(wcom!=null){
                iWalletCommissionService.deleteById(commissionItem.getCommission_id());
                msg.setCode(200);
                msg.setMsg("successfully deleted commission");
            }else{
                msg.setCode(504);
                msg.setMsg("failure deleted commission its existed");
                return msg;
            }
        }else{
            msg.setCode(504);
            msg.setMsg("failure save commission");
        }
        return msg;
    }
}
