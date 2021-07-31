package com.neusoft.springboottest.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neusoft.springboottest.user.dto.BSSException;
import com.neusoft.springboottest.user.entity.*;
import com.neusoft.springboottest.user.mapper.ProOrderMapper;
import com.neusoft.springboottest.user.mapper.ProProductMapper;
import com.neusoft.springboottest.user.mapper.WalletAccountFundMapper;
import com.neusoft.springboottest.user.service.IWalletAccountFundService;
import com.neusoft.springboottest.user.service.IWalletFundOrderService;
import com.neusoft.springboottest.user.service.IWalletTransactionAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
public class WalletAccountFundServiceImp extends ServiceImpl<WalletAccountFundMapper, WalletAccountFund>
        implements IWalletAccountFundService {

    @Autowired
    WalletAccountFundMapper walletAccountFundMapper;

    @Autowired
    ProOrderMapper proOrderMapper;

    @Autowired
    IWalletTransactionAuditService iWalletTransactionAuditService;

    @Autowired
    IWalletFundOrderService iWalletFundOrderService;

    @Autowired
    WalletCommissionServiceImp walletCommissionServiceImp;

    @Autowired
    ProProductMapper proProductMapper;

    @Override
    public int InsertWalletAccountFund(WalletAccountFund walletAccountFund) {
        return walletAccountFundMapper.insertWalletAccfund(walletAccountFund);
    }
    @Override
    public int UpdateWalletAccountFund(WalletAccountFund walletAccountFund) {
        return walletAccountFundMapper.updateWalletAccfund(walletAccountFund);
    }

    @Override
    public WalletAccountFund getById(Integer buyer_id){
        return walletAccountFundMapper.getById(buyer_id);
    }


    @Override
    public int PassedFund(WalletAccountFund  walletAccountFund, WalletFundOrder walletFundOrder,
                          SysUser user,int order_id){

        WalletTransactionAudit audit=new WalletTransactionAudit();

        audit.setBUYER_ID(walletFundOrder.getBuyer_Id());

        audit.setAVAILABLE_MONEY_BEFORE((walletAccountFund.getDEPOSITING_MONEY()
                +walletAccountFund.getWITHDRAWING_MONEY()));
        audit.setDEPOSITING_MONEY_BEFORE(walletAccountFund.getDEPOSITING_MONEY());
        audit.setWITHDRAWING_MONEY_BEFORE(walletAccountFund.getWITHDRAWING_MONEY());
        audit.setCOMMISSION_BEFORE(walletAccountFund.getCOMMISSION());

        // when dopsiting to account the system calculate its commission and
        // added to the old commission in accountFund commission column
        walletAccountFund.setDEPOSITING_MONEY(walletAccountFund.getDEPOSITING_MONEY()
                +(walletFundOrder.getDepositing_money()-walletFundOrder.getCommission()));
        /// Set withdrawing
        if(walletFundOrder.getWithdrawing_money()!=0) {
            walletAccountFund.setWITHDRAWING_MONEY(walletAccountFund.getWITHDRAWING_MONEY()
                    - (walletFundOrder.getWithdrawing_money()+walletFundOrder.getCommission()));
        }else{
            walletAccountFund.setWITHDRAWING_MONEY(walletAccountFund.getWITHDRAWING_MONEY());
        }
        ////
        walletAccountFund.setCOMMISSION(walletAccountFund.getCOMMISSION()
                +walletFundOrder.getCommission());

        /*audit.setAVAILABLE_MONEY_AFTER((walletAccountFund.getDEPOSITING_MONEY()
                -walletAccountFund.getWITHDRAWING_MONEY()));*/
        audit.setDEPOSITING_MONEY_AFTER(walletAccountFund.getDEPOSITING_MONEY());
        audit.setWITHDRAWING_MONEY_AFTER(walletAccountFund.getWITHDRAWING_MONEY());
        audit.setAVAILABLE_MONEY_AFTER((audit.getDEPOSITING_MONEY_AFTER()
                +audit.getWITHDRAWING_MONEY_AFTER()));
        audit.setCOMMISSION_AFTER(walletAccountFund.getCOMMISSION());

        audit.setDEPOSITING_MONEY_ACTUAL(walletFundOrder.getDepositing_money());
        audit.setWITHDRAWING_MONEY_ACTUAL(walletFundOrder.getWithdrawing_money());
        audit.setCOMMISSION_MONEY_ACTUAL(walletFundOrder.getCommission());
        audit.setTRANSACTION_TYPE_ID(walletFundOrder.getTransaction_type_id());
        audit.setIS_CANCEL(0);
        audit.setOrder_id(order_id);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        audit.setCREATE_BY(user.getUsername());
        audit.setCREATE_TIME(dtf.format(now));

        System.out.println("fundPass   audit==> :"+audit);

        int i=iWalletTransactionAuditService.InsertWalletTransactionAudit(audit);
        if(i>0){
            walletAccountFundMapper.updateWalletAccfund(walletAccountFund);
            iWalletFundOrderService.deleteById(walletFundOrder.getBuyer_Id());
        }
        return i;
    }

    @Override
    public void CancelFund(WalletAccountFund  walletAccountFund, WalletFundOrder walletFundOrder,
                           SysUser user,String reasonCancel){

        WalletTransactionAudit audit=new WalletTransactionAudit();

        audit.setBUYER_ID(walletFundOrder.getBuyer_Id());

        audit.setIS_CANCEL(1);
        audit.setREASON_CANCEL(reasonCancel);
        // when dopsiting to account the system calculate its commission and
        // added to the old commission in accountFund commission column
        walletAccountFund.setDEPOSITING_MONEY(walletAccountFund.getDEPOSITING_MONEY()
                +(walletFundOrder.getDepositing_money()-walletFundOrder.getCommission()));
        /// Set withdrawing
        if(walletFundOrder.getWithdrawing_money()!=0) {
            walletAccountFund.setWITHDRAWING_MONEY(walletAccountFund.getWITHDRAWING_MONEY()
                    - (walletFundOrder.getWithdrawing_money()+walletFundOrder.getCommission()));
        }else{
            walletAccountFund.setWITHDRAWING_MONEY(walletAccountFund.getWITHDRAWING_MONEY());
        }
        ////
        walletAccountFund.setCOMMISSION(walletAccountFund.getCOMMISSION()
                +walletFundOrder.getCommission());


        /*audit.setAVAILABLE_MONEY_AFTER((walletAccountFund.getDEPOSITING_MONEY()
                -walletAccountFund.getWITHDRAWING_MONEY()));*/
        audit.setDEPOSITING_MONEY_AFTER(walletAccountFund.getDEPOSITING_MONEY());
        audit.setWITHDRAWING_MONEY_AFTER(walletAccountFund.getWITHDRAWING_MONEY());
        audit.setAVAILABLE_MONEY_AFTER((audit.getDEPOSITING_MONEY_AFTER()
                -audit.getWITHDRAWING_MONEY_AFTER()));
        audit.setCOMMISSION_AFTER(walletAccountFund.getCOMMISSION());

        audit.setDEPOSITING_MONEY_ACTUAL(walletFundOrder.getDepositing_money());
        audit.setWITHDRAWING_MONEY_ACTUAL(walletFundOrder.getWithdrawing_money());
        audit.setCOMMISSION_MONEY_ACTUAL(walletFundOrder.getCommission());
        audit.setTRANSACTION_TYPE_ID(walletFundOrder.getTransaction_type_id());
        audit.setIS_CANCEL(1);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        audit.setCREATE_BY(user.getUsername());
        audit.setCREATE_TIME(dtf.format(now));

        System.out.println("fundPass   audit==> :"+audit);

        int i=iWalletTransactionAuditService.InsertWalletTransactionAudit(audit);
        if(i>0){
            walletAccountFundMapper.updateWalletAccfund(walletAccountFund);
            iWalletFundOrderService.deleteById(walletFundOrder.getBuyer_Id());
        }
    }


    @Override
    public void Payment(SysUser user,float payAmount,int buyer_id,
                        int man_id, ProOrder proOrder,int transaction_id){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        WalletAccountFund buyer_wafund=this.getById(buyer_id);
        WalletAccountFund man_wafund=this.getById(man_id);

        /// apply withdraw on buyer wallet ///////////////

        WalletFundOrder wforder = new WalletFundOrder();
        wforder.setBuyer_Id(buyer_id);
        wforder.setTransaction_type_id(transaction_id);
        wforder.setWithdrawing_money(payAmount);
        BigDecimal b = new BigDecimal(0);
        System.out.println("actual withdraw by payment process  ===> "+wforder);
        WalletCommission comm = walletCommissionServiceImp.getByCommTypeId(4);
        if (comm != null) {
            float commvalue1 = walletCommissionServiceImp.getCommValue(payAmount, comm);
            BigDecimal cvalue = new BigDecimal(commvalue1);
            wforder.setCommission(commvalue1);
        }
        wforder.setCreate_by(user.getUsername());
        wforder.setCreate_time(dtf.format(now));
        int transwithdraw_id=this.PassedFund(buyer_wafund, wforder,user,proOrder.getOr_id());
        proOrderMapper.updateTransWithdrawId(proOrder.getOr_id(),transwithdraw_id);

        /// apply deposit to manufacturer wallet ///////////////

        WalletFundOrder wmanforder = new WalletFundOrder();
        wmanforder.setBuyer_Id(man_id);
        wmanforder.setTransaction_type_id(transaction_id);
        wmanforder.setDepositing_money(payAmount);

        System.out.println("actual deposit payment  ===> "+wmanforder);
        WalletCommission commdeposit = walletCommissionServiceImp.getByCommTypeId(4);
        if (commdeposit != null) {
            float commvalue1 = walletCommissionServiceImp.getCommValue(payAmount, commdeposit);
            BigDecimal cvalue = new BigDecimal(commvalue1);
            wmanforder.setCommission(commvalue1);
        }
        wmanforder.setCreate_by(user.getUsername());
        wmanforder.setCreate_time(dtf.format(now));
        int transdeposit_id=this.PassedFund(man_wafund, wmanforder,user,proOrder.getOr_id());
        proOrderMapper.updateTransDepositId(proOrder.getOr_id(),transdeposit_id);

        if(transaction_id==4){
            proProductMapper.updatePaymentQty(proOrder.getQTY(),proOrder.getPro_id());
        }else if(transaction_id==5){
            proProductMapper.updateRefundQty(proOrder.getQTY(),proOrder.getPro_id());
        }
    }


}
