package com.cz.my.shop.web.admin.web.controller;

import com.cz.my.shop.commons.dto.BaseResult;
import com.cz.my.shop.domain.TbUser;
import com.cz.my.shop.web.admin.service.TbUserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @ModelAttribute
    public TbUser getTbUser(Long id)
    {
        TbUser tbUser = null;
        if (id == null)
        {
            tbUser = new TbUser();
        }
        else
        {
            tbUser = tbUserService.getById(id);
        }
        return tbUser;
    }

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

    /**
     * 跳转到用户表单页
     *
     * @return
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form()
    {
        return "user_form";
    }

    /**
     * 保存用户信息
     *
     * @param tbUser
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbUser tbUser, Model model, RedirectAttributes redirectAttributes)
    {
        final BaseResult baseResult = tbUserService.save(tbUser);

        if (baseResult.getStatus() == BaseResult.STATUS_SUCCESS)
        {
            // 保存成功
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/user/list";
        }
        else
        {
            // 保存失败
            model.addAttribute("baseResult", baseResult);
            return "user_form";
        }
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(TbUser tbUser, Model model)
    {
        List<TbUser> tbUsers = tbUserService.search(tbUser);
        model.addAttribute("tbUsers", tbUsers);
        return "user_list";
    }

}
