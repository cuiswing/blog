package com.cui.blog.biz.service;

import com.cui.blog.biz.dto.ArticleDTO;
import com.cui.blog.biz.dto.ArticleStatisticsInfoDTO;
import com.cui.blog.biz.exception.BlogException;
import com.cui.blog.dal.po.ArticleDO;
import com.cui.common.page.PageList;

/**
 * 文章处理业务接口
 * Created by cuishixiang on 2017-09-07.
 */
public interface ArticleService {

    /**
     * 根据id获取文章
     *
     * @param id 文章id
     * @return 文章DTO
     */
    ArticleDTO getById(Integer id);

    /**
     * 分页查询文章
     *
     * @param pageNo   当前页
     * @param pageSize 每页条目数
     * @return 分页数据集
     */
    PageList<ArticleDTO> pageQuery(int pageNo, int pageSize);

    /**
     * 分页查询文章-倒序排列
     *
     * @param pageNo   当前页
     * @param pageSize 每页条目数
     * @return 分页数据集
     */
    PageList<ArticleDTO> pageQueryDesc(int pageNo, int pageSize);

    /**
     * 查询文章及评论数量
     *
     * @return 文章统计信息
     */
    ArticleStatisticsInfoDTO statistics();

    /**
     * 保存文章
     *
     * @param articleDTO 文章DTO
     * @return 文章DO
     */
    ArticleDO save(ArticleDTO articleDTO) throws BlogException;

    /**
     * 更新文章
     *
     * @param articleDTO 文章DTO
     * @return 数据影响行数
     */
    int update(ArticleDTO articleDTO) throws BlogException;

    /**
     * 更新文章的访问量
     *
     * @param id    文章id
     * @param count 增加的访问量
     * @return 数据影响行数
     */
    int updateViewCount(Integer id, Integer count) throws BlogException;
}
