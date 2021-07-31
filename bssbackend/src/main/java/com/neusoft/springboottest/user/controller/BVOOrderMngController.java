package com.neusoft.springboottest.user.controller;

import com.neusoft.springboottest.dto.Message;
import com.neusoft.springboottest.user.dto.BVOItemOrder;
import com.neusoft.springboottest.user.dto.MVOOrderItem;
import com.neusoft.springboottest.user.dto.PaymentInfoByBVO;
import com.neusoft.springboottest.user.entity.*;
import com.neusoft.springboottest.user.service.IProCartService;
import com.neusoft.springboottest.user.service.IProOrderService;
import com.neusoft.springboottest.user.service.IProProductService;
import com.neusoft.springboottest.user.service.ISysUserService;
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
@RequestMapping("/user/bvo-order")
public class BVOOrderMngController {

    @Autowired
    IProOrderService iProOrderService;
    @Autowired
    ISysUserService iSysUserService;
    @Autowired
    IProCartService iProCartService;
    @Autowired
    IProProductService iProProductService;

    @Autowired
    WalletAccountFundServiceImp walletAccountFundServiceImp;


    @PostMapping("sendOrder")
    public Message sendOrder(@RequestBody BVOItemOrder bvoItemOrder){
        System.out.println("bvoItemOrder===> "+bvoItemOrder);
        Message msg=new Message();
        SysUser user=iSysUserService.getUserInfoByUserId(bvoItemOrder.getUser_id());
        ProCart cart=iProCartService.getById(bvoItemOrder.getCart_id());
        ProProduct product=null;
        if(cart!=null){
            product=iProProductService.getById(cart.getProId());
        }
        if(user!=null && product!=null){
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            ProOrder order=new ProOrder();
            order.setBvo_user_id(user.getUserId());
            order.setPro_id(product.getProId());
            order.setMan_id(product.getManId());
            order.setORDER_ID(bvoItemOrder.getDropshop());
            order.setSALES_PRICE(product.getRetailPrice());
            order.setQTY(cart.getQuantity());
            order.setSKU_NO(product.getSkuCd());
            order.setORDER_CREATED_TIME(dtf.format(now));
            int id=iProOrderService.sendOrder(order);
            if(id>0){
                iProCartService.removeById(cart.getCartId());
                msg.setCode(200);
                msg.setMsg("successfully sent the order");
            }
        }else{
            msg.setCode(504);
            msg.setMsg("failure sent the order");
        }
        return msg;
    }

    @PostMapping("deleteOrder")
    public Message deleteOrder(@RequestBody BVOItemOrder bvoItemOrder){
        // You can delete the order if it's status is "in process" and sts_cd=0
        System.out.println("bvoItemOrder===> "+bvoItemOrder);
        Message msg=new Message();
        SysUser user=iSysUserService.getUserInfoByUserId(bvoItemOrder.getUser_id());
        ProOrder order=iProOrderService.getOrderById(bvoItemOrder.getO_id());
        if(order!=null & user!=null){
            System.out.println("Order ===> "+order);
            System.out.println("User ===> "+user);

            if(user.getUserId()==order.getBvo_user_id()) {
                if (order.getSts_cd() == 0) {
                    iProOrderService.deleteOrder(order);
                    msg.setCode(200);
                    msg.setMsg("successfully deleted the order");
                } else {
                    msg.setCode(504);
                    msg.setMsg("failure deleted the order because is not in process status");
                }
            }else{
                msg.setCode(504);
                msg.setMsg("failure deleted the order because the order created by another user");
            }
        }else{
            msg.setCode(504);
            if(order==null)
                msg.setMsg("failure deleted the order because the order isn't exist");
            else if(user==null)
                msg.setMsg("failure deleted the order because the user isn't exist");
            else if(user==null & order==null)
                msg.setMsg("failure deleted the order because the user and the order aren't exist");
        }
        return msg;
    }


    @PostMapping("getAllBVOOrders")
    public Message getAllBVOOrders(@RequestBody BVOItemOrder bvoItemOrder) {
        System.out.println("bvoItemOrder===> " + bvoItemOrder);
        Message msg = new Message();
        SysUser user=iSysUserService.getUserInfoByUserId(bvoItemOrder.getUser_id());
        if(user!=null){
            List<ProOrder> list=iProOrderService.getBVOOrders(bvoItemOrder.getUser_id(),bvoItemOrder.getStatus_id());
            msg.setCode(200);
            msg.setObj(list);
        }else{
            msg.setCode(504);
            msg.setMsg("failure got the orders");
        }
        return  msg;
    }


    @PostMapping("paymentInfoByBVO")
    public Message paymentInfoByBVO(@RequestBody PaymentInfoByBVO paymentInfoByBVO) {
        System.out.println("paymentInfoByBVO===> " + paymentInfoByBVO);
        Message msg = new Message();
        SysUser user=iSysUserService.getUserInfoByUserId(paymentInfoByBVO.getUser_id());
        ProOrder order=iProOrderService.getOrderById(paymentInfoByBVO.getO_id());
        if(user!=null & order!=null){
            String errormsg="";
            boolean ok=true;
            if(paymentInfoByBVO.getPrice().equals(0)|| paymentInfoByBVO.getPrice()==null) {
                errormsg +=" Price should be large then 0 ";
                ok=false;
            }
            if(paymentInfoByBVO.getReceive_name().length()<1|| paymentInfoByBVO.getReceive_name()==null) {
                errormsg +=" should be enter receiver name";
                ok=false;
            }
            if(paymentInfoByBVO.getReceive_address().length()<1|| paymentInfoByBVO.getReceive_address()==null) {
                errormsg +="  should be enter receiver address";
                ok=false;
            }
            if(paymentInfoByBVO.getReceive_phone().length()<1|| paymentInfoByBVO.getReceive_phone()==null) {
                errormsg +="  should be enter receiver phone";
                ok=false;
            }

            if(ok==false){
                msg.setCode(504);
                msg.setMsg(errormsg);
            }else{
                WalletAccountFund buyer_wafund=walletAccountFundServiceImp.getById(user.getUserId());
                System.out.println("Payment walletAccountFund :==>"+buyer_wafund);
                float balance_actual=buyer_wafund.getDEPOSITING_MONEY()+buyer_wafund.getWITHDRAWING_MONEY();
                float price=Float.parseFloat(paymentInfoByBVO.getPrice().toString());
                if(balance_actual>=price) {
                    WalletAccountFund manufacturer = walletAccountFundServiceImp.getById(order.getMan_id());
                    if (manufacturer == null) {
                        msg.setCode(504);
                        msg.setMsg("failure, the manufacturer doesn't have the wallet on the system");
                        return msg;
                    }
                    walletAccountFundServiceImp.Payment(user, price, order.getBvo_user_id(),
                            order.getMan_id(), order,4);
                    WalletAccountFund receipt_wafund = walletAccountFundServiceImp.getById(user.getUserId());

                }else{
                    msg.setCode(502);
                    msg.setMsg("failure, Your balance doesn't enough please deposit to your the wallet");
                    return msg;
                }
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                order.setSts_cd(6);
                order.setReceive_name(paymentInfoByBVO.getReceive_name());
                order.setReceive_address(paymentInfoByBVO.getReceive_address());
                order.setReceive_phone(paymentInfoByBVO.getReceive_phone());
                order.setSALES_PRICE(paymentInfoByBVO.getPrice());
                order.setPAID_TIME(dtf.format(now));
                order.setPaid_by(user.getUsername());
                iProOrderService.paymentOrder(order);
                msg.setCode(200);
                msg.setMsg("successfully paid");
            }


        }else {
            msg.setCode(504);
            msg.setMsg("failure get all orders waiting for payment");
        }
        return  msg;
    }

    @PostMapping("deliveredOrder")
    public Message deliveredOrder(@RequestBody BVOItemOrder bvoItemOrder) {
        System.out.println("deliveredOrder  bvoItemOrder===> " + bvoItemOrder);
        Message msg = new Message();
        SysUser user=iSysUserService.getUserInfoByUserId(bvoItemOrder.getUser_id());
        ProOrder order=iProOrderService.getOrderById(bvoItemOrder.getO_id());
        if(user!=null & order!=null){
            if(order.getSts_cd()!=3) {
                msg.setCode(504);
                msg.setMsg("failure delivered the order");
            }else{
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                order.setSts_cd(4);
                order.setCompleted_by(user.getUsername());
                order.setCompleted_time(dtf.format(now));
                iProOrderService.bvoDeliveredOrder(order);
                msg.setCode(200);
                msg.setMsg("successfully delivered the order");
            }


        }else {
            msg.setCode(504);
            msg.setMsg("failure delivered the order");
        }
        return  msg;
    }

}
