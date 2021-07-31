package com.neusoft.springboottest.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neusoft.springboottest.user.entity.WalletAccount;
import com.neusoft.springboottest.user.entity.WalletAccountFund;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component("WalletAccountFundMapper")
public interface WalletAccountFundMapper extends BaseMapper<WalletAccountFund> {

    int insertWalletAccfund(WalletAccountFund walletAccountFund);
    int updateWalletAccfund(WalletAccountFund walletAccountFund);
    WalletAccountFund getById(Integer buyer_id);
    int getTotalCommission();

}
