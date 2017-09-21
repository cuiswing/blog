package com.cui.blog.biz.mappper;

import com.cui.blog.biz.dto.ArticleDTO;
import com.cui.blog.dal.po.ArticleDO;

/**
 * 文章转换工具
 * Created by cuishixiang on 2017-09-14.
 */
public class ArticleMapper {


    /**
     * change DO to DTO
     *
     * @param articleDO 文章DO
     * @return 文章DTO
     */
    public static ArticleDTO articleDO2DTO(ArticleDO articleDO) {
        if (articleDO == null) {
            return null;
        }
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(articleDO.getId());
        articleDTO.setCreateTime(articleDO.getCreateTime());
        articleDTO.setCreateUser(articleDO.getCreateUser());
        articleDTO.setUpdateTime(articleDO.getUpdateTime());
        articleDTO.setUpdateUser(articleDO.getUpdateUser());
        articleDTO.setArticleCategoryId(articleDO.getArticleCategoryId());
        articleDTO.setCategoryName(articleDO.getCategoryName());
        articleDTO.setTitle(articleDO.getTitle());
        articleDTO.setContent(articleDO.getContent());
        articleDTO.setVisitCount(articleDO.getVisitCount());
        articleDTO.setCommentCount(articleDO.getCommentCount());
        return articleDTO;
    }
}
