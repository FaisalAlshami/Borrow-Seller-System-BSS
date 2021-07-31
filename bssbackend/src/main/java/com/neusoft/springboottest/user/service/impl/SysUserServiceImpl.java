package com.neusoft.springboottest.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.springboottest.Utils.FilenameUtils;
import com.neusoft.springboottest.dto.Message;
import com.neusoft.springboottest.user.dto.MenuList;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.SysRole;
import com.neusoft.springboottest.user.entity.SysUser;
import com.neusoft.springboottest.user.mapper.SysRoleMapper;
import com.neusoft.springboottest.user.mapper.SysUserMapper;
import com.neusoft.springboottest.user.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author phil
 * @since 2021-01-13
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Autowired
    SysUserMapper sysUserMapper;

    public int updateManBuyerId(SysUser sysUser){return sysUserMapper.updateManBuyerId(sysUser);}
    @Value("${filePath}")
    String realPath;
    public Message verifyUserInfo(SysUser sysuser) {
        Message message=  new Message<SysUser>();
//        String username = "blake";
//        String password = "123";
        //  Conditional constructor
        QueryWrapper<SysUser> userQuery = new QueryWrapper<>();
        userQuery.eq("username", sysuser.getUsername()).eq("password", sysuser.getPassword());
        SysUser user = null;
        try {
            user = sysUserMapper.selectOne(userQuery);
            if (user == null) {
                    // dto msg
                message.setCode(504);
                message.setMsg("login failure wrong username or password");
//                System.out.println("login failure wrong username or password");
            } else {
                message.setCode(200);
                message.setMsg("login successful");
                message.setObj(user);
//                System.out.println(user);
//                System.out.println("login successful");
            }
        } catch (MyBatisSystemException e) {
            message.setCode(504);
            message.setMsg("login failure");
            message.setMsg("login failure");
            System.err.println("find more than one user--------check your database："+sysuser);
        }
        return message;
    }

    public IPage<SysUser> getMenuListByConditions(PageCondition pc) {

        //  Conditional constructor
        QueryWrapper<SysUser> userQuery = Wrappers.query();

        IPage<SysUser> page = new Page<SysUser>(pc.getCurrentPage(), pc.getPageSize());

        IPage<SysUser> userPage = sysUserMapper.selectPage(page,userQuery);
        System.out.println("total pages num：" + userPage.getPages());
        System.out.println("total：" + userPage.getTotal());


        return userPage;
//        List<SysUser> userList = userPage.getRecords();
//        userList.forEach(System.out::println);

    }
    public SysUser getUserInfoByUsername(String username){

        QueryWrapper<SysUser> userQuery = new QueryWrapper<>();
        userQuery.eq("username",username);
        SysUser  user = sysUserMapper.selectOne(userQuery);
        return user;
    }

    public SysUser getUserInfoByUserId(Integer userId){

        QueryWrapper<SysUser> userQuery = new QueryWrapper<>();
        userQuery.eq("USER_ID",userId);
        SysUser  user = sysUserMapper.selectOne(userQuery);
        return user;
    }

    @Autowired
    SysRoleMapper sysRoleMapper;
    public List<SysRole> findRolesByUsername(String username){
        return sysRoleMapper.findRolesByUsername(username);
    }

    public String uploadPicture(MultipartFile uploadFile, HttpServletRequest request){
//        String realPath="\\root\\photo";
        File file=new File(realPath);


        System.out.println(file);
        System.out.println(file.getAbsolutePath());

        // verify readpath is directory
        if(!file.isDirectory()){
            file.mkdirs();
        }
        // upload file
//        System.out.println("uploadFile.getName() :"+uploadFile.getName());
        String fileName= FilenameUtils.getFileName(uploadFile.getOriginalFilename());

        //Build the real file path
        File newFile = new File(file.getAbsolutePath() + File.separator + fileName);
        try {
            System.out.println("fileName :"+fileName);
            uploadFile.transferTo(newFile);
//            String filePath ="http://127.0.0.1:8091/image/"+fileName;
            String filePath = request.getScheme() + "://" + request.getServerName() + ":"
                    + request.getServerPort() + "/usrimg/" + fileName;
            return filePath;
        }catch (Exception e){

        }
        return "no photo";
    }


}
