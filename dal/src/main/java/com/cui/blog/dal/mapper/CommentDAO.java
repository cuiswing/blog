package com.cui.blog.dal.mapper;

import com.cui.blog.dal.po.CommentDO;

import java.util.List;

/**
 * Mapper接口：comment
 * Created by cuishixiang on 2017-09-07.
 */
public interface CommentDAO {

    /**
     * 根据id获取评论
     *
     * @param id 评论id
     * @return 一条评论
     */
    CommentDO getById(Integer id);

    /**
     * 根据文章id获取所有评论
     *
     * @param articleId 文章id
     * @return 多条评论
     */
    List<CommentDO> listAll(Integer articleId);

    /**
     * 保存评论
     *
     * @param commentDO 评论
     * @return 数据库影响行数
     */
    int save(CommentDO commentDO);

    /**
     * 删除评论
     *
     * @param id 主键id
     * @return 数据库影响行数
     */
    int delete(Integer id);
}
