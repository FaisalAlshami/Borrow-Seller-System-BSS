package com.neusoft.springboottest.user.mapper;

import com.neusoft.springboottest.user.entity.ProProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author phil
 * @since 2021-01-26
 */
@Mapper
@Component("ProProductMapper")
public interface ProProductMapper extends BaseMapper<ProProduct> {

    int updatePaymentQty(int ship_qty,int pro_id);
    int updateRefundQty(int refund_qty,int pro_id);
    int getTotalGoods();
}
