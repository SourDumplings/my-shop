package com.cz.my.shop.web.admin.web.controller;

import com.cz.my.shop.commons.constant.ConstantUtils;
import com.cz.my.shop.commons.utils.CookieUtils;
import com.cz.my.shop.domain.User;
import com.cz.my.shop.web.admin.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/8 15:53
 */
@Controller
public class LoginController
{
    @Autowired
    private UserService userService;

    private static final String COOKIE_NAME_USER_NAME = "userInfo";

    /**
     * 跳转登录页面
     *
     * @return
     */
    @RequestMapping(value = {"login", ""}, method = RequestMethod.GET)
    public String login(HttpServletRequest request)
    {
        final String userInfo = CookieUtils.getCookieValue(request, COOKIE_NAME_USER_NAME);
        if (!StringUtils.isEmpty(userInfo))
        {
            String[] userInfoArray = userInfo.split(":");
            final String email = userInfoArray[0];
            final String password = userInfoArray[1];
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.setAttribute("isRemember", true);
        }
        return "login";
    }

    /**
     * 登录逻辑
     *
     * @param email
     * @param password
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email,
        @RequestParam(required = true) String password,
        @RequestParam(required = false) String isRemember,
        HttpServletRequest request, HttpServletResponse response)
    {
        // 用户未勾选“记住我”
        if (StringUtils.isEmpty(isRemember))
        {
            CookieUtils.deleteCookie(request, response, COOKIE_NAME_USER_NAME);
        }

        final User user = userService.login(email, password);

        // 登录失败
        if (user == null)
        {
            request.setAttribute("message", "登录失败，邮箱或密码错误");
            return login(request);
        }
        // 登录成功
        else
        {
            // 用户选择了“记住我”
            if (!StringUtils.isEmpty(isRemember))
            {
                // 用户信息存储一周
                // Cookie的值里不能有分号
                CookieUtils
                    .setCookie(request, response, COOKIE_NAME_USER_NAME,
                        String.format("%s:%s", email, password),
                        7 * 24 * 3600);
            }

            // 将登录信息放入会话
            request.getSession().setAttribute(ConstantUtils.SESSION_USER, user);
            // 重定向到main
            return "redirect:/main";
        }
    }

    /**
     * 注销
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request)
    {
        request.getSession().invalidate();
        return login(request);
    }

}

