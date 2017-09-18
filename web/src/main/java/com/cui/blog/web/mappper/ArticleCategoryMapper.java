package com.cui.blog.web.mappper;

import com.cui.blog.biz.dto.ArticleCategoryDTO;
import com.cui.blog.web.vo.ArticleCategoryVO;

/**
 * 文章类别转换器
 * Created by cuishixiang on 2017-09-18.
 */
public class ArticleCategoryMapper {

    /**
     * change VO to DTO
     *
     * @param articleCategoryVO 类别VO
     * @return 类别DTO
     */
    public static ArticleCategoryDTO articleCategoryVO2DTO(ArticleCategoryVO articleCategoryVO) {
        ArticleCategoryDTO articleCategoryDTO = new ArticleCategoryDTO();
        articleCategoryDTO.setId(articleCategoryVO.getId());
        articleCategoryDTO.setCreateUser(articleCategoryVO.getCreateUser());
        articleCategoryDTO.setUpdateUser(articleCategoryVO.getUpdateUser());
        articleCategoryDTO.setCategoryName(articleCategoryVO.getCategoryName().trim());
        articleCategoryDTO.setArticleCount(articleCategoryVO.getArticleCount());
        return articleCategoryDTO;
    }
}
