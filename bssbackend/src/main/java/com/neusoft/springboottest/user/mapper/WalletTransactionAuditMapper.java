package com.neusoft.springboottest.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neusoft.springboottest.user.entity.WalletTransactionAudit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("WalletTransactionAuditMapper")
public interface WalletTransactionAuditMapper extends BaseMapper<WalletTransactionAudit> {

    int insertWalletTransAudit(WalletTransactionAudit walletTransactionAudit);
    int updateWalletTransAudit(WalletTransactionAudit walletTransactionAudit);
    WalletTransactionAudit getById(Integer transaction_audit_id);

    List<WalletTransactionAudit> getAllTransactions(Integer buyer_id);
    List<WalletTransactionAudit> getAllTransCanceled(Integer buyer_id);

    List<WalletTransactionAudit> getTransPaidByOrderId(Integer TRANSACTION_TYPE_ID,Integer order_id);


}
