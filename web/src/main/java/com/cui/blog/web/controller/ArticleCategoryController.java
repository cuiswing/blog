package com.cui.blog.web.controller;

import com.cui.blog.biz.dto.AdminAccountDTO;
import com.cui.blog.biz.exception.BlogException;
import com.cui.blog.biz.service.ArticleCategoryService;
import com.cui.blog.dal.po.ArticleCategoryDO;
import com.cui.blog.web.form.Result;
import com.cui.blog.web.mappper.ArticleCategoryMapper;
import com.cui.blog.web.vo.ArticleCategoryVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 文章类别处理前端控制器
 * Created by cuishixiang on 2017-09-07.
 */
@Controller
public class ArticleCategoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleCategoryController.class);

    @Resource
    private ArticleCategoryService articleCategoryService;

    /**
     * 返回列表页面地址
     *
     * @return 列表页面
     */
    @RequestMapping("/admin/category")
    public String getListPage() {
        return "category/list";
    }


    /**
     * 获取文章类别编辑页面
     *
     * @return 文章类别编辑页面
     */
    @RequestMapping(value = "/admin/category/edit", method = RequestMethod.GET)
    public String edit() {
        return "articleCategory/edit";
    }

    /**
     * 获取所有的类别
     *
     * @return 查询结果集
     */
    @RequestMapping(value = "/category/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Result list() {
        Result<List<ArticleCategoryDO>> result = new Result<>();
        List<ArticleCategoryDO> articleCategoryDOS = articleCategoryService.listAll();
        result.setData(articleCategoryDOS);
        return result;
    }

    /**
     * 新增类别
     *
     * @return 保存结果
     */
    @RequestMapping(value = "/admin/category/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(HttpSession session, @RequestBody ArticleCategoryVO articleCategoryVO, BindingResult bindingResult) {
        Result<ArticleCategoryDO> result = new Result<>();
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            result.setSuccess(false);
            result.setErrorMessage(fieldError.getDefaultMessage());
            return result;
        }

        AdminAccountDTO adminAccountDTO = (AdminAccountDTO) session.getAttribute("user");
        articleCategoryVO.setCreateUser(adminAccountDTO.getId());
        articleCategoryVO.setUpdateUser(adminAccountDTO.getId());

        try {
            if (articleCategoryVO.getId() == 0) {
                ArticleCategoryDO articleCategoryDO = articleCategoryService.save(ArticleCategoryMapper.articleCategoryVO2DTO(articleCategoryVO));
                result.setData(articleCategoryDO);
            } else {
                articleCategoryService.update(ArticleCategoryMapper.articleCategoryVO2DTO(articleCategoryVO));
            }
        } catch (BlogException e) {
            LOGGER.error("<web><ArticleCategoryController><save><><>文章类别保存失败：", e);
            result.setSuccess(false);
            result.setErrorCode(e.getErrorCode());
            result.setErrorMessage(e.getMessage());
        }
        return result;
    }

}
