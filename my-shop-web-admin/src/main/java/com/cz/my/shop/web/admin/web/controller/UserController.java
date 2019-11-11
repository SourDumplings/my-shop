package com.cz.my.shop.web.admin.web.controller;

import com.cz.my.shop.domain.TbUser;
import com.cz.my.shop.web.admin.service.TbUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户管理
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/11 19:30
 */
@Controller
@RequestMapping(value = "user")
public class UserController
{
    @Autowired
    private TbUserService tbUserService;

    /**
     * 跳转到用户列表页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model)
    {
        final List<TbUser> tbUsers = tbUserService.selectAll();
        model.addAttribute("tbUsers", tbUsers);
        return "user_list";
    }

}
