package com.cui.blog.biz.mappper;

import com.cui.blog.biz.dto.AdminAccountDTO;
import com.cui.blog.dal.po.AdminAccountDO;

/**
 * 账号转换工具
 * Created by cuishixiang on 2017-09-14.
 */
public class AdminAccountMapper {

    /**
     * change DO to DTO
     *
     * @param adminAccountDO 账户DO
     * @return 账户DTO
     */
    public static AdminAccountDTO adminAccountDO2DTO(AdminAccountDO adminAccountDO) {
        AdminAccountDTO adminAccountDTO = new AdminAccountDTO();
        adminAccountDTO.setId(adminAccountDO.getId());
        adminAccountDTO.setAccountName(adminAccountDO.getAccountName());
        adminAccountDTO.setCellphone(adminAccountDO.getCellphone());
        adminAccountDTO.setEmail(adminAccountDO.getEmail());
        return adminAccountDTO;
    }
}
