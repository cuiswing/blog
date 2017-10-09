package com.cui.blog.biz.dto;

import java.io.Serializable;

/**
 * 文章统计信息对象DTO
 * Created by cuishixiang on 2017-10-09.
 */
public class ArticleStatisticsInfoDTO implements Serializable {

    /**
     * 文章数量
     */
    private Integer articleCount;
    /**
     * 评论数量
     */
    private Integer commentCount;

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}
