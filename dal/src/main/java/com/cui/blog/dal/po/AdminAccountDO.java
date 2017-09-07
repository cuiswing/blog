package com.cui.blog.dal.po;

import java.io.Serializable;
import java.util.Date;

/**
 * 对应管理员账号表
 * Created by cuishixiang on 2017-09-07.
 */
public class AdminAccountDO implements Serializable {

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
     * 账户名
     */
    private String accountName;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号码
     */
    private String cellphone;
    /**
     * 邮箱
     */
    private String email;

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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
