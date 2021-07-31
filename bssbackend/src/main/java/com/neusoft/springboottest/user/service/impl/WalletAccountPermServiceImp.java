package com.neusoft.springboottest.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.springboottest.user.entity.WalletAccountPerm;
import com.neusoft.springboottest.user.entity.WalletFundOrder;
import com.neusoft.springboottest.user.mapper.WalletAccountPermMapper;
import com.neusoft.springboottest.user.mapper.WalletFundOrderMapper;
import com.neusoft.springboottest.user.service.IWalletAccountPermService;
import com.neusoft.springboottest.user.service.IWalletFundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletAccountPermServiceImp extends ServiceImpl<WalletAccountPermMapper, WalletAccountPerm> implements IWalletAccountPermService {

    @Autowired
    WalletAccountPermMapper walletAccountPermMapper;

    @Override
    public int insertWalletAccountPerm(WalletAccountPerm walletAccountPerm) {
        return walletAccountPermMapper.insertWalletAccountPerm(walletAccountPerm);
    }

    @Override
    public int updateWalletAccountPerm(WalletAccountPerm walletAccountPerm) {
        return walletAccountPermMapper.updateWalletAccountPerm(walletAccountPerm);
    }

    @Override
    public  int deleteByBuyerId(Integer buyer_id){
        return walletAccountPermMapper.deleteByBuyerId(buyer_id);
    }
    @Override
    public  WalletAccountPerm getByBuyerId(Integer buyer_id){
        return walletAccountPermMapper.getByBuyerId(buyer_id);
    }

    @Override
    public  void savePerm(WalletAccountPerm walletAccountPerm){
        WalletAccountPerm acPerm=getByBuyerId(walletAccountPerm.getBuyer_id());
        if(acPerm!=null){
            acPerm.setIs_direct_deposit(walletAccountPerm.getIs_direct_deposit());
            acPerm.setIs_direct_transaction(walletAccountPerm.getIs_direct_transaction());
            acPerm.setIs_direct_withdraw(walletAccountPerm.getIs_direct_withdraw());
            acPerm.setAdd_deposit(walletAccountPerm.getAdd_deposit());
            acPerm.setAdd_withdraw(walletAccountPerm.getAdd_withdraw());
            updateWalletAccountPerm(acPerm);
        }else{
            acPerm=new WalletAccountPerm();
            acPerm.setIs_direct_deposit(walletAccountPerm.getIs_direct_deposit());
            acPerm.setIs_direct_transaction(walletAccountPerm.getIs_direct_transaction());
            acPerm.setIs_direct_withdraw(walletAccountPerm.getIs_direct_withdraw());
            acPerm.setAdd_deposit(walletAccountPerm.getAdd_deposit());
            acPerm.setAdd_withdraw(walletAccountPerm.getAdd_withdraw());
            insertWalletAccountPerm(acPerm);
        }
    }

}
