package com.cui.blog.web.mappper;

import com.cui.blog.biz.dto.ArticleDTO;
import com.cui.blog.web.vo.ArticleVO;

/**
 * 文章转换器
 * Created by cuishixiang on 2017-09-18.
 */
public class ArticleMapper {

    /**
     * change VO to DTO
     *
     * @param articleVO 文章VO
     * @return 文章DTO
     */
    public static ArticleDTO articleVO2DTO(ArticleVO articleVO) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(articleVO.getId());
        articleDTO.setCreateUser(articleVO.getCreateUser());
        articleDTO.setUpdateUser(articleVO.getUpdateUser());
        articleDTO.setTitle(articleVO.getTitle().trim());
        articleDTO.setContent(articleVO.getContent());
        articleDTO.setArticleCategoryId(articleVO.getArticleCategoryId());
        articleDTO.setCategoryName(articleVO.getCategoryName().trim());
        articleDTO.setVisitCount(articleVO.getVisitCount());
        articleDTO.setCommentCount(articleVO.getCommentCount());
        return articleDTO;
    }

    /**
     * change DTO to VO
     *
     * @param articleDTO 文章DTO
     * @return 文章VO
     */
    public static ArticleVO articleDTO2VO(ArticleDTO articleDTO) {
        if (articleDTO == null) {
            return null;
        }
        ArticleVO articleVO = new ArticleVO();
        articleVO.setId(articleDTO.getId());
        articleVO.setCreateUser(articleDTO.getCreateUser());
        articleVO.setCreateTime(articleDTO.getCreateTime());
        articleVO.setUpdateUser(articleDTO.getUpdateUser());
        articleVO.setUpdateTime(articleDTO.getUpdateTime());
        articleVO.setTitle(articleDTO.getTitle());
        articleVO.setContent(articleDTO.getContent());
        articleVO.setArticleCategoryId(articleDTO.getArticleCategoryId());
        articleVO.setCategoryName(articleDTO.getCategoryName());
        articleVO.setVisitCount(articleDTO.getVisitCount());
        articleVO.setCommentCount(articleDTO.getCommentCount());
        return articleVO;
    }
}
