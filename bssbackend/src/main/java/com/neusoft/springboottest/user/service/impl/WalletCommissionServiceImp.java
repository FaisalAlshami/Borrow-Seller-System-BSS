package com.neusoft.springboottest.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.springboottest.user.entity.WalletAccount;
import com.neusoft.springboottest.user.entity.WalletCommission;
import com.neusoft.springboottest.user.mapper.WalletAccountMapper;
import com.neusoft.springboottest.user.mapper.WalletCommissionMapper;
import com.neusoft.springboottest.user.service.IWalletAccountService;
import com.neusoft.springboottest.user.service.IWalletCommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class WalletCommissionServiceImp extends ServiceImpl<WalletCommissionMapper, WalletCommission> implements IWalletCommissionService {

    @Autowired
    WalletCommissionMapper walletCommissionMapperp;

    @Override
    public int insertWalletCommission(WalletCommission walletCommission) {
        return walletCommissionMapperp.insertWalletComm(walletCommission);
    }
    @Override
    public int updateWalletCommission(WalletCommission walletCommission) {
        return walletCommissionMapperp.updateWalletComm(walletCommission);
    }
    @Override
    public WalletCommission getByIdWithCommTypeId(Integer commission_id,Integer transaction_type_id){
        return walletCommissionMapperp.getByIdWithCommTypeId(commission_id,transaction_type_id);
    }
    @Override
    public WalletCommission getByCommTypeId(Integer transaction_type_id){
        return walletCommissionMapperp.getByCommTypeId(transaction_type_id);
    }
    @Override
    public  WalletCommission getByCommId(Integer commission_id){
        return walletCommissionMapperp.getByCommId(commission_id);
    }

    @Override
    public  List<WalletAccount> getAll(){
        return walletCommissionMapperp.getAll();
    }

    @Override
    public  int deleteById(Integer commission_id){
        return walletCommissionMapperp.deleteById(commission_id);
    }

    @Override
    public  float getCommValue(float amount,WalletCommission comm){
        try{
            float pr=comm.getPercent();
            float amt=amount;
            float result=((amt*pr)/100);
            return result;
        }
        catch (Exception ex){
            return 0;
        }


    }

}
