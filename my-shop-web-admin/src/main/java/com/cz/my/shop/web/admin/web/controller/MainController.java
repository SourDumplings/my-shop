package com.cz.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/8 15:54
 */
@Controller
public class MainController
{
    @RequestMapping(value = "main", method = RequestMethod.GET)
    public String main()
    {
        return "main";
    }
}
