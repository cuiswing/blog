package com.cui.blog.web.controller;

import com.cui.blog.web.form.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传处理：应该由独立的图片处理模块来提供服务，并且提供独立的域名来访问图片，但是没有这么多精力和资源就单机版吧
 * <p>
 * Created by cuishixiang on 2017-09-20.
 */
@RequestMapping("/admin/image")
@Controller
public class ImageController {
    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);
    /**
     * 文件保存的路径：先暂时就保存在本机，暂时就使用变量来代替配置文件了
     */
    private static final String imagePath = "/cui/blog/image/";
    /**
     * 图片服务器域名
     * 可以在tomcat服务器的/conf/server.xml 文件的host节点中配置：<Context docBase="/image/blog" path="/image" reloadable="true"/>
     */
    private static String imageDomain;

    /**
     * 上传图片后并返回图片地址
     *
     * @param multipartFiles 多份文件
     * @return 图片上传结果
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Result uploadImage(@RequestParam(value = "image", required = false) MultipartFile[] multipartFiles) {
        Result<List<String>> result = new Result<>();
        try {
            List<String> imageUrls = new ArrayList<>();
            for (MultipartFile multipartFile : multipartFiles) {
                String originalFilename = multipartFile.getOriginalFilename();
                int dotIndex = originalFilename.indexOf(".");
                String extName = originalFilename.substring(dotIndex + 1).toLowerCase();
                if (!"bmp".equals(extName) && !"png".equals(extName) && !"gif".equals(extName) && !"jpg".equals(extName) && !"jpeg".equals(extName)) {
                    result.setSuccess(false);
                    result.setErrorMessage("文件格式错误");
                } else {
                    String newFileName = UUID.randomUUID() + originalFilename.substring(dotIndex);
                    File file = new File(imagePath + newFileName);
                    multipartFile.transferTo(file);
                    imageUrls.add(imageDomain + newFileName);
                }
            }
            result.setData(imageUrls);
        } catch (IOException e) {
            logger.error("<web><ImageController><uploadImage><><>图片保存失败：", e);
            result.setSuccess(false);
        }
        return result;
    }

    /**
     * 通过配置文件注入值
     *
     * @param imageDomain 图片服务器域名地址
     */
    @Value("${imageDomain}")
    public void setImageDomain(String imageDomain) {
        ImageController.imageDomain = imageDomain;
    }
}
