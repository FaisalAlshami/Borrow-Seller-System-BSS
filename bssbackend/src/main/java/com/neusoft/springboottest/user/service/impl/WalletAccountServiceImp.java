package com.neusoft.springboottest.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.springboottest.user.entity.ProOrder;
import com.neusoft.springboottest.user.entity.WalletAccount;
import com.neusoft.springboottest.user.mapper.ProOrderMapper;
import com.neusoft.springboottest.user.mapper.WalletAccountMapper;
import com.neusoft.springboottest.user.service.IProOrderService;
import com.neusoft.springboottest.user.service.IWalletAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletAccountServiceImp extends ServiceImpl<WalletAccountMapper, WalletAccount> implements IWalletAccountService {

    @Autowired
    WalletAccountMapper walletAccountMapper;

    @Override
    public int InsertWalletAccount(WalletAccount walletAccount) {
        return walletAccountMapper.insertWalletAcc(walletAccount);
    }
    @Override
    public int UpdateWalletAccount(WalletAccount walletAccount) {
        return walletAccountMapper.updateWalletAcc(walletAccount);
    }
    @Override
    public WalletAccount getByBuyerId(Integer buyer_id){
        return walletAccountMapper.getByBuyerId(buyer_id);
    }

    @Override
    public List<WalletAccount> getAll(){
        return walletAccountMapper.getAll();
    }
}
