package com.neusoft.springboottest.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.springboottest.Utils.FilenameUtils;
import com.neusoft.springboottest.user.dto.PageCondition;
import com.neusoft.springboottest.user.entity.ProProduct;
import com.neusoft.springboottest.user.entity.SysUser;
import com.neusoft.springboottest.user.mapper.ProProductMapper;
import com.neusoft.springboottest.user.service.IProProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author phil
 * @since 2021-01-26
 */
@Service
public class ProProductServiceImpl extends ServiceImpl<ProProductMapper, ProProduct> implements IProProductService {

    @Value("${filePath}")
    String realPath;

    public String uploadPicture(MultipartFile uploadFile){

//        String realPath="\\root\\photo";
        File file=new File(realPath);


        System.out.println(file);
        System.out.println(file.getAbsolutePath());

        // verify readpath is directory
        if(!file.isDirectory()){
            file.mkdirs();
        }
            // upload file
       String fileName= FilenameUtils.getFileName(uploadFile.getOriginalFilename());

            //Build the real file path
        File newFile = new File(file.getAbsolutePath() + File.separator + fileName);
        // Export the file to the specified path. If the file name is repeated, the previous file
        // will be overwritten. Here is to upload the file to "Absolute path".
        try {
            uploadFile.transferTo(newFile);


            String filePath ="http://127.0.0.1:8091/image/"+fileName;
            return filePath;
        }catch (Exception e){

        }


        return "no photo";
    }
    public String uploadPicture2(MultipartFile uploadFile, HttpServletRequest request){
//        String realPath="\\root\\photo";
        File file=new File(realPath);


        System.out.println(file);
        System.out.println(file.getAbsolutePath());

        // verify readpath is directory
        if(!file.isDirectory()){
            file.mkdirs();
        }
        // upload file
        String fileName= FilenameUtils.getFileName(uploadFile.getOriginalFilename());

        //Build the real file path
        File newFile = new File(file.getAbsolutePath() + File.separator + fileName);
        // Export the file to the specified path. If the file name is repeated, the previous file
        // will be overwritten. Here is to upload the file to "Absolute path".
        try {
            uploadFile.transferTo(newFile);


//            String filePath ="http://127.0.0.1:8091/image/"+fileName;

            String filePath = request.getScheme() + "://" + request.getServerName() + ":"
                    + request.getServerPort() + "/image/" + fileName;

            return filePath;
        }catch (Exception e){

        }


        return "no photo";
    }


    @Autowired
    ProProductMapper proProductMapper;

    public IPage<ProProduct> getProductConditions(PageCondition pc){

        //  Conditional constructor
        QueryWrapper<ProProduct> productQuery = Wrappers.query();
        IPage<ProProduct> page = new Page<ProProduct>(pc.getCurrentPage(), pc.getPageSize());

        IPage<ProProduct> productPage = proProductMapper.selectPage(page,productQuery);
        System.out.println("total pages num：" + productPage.getPages());
        System.out.println("total：" + productPage.getTotal());
        return productPage;
    }

    public IPage<ProProduct> getProductByBrandByUserId(PageCondition pc){

        //  Conditional constructor
        QueryWrapper<ProProduct> productQuery = Wrappers.query();
        productQuery.eq("user_id",pc.getUserId());
        IPage<ProProduct> page = new Page<ProProduct>(pc.getCurrentPage(), pc.getPageSize());

        IPage<ProProduct> productPage = proProductMapper.selectPage(page,productQuery);
        System.out.println("total pages num：" + productPage.getPages());
        System.out.println("total：" + productPage.getTotal());
        return productPage;
    }



}
