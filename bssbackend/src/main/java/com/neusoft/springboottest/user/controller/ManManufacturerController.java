package com.neusoft.springboottest.user.controller;

import ch.qos.logback.classic.pattern.SyslogStartConverter;
import com.neusoft.springboottest.dto.ManufactWithUserId;
import com.neusoft.springboottest.dto.Message;
import com.neusoft.springboottest.user.entity.ManManufacturer;
import com.neusoft.springboottest.user.entity.ProBrand;
import com.neusoft.springboottest.user.entity.SysCompany;
import com.neusoft.springboottest.user.entity.SysUser;
import com.neusoft.springboottest.user.service.IManManufacturerService;
import com.neusoft.springboottest.user.service.IProBrandService;
import com.neusoft.springboottest.user.service.ISysCompanyService;
import com.neusoft.springboottest.user.service.ISysUserService;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/user/man-manufacturer")
public class ManManufacturerController {
    @Autowired
    IManManufacturerService iManManufacturerService;
    @Autowired
    ISysUserService iSysUserService;

    @Autowired
    IProBrandService iProBrandService;

    @PostMapping("getAllMans")
    public Message getAllManufacturer(){

        System.out.println("getAllManufacturer :");
        List<ManManufacturer> list= iManManufacturerService.getAllManManufacturer();
        Message msg= new Message();
        if(list!=null && list.size()>0){
            msg.setObj(list);
            msg.setCode(200);
            msg.setMsg("get getAllManufacturer successful");
        }else{
            msg.setCode(504);
            msg.setMsg("get getAllManufacturer failure");
        }
        return msg;
    }


    @PostMapping("getManufacturerByUserID")
    public Message getManufacturerByUserID (@RequestBody SysUser sysUser){
        Message msg = new Message();
        System.out.println("UserId :===>:"+sysUser.getUserId() );
        ManManufacturer manManufacturer=null;
        SysUser user=iSysUserService.getUserInfoByUserId(sysUser.getUserId());
        System.out.println("UserId :===>:"+sysUser.getUserId() +" :==>: ManBuyerId:"+user.getUserId());
        if(user!=null) {
         /*   if(user.getManBuyerId()==null || user.getManBuyerId()==0){
                msg.setCode(504);
                msg.setMsg("This user don't have company information please enter company information");
            }else{*/
                manManufacturer = iManManufacturerService.getByManId(user.getUserId());
                if(manManufacturer==null){
                    msg.setCode(504);
                    msg.setMsg("The company with No. ("+user.getUserId()+") doesn't exist please enter company information");
                }else{
                    msg.setCode(200);
                    msg.setMsg("Success get company information");
                    msg.setObj(manManufacturer);
                }
           // }
        }
        System.out.println("get manManufacturer Info : "+manManufacturer);
        System.out.println(msg);
        return msg;

    }
    @PostMapping("saveManufacturer")
    public Message addManufacturer(@RequestBody ManufactWithUserId manufactWithUserId){
        System.out.println("Manufacturer Info :"+manufactWithUserId.toString());
        Message msg = new Message();
        SysUser user=iSysUserService.getById(manufactWithUserId.getUser_id());
        System.out.println("UserID :"+manufactWithUserId.getUser_id());
        if(user==null){
            msg.setCode(504);
            msg.setMsg("can't save company information because the user don't auth!");
        }else{
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            ManManufacturer manManufacturer=new ManManufacturer();
            manManufacturer.setMAN_ID(user.getUserId());
            manManufacturer.setNAME_EN(manufactWithUserId.getName_en());
            manManufacturer.setNAME_CN(manufactWithUserId.getName_cn());

            manManufacturer.setDESCRIPTION(manufactWithUserId.getDescription());
            manManufacturer.setGMC_REPORT_TYPE(manufactWithUserId.getGmc_report_type());
            manManufacturer.setGMC_REPORT_URL(manufactWithUserId.getGmc_report_url());

            ManManufacturer manManufacturerExist=iManManufacturerService.getByManId(manManufacturer.getMAN_ID());
            if(manManufacturerExist==null){
                System.out.println("The company don't exist :"+manManufacturerExist);
                manManufacturer.setCREATED_BY(user.getUsername());
                manManufacturer.setCREATION_DATE(dtf.format(now));
                iManManufacturerService.insert(manManufacturer);
                user.setManBuyerId(user.getUserId());
                System.out.println("user after updateManBuyerId :"+user);
                iSysUserService.updateManBuyerId(user);

            }else{
                System.out.println("The company is exist ==>  iManManufacturerService.update:  ==>"+manManufacturer);
                manManufacturer.setLAST_UPDATE_BY(user.getUsername());
                manManufacturer.setLAST_UPDATE_DATE(dtf.format(now));
                iManManufacturerService.update(manManufacturer);
            }

            msg.setCode(200);
            msg.setMsg("Save company information  successfully");
        }
        return msg;
    }


    @PostMapping("removeManufacturer")
    public Message removeManufacturer(@RequestBody ManufactWithUserId manufactWithUserId){
        System.out.println("Manufacturer Info :"+manufactWithUserId.toString());
        Message msg = new Message();
        SysUser user=iSysUserService.getById(manufactWithUserId.getUser_id());
        System.out.println("UserID :"+manufactWithUserId.getUser_id());
        if(user==null){
            msg.setCode(504);
            msg.setMsg("can't delete company information because the user don't auth!");
        }else{
            ManManufacturer manManufacturerExist=iManManufacturerService.getByManId(manufactWithUserId.getUser_id());
            if(manManufacturerExist!=null){
                List<ProBrand> listBrand=iProBrandService.getByManId(manManufacturerExist.getMAN_ID());
                for(ProBrand p: listBrand){
                    iProBrandService.delete(p.getBRD_ID());
                }
                iManManufacturerService.delete(manManufacturerExist.getMAN_ID());
                System.out.println("Successfully deleted company information :");
                msg.setCode(200);
                msg.setMsg("Successfully deleted company information");
            }

        }
        return msg;
    }
}
