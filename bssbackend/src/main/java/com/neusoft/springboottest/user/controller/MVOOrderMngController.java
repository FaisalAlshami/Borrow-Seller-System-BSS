package com.neusoft.springboottest.user.controller;

import com.neusoft.springboottest.dto.Message;
import com.neusoft.springboottest.user.dto.MVOOrderItem;
import com.neusoft.springboottest.user.dto.PaymentInfoByBVO;
import com.neusoft.springboottest.user.entity.*;
import com.neusoft.springboottest.user.mapper.WalletTransactionAuditMapper;
import com.neusoft.springboottest.user.service.*;
import com.neusoft.springboottest.user.service.impl.WalletAccountFundServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/user/mvo-order")
public class MVOOrderMngController {
    @Autowired
    IProOrderService iProOrderService;
    @Autowired
    ISysUserService iSysUserService;
    @Autowired
    IManManufacturerService iManManufacturerService;
    @Autowired
    IProProductService iProProductService;

    @Autowired
    WalletAccountFundServiceImp walletAccountFundServiceImp;

    @Autowired
    WalletTransactionAuditMapper walletTransactionAuditMapper;

    @PostMapping("getAllMVOOrders")
    public Message getAllMVOOrders(@RequestBody MVOOrderItem mvoOrderItem) {
        System.out.println("getAllMVOOrders mvoOrderItem===> " + mvoOrderItem);
        Message msg = new Message();
        SysUser user=iSysUserService.getUserInfoByUserId(mvoOrderItem.getUser_id());
        ManManufacturer company=iManManufacturerService.getByManId(user.getManBuyerId());
        System.out.println("ManManufacturer company===> " + company);
        if(company!=null){
            List<ProOrder> list=iProOrderService.getMVOOrders(company.getMAN_ID(),mvoOrderItem.getStatus_id());
            msg.setCode(200);
            msg.setObj(list);
            msg.setMsg("Successfully get all MVO orders");
        }else{
            msg.setCode(504);
            msg.setMsg("failure MVO the orders");
        }
        return  msg;
    }

    @PostMapping("allowedOrder")
    public Message allowedOrder(@RequestBody MVOOrderItem mvoOrderItem){
        System.out.println("allowedOrder===> "+mvoOrderItem);
        Message msg=new Message();
        SysUser user=iSysUserService.getUserInfoByUserId(mvoOrderItem.getUser_id());
        ProOrder order=iProOrderService.getOrderById(mvoOrderItem.getO_id());
        if(user!=null && order!=null){
            //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            //LocalDateTime now = LocalDateTime.now();
            int id=iProOrderService.allowedOrder(order.getOr_id());
            msg.setCode(200);
            msg.setMsg("successfully allowed payment to the order");
        }else{
            msg.setCode(504);
            msg.setMsg("failure sent the order");
        }
        return msg;
    }

    @PostMapping("cancelOrder")
    public Message cancelOrder(@RequestBody MVOOrderItem mvoOrderItem){
        System.out.println("cancelOrder mvoOrderItem===> "+mvoOrderItem);
        Message msg=new Message();
        SysUser user=iSysUserService.getUserInfoByUserId(mvoOrderItem.getUser_id());
        ProOrder order=iProOrderService.getOrderById(mvoOrderItem.getO_id());
        if(user!=null && order!=null){
            if(order.getSts_cd()!=0 || order.getSts_cd()!=2){
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                order.setCancelled_time(dtf.format(now));
                order.setCanceled_by(user.getUsername());
                order.setCanceled_reason(mvoOrderItem.getCancelreason());
                int id=iProOrderService.mvoCancelOrder(order);
                msg.setCode(200);
                msg.setMsg("successfully allowed payment to the order");

            }else{
                msg.setCode(504);
                msg.setMsg("failure cancel the order because the order status is "+order.getSts_cd());
            }

        }else{
            msg.setCode(504);
            msg.setMsg("failure sent the order");
        }
        return msg;
    }

    @PostMapping("shipmentOrder")
    public Message shipmentOrder(@RequestBody MVOOrderItem mvoOrderItem){
        System.out.println("shipmentOrder mvoOrderItem===> "+mvoOrderItem);
        Message msg=new Message();
        SysUser user=iSysUserService.getUserInfoByUserId(mvoOrderItem.getUser_id());
        ProOrder order=iProOrderService.getOrderById(mvoOrderItem.getO_id());
        if(user!=null && order!=null){
            if(order.getSts_cd()==6){
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                order.setShip_time(dtf.format(now));
                order.setShip_by(user.getUsername());
                order.setTracking_company(mvoOrderItem.getTrackingCompany());
                int id=iProOrderService.mvoShipmentOrder(order);
                msg.setCode(200);
                msg.setMsg("successfully shipped the order");

            }else{
                msg.setCode(504);
                msg.setMsg("failure shipped the order because didn't payment to this order");
            }

        }else{
            msg.setCode(504);
            msg.setMsg("failure shipped the order");
        }
        return msg;
    }

    @PostMapping("refundOrder")
    public Message refundOrder(@RequestBody MVOOrderItem mvoOrderItem) {

        System.out.println("refund mvoOrderItem===> " + mvoOrderItem);
        Message msg = new Message();
        SysUser user=iSysUserService.getUserInfoByUserId(mvoOrderItem.getUser_id());
        ProOrder order=iProOrderService.getOrderById(mvoOrderItem.getO_id());
        if(user!=null & order!=null){

                WalletAccountFund buyer_wafund=walletAccountFundServiceImp.getById(order.getMan_id());
                System.out.println("refund walletAccountFund :==>"+buyer_wafund);
                float balance_actual=buyer_wafund.getDEPOSITING_MONEY()+buyer_wafund.getWITHDRAWING_MONEY();

                List<WalletTransactionAudit> audits=walletTransactionAuditMapper
                        .getTransPaidByOrderId(4,mvoOrderItem.getO_id());
                float price=audits.get(0).getDEPOSITING_MONEY_ACTUAL();
               System.out.println(" Actual Transaction:==>"+audits.get(0));
                System.out.println(" price :==>"+price+"  balance_actual==>"+balance_actual);
                if(balance_actual>=price) {
                    WalletAccountFund manufacturer = walletAccountFundServiceImp.getById(order.getMan_id());
                    if (manufacturer == null) {
                        msg.setCode(504);
                        msg.setMsg("failure, the manufacturer doesn't have the wallet on the system");
                        return msg;
                    }
                    walletAccountFundServiceImp.Payment(user, price,order.getMan_id() ,
                            order.getBvo_user_id(), order,5);
                    WalletAccountFund receipt_wafund = walletAccountFundServiceImp.getById(user.getUserId());

                }else{
                    msg.setCode(502);
                    msg.setMsg("failure, Your balance doesn't enough please deposit to your the wallet");
                    return msg;
                }
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                iProOrderService.refundOrder(order.getOr_id(),7);
                msg.setCode(200);
                msg.setMsg("successfully refund order");



        }else {
            msg.setCode(504);
            msg.setMsg("failure get all orders waiting for payment");
        }
        return  msg;
    }
}
