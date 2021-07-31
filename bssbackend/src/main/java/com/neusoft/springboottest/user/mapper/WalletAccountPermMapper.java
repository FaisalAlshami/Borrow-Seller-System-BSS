package com.neusoft.springboottest.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neusoft.springboottest.user.entity.WalletAccountPerm;
import com.neusoft.springboottest.user.entity.WalletFundOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("WalletAccountPermMapper")
public interface WalletAccountPermMapper extends BaseMapper<WalletAccountPerm> {

    int insertWalletAccountPerm(WalletAccountPerm walletAccountPerm);
    int updateWalletAccountPerm(WalletAccountPerm walletAccountPerm);
    int deleteByBuyerId(Integer buyer_id);
    WalletAccountPerm getByBuyerId(Integer buyer_id);

}
