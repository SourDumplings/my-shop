package com.cz.my.shop.web.ui.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2020/2/16 20:43
 */
public class RegisterController
{

    /**
     * 跳转注册页
     *
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register()
    {
        return "register";
    }
}
