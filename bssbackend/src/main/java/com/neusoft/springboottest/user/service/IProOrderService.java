package com.neusoft.springboottest.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.springboottest.user.entity.ProOrder;

import java.util.List;


public interface IProOrderService extends IService<ProOrder> {
    int sendOrder(ProOrder proOrder);
    List<ProOrder> getBVOSentOrder(int bvo_user_id);


    ProOrder getOrderById(int or_id);
    int deleteOrder(ProOrder proOrder);
    int paymentOrder(ProOrder proOrder);

    List<ProOrder> getBVOOrders(int bvo_user_id,int sts_cd);
    /*********** MVO ***********/

    List<ProOrder> getMVOOrders(int man_id,int sts_cd);
    int allowedOrder(int or_id);

    int mvoCancelOrder(ProOrder proOrder);
    int mvoShipmentOrder(ProOrder proOrder);

    int bvoDeliveredOrder(ProOrder proOrder);

    int refundOrder(int or_id,int sts_cd);
}