package com.cui.blog.biz.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章类别DTO
 * Created by cuishixiang on 2017-09-18.
 */
public class ArticleCategoryDTO implements Serializable {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 删除标识：0-无效，1-有效
     */
    private Integer enabled;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建用户id
     */
    private Integer createUser;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新用户id
     */
    private Integer updateUser;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 文章数量
     */
    private Integer articleCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }
}
