package com.cui.blog.biz.service;

import com.cui.blog.biz.dto.AdminAccountDTO;
import com.cui.blog.biz.exception.BlogException;

/**
 * 账号处理业务接口
 * Created by cuishixiang on 2017-09-07.
 */
public interface AdminAccountService {

    /**
     * 新增账户
     *
     * @param adminAccountDTO 账户信息
     * @return 是否成功
     */
    boolean addAccount(AdminAccountDTO adminAccountDTO) throws BlogException;

    /**
     * 更新账户
     *
     * @param adminAccountDTO 账户信息
     * @return 是否成功
     */
    boolean updateAccount(AdminAccountDTO adminAccountDTO);

    /**
     * 修改密码
     *
     * @param adminAccountDTO 账户信息
     * @return 是否成功
     */
    boolean updatePassword(AdminAccountDTO adminAccountDTO);

    /**
     * 删除账户
     *
     * @param id 账户id
     * @return 是否成功
     */
    boolean deleteAccount(Integer id);

    /**
     * 验证账户信息
     *
     * @param username 账号
     * @param password 密码
     * @return 账户信息
     */
    AdminAccountDTO login(String username, String password) throws BlogException;


}
