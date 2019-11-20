package com.cz.my.shop.web.admin.web.controller;

import com.cz.my.shop.domain.TbContentCategory;
import com.cz.my.shop.web.admin.service.TbContentCategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 内容分类管理
 *
 * @author CHANG Zheng
 * @version 1.0.0
 * @projectName my-shop
 * @date 2019/11/20 8:29
 */
@Controller
@RequestMapping(value = "content/category")
public class ContentCategoryController
{
    @Autowired
    private TbContentCategoryService tbContentCategoryService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model)
    {
        final List<TbContentCategory> tbContentCategories = tbContentCategoryService.selectAll();
        model.addAttribute("tbContentCategories", tbContentCategories);
        return "content_category_list";
    }
}
