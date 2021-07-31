package com.neusoft.springboottest.user.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.springboottest.dto.Message;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.Emp;
import com.neusoft.springboottest.user.entity.ParParameter;
import com.neusoft.springboottest.user.entity.SysUser;
import com.neusoft.springboottest.user.mapper.ParParameterMapper;
import com.neusoft.springboottest.user.service.IParParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author phil
 * @since 2021-03-12
 */
@RestController
@RequestMapping("/user/par-parameter")
public class ParParameterController {

    @Autowired
    IParParameterService iParParameterService;


    /*** Get parameters and handling pagination ***/
    @PostMapping("getAllParameters")
    public Message getAllParameters(@RequestBody PageCondition pageCondition) {
        Message msg = new Message();
        IPage<ParParameter> paramPage = iParParameterService.getParameterList(pageCondition);
        List<ParParameter> paramList = paramPage.getRecords();
        if (paramList != null && paramList.size() > 0) {
            msg.setCode(200);
            msg.setMsg("Success");
            msg.setObj(paramPage);
        } else {
            msg.setCode(504);
            msg.setMsg("There is no parameter");
        }
        return msg;
    }

    /*** Update by parameterID ***/
    @PostMapping("updateParameterId")
    public Message updateParameterId(@RequestBody ParParameter parParameter){
        System.out.println("updateParameterId ==>"+parParameter);
        boolean isUpdated =  iParParameterService.saveOrUpdate(parParameter);
        Message msg = new Message();
        if(isUpdated){
            msg.setCode(200);
            ParParameter param = iParParameterService.getById(parParameter.getParId());
            if(param != null) {
                LocalDateTime a = LocalDateTime.now();
                param.setCreationDate(a);

                msg.setObj(param);
            }
            msg.setMsg("Successfully saved");
        }else{
            msg.setCode(504);
            msg.setMsg("Adding is failed");
        }
        return msg;
    }

    /*** Delete by parameterID ***/
    @PostMapping("deleteParameterById")
    public Message deleteParameterById(@RequestBody ParParameter parParameter){
        boolean isDeleted =  iParParameterService.removeById(parParameter.getParId());
        Message msg = new Message();
        if(isDeleted){
            msg.setCode(200);
            msg.setMsg("Delete is successful");
        }else{
            msg.setCode(504);
            msg.setMsg("Delete is failed");
        }
        return msg;
    }

    /*** Inserting parameter by Id ***/
    @Autowired
    ParParameterMapper parParameterMapper;
    @PostMapping("addParameterById")
    public Message addParameterById(@RequestBody ParParameter parParameter){
        System.out.println("addParameterById ==>"+parParameter);
        Integer insertParam = parParameterMapper.insert(parParameter);
        Message msg = new Message();
        if(insertParam != null){
            msg.setCode(200);
            msg.setMsg("Successfully saved");
        }else{
            msg.setCode(504);
            msg.setMsg("Adding is failed");
        }
        return msg;
    }
}
