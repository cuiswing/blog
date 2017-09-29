package com.cui.blog.biz.service.impl;

import com.cui.blog.biz.dto.ArticleDTO;
import com.cui.blog.biz.errormessage.BlogErrorMessageFactory;
import com.cui.blog.biz.exception.BlogException;
import com.cui.blog.biz.mappper.ArticleMapper;
import com.cui.blog.biz.service.ArticleService;
import com.cui.blog.dal.dao.ArticleDAO;
import com.cui.blog.dal.po.ArticleDO;
import com.cui.common.page.PageList;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文章处理业务接口实现
 * Created by cuishixiang on 2017-09-07.
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    /**
     * 错误工厂
     */
    private static final transient BlogErrorMessageFactory errorMessageFactory = BlogErrorMessageFactory.getInstance();

    @Resource
    private ArticleDAO articleDAO;

    /**
     * 根据id获取文章
     *
     * @param id 文章id
     * @return 文章DTO
     */
    @Override
    public ArticleDTO getById(Integer id) {
        ArticleDO articleDO = articleDAO.getById(id);
        return ArticleMapper.articleDO2DTO(articleDO);
    }

    /**
     * 分页查询文章
     *
     * @param pageNo   当前页
     * @param pageSize 每页条目数
     * @return 分页数据集
     */
    @Override
    public PageList<ArticleDTO> pageQuery(int pageNo, int pageSize) {
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        PageInfo<ArticleDO> pageInfo = PageHelper.startPage(pageNo, pageSize).doSelectPageInfo(() -> articleDAO.listAll());
        if (pageInfo.getList() != null) {
            pageInfo.getList().forEach(articleDO -> {
                articleDTOS.add(ArticleMapper.articleDO2DTO(articleDO));
            });
            return new PageList<>(articleDTOS, (int) pageInfo.getTotal(), pageNo, pageSize);
        }
        return null;
    }

    /**
     * 分页查询文章-倒序排列
     *
     * @param pageNo   当前页
     * @param pageSize 每页条目数
     * @return 分页数据集
     */
    @Override
    public PageList<ArticleDTO> pageQueryDesc(int pageNo, int pageSize) {
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        PageInfo<ArticleDO> pageInfo = PageHelper.startPage(pageNo, pageSize).doSelectPageInfo(() -> articleDAO.listAllDesc());
        if (pageInfo.getList() != null) {
            pageInfo.getList().forEach(articleDO -> {
                articleDTOS.add(ArticleMapper.articleDO2DTO(articleDO));
            });
            return new PageList<>(articleDTOS, (int) pageInfo.getTotal(), pageNo, pageSize);
        }
        return null;
    }

    /**
     * 保存文章
     *
     * @param articleDTO 文章DTO
     * @return 文章DO
     */
    @Override
    public ArticleDO save(ArticleDTO articleDTO) throws BlogException {
        Map<String, Object> param = new HashMap<>();
        param.put("id", articleDTO.getId());
        param.put("title", articleDTO.getTitle());
        int count = articleDAO.getCountByTitle(param);
        if (count > 0) {
            throw new BlogException(errorMessageFactory.articleTitleRepeatError(articleDTO.getTitle()));
        }

        ArticleDO articleDO = new ArticleDO();
        articleDO.setCreateUser(articleDTO.getCreateUser());
        articleDO.setUpdateUser(articleDTO.getUpdateUser());
        articleDO.setTitle(articleDTO.getTitle());
        articleDO.setContent(articleDTO.getContent());
        articleDO.setVisitCount(0);
        articleDO.setCommentCount(0);
        articleDO.setArticleCategoryId(articleDTO.getArticleCategoryId());
        articleDO.setCategoryName(articleDTO.getCategoryName());
        articleDAO.saveAndReturnKey(articleDO);
        return articleDO;
    }

    /**
     * 更新文章
     *
     * @param articleDTO 文章DTO
     * @return 数据影响行数
     */
    @Override
    public int update(ArticleDTO articleDTO) throws BlogException {
        Map<String, Object> param = new HashMap<>();
        param.put("id", articleDTO.getId());
        param.put("title", articleDTO.getTitle());
        int count = articleDAO.getCountByTitle(param);
        if (count > 0) {
            throw new BlogException(errorMessageFactory.articleTitleRepeatError(articleDTO.getTitle()));
        }

        ArticleDO articleDO = articleDAO.getById(articleDTO.getId());
        articleDO.setUpdateUser(articleDTO.getUpdateUser());
        articleDO.setTitle(articleDTO.getTitle());
        articleDO.setContent(articleDTO.getContent());
        articleDO.setArticleCategoryId(articleDTO.getArticleCategoryId());
        articleDO.setCategoryName(articleDTO.getCategoryName());
        return articleDAO.update(articleDO);
    }

    /**
     * 更新文章的访问量
     *
     * @param id    文章id
     * @param count 增加的访问量
     * @return 数据影响行数
     */
    @Override
    public int updateViewCount(Integer id, Integer count) throws BlogException {
        ArticleDO articleDO = articleDAO.getById(id);
        if (articleDO == null) {
            throw new BlogException(errorMessageFactory.articleNotExist(id));
        }
        articleDO.setVisitCount(articleDO.getVisitCount() + count);
        return articleDAO.update(articleDO);
    }
}
