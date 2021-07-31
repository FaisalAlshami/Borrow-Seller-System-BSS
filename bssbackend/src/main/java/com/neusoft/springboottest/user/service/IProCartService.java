package com.neusoft.springboottest.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.ProCart;
import com.neusoft.springboottest.user.entity.ProProduct;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author phil
 * @since 2021-01-26
 */
public interface IProCartService extends IService<ProCart> {



    public IPage<ProCart> getCartConditions(PageCondition pc);
    public List<ProCart> getCartByBorrowUserId(Integer id);
    public List<ProCart> getCartByBrandUserId(Integer branduserid);
}
