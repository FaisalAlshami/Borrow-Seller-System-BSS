package com.neusoft.springboottest.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.springboottest.dto.Message;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.ManManufacturer;
import com.neusoft.springboottest.user.entity.SysRole;
import com.neusoft.springboottest.user.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author phil
 * @since 2021-01-13
 */
public interface ISysUserService extends IService<SysUser> {


    int updateManBuyerId(SysUser sysUser);
    public Message verifyUserInfo(SysUser sysuser);
    public IPage<SysUser> getMenuListByConditions(PageCondition pc);
    public SysUser getUserInfoByUsername(String username);
    public SysUser getUserInfoByUserId(Integer userId);
    public List<SysRole> findRolesByUsername(String username);

    public String uploadPicture(MultipartFile uploadFile, HttpServletRequest request);

}
