package com.cz.my.shop.web.ui.controller;

import com.cz.my.shop.commons.dto.BaseResult;
import com.cz.my.shop.commons.utils.EmailSendUtils;
import com.cz.my.shop.web.ui.api.UsersApi;
import com.cz.my.shop.web.ui.dto.TbUser;
import com.google.code.kaptcha.Constants;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2020/2/14 18:42
 */
@Controller
public class LoginController
{

    @Autowired
    private EmailSendUtils emailSendUtils;

    /**
     * 跳转登录页
     *
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login()
    {
        return "login";
    }

    /**
     * 登录
     *
     * @param tbUser
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(TbUser tbUser, Model model, HttpServletRequest request) throws Exception
    {
        // 验证码验证失败
        if (!checkVerification(tbUser, request))
        {
            model.addAttribute("baseResult", BaseResult.fail("验证码输入错误，请重新输入"));
            return "login";
        }

        TbUser user = UsersApi.login(tbUser);

        // 登录失败
        if (user == null)
        {
            model.addAttribute("baseResult", BaseResult.fail("用户名或密码错误，请重新输入！"));
            return "login";
        }

        // 登录成功
        else
        {
            emailSendUtils.send("用户登录", String.format("用户 【%s】 登录 MyShop", user.getUsername()),
                "changzheng300@foxmail.com");
            // 将会员信息放入 Session
            request.getSession().setAttribute("tbUser", user);
            // 重定向回首页
            return "redirect:/index";
        }
    }

    /**
     * 注销
     *
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request)
    {
        request.getSession().invalidate();
        return "redirect:/index";
    }

    private boolean checkVerification(TbUser tbUser, HttpServletRequest request)
    {
        String verfication = (String) request.getSession()
            .getAttribute(Constants.KAPTCHA_SESSION_KEY);
        return StringUtils.equals(verfication, tbUser.getVerification());
    }
}
