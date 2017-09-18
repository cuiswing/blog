package com.cui.blog.biz.service.impl;

import com.cui.blog.biz.dto.AdminAccountDTO;
import com.cui.blog.biz.errormessage.BlogErrorMessageFactory;
import com.cui.blog.biz.exception.BlogException;
import com.cui.blog.biz.mappper.AdminAccountMapper;
import com.cui.blog.biz.service.AdminAccountService;
import com.cui.blog.dal.dao.AdminAccountDAO;
import com.cui.blog.dal.po.AdminAccountDO;
import com.cui.common.utils.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 账号处理业务接口实现
 * Created by cuishixiang on 2017-09-07.
 */
@Service
public class AdminAccountServiceImpl implements AdminAccountService {
    /**
     * 错误工厂
     */
    private static final BlogErrorMessageFactory errorMessageFactory = BlogErrorMessageFactory.getInstance();

    @Resource
    private AdminAccountDAO adminAccountDAO;


    /**
     * 新增账户
     *
     * @param adminAccountDTO 账户信息
     * @return 是否成功
     */
    @Override
    @Transactional
    public boolean addAccount(AdminAccountDTO adminAccountDTO) throws BlogException {
        AdminAccountDO adminAccountDO = new AdminAccountDO();
        adminAccountDO.setAccountName(adminAccountDTO.getAccountName());
        adminAccountDO.setPassword(MD5Util.encrypt(adminAccountDTO.getAccountName(), adminAccountDTO.getPassword()));
        adminAccountDO.setCellphone(adminAccountDTO.getCellphone());
        adminAccountDO.setEmail(adminAccountDTO.getEmail());

        int rows = adminAccountDAO.save(adminAccountDO);
        return rows > 0;
    }

    /**
     * 更新账户
     *
     * @param adminAccountDTO 账户信息
     * @return 是否成功
     */
    @Override
    public boolean updateAccount(AdminAccountDTO adminAccountDTO) {
        return false;
    }

    /**
     * 修改密码
     *
     * @param adminAccountDTO 账户信息
     * @return 是否成功
     */
    @Override
    public boolean updatePassword(AdminAccountDTO adminAccountDTO) {
        return false;
    }

    /**
     * 删除账户
     *
     * @param id 账户id
     * @return 是否成功
     */
    @Override
    public boolean deleteAccount(Integer id) {
        return false;
    }

    /**
     * 验证账户信息
     *
     * @param username 账号
     * @param password 密码
     * @return 账户信息
     */
    @Override
    public AdminAccountDTO login(String username, String password) throws BlogException {
        AdminAccountDO adminAccountDO = adminAccountDAO.getByUsername(username);
        if (adminAccountDO == null) {
            throw new BlogException(errorMessageFactory.userNotExist(username));
        }
        String encrypt = MD5Util.encrypt(username, password);
        if (adminAccountDO.getPassword().equals(encrypt)) {
            return AdminAccountMapper.adminAccountDO2DTO(adminAccountDO);
        } else {
            return null;
        }
    }
}
