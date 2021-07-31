package com.neusoft.springboottest.user.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neusoft.springboottest.user.entity.WalletAccount;
import com.neusoft.springboottest.user.entity.WalletCommission;
import com.neusoft.springboottest.user.entity.WalletFundOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("WalletFundOrderMapper")
@TableName("wallet_transaction_audit")
public interface WalletFundOrderMapper extends BaseMapper<WalletFundOrder> {

    int insertWalletFundOrder(WalletFundOrder walletFundOrder);
    List<WalletFundOrder> getAll();

    int deleteById(Integer buyer_id);
    WalletFundOrder getByBuyerId(Integer buyer_id);

}
