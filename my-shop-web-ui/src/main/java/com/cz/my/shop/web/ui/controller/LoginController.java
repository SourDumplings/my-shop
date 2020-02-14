package com.cz.my.shop.web.ui.controller;

import com.cz.my.shop.commons.dto.BaseResult;
import com.cz.my.shop.web.ui.api.UsersApi;
import com.cz.my.shop.web.ui.dto.TbUser;
import javax.servlet.http.HttpServletRequest;
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
        // if (!checkVerification(tbUser, request))
        // {
        //     model.addAttribute("baseResult", BaseResult.fail("验证码输入错误，请重新输入"));
        //     return "login";
        // }

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
}
