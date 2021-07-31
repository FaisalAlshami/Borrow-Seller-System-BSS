package com.neusoft.springboottest.user.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.springboottest.dto.Message;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.ProProduct;
import com.neusoft.springboottest.user.entity.SysUser;
import com.neusoft.springboottest.user.service.IProProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author phil
 * @since 2021-01-26
 */
@RestController
@RequestMapping("/user/pro-product")
public class ProProductController {

    @Autowired
    IProProductService iProProductService;

    @PostMapping("getAllProduct")
    public Message getAllProduct(){


       List<ProProduct> list= iProProductService.list();
        Message msg= new Message();
       if(list!=null && list.size()>0){
           msg.setObj(list);
           msg.setCode(200);
           msg.setMsg("get getAllProduct successful");
       }else{
           msg.setCode(504);
           msg.setMsg("get getAllProduct failure");
       }
        return msg;

    }


    @PostMapping("uploadPicture")
    public Message uploadPicture(@RequestParam(name = "fileName", required = false)MultipartFile uploadFile, HttpServletRequest request){

        Message msg=new Message();
        String imgpath= iProProductService.uploadPicture2(uploadFile,request);
        msg.setCode(200);
        msg.setMsg("uploadPicture successful");
        msg.setObj(imgpath);
        return msg;
    }
    @PostMapping("getAllProduct2")
    public Message getAllProduct2(@RequestBody PageCondition pc){
        System.out.println(pc);
        Message msg = new Message();
        IPage<ProProduct> productIPage=  iProProductService.getProductConditions(pc);
        List<ProProduct> list=productIPage.getRecords();
        if(list!=null && list.size()>0){
            msg.setCode(200);
            msg.setMsg("getAllProduct2 successful");
            msg.setObj(productIPage);
        }else{
            msg.setCode(504);
            msg.setMsg("getAllProduct2 failure can't find data ");
        }
        return msg;
    }
    @PostMapping("getAllProductBrandByUserId")
    public Message getAllProductBrandByUserId(@RequestBody PageCondition pc){
        System.out.println(pc);
        Message msg = new Message();
        IPage<ProProduct> productIPage=  iProProductService.getProductByBrandByUserId(pc);
        List<ProProduct> list=productIPage.getRecords();
        if(list!=null && list.size()>0){
            msg.setCode(200);
            msg.setMsg("get All Product brand by user Id successful");
            msg.setObj(productIPage);
        }else{
            msg.setCode(504);
            msg.setMsg("get All Product brand by user Id failure can't find data ");
        }
        return msg;
    }
    @PostMapping("saveProduct")
    public Message saveProduct(@RequestBody ProProduct proProduct){

        proProduct.setManId(proProduct.getUserId());

        Message msg = new Message();
        boolean ok=iProProductService.save(proProduct);
        if(ok){
            msg.setCode(200);
            msg.setMsg("saveProduct successful");
        }else{
            msg.setCode(504);
            msg.setMsg("saveProduct failure");
        }
        return msg;
    }
}
