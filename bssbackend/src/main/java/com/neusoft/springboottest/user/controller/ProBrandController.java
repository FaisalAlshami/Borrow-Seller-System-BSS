package com.neusoft.springboottest.user.controller;

import com.neusoft.springboottest.dto.Message;
import com.neusoft.springboottest.user.entity.ManManufacturer;
import com.neusoft.springboottest.user.entity.ProBrand;
import com.neusoft.springboottest.user.entity.SysUser;
import com.neusoft.springboottest.user.service.IManManufacturerService;
import com.neusoft.springboottest.user.service.ISysUserService;
import com.neusoft.springboottest.user.service.impl.ProBrandServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/user/pro-brand")
public class ProBrandController {

    @Autowired
    IManManufacturerService iManManufacturerService;
    @Autowired
    ISysUserService iSysUserService;
    @Autowired
    ProBrandServiceImp proBrandServiceImp;

    @PostMapping("getbrandbyuserid")
    public Message getBrandByUserId(@RequestBody SysUser sysUser){

        System.out.println("get all brands manId:"+sysUser.getUserId());
        List<ProBrand> list= proBrandServiceImp.getByManId(sysUser.getUserId());
        Message msg= new Message();
        if(list!=null && list.size()>=0){
            msg.setObj(list);
            msg.setCode(200);
            msg.setMsg("get all brands successful");
        }else{
            msg.setCode(504);
            msg.setMsg("failure got the brands");
        }
        System.out.println("Msg Brand :"+msg);
        return msg;
    }

    @PostMapping("addBrand")
    public Message insertBrand(@RequestBody ProBrand proBrand){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("add proBrand  :==>"+proBrand.toString());
        Message msg= new Message();
        SysUser user=iSysUserService.getUserInfoByUserId(proBrand.getMAN_ID());
        ManManufacturer manManufacturer=iManManufacturerService.getByManId(proBrand.getMAN_ID());
        if(manManufacturer==null) {
            msg.setCode(504);
            msg.setMsg("Adding failed because the company " + proBrand.getMAN_ID() + " doesn't exist");
        }else{
            proBrand.setCREATED_BY(user.getUsername());
            proBrand.setCREATION_DATE(dtf.format(now));
            proBrand.setLAST_UPDATE_BY(user.getUsername());
            proBrand.setLAST_UPDATE_DATE(dtf.format(now));
            proBrandServiceImp.insert(proBrand);
            msg.setCode(200);
            msg.setMsg("Successfully added");
        }

        return msg;
    }
    @PostMapping("updateBrand")
    public Message updateBrand(@RequestBody ProBrand proBrand){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("update proBrand  :==>"+proBrand.toString());
        Message msg= new Message();
        SysUser user=iSysUserService.getUserInfoByUserId(proBrand.getMAN_ID());
        ProBrand brand=proBrandServiceImp.getByBrdId(proBrand.getBRD_ID());
        if(brand==null) {
            msg.setCode(504);
            msg.setMsg("updating failed because this brand " + proBrand.getBRD_ID() + " doesn't exist");
        }else{
            brand.setNAME_EN(proBrand.getNAME_EN());
            brand.setNAME_CN(proBrand.getNAME_CN());
            brand.setDESCRIPTION(proBrand.getDESCRIPTION());
            brand.setLAST_UPDATE_BY(user.getUsername());
            brand.setLAST_UPDATE_DATE(dtf.format(now));
            proBrandServiceImp.update(brand);
            msg.setCode(200);
            msg.setMsg("Successfully updated");
        }

        return msg;
    }

    @PostMapping("deleteBrand")
    public Message deleteBrand(@RequestBody ProBrand proBrand){

        System.out.println("delet proBrand  :==>"+proBrand.toString());
        Message msg= new Message();
        SysUser user=iSysUserService.getUserInfoByUserId(proBrand.getMAN_ID());
        ProBrand brand=proBrandServiceImp.getByBrdId(proBrand.getBRD_ID());
        if(brand==null) {
            msg.setCode(504);
            msg.setMsg("deleting failed because this brand " + proBrand.getBRD_ID() + " doesn't exist");
        }else{
            proBrandServiceImp.delete(brand.getBRD_ID());
            msg.setCode(200);
            msg.setMsg("Successfully deleted");
        }

        return msg;
    }
}
