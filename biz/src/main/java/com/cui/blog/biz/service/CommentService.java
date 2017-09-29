package com.cui.blog.biz.service;

import com.cui.blog.biz.dto.CommentDTO;
import com.cui.blog.biz.exception.BlogException;
import com.cui.blog.dal.po.CommentDO;

import java.util.List;

/**
 * 评论处理业务接口
 * Created by cuishixiang on 2017-09-07.
 */
public interface CommentService {

    /**
     * 保存评论
     *
     * @param commentDTO 评论DTO
     * @return 评论DO
     */
    CommentDO save(CommentDTO commentDTO) throws BlogException;

    /**
     * 根据文章id获取所有的评论
     *
     * @param articleId 文章id
     * @return 评论数据集
     */
    List<CommentDO> listAll(Integer articleId);
}
