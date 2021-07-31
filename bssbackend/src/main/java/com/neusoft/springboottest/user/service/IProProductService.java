package com.neusoft.springboottest.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.ProProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.springboottest.user.entity.SysUser;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author phil
 * @since 2021-01-26
 */
public interface IProProductService extends IService<ProProduct> {


    public String uploadPicture(MultipartFile uploadFile);
    public String uploadPicture2(MultipartFile uploadFile, HttpServletRequest request);

    public IPage<ProProduct> getProductConditions(PageCondition pc);
    public IPage<ProProduct> getProductByBrandByUserId(PageCondition pc);
}
