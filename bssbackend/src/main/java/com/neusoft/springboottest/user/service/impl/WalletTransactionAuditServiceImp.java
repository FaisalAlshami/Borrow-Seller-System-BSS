package com.neusoft.springboottest.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.springboottest.user.entity.WalletTransactionAudit;
import com.neusoft.springboottest.user.mapper.WalletTransactionAuditMapper;
import com.neusoft.springboottest.user.service.IWalletTransactionAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletTransactionAuditServiceImp extends ServiceImpl<WalletTransactionAuditMapper, WalletTransactionAudit>
        implements IWalletTransactionAuditService {

    @Autowired
    WalletTransactionAuditMapper walletTransactionAuditMapper;

    @Override
    public int InsertWalletTransactionAudit(WalletTransactionAudit walletTransactionAudit) {
        return walletTransactionAuditMapper.insertWalletTransAudit(walletTransactionAudit);
    }
    @Override
    public int UpdateWalletTransactionAudit(WalletTransactionAudit walletTransactionAudit) {
        return walletTransactionAuditMapper.updateWalletTransAudit(walletTransactionAudit);
    }

    @Override
    public  WalletTransactionAudit getById(Integer transaction_audit_id){
        return walletTransactionAuditMapper.getById(transaction_audit_id);
    }

    @Override
    public  List<WalletTransactionAudit> getAllTransactions(Integer buyer_id){
        return walletTransactionAuditMapper.getAllTransactions(buyer_id);
    }
    @Override
    public  List<WalletTransactionAudit> getAllTransCanceled(Integer buyer_id){
        return walletTransactionAuditMapper.getAllTransCanceled(buyer_id);
    }


}
