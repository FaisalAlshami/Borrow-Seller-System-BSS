package com.neusoft.springboottest.user.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.springboottest.config.security.EncryptionAlgorithm;
import com.neusoft.springboottest.dto.ChangePassword;
import com.neusoft.springboottest.dto.Message;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.SysUser;
import com.neusoft.springboottest.user.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
 * @since 2021-01-13
 */
@RestController
@RequestMapping("/user/sys-user")
public class SysUserController {

    @Autowired
    ISysUserService iSysUserService;

    @PostMapping("login")
    public Message method2(@RequestBody SysUser user) {
        System.out.println("Login =====> Username:"+user.getUsername()+"  password:"+user.getPassword());
        Message msg = iSysUserService.verifyUserInfo(user);
        return msg;
    }

    @PostMapping("register")
    public Message method3(@RequestBody SysUser user){
        Message msg = new Message();
        String password=user.getPassword();
        System.out.println("Password  before Encoder :"+password);
        String encoderType= EncryptionAlgorithm.ENCODER_TYPE.get(1);
        PasswordEncoder passwordEncoder=EncryptionAlgorithm.ENCODER_MAP.get(encoderType);
        user.setPassword(String.format(EncryptionAlgorithm.PASSWORD_FORMAT,passwordEncoder.encode(password)));
       // user.setPassword(String.format(EncryptionAlgorithm.PASSWORD_FORMAT,encoderType,passwordEncoder.encode(password)));
       // System.out.println("Password after Encoder :"+user.getPassword());
       boolean ok=  iSysUserService.save(user);
        if(ok){
            msg.setCode(200);
            msg.setMsg("register successful");
            msg.setObj(user);
        }else{
            msg.setCode(504);
            msg.setMsg("register failure");
        }

        return msg;
    }


    @PostMapping("getAllUsers")
    public Message getAllUsers(){
        Message msg = new Message();
        List<SysUser> list=  iSysUserService.list();
        if(list!=null && list.size()>0){
            msg.setCode(200);
            msg.setMsg("getAllUsers successful");
            msg.setObj(list);
        }else{
            msg.setCode(504);
            msg.setMsg("getAllUsers failure can't find data ");
        }
        return msg;
    }
    @PostMapping("getAllUsers2")
    public Message getAllUsers2(@RequestBody PageCondition pc){
        Message msg = new Message();
        IPage<SysUser> userIPage=  iSysUserService.getMenuListByConditions(pc);
        List<SysUser> list=userIPage.getRecords();
        if(list!=null && list.size()>0){
            msg.setCode(200);
            for (SysUser user:list){
                if(user.getRoleId()=="1"){
                    user.setRole_Name("Admin");
                }
                if(user.getRoleId()=="2"){
                    user.setRole_Name("MVO");}
                if(user.getRoleId()=="3") {
                    user.setRole_Name("BVO");
                }
                if(user.getRoleId()=="0") {
                    user.setRole_Name("Not Active ");
                }
            }
            msg.setMsg("getAllUsers2 successful");
            msg.setObj(userIPage);
        }else{
            msg.setCode(504);
            msg.setMsg("getAllUsers2 failure can't find data ");
        }
        return msg;
    }

    @PostMapping("UpdateUserInfo")
    public Message UpdateUserInfo(@RequestBody SysUser sysUser){

      boolean ok=  iSysUserService.saveOrUpdate(sysUser);
        Message msg = new Message();
      if(ok){
          msg.setCode(200);
          SysUser user=iSysUserService.getById(sysUser.getUserId());
          if(user!=null) {
              msg.setObj(user);
          }
          msg.setMsg("update successful");
      }else{
          msg.setCode(504);
          msg.setMsg("update failure");
      }
        return msg;
    }

    @PostMapping("UpdateUserPassword")
    public Message UpdateUserPassword(@RequestBody ChangePassword changePassword){

        boolean ok=false;
        System.out.println("changePassword :"+changePassword.userId+"  " +changePassword.password);
        SysUser user=iSysUserService.getById(changePassword.userId);
        if(user!=null){
            user.setPassword(changePassword.password);
            ok=  iSysUserService.saveOrUpdate(user);
        }
        Message msg = new Message();
        System.out.println("Ok :"+ok);
        if(ok){
            msg.setCode(200);
            msg.setMsg("update successful");
        }else{
            msg.setCode(504);
            msg.setMsg("update failure");
        }
        System.out.println("message "+msg);
        return msg;
    }



    @PostMapping("deleteById")
    public Message deleteById(@RequestBody SysUser sysUser){

        boolean ok=  iSysUserService.removeById(sysUser.getUserId());
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

    @PostMapping("uploadUserPicture")
    public Message uploadUserPicture(@RequestParam(name = "fileName", required = false)MultipartFile uploadFile, HttpServletRequest request){


        System.out.println("Before upload image ");
        Message msg=new Message();
        String imgpath= iSysUserService.uploadPicture(uploadFile,request);
        System.out.println("Path Image==:>"+imgpath);
        msg.setCode(200);
        msg.setMsg("uploadPicture successful");
        msg.setObj(imgpath);
        System.out.println("after upload image ");
        return msg;
    }


}