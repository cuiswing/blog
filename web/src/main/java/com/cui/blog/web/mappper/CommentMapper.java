package com.cui.blog.web.mappper;

import com.cui.blog.biz.dto.CommentDTO;
import com.cui.blog.web.vo.CommentVO;

/**
 * 评论VO、DTO转换器
 * Created by cuishixiang on 2017-09-25.
 */
public class CommentMapper {

    /**
     * change VO to DTO
     *
     * @param commentVO 评论VO
     * @return 评论DTO
     */
    public static CommentDTO commentVO2DTO(CommentVO commentVO) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(commentVO.getId());
        commentDTO.setCreateUser(commentVO.getCreateUser());
        commentDTO.setUpdateUser(commentVO.getUpdateUser());
        commentDTO.setArticleId(commentVO.getArticleId());
        commentDTO.setUsername(commentVO.getUsername().trim());
        commentDTO.setEmail(commentVO.getEmail().trim());
        commentDTO.setPersonalWebsite(commentVO.getPersonalWebsite() == null ? "" : commentVO.getPersonalWebsite().trim());
        commentDTO.setContent(commentVO.getContent().trim());
        return commentDTO;
    }

    /**
     * change DTO to VO
     *
     * @param commentDTO 评论DTO
     * @return 评论VO
     */
    public static CommentVO commentDTO2VO(CommentDTO commentDTO) {
        if (commentDTO == null) {
            return null;
        }
        CommentVO commentVO = new CommentVO();
        commentVO.setId(commentDTO.getId());
        commentVO.setCreateUser(commentDTO.getCreateUser());
        commentVO.setCreateTime(commentDTO.getCreateTime());
        commentVO.setUpdateUser(commentDTO.getUpdateUser());
        commentVO.setUpdateTime(commentDTO.getUpdateTime());
        commentVO.setArticleId(commentDTO.getArticleId());
        commentVO.setUsername(commentDTO.getUsername());
        commentVO.setEmail(commentDTO.getEmail());
        commentVO.setPersonalWebsite(commentDTO.getPersonalWebsite());
        commentVO.setContent(commentDTO.getContent());
        return commentVO;
    }
}
