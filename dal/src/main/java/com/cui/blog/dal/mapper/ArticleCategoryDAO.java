package com.cui.blog.dal.mapper;

import com.cui.blog.dal.po.ArticleCategoryDO;

import java.util.List;

/**
 * Mapper接口：article_category
 * Created by cuishixiang on 2017-09-07.
 */
public interface ArticleCategoryDAO {
    /**
     * 根据id获取文章类别
     *
     * @param id 主键id
     * @return 文章类别
     */
    ArticleCategoryDO getById(Integer id);

    /**
     * 获取所有文章类别信息
     *
     * @return 多条文章类别信息
     */
    List<ArticleCategoryDO> listAll();

    /**
     * 保存
     *
     * @param articleCategoryDO 文章类别
     * @return 数据库影响行数
     */
    int save(ArticleCategoryDO articleCategoryDO);

    /**
     * 更新
     *
     * @param articleCategoryDO 文章类别
     * @return 数据库影响行数
     */
    int update(ArticleCategoryDO articleCategoryDO);

    /**
     * 删除
     *
     * @param id 主键id
     * @return 数据库影响行数
     */
    int delete(Integer id);
}
