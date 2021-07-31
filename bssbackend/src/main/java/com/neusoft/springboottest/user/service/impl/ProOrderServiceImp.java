package com.neusoft.springboottest.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.springboottest.user.entity.ProOrder;
import com.neusoft.springboottest.user.mapper.ProOrderMapper;
import com.neusoft.springboottest.user.service.IProOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProOrderServiceImp extends ServiceImpl<ProOrderMapper, ProOrder> implements IProOrderService {

    @Autowired
    ProOrderMapper proOrderMapper;

    @Override
    public int sendOrder(ProOrder proOrder) {
        return proOrderMapper.sendOrder(proOrder);
    }

    @Override
    public List<ProOrder> getBVOSentOrder(int bvo_user_id){
        return proOrderMapper.getBVOSentOrder(bvo_user_id);
    }

    @Override
    public ProOrder getOrderById(int or_id){
        return proOrderMapper.getOrderById(or_id);
    }

    @Override
    public int deleteOrder(ProOrder proOrder){
        return proOrderMapper.deleteOrder(proOrder);
    }


    @Override
    public int paymentOrder(ProOrder proOrder){
        return proOrderMapper.paymentOrder(proOrder);
    }

    @Override
    public List<ProOrder> getBVOOrders(int bvo_user_id,int sts_cd){
        return proOrderMapper.getBVOOrders(bvo_user_id,sts_cd);
    }

    /********** MVO **********/
    @Override
    public List<ProOrder> getMVOOrders(int man_id,int sts_cd){
        return proOrderMapper.getMVOOrders(man_id,sts_cd);
    }
    @Override
    public int allowedOrder(int or_id){
        return proOrderMapper.allowedOrder(or_id);
    }

    @Override
    public int mvoCancelOrder(ProOrder proOrder){
        return proOrderMapper.mvoCancelOrder(proOrder);
    }

    @Override
    public int mvoShipmentOrder(ProOrder proOrder){

        StringBuffer sBuffer = new StringBuffer("");
        for(int i=0;i<10;i ++) {
            int tmp = (int)(10*(Math.random()));
            sBuffer.append(tmp);
        }
        proOrder.setTracking_number(sBuffer.toString());
        return proOrderMapper.mvoShipmentOrder(proOrder);

    }

    @Override
    public int bvoDeliveredOrder(ProOrder proOrder){
        return proOrderMapper.bvoDeliveredOrder(proOrder);
    }

    @Override
    public int refundOrder(int or_id,int sts_cd){
        return proOrderMapper.refundOrder(or_id,sts_cd);
    }
}
