package com.cui.blog.biz.service.impl;

import com.cui.blog.biz.dto.ArticleCategoryDTO;
import com.cui.blog.biz.errormessage.BlogErrorMessageFactory;
import com.cui.blog.biz.exception.BlogException;
import com.cui.blog.biz.service.ArticleCategoryService;
import com.cui.blog.dal.dao.ArticleCategoryDAO;
import com.cui.blog.dal.po.ArticleCategoryDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章类别处理业务接口实现
 * Created by cuishixiang on 2017-09-07.
 */
@Service
public class ArticleCategoryServiceImpl implements ArticleCategoryService {
    /**
     * 错误工厂
     */
    private static final transient BlogErrorMessageFactory errorMessageFactory = BlogErrorMessageFactory.getInstance();

    @Resource
    private ArticleCategoryDAO articleCategoryDAO;

    /**
     * 获取所有的类别
     *
     * @return 类别数据集
     */
    @Override
    public List<ArticleCategoryDO> listAll() {
        return articleCategoryDAO.listAll();
    }

    /**
     * 保存类别
     *
     * @param articleCategoryDTO 类别
     * @return 保存后的类别对象
     */
    @Override
    public ArticleCategoryDO save(ArticleCategoryDTO articleCategoryDTO) throws BlogException {
        int count = articleCategoryDAO.getCountByName(articleCategoryDTO.getCategoryName());
        if (count > 0) {
            throw new BlogException(errorMessageFactory.categoryNameRepeatError(articleCategoryDTO.getCategoryName()));
        }
        ArticleCategoryDO articleCategoryDO = new ArticleCategoryDO();
        articleCategoryDO.setCategoryName(articleCategoryDTO.getCategoryName());
        articleCategoryDO.setArticleCount(0);
        articleCategoryDO.setCreateUser(articleCategoryDTO.getCreateUser());
        articleCategoryDO.setUpdateUser(articleCategoryDTO.getUpdateUser());
        articleCategoryDAO.saveAndReturnKey(articleCategoryDO);
        return articleCategoryDO;
    }

    /**
     * 更新类别
     *
     * @param articleCategoryDTO 类别
     * @return 数据影响行数
     */
    @Override
    public int update(ArticleCategoryDTO articleCategoryDTO) throws BlogException {
        int count = articleCategoryDAO.getCountByName(articleCategoryDTO.getCategoryName());
        if (count > 0) {
            throw new BlogException(errorMessageFactory.categoryNameRepeatError(articleCategoryDTO.getCategoryName()));
        }
        ArticleCategoryDO articleCategoryDO = articleCategoryDAO.getById(articleCategoryDTO.getId());
        articleCategoryDO.setCategoryName(articleCategoryDTO.getCategoryName());
        articleCategoryDO.setUpdateUser(articleCategoryDTO.getUpdateUser());
        return articleCategoryDAO.update(articleCategoryDO);
    }
}
