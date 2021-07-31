package com.neusoft.springboottest.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.springboottest.user.entity.*;

public interface IWalletAccountFundService extends IService<WalletAccountFund> {

    int InsertWalletAccountFund(WalletAccountFund walletAccountFund);
    int UpdateWalletAccountFund(WalletAccountFund walletAccountFund);
    WalletAccountFund getById(Integer buyer_id);

    int PassedFund(WalletAccountFund  walletAccountFund, WalletFundOrder walletFundOrder,
                   SysUser user,int order_id);
    void CancelFund(WalletAccountFund  walletAccountFund, WalletFundOrder walletFundOrder,
                    SysUser user,String reasonCancel);

    void Payment(SysUser user, float payAmount, int buyer_id, int man_id, ProOrder proOrder,int transaction_id);
}
