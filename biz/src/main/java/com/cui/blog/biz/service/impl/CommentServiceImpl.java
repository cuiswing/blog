package com.cui.blog.biz.service.impl;

import com.cui.blog.biz.dto.CommentDTO;
import com.cui.blog.biz.service.CommentService;
import com.cui.blog.dal.dao.CommentDAO;
import com.cui.blog.dal.po.CommentDO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 评论处理业务接口实现
 * Created by cuishixiang on 2017-09-07.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDAO commentDAO;

    /**
     * 保存评论
     *
     * @param commentDTO 评论DTO
     * @return 评论DO
     */
    @Override
    public CommentDO save(CommentDTO commentDTO) {
        CommentDO commentDO = new CommentDO();
        commentDO.setArticleId(commentDTO.getArticleId());
        commentDO.setUsername(commentDTO.getUsername());
        commentDO.setEmail(commentDTO.getEmail());
        commentDO.setPersonalWebsite(commentDTO.getPersonalWebsite());
        commentDO.setContent(commentDTO.getContent());
        commentDO.setId(commentDTO.getId());
        commentDAO.saveAndReturnKey(commentDO);
        return commentDO;
    }

    /**
     * 根据文章id获取所有的评论
     *
     * @param articleId 文章id
     * @return 评论数据集
     */
    @Override
    public List<CommentDO> listAll(Integer articleId) {
        return commentDAO.listAll(articleId);
    }
}
