package com.neusoft.springboottest.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neusoft.springboottest.user.entity.WalletAccount;
import com.neusoft.springboottest.user.entity.WalletCommission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("WalletCommissionMapper")
public interface WalletCommissionMapper extends BaseMapper<WalletCommission> {

    int insertWalletComm(WalletCommission walletCommission);
    int updateWalletComm(WalletCommission walletCommission);
    WalletCommission getByIdWithCommTypeId(Integer commission_id,Integer transaction_type_id);
    WalletCommission getByCommTypeId(Integer transaction_type_id);

    WalletCommission getByCommId(Integer commission_id);
    List<WalletAccount> getAll();

    int deleteById(Integer commission_id);

}
