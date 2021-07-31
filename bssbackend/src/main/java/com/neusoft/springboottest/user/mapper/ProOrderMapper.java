package com.neusoft.springboottest.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neusoft.springboottest.user.entity.ManManufacturer;
import com.neusoft.springboottest.user.entity.ProOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("ProOrderMapper")
public interface ProOrderMapper extends BaseMapper<ProOrder> {
    int sendOrder(ProOrder proOrder);
    List<ProOrder> getBVOSentOrder(int bvo_user_id);
    ProOrder getOrderById(int or_id);
    int deleteOrder(ProOrder proOrder);
    int paymentOrder(ProOrder proOrder);

    List<ProOrder> getBVOOrders(int bvo_user_id,int sts_cd);

    List<ProOrder> getMVOOrders(int man_id,int sts_cd);
    int allowedOrder(int or_id);
    int mvoCancelOrder(ProOrder proOrder);
    int mvoShipmentOrder(ProOrder proOrder);
    int bvoDeliveredOrder(ProOrder proOrder);

    int updateTransWithdrawId(int or_id,int  w_id);
    int updateTransDepositId(int or_id,int d_id);
    int refundOrder(int or_id,int sts_cd);

    int getTotalOrders();

}
