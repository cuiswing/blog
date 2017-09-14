package com.cui.blog.web.controller;

import com.cui.blog.biz.dto.AdminAccountDTO;
import com.cui.blog.biz.exception.BlogException;
import com.cui.blog.biz.service.AdminAccountService;
import com.cui.blog.web.form.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 账号处理前端控制器
 * Created by cuishixiang on 2017-09-07.
 */
@Controller
@RequestMapping("/admin/account")
public class AdminAccountController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminAccountController.class);

    @Resource
    private AdminAccountService adminAccountService;

    /**
     * 获取登陆页面
     *
     * @return 登陆页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }


    /**
     * 登陆
     *
     * @param username 账户
     * @param password 密码
     * @return 登陆结果
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(HttpSession session, String username, String password) {
        Result result = new Result();
        try {
            AdminAccountDTO adminAccountDTO = adminAccountService.login(username, password);
            if (adminAccountDTO == null) {
                result.setSuccess(false);
            } else {
                result.setSuccess(true);
                session.setAttribute("user", adminAccountDTO);
            }
        } catch (BlogException e) {
            LOGGER.error("<web><AdminAccountController><login><{}><>账号密码验证失败：", username, e);
            result.setSuccess(false);
            result.setErrorCode(e.getErrorCode());
            result.setErrorMessage(e.getMessage());
        }
        return result;
    }


}
