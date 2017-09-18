package com.cui.blog.biz.service;

import com.cui.blog.biz.dto.ArticleCategoryDTO;
import com.cui.blog.biz.exception.BlogException;
import com.cui.blog.dal.po.ArticleCategoryDO;

import java.util.List;

/**
 * 文章类别处理业务接口
 * Created by cuishixiang on 2017-09-07.
 */
public interface ArticleCategoryService {

    /**
     * 获取所有的类别
     *
     * @return 类别数据集
     */
    List<ArticleCategoryDO> listAll();

    /**
     * 保存类别
     *
     * @param articleCategoryDTO 类别
     * @return 保存后的类别对象
     */
    ArticleCategoryDO save(ArticleCategoryDTO articleCategoryDTO);

    /**
     * 更新类别
     *
     * @param articleCategoryDTO 类别
     * @return 数据影响行数
     */
    int update(ArticleCategoryDTO articleCategoryDTO) throws BlogException;
}
