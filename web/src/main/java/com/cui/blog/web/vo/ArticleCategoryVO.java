package com.cui.blog.web.vo;

import java.io.Serializable;

/**
 * 文章类别VO
 * Created by cuishixiang on 2017-09-18.
 */
public class ArticleCategoryVO implements Serializable {

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 分类名称
     */
//    @NotBlank(message = "分类名称不能为空")
//    @Size(max = 45, message = "分类名称长度不能超过45个字符")
    private String categoryName;
    /**
     * 创建用户id
     */
    private Integer createUser;
    /**
     * 更新用户id
     */
    private Integer updateUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }
}
