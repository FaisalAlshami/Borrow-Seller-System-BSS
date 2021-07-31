package com.neusoft.springboottest.user.controller;

import com.neusoft.springboottest.config.security.EncryptionAlgorithm;
import com.neusoft.springboottest.dto.Message;
import com.neusoft.springboottest.user.dto.BSSException;
import com.neusoft.springboottest.user.dto.DepositItem;
import com.neusoft.springboottest.user.dto.FundItem;
import com.neusoft.springboottest.user.dto.WithdrawItem;
import com.neusoft.springboottest.user.entity.*;
import com.neusoft.springboottest.user.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@RestController
@RequestMapping("/user/wa-user")
public class WalletController {
    @Autowired
    SysUserServiceImpl sysUserService;

    @Autowired
    WalletAccountServiceImp walletAccountServiceImp;

    @Autowired
    WalletAccountFundServiceImp walletAccountFundServiceImp;

    @Autowired
    WalletTransactionAuditServiceImp walletTransactionAuditServiceImp;



    @Autowired
    WalletFundOrderServiceImp walletFundOrderServiceImp;

    @Autowired
    WalletCommissionServiceImp walletCommissionServiceImp;

    @Autowired
    WalletAccountPermServiceImp walletAccountPermServiceImp;

    ////////////////////////// get methods /////////////////////////
    @PostMapping("getAll")
    public Message getAllAccount(@RequestBody SysUser sysUser) {
        Message msg = new Message();
        System.out.println("getAllAccount  sysUser  ==>" + sysUser);
        msg.setCode(200);
        msg.setObj(walletAccountServiceImp.getAll());
        msg.setMsg("successfully get all accounts");
        return msg;
    }

    @PostMapping("getAccount")
    public Message getAccount(@RequestBody SysUser sysUser) {
        Message msg = new Message();
        System.out.println("getAllAccount  sysUser  ==>" + sysUser);
        msg.setCode(200);
        msg.setObj(walletAccountServiceImp.getByBuyerId(sysUser.getUserId()));
        msg.setMsg("successfully get account");
        return msg;
    }

    @PostMapping("getTransactions")
    public Message getTransactions(@RequestBody SysUser sysUser) {
        Message msg = new Message();
        System.out.println("getTransactions  sysUser  ==>" + sysUser);
        msg.setCode(200);
        msg.setObj(walletTransactionAuditServiceImp.getAllTransactions(sysUser.getUserId()));
        msg.setMsg("successfully get all account's transactions");
        return msg;
    }
    @PostMapping("getTransCancel")
    public Message getTransCancel(@RequestBody SysUser sysUser) {
        Message msg = new Message();
        System.out.println("getTransCancel  sysUser  ==>" + sysUser);
        msg.setCode(200);
        msg.setObj(walletTransactionAuditServiceImp.getAllTransCanceled(sysUser.getUserId()));
        msg.setMsg("successfully get all account's canceled transactions");
        return msg;
    }
    /////////////////////////////////////

    @PostMapping("saveAccount")
    public Message saveAccount(@RequestBody WalletAccount walletAccount){
        Message msg = new Message();
        System.out.println("saveAccount  walletAccount==>"+walletAccount);
        SysUser user=sysUserService.getUserInfoByUserId(walletAccount.getBUYER_ID());
        System.out.println("saveAccount  user==>"+user);
        if(user!=null){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            walletAccount.setACCOUNT_NAME(user.getUsername());
            walletAccount.setACCOUNT_TYPE(Integer.parseInt(user.getRoleId()));
            walletAccount.setCREATE_BY(user.getUsername());
            walletAccount.setCREATE_TIME(dtf.format(now));

            WalletAccount wacc=walletAccountServiceImp.getById(user.getUserId());
            if(wacc!=null){
                walletAccountServiceImp.UpdateWalletAccount(walletAccount);

            }else{
                System.out.println("Insert Wallet  Account==>"+walletAccount);
                walletAccountServiceImp.InsertWalletAccount(walletAccount);
                WalletAccountPerm acPerm=new WalletAccountPerm();
                acPerm.setBuyer_id(walletAccount.getBUYER_ID());
                acPerm.setAdd_withdraw(0);
                acPerm.setAdd_deposit(0);
                acPerm.setIs_direct_withdraw(0);
                acPerm.setIs_direct_transaction(0);
                acPerm.setIs_direct_deposit(0);

                WalletAccountFund wacfund=new WalletAccountFund();
                wacfund.setCOMMISSION(0);
                wacfund.setWITHDRAWING_MONEY(0);
                wacfund.setDEPOSITING_MONEY(0);
                wacfund.setBUYER_ID(walletAccount.getBUYER_ID());
                wacfund.setCREATE_BY(user.getUsername());
                wacfund.setCREATE_TIME(dtf.format(now));
                walletAccountFundServiceImp.InsertWalletAccountFund(wacfund);

                walletAccountPermServiceImp.insertWalletAccountPerm(acPerm);
                System.out.println("Insert Wallet  Account perms==>"+acPerm);
            }
            msg.setCode(200);
            msg.setMsg("Successfully saved wallet account");

        }else{
            msg.setCode(504);
            msg.setMsg("failure created wallet account");
        }
        return msg;
    }
    @PostMapping("savePerm")
    public Message savePerm(@RequestBody WalletAccountPerm walletAccountPerm){
        Message msg = new Message();
        System.out.println("savePerm  WalletAccountPerm==>"+walletAccountPerm);
        SysUser user=sysUserService.getUserInfoByUserId(walletAccountPerm.getBuyer_id());
        WalletAccount account=walletAccountServiceImp.getByBuyerId(walletAccountPerm.getBuyer_id());
        if(account!=null){
            walletAccountPermServiceImp.updateWalletAccountPerm(walletAccountPerm);
            msg.setCode(200);
            msg.setMsg("Successfully saved wallet account permissions");

        }else{
            msg.setCode(504);
            msg.setMsg("failure saved wallet account permissions");
        }
        return msg;
    }

    @PostMapping("checkPassword")
    public Message checkPassword(@RequestBody WalletAccount walletAccount){
        Message msg = new Message();
        System.out.println("checkPassword  walletAccount==>"+walletAccount);
        SysUser user=sysUserService.getUserInfoByUserId(walletAccount.getBUYER_ID());
        WalletAccount account=walletAccountServiceImp.getByBuyerId(walletAccount.getBUYER_ID());
        System.out.println("checkPassword  account==>"+account);
        String isActive=account.getIS_ACTIVE();
       /* if(account.getIS_ACTIVE()==null){
            msg.setCode(504);
            msg.setMsg("This account is dis-active please contact with administrator");
        }else if(isActive.equals("N")){
            msg.setCode(504);
            msg.setMsg("This account is dis-active please contact with administrator");
        }else{*/
        if(account.getPASSWORD().equals(walletAccount.getPASSWORD())){
            msg.setCode(200);
            msg.setMsg("Matched");

        }else{
            msg.setCode(504);
            msg.setMsg("The password doesn't match");
        }
        // }
        return msg;
    }


    @PostMapping("/deposit")
    public Message Deposit(@RequestBody DepositItem depositItem){
        Message msg = new Message();
        SysUser user = sysUserService.getUserInfoByUserId(depositItem.getUser_id());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        WalletAccount walletAccount = walletAccountServiceImp.getByBuyerId(depositItem.getUser_id());
        System.out.println("Deposit  depositItem==>"+depositItem.toString());
        System.out.println("walletAccount  walletAccount==>"+walletAccount);
        if(walletAccount.getPASSWORD().equals(depositItem.getPassword())){
            WalletAccountPerm perm=walletAccountPermServiceImp.getByBuyerId(depositItem.getUser_id());
            if(perm!=null && perm.getIs_direct_deposit().equals(1)){
                WalletFundOrder wforder = new WalletFundOrder();
                wforder.setBuyer_Id(depositItem.getUser_id());
                wforder.setTransaction_type_id(1);
                wforder.setDepositing_money(depositItem.getDepositing_money());
                BigDecimal b = new BigDecimal(0);
                wforder.setWithdrawing_money(0);
                WalletCommission comm = walletCommissionServiceImp.getByCommTypeId(1);
                if (comm != null) {
                    float commvalue1 = walletCommissionServiceImp.getCommValue(depositItem.getDepositing_money(), comm);
                    System.out.println("Commission comm :" +comm);
                    System.out.println("Commission value :" +commvalue1);
                    BigDecimal cvalue = new BigDecimal(commvalue1);
                    wforder.setCommission(commvalue1);
                }
                wforder.setCreate_by(user.getUsername());
                wforder.setCreate_time(dtf.format(now));
                System.out.println("Successfully depositing directly");
                WalletAccountFund waFund=walletAccountFundServiceImp.getById(depositItem.getUser_id());
                walletAccountFundServiceImp.PassedFund(waFund, wforder,user,0);
                walletFundOrderServiceImp.insertWalletFundOrder(wforder);

                msg.setCode(200);
                msg.setMsg("Successfully deposited");

            }else {
                WalletFundOrder orderexist = walletFundOrderServiceImp.getByBuyerId(depositItem.getUser_id());
                if (orderexist != null) {
                    msg.setCode(504);
                    msg.setMsg("Failure sent the deposit request to administrator," +
                            " because there another request didn't reply for it" +
                            " Please contact with administrator");
                    return msg;
                } else {
                    WalletFundOrder wforder = new WalletFundOrder();
                    wforder.setBuyer_Id(depositItem.getUser_id());
                    wforder.setTransaction_type_id(1);
                    wforder.setDepositing_money(depositItem.getDepositing_money());
                    BigDecimal b = new BigDecimal(0);
                    wforder.setWithdrawing_money(0);
                    WalletCommission comm = walletCommissionServiceImp.getByCommTypeId(1);
                    if (comm != null) {
                        float commvalue1 = walletCommissionServiceImp.getCommValue(depositItem.getDepositing_money(), comm);
                        BigDecimal cvalue = new BigDecimal(commvalue1);
                        wforder.setCommission(commvalue1);
                    }
                    wforder.setCreate_by(user.getUsername());
                    wforder.setCreate_time(dtf.format(now));
                    walletFundOrderServiceImp.insertWalletFundOrder(wforder);
                    msg.setCode(200);
                    msg.setMsg("Successfully sent the deposit request to administrator, Please wait reply");
                }
            }

        }else{
            msg.setCode(504);
            msg.setMsg("The password is invalid");
        }
        return msg;
    }

    @PostMapping("/withdraw")
    public  Message Withdraw(@RequestBody WithdrawItem withdrawItem){
        Message msg = new Message();
        SysUser user = sysUserService.getUserInfoByUserId(withdrawItem.getUser_id());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        if (user == null) {
            throw BSSException.USERNAME_NOT_EXISTS;
        }
        System.out.println("withdrawItem ===> "+withdrawItem);
        WalletAccount walletAccount = walletAccountServiceImp.getById(withdrawItem.getUser_id());
        if(walletAccount.getPASSWORD().equals(withdrawItem.getPassword())){
            WalletAccountPerm perm=walletAccountPermServiceImp.getByBuyerId(withdrawItem.getUser_id());
            if(perm!=null && perm.getIs_direct_withdraw().equals(1)){
                WalletFundOrder wforder = new WalletFundOrder();
                wforder.setBuyer_Id(withdrawItem.getUser_id());
                wforder.setTransaction_type_id(2);
                wforder.setWithdrawing_money(withdrawItem.getWithdraw_money());
                BigDecimal b = new BigDecimal(0);
                System.out.println("actual withdrawItem  ===> "+wforder);
                WalletCommission comm = walletCommissionServiceImp.getByCommTypeId(2);
                if (comm != null) {
                    float commvalue1 = walletCommissionServiceImp.getCommValue(withdrawItem.getWithdraw_money(), comm);
                    BigDecimal cvalue = new BigDecimal(commvalue1);
                    wforder.setCommission(commvalue1);
                }
                wforder.setCreate_by(user.getUsername());
                wforder.setCreate_time(dtf.format(now));

                WalletAccountFund waFund=walletAccountFundServiceImp.getById(withdrawItem.getUser_id());
                walletAccountFundServiceImp.PassedFund(waFund, wforder,user,0);
                walletFundOrderServiceImp.insertWalletFundOrder(wforder);
                msg.setCode(200);
                msg.setMsg("Successfully withdraw");
            }else {
                WalletFundOrder orderexist=walletFundOrderServiceImp.getByBuyerId(withdrawItem.getUser_id());
                if(orderexist!=null){
                    msg.setCode(504);
                    msg.setMsg("Failure sent the withdraw request to administrator," +
                            " because there another request didn't reply for it" +
                            " Please contact with administrator");
                    return msg;
                }else {
                    WalletFundOrder wforder = new WalletFundOrder();
                    wforder.setBuyer_Id(withdrawItem.getUser_id());
                    wforder.setTransaction_type_id(2);
                    BigDecimal b = new BigDecimal(0);
                    wforder.setDepositing_money(0);
                    wforder.setWithdrawing_money(withdrawItem.getWithdraw_money());
                    wforder.setCreate_by(user.getUsername());
                    wforder.setCreate_time(dtf.format(now));
                    walletFundOrderServiceImp.insertWalletFundOrder(wforder);
                    msg.setCode(200);
                    msg.setMsg("Successfully sent the withdraw request to administrator, Please wait reply");
                }
            }



        }else{
            msg.setCode(504);
            msg.setMsg("The password is invalid");
        }
        return msg;
    }
    /******************* Funds ************/
    @PostMapping("getAllfund")
    public Message getAllfund(@RequestBody SysUser sysUser){
        Message msg = new Message();
        System.out.println("getAllfund  ==> :"+sysUser);
        SysUser user=  sysUserService.getUserInfoByUserId(sysUser.getUserId());
        if(user!=null){
            msg.setCode(200);
            msg.setObj(walletFundOrderServiceImp.getAll());
            msg.setMsg("successfully get all funds");
        }else{
            msg.setCode(504);
            msg.setMsg("failure got funds");
        }
        return msg;
    }

    @PostMapping("fundPass")
    public Message fundPass(@RequestBody WalletFundOrder walletFundOrder){
        Message msg = new Message();
        WalletFundOrder fundOrder= walletFundOrderServiceImp.getByBuyerId(walletFundOrder.getBuyer_Id());
        System.out.println("fundPass  fundOrder  ==> :"+fundOrder);
        if(fundOrder!=null){
            WalletAccountFund walletAccountFund=walletAccountFundServiceImp.getById(fundOrder.getBuyer_Id());
            SysUser user=sysUserService.getUserInfoByUserId(fundOrder.getBuyer_Id());
            System.out.println("fundPass  walletAccountFund  ==> :"+walletAccountFund);
            System.out.println("fundPass   user==> :"+user);
            walletAccountFundServiceImp.PassedFund(walletAccountFund, fundOrder,user,0);
            msg.setCode(200);
            msg.setObj(walletFundOrderServiceImp.getAll());
            msg.setMsg("successfully get all funds");
        }else{
            msg.setCode(504);
            msg.setMsg("failure got funds");
        }
        return msg;
    }

    @PostMapping("canceledfund")
    public Message canceledfund(@RequestBody FundItem fundItem){
        Message msg = new Message();
        WalletFundOrder fundOrder= walletFundOrderServiceImp.getByBuyerId(fundItem.getBuyer_id());
        System.out.println("canceledfund  fundItem  ==> :"+fundItem);
        if(fundOrder!=null){
            WalletAccountFund walletAccountFund=walletAccountFundServiceImp.getById(fundOrder.getBuyer_Id());
            SysUser user=sysUserService.getUserInfoByUserId(fundOrder.getBuyer_Id());
            System.out.println("fundPass  walletAccountFund  ==> :"+walletAccountFund);
            System.out.println("fundPass   user==> :"+user);
            walletAccountFundServiceImp.CancelFund(walletAccountFund, fundOrder,user,fundItem.getCancelreason());
            msg.setCode(200);
            msg.setObj(walletFundOrderServiceImp.getAll());
            msg.setMsg("successfully get all funds");
        }else{
            msg.setCode(504);
            msg.setMsg("failure got funds");
        }
        return msg;
    }

    /******************* End Funds ************/
}
