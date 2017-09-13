package com.cui.blog.dal.mapper;

import com.cui.blog.dal.po.AdminAccountDO;

import java.util.List;

/**
 * Mapper接口：admin_account
 * Created by cuishixiang on 2017-09-07.
 */
public interface AdminAccountMapper {

    /**
     * 根据id获取账号
     *
     * @param id 主键id
     * @return 账号信息
     */
    AdminAccountDO getById(Integer id);

    /**
     * 根据账户名获取账户数据
     *
     * @param username 账号
     * @return 账号信息
     */
    AdminAccountDO getByUsername(String username);

    /**
     * 获取所有账号信息
     *
     * @return 多条账号信息
     */
    List<AdminAccountDO> listAll();

    /**
     * 保存
     *
     * @param adminAccountDO 账号
     * @return 数据库影响行数
     */
    int save(AdminAccountDO adminAccountDO);

    /**
     * 更新
     *
     * @param adminAccountDO 账号
     * @return 数据库影响行数
     */
    int update(AdminAccountDO adminAccountDO);

    /**
     * 修改密码
     *
     * @param adminAccountDO 账号
     * @return 数据库影响行数
     */
    int updatePassword(AdminAccountDO adminAccountDO);

    /**
     * 删除
     *
     * @param id 主键id
     * @return 数据库影响行数
     */
    int delete(Integer id);
}
