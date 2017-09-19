package com.cui.blog.web.controller;

import com.cui.blog.biz.dto.AdminAccountDTO;
import com.cui.blog.biz.dto.ArticleDTO;
import com.cui.blog.biz.exception.BlogException;
import com.cui.blog.biz.service.ArticleService;
import com.cui.blog.dal.po.ArticleDO;
import com.cui.blog.web.form.PageRequest;
import com.cui.blog.web.form.Result;
import com.cui.blog.web.mappper.ArticleMapper;
import com.cui.blog.web.vo.ArticleVO;
import com.cui.common.page.PageList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 文章处理前端控制器
 * Created by cuishixiang on 2017-09-07.
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    @Resource
    private ArticleService articleService;


    /**
     * 获取文章列表页面
     *
     * @return 文章列表页面
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listPage() {
        return "article/list";
    }

    /**
     * 分页查询文章
     *
     * @param pageRequest 分页查询请求
     * @return 查询结果集
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Result list(PageRequest pageRequest) {
        Result<PageList<ArticleDTO>> result = new Result<>();
        PageList<ArticleDTO> pageList = articleService.pageQuery(pageRequest.getPageNo(), pageRequest.getPageSize());
        result.setData(pageList);
        return result;
    }


    /**
     * 获取编辑页面地址
     *
     * @param id    文章id
     * @param model 属性模型对象
     * @return 文章编辑页面
     */
    @RequestMapping(value = "/edit", method = {RequestMethod.GET})
    public String editPage(@RequestParam(value = "id", required = false) Integer id, Model model) {
        model.addAttribute("articleId", id);
        return "article/edit";
    }

    /**
     * 获取文章信息
     *
     * @param id 文章id
     * @return 响应数据
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result getById(@PathVariable Integer id) {
        Result<ArticleVO> result = new Result<>();
        ArticleDTO articleDTO = articleService.getById(id);
        result.setData(ArticleMapper.articleDTO2VO(articleDTO));
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result save(@RequestBody ArticleVO articleVO, HttpSession session) {
        Result<ArticleDO> result = new Result<>();

        AdminAccountDTO adminAccountDTO = (AdminAccountDTO) session.getAttribute("user");
        articleVO.setCreateUser(adminAccountDTO.getId());
        articleVO.setUpdateUser(adminAccountDTO.getId());

        try {
            if (articleVO.getId() == 0) {
                ArticleDO articleDO = articleService.save(ArticleMapper.articleVO2DTO(articleVO));
                result.setData(articleDO);
            } else {
                articleService.update(ArticleMapper.articleVO2DTO(articleVO));
            }
        } catch (BlogException e) {
            LOGGER.error("<web><ArticleController><save><><>文章保存失败：", e);
            result.setSuccess(false);
            result.setErrorCode(e.getErrorCode());
            result.setErrorMessage(e.getMessage());
        }
        return result;

    }
}