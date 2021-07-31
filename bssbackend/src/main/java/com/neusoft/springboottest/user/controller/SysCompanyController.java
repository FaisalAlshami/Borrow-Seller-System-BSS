package com.neusoft.springboottest.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.springboottest.dto.Message;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.ProProduct;
import com.neusoft.springboottest.user.entity.SysCompany;
import com.neusoft.springboottest.user.entity.SysUser;
import com.neusoft.springboottest.user.service.IProProductService;
import com.neusoft.springboottest.user.service.ISysCompanyService;
import com.neusoft.springboottest.user.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/sys-company")
public class SysCompanyController {
    @Autowired
    ISysCompanyService iSysCompanyService;
    @Autowired
    ISysUserService iSysUserService;

    @PostMapping("getAllCompany")
    public Message getAllCompany(){

        System.out.println("getAllCompany :");
        List<SysCompany> list= iSysCompanyService.getAllCompany();
        Message msg= new Message();
        if(list!=null && list.size()>0){
            msg.setObj(list);
            msg.setCode(200);
            msg.setMsg("get getAllCompany successful");
        }else{
            msg.setCode(504);
            msg.setMsg("get getAllCompany failure");
        }
        return msg;
    }
    @PostMapping("postCompanyInfo1")
    public Message postCompanyInfo1(){
        Message msg = new Message();
        SysCompany company=new SysCompany();
        company.setCompanyName("Company Name 1");
        company.setCompanyPhone("123456789");
        company.setCompanyAddress("Address");
        company.setCompanyDescription("DDDDDDDDDDDDDDDDDDDD");
        msg.setCode(200);
        msg.setMsg("getAllUsers successful");
        msg.setObj(company);
        System.out.println("Company Info "+company);
        return msg;
    }
    @PostMapping("getCompanyInfo1")
    public Message getCompanyInfo1(){
        Message msg = new Message();
        SysCompany company=new SysCompany();
        company.setCompanyName("Company Name 1");
        company.setCompanyPhone("123456789");
        company.setCompanyAddress("Address");
        company.setCompanyDescription("DDDDDDDDDDDDDDDDDDDD");
        msg.setCode(200);
        msg.setMsg("getAllUsers successful");
        msg.setObj(company);
        System.out.println("getCompanyInfo1 Company Info "+company);
        return msg;
    }

    @PostMapping("getCompanyInfo")
    public Message getCompanyInfo(@RequestBody SysUser sysUser){
        System.out.println("getCompanyInfo getUsername:"+sysUser.getUsername());
        System.out.println("getCompanyInfo getUserId:"+sysUser.getUserId());
        Message msg = new Message();
        SysCompany sysCompany=null;
        SysUser user=iSysUserService.getUserInfoByUsername(sysUser.getUsername());
        if(user!=null) {
             sysCompany = iSysCompanyService.getCompanyInfoByUserId(user.getUserId().toString());
             System.out.println("get Company  info "+sysCompany);
            if (sysCompany != null) {
                msg.setCode(200);
                msg.setMsg("get Company Info successful");
                msg.setObj(sysCompany);
            } else if (user.getRoleId() == "2") {
                sysCompany = new SysCompany();
                sysCompany.setUserId(user.getUserId());
                msg.setCode(501);
                msg.setMsg("You should insert company Info");
                msg.setObj(sysCompany);
            } else {
                msg.setCode(504);
                msg.setMsg("get Company Info failure can't find data");
                msg.setObj(sysCompany);
            }
        }
        else {
            msg.setCode(504);
            msg.setMsg("get Company Info failure can't find data");
            msg.setObj(sysCompany);
        }
        System.out.println("get Company  info before invoke "+sysCompany);
        return msg;

    }
    @PostMapping("saveCompany")
    public Message saveCompany(@RequestBody SysCompany sysCompany){
        System.out.println("Company Info :"+sysCompany);
        Message msg = new Message();
        SysUser user=iSysUserService.getById(sysCompany.getUserId());
        if(user.getUserId().equals(sysCompany.getUserId())) {
            boolean ok = iSysCompanyService.saveOrUpdate(sysCompany);
            if (ok) {
                msg.setCode(200);
                msg.setMsg("saveProduct successful");
            } else {
                msg.setCode(504);
                msg.setMsg("saveProduct failure");
            }
        }
        else {
            msg.setCode(500);
            msg.setMsg("You can't update company information, you aren't owner");
        }
        return msg;
    }
}
