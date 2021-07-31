package com.neusoft.springboottest.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.springboottest.user.entity.WalletAccount;
import com.neusoft.springboottest.user.entity.WalletCommission;
import com.neusoft.springboottest.user.entity.WalletFundOrder;
import com.neusoft.springboottest.user.mapper.WalletCommissionMapper;
import com.neusoft.springboottest.user.mapper.WalletFundOrderMapper;
import com.neusoft.springboottest.user.service.IWalletCommissionService;
import com.neusoft.springboottest.user.service.IWalletFundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletFundOrderServiceImp extends ServiceImpl<WalletFundOrderMapper, WalletFundOrder> implements IWalletFundOrderService {

    @Autowired
    WalletFundOrderMapper walletFundOrderMapper;

    @Override
    public int insertWalletFundOrder(WalletFundOrder walletFundOrder) {
        return walletFundOrderMapper.insertWalletFundOrder(walletFundOrder);
    }
    @Override
    public  List<WalletFundOrder> getAll(){
        return walletFundOrderMapper.getAll();
    }

    @Override
    public  int deleteById(Integer buyer_id){
        return walletFundOrderMapper.deleteById(buyer_id);
    }
    @Override
    public  WalletFundOrder getByBuyerId(Integer buyer_id){
        return walletFundOrderMapper.getByBuyerId(buyer_id);
    }

}
