package com.neusoft.springboottest.user.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.springboottest.dto.Message;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.SysData;
import com.neusoft.springboottest.user.service.ISysDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Reem
 * @since 2021-02-15
 */
@RestController
@RequestMapping("/user/sys-data")
public class SysDataController {


    @Autowired

    ISysDataService iSysDataService;
    @PostMapping("Save")
    public Message Save(@RequestBody SysData sysData){

        boolean ok=  iSysDataService.saveOrUpdate(sysData);
        Message msg = new Message();
        if(ok){
            msg.setCode(200);
            msg.setMsg("save successful");
        }else{
            msg.setCode(504);
            msg.setMsg("save failure");
        }
        return msg;

    }

    @PostMapping("getAllData")
    public Message getAllData(@RequestBody PageCondition pc){
        Message msg = new Message();
        IPage<SysData> dataIPage=  iSysDataService.getMenuListByConditions(pc);
        List<SysData> list=dataIPage.getRecords();
        if(list!=null && list.size()>0){
            msg.setCode(200);
            msg.setMsg("getAllUsers2 successful");
            msg.setObj(dataIPage);
        }else{
            msg.setCode(504);
            msg.setMsg("getAllUsers2 failure can't find data ");
        }
        return msg;
    }
    @PostMapping("deleteById")
    public Message deleteById(@RequestBody SysData sysData){

        boolean ok=  iSysDataService.removeById(sysData.getId());
        Message msg = new Message();
        if(ok){
            msg.setCode(200);
            msg.setMsg("delete successful");
        }else{
            msg.setCode(504);
            msg.setMsg("delete failure");
        }
        return msg;

    }
}
