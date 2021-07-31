package com.neusoft.springboottest.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neusoft.springboottest.user.entity.WalletAccount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("WalletAccountMapper")
public interface WalletAccountMapper extends BaseMapper<WalletAccount> {

    int insertWalletAcc(WalletAccount walletAccount);
    int updateWalletAcc(WalletAccount walletAccount);
    WalletAccount getByBuyerId(Integer buyer_id);
    List<WalletAccount> getAll();

}
