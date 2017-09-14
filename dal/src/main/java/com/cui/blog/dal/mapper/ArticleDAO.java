package com.cui.blog.dal.mapper;

import com.cui.blog.dal.po.ArticleDO;

import java.util.List;

/**
 * Mapper接口：article
 * Created by cuishixiang on 2017-09-07.
 */
public interface ArticleDAO {
    /**
     * 根据id获取文章
     *
     * @param id 主键id
     * @return 文章
     */
    ArticleDO getById(Integer id);

    /**
     * 获取所有文章信息
     *
     * @return 多条文章信息
     */
    List<ArticleDO> listAll();

    /**
     * 保存
     *
     * @param articleDO 文章
     * @return 数据库影响行数
     */
    int save(ArticleDO articleDO);

    /**
     * 更新
     *
     * @param articleDO 文章
     * @return 数据库影响行数
     */
    int update(ArticleDO articleDO);

    /**
     * 删除
     *
     * @param id 主键id
     * @return 数据库影响行数
     */
    int delete(Integer id);
}
